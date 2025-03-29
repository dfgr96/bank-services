#!/bin/bash

echo "Iniciando el despliegue del proyecto..."

echo "Compilando los microservicios con Maven..."
mvn clean install -DskipTests

echo "Deteniendo y eliminando contenedores anteriores..."
docker-compose down

echo "Iniciando MySQL y RabbitMQ..."
docker-compose up -d mysql-db rabbitmq

echo "Esperando a que MySQL inicie..."
until docker exec mysql-db mysqladmin ping -h "localhost" --silent; do
    sleep 2
    echo "Esperando a que MySQL esté listo..."
done
echo "MySQL está listo!!"

echo "Creando tablas en MySQL..."
docker exec -i mysql-db mysql -uroot -proot bankdb < initTables.sql
echo "Tablas creadas correctamente."

echo "Construyendo imágenes de los servicios..."
docker-compose build

echo "Levantando los microservicios..."
docker-compose up -d account-service transaction-service

echo "Despliegue completado correctamente. ¡Todo listo!"
