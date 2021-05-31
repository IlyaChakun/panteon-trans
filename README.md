# panteon-trans
The project is represented by the transport exchange.


# How to run with docker?

mysql on port 3306

1. run mvn install
2. docker-compose --env-file config-files/.env up

# How to run local?

Use dev profile with all services except config service.

1. Run config service
2. Run registry service
3. Run other services



# Временно
для запуска локаольно сначала запускаем базу а докере (docker-compose up)

а далее запускаем сервисы локально как указано выше

! для конфиг сервиса указать profile native

! для остальных dev




