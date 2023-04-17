# Веб-приложение Shopping card с использованием Spring Boot

## О чем проект

Покупатели заходят в онлайн-магазин, у каждого есть своя уч. запись (shopping card).
Также они должны зарегистрироваться в системе с помощью логина.

Используются базы данных H2

## Конфигурация

### Файлы конфигурации

Папка **src/resources/** содержит конфигурац. файлы для **shopping-cart** Spring Boot.

* **src/resources/application.properties** - основной файл конфигурации, здесь можно поменять логин и пароль админа, а также норме порта.

## Описание запуска

Варианты: Maven Wrapper, Maven или Docker. 

Как только приложение запустилось, нужно открыть в браузере `http://localhost:8070/home`

Admin username: **admin**

Admin password: **admin**

User username: **user**

User password: **password**

### Maven Wrapper

#### Maven Plugin

В корневой папке ввести:
```bash
$ chmod +x scripts/mvnw
$ scripts/mvnw spring-boot:run
```

#### Файл jar

```bash
$ scripts/mvnw clean package
``` 

```bash
$ java -jar target/shopping-cart-0.0.1-SNAPSHOT.jar
```

### Maven

Открыть терминал

Проверить версию джавы и maven

```bash
$ java -version
java version "1.8.0_102"
```

```bash
$ mvn -v
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T16:41:47+00:00)
Maven home: /usr/local/Cellar/maven/3.3.9/libexec
Java version: 1.8.0_102, vendor: Oracle Corporation
```

#### Maven Plugin

Плагин Spring Boot Maven включает цель выполнения, которую можно использовать для быстрой компиляции и запуска приложения.
Команды Maven для запуска приложения Spring Boot:
 
```bash
$ mvn spring-boot:run
``` 

#### Файл jar

Чтобы создать и запустить исполняемый файл jar:

```bash
$ mvn clean package
$ java -jar target/shopping-cart-0.0.1-SNAPSHOT.jar
```

### Docker

Сборка Docker файлов:
```bash
$ mvn clean package
$ docker build -t shopping-cart:dev -f docker/Dockerfile .
```

Запуск Docker контейнера:
```bash
$ docker run --rm -i -p 8070:8070 \
      --name shopping-cart \
      shopping-cart:dev
```

## Docker 

Folder **docker** contains:

* **docker/shopping-cart/Dockerfile** - Файл сборки Docker для запуска образа Docker для корзины.

## Скрипты для Docker

* **scripts/run_docker.sh.sh** - скрипт для запуска Docker-корзины с помощью **docker/Dockerfile**

## Тесты

Запуск тестов

```bash
$ mvn test
```
