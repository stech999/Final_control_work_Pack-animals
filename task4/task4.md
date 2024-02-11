Для установки и удаления deb-пакета с помощью dpkg можно выполнить следующие шаги:

Скачаем deb-пакет, например, Midnight Commander (MC), с помощью команды:
wget http://archive.ubuntu.com/ubuntu/pool/universe/m/mc/mc_4.8.30-1_amd64.deb

Установим deb-пакет с помощью команды:
sudo dpkg -i mc_4.8.30-1_amd64.deb

Удалим установленный пакет с помощью команды:
sudo dpkg -r mc_4.8.30-1_amd64.deb