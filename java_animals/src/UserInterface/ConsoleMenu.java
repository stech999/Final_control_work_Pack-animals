package UserInterface;

import java.util.Scanner;
import Controller.*;
import Exceptions.UncorrectDataException;
import Model.PetType;

public class ConsoleMenu {

    PetController petController;

    public ConsoleMenu(PetController controller) {
        this.petController = controller;
    }

    public void start() {

        System.out.print("\033[H\033[J");
        try (Scanner in = new Scanner(System.in, "ibm866"); Counter count = new Counter()) {

            boolean flag = true;
            int id;
            while (flag) {

                System.out.println(
                        "\n1 - Все животные\n2 - Завести новое животное\n3 - Изменить данные\n4 - Способности твоего питомца\n5 - Изучение новой команды\n6 - Удалить запись\n0 - Выход");
                String key = in.next();
                switch (key) {
                    case "1":
                        petController.getAllPet();
                        break;
                    case "2":
                        PetType type = menuChoice(in);
                        if (type != null) {
                            try {
                                petController.createPet(type);
                                count.add();
                                System.out.println("ОК");
                            } catch (UncorrectDataException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    case "3":
                        while (true) {
                            id = menuChoicePet(in);
                            if (id != 0)
                                try {
                                    petController.updatePet(id);
                                } catch (UncorrectDataException e) {
                                    System.out.println(e.getMessage());
                                }
                            else
                                break;
                        }
                        break;
                    case "4":
                        while (true) {
                            id = menuChoicePet(in);
                            if (id != 0)
                                petController.getCommands(id);
                            else
                                break;
                        }
                        break;
                    case "5":
                        id = menuChoicePet(in);
                        if (id != 0)
                            menuTrainPet(id, in);
                        break;
                    case "6":
                        id = menuChoicePet(in);
                        if (id != 0)
                            petController.delete(id);
                        break;
                    case "0":
                        flag = false;
                        break;
                    default:
                        System.out.println("Попробуй еще раз, я уверен ты сможешь !)");
                        break;
                }
            }
        }
    }

    private PetType menuChoice(Scanner in) {
        System.out.println("Какого питомца хотите добавить:\n1 - Кошка\n2 - Собака\n3 - Хомяк\n0 - Назад в основное меню");

        while (true) {
            String key = in.next();
            switch (key) {
                case "1":
                    return PetType.Cat;
                case "2":
                    return PetType.Dog;
                case "3":
                    return PetType.Hamster;
                case "0":
                    return null;
                default:
                    System.out.println("Попробуй еще раз, введите число от 0 до 3");
                    break;
            }
        }
    }

    private int menuChoicePet(Scanner in) {
        System.out.println("\nВведите номер питомца, 0 для возврата в основное меню: ");
        while (true) {
            int id = in.nextInt();
            in.nextLine();
            if (id == 0)
                return id;
            if (petController.getById(id) == null) {
                System.out.println("Питомца с таким номером нет, попробуйте еще раз, 0 для возврата в основное меню:");
            } else
                return id;

        }
    }

    private void menuTrainPet(int petId, Scanner in) {
        Scanner sc = in;
        while (true) {
            System.out.println("Попробуй новую команду, 0 для возврата в основное меню: ");
            String command = sc.nextLine();
            if (command.length() == 1 && command.equals("0"))
                return;
            if (petController.trainPet(petId, command))
                System.out.println("Успех!");
        }
    }
}