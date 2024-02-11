Для подключения дополнительного репозитория MySQL можно выполнить следующие шаги:

Откроем терминал и перейдем в режим суперпользователя с помощью команды:
sudo su

Добавим ключ репозитория с помощью команды:
wget -O- https://repo.mysql.com/RPM-GPG-KEY-mysql-2022 | sudo apt-key add -

Добавим репозиторий MySQL в систему с помощью команды:
echo "deb http://repo.mysql.com/apt/ubuntu/ $(lsb_release -sc) mysql-8.0" | sudo tee /etc/apt/sources.list.d/mysql.list

Обновим список пакетов с помощью команды:
sudo apt update

Установим любой пакет из репозитория MySQL, например, пакет mysql-shell, с помощью команды:
sudo apt install mysql-shell