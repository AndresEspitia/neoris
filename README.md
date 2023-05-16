<<<<<<< HEAD
# PASOS PARA EJECUTAR EL DOCKER

Se creo una red para que los dos contenedores tanto el de la app backend como el de la base de datos no interfieran en otros puertos.

## Pasos para ejecutar la red y los contenedores

### 1) Crear con el siguiente comando la red

----> docker network create neoris-mysql

### 2) Crear el contenedor neorissqldb ya que es dependencia para el utlimo punto

----> docker container run --name neorissqldb --network neoris-mysql -e MYSQL_ROOT_PASSWORD=12345 -e MYSQL_DATABASE=neoris -d mysql:8

### 3) Vamos a crear una imagen para la app backend alojada en el Dockerfile

----> docker image build -t neoris-app .

### 4) Vamos a crear un contenedor para la imagen que acabamos de crear

----> docker container run --network neoris-mysql --name neoris-app-container -p 8090:8090 -d neoris-app


### 4) Ya estando completa la imagen de la app del punto anterior por ultimo paso ejecutamos el archivo docker-compose.yml para el arranque instantaneo de los dos contenedores

----> docker-compose up

---> Pausar el contenedor neoris-app-container 

----> docker-compose up
=======
# neoris_test
Test for Neoris
>>>>>>> origin/main
