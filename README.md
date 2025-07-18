# S0402 - API REST con Spring Boot
## 🎯 Objetivos

    Familiarizarse con los Verbos HTTP.

    Comprender los Encabezados HTTP.

    Conocer los Códigos de Estado HTTP.

    Realizar operaciones CRUD con Spring Boot.

    Trabajar con H2 en memoria, MySQL y MongoDB.


## 🔹 Descripción

En esta tarea, implementarás operaciones CRUD (Crear, Leer, Actualizar, Eliminar) utilizando tres bases de datos diferentes. Aprenderás a usar correctamente los verbos HTTP y a gestionar los códigos de respuesta.


### 🔹 Nivel 1
### 📘 Ejercicio CRUD con H2

Este ejercicio te introducirá al desarrollo de una API REST con Spring Boot y la base de datos en memoria H2.

Accede a la página de Spring Initializer (👉 https://start.spring.io/) y genera un proyecto Spring Boot con las siguientes características:

    PROJECT (gestor de dependencias): Maven o Gradle

    LANGUAGE: Java

    SPRING BOOT: La última versión estable.

    PROJECT METADATA:

        Group: cat.itacademy.s04.t02.n01

        Artifact: S04T02N01

        Name: S04T02N01

        Description: S04T02N01GognomsNom

        Package name: cat.itacademy.s04.t02.n01

    PACKAGING: Jar

    JAVA: Mínimo versión 11

Dependencias:

    Spring Boot DevTools

    Spring Web

    Spring Data JPA

    H2 Database

Importa el proyecto en tu IDE (Eclipse o IntelliJ IDEA) con la opción File > Import > Existing Maven Project o File > Import > Existing Gradle Project, según el gestor de dependencias elegido.

Tenemos una entidad llamada "Fruta", que dispone de las siguientes propiedades:

    int id

    String nombre

    int cantidadQuilos

Aprovechando la especificación JPA, deberás persistir esta entidad a una base de datos H2, siguiendo el patrón MVC. Para ello, dependiendo del paquete principal, crearás una estructura de paquetes donde ubicarás las clases que necesites:

    cat.itacademy.s04.t02.n01.controllers

    cat.itacademy.s04.t02.n01.model

    cat.itacademy.s04.t02.n01.services

    cat.itacademy.s04.t02.n01.repository

    cat.itacademy.s04.t02.n01.exception

La clase ubicada en el paquete controllers (por ejemplo, FrutaController), deberá ser capaz de dar respuesta a las siguientes peticiones para actualizar y consultar información:

    http://localhost:8080/fruta/add

    http://localhost:8080/fruta/update

    http://localhost:8080/fruta/delete/{id}

    http://localhost:8080/fruta/getOne/{id}

    http://localhost:8080/fruta/getAll

Importante:
Deberás tener en cuenta las buenas prácticas de diseño de las API, haciendo servir correctamente los códigos de error y las respuestas en caso de invocaciones incorrectas. (Puedes consultar información sobre ResponseEntity). Además, es necesario implementar un GlobalExceptionHandler para gestionar las excepciones globalmente en la aplicación. Esto permitirá capturar y tratar errores de manera centralizada, mejorando la robustez y la coherencia en la gestión de las excepciones.


### 🔹 Nivel 2
### 📘 Ejercicio CRUD con MySQL

En este nivel, realizarás la misma implementación CRUD que en el Nivel 1, pero utilizando MySQL como base de datos persistente.

Accede a la página de Spring Initializer (👉 https://start.spring.io/) y genera un proyecto Spring Boot con las siguientes características:

    PROJECT (gestor de dependencias): Maven o Gradle

    LANGUAGE: Java

    SPRING BOOT: La última versión estable.

    PROJECT METADATA:

        Group: cat.itacademy.s04.t02.n02

        Artifact: S04T02N02

        Name: S04T02N02

        Description: S04T02N02

        Package name: cat.itacademy.s04.t02.n02

    PACKAGING: Jar

    JAVA: Mínimo versión 11

Dependencias:

    Spring Boot DevTools

    Spring Web

    Spring Data JPA

    MySQL Driver

Importa el proyecto en tu IDE.

Consideraciones para la conexión a MySQL:

    Túnel SSH: Se ha configurado un túnel SSH para la conexión a la base de datos, en caso de que sea requerido por tu entorno.

    Archivos de Configuración: En la carpeta src/main/resources, encontrarás un archivo de configuración con los parámetros de conexión para el túnel SSH y la base de datos.

        Opcional: Archivo de propiedades externo: Para evitar subir credenciales y datos sensibles a Git, se ha implementado la opción de cargar un archivo de propiedades externo. Este archivo, que no debe estar en el repositorio, contiene los valores reales de conexión y puede ser pasado como parámetro al iniciar el programa. De esta forma, puedes tener valores genéricos para pruebas en el archivo de recursos y un archivo separado con los valores reales para tu entorno.


### 🔹 Nivel 3
### 📘 Ejercicio CRUD con MongoDB

En este nivel, implementarás las mismas operaciones CRUD, pero persistiendo los datos en MongoDB.

Accede a la página de Spring Initializer (👉 https://start.spring.io/) y genera un proyecto Spring Boot con las siguientes características:

    PROJECT (gestor de dependencias): Maven o Gradle

    LANGUAGE: Java

    SPRING BOOT: La última versión estable.

    PROJECT METADATA:

        Group: cat.itacademy.s04.t02.n03

        Artifact: S04T02N03

        Name: S04T02N03

        Description: S04T02N03

        Package name: cat.itacademy.s04.t02.n03

    PACKAGING: Jar

    JAVA: Mínimo versión 11

Dependencias:

    Spring Boot DevTools

    Spring Web

    Spring Data MongoDB

Importa el proyecto en tu IDE.

Consideraciones para la conexión a MongoDB:

    Túnel SSH: Se ha configurado un túnel SSH para la conexión a la base de datos, en caso de que sea requerido por tu entorno.

    Archivos de Configuración: En la carpeta src/main/resources, encontrarás un archivo de configuración con los parámetros de conexión para el túnel SSH y la base de datos.

        Opcional: Archivo de propiedades externo: Para evitar subir credenciales y datos sensibles a Git, se ha implementado la opción de cargar un archivo de propiedades externo. Este archivo, que no debe estar en el repositorio, contiene los valores reales de conexión y puede ser pasado como parámetro al iniciar el programa. De esta forma, puedes tener valores genéricos para pruebas en el archivo de recursos y un archivo separado con los valores reales para tu entorno.


## 🛠️  Tecnologías Utilizadas

    Java 11 o superior

    Spring Boot

    Maven

    Gradle

    HTTP Protocol

    H2 Database

    MySQL

    MongoDB

    Postman


## ⚙️ Instalación & Ejecución
### 📋 Requisitos

Para ejecutar este proyecto, necesitamos:

    Java Development Kit (JDK) 11 o superior

    Eclipse o IntelliJ IDEA

    Git

    MySQL Server (para Nivel 2)

    MongoDB Server (para Nivel 3)


## 🛠️  Instalación

    Asegurarse de que JDK 11 o superior está instalado.

    Clonar este repositorio:

    git clone https://github.com/DiegoBalaguer/S0402-ApiRestWithSpringBoot.git

    Abrir el proyecto con tu IDE preferido (Eclipse o IntelliJ IDEA) como un proyecto Maven o Gradle existente, según corresponda a cada nivel.


## ▶️ Ejecución

Desde el IDE:

        Abrir la clase principal de cada proyecto (la que contiene el método main(), que inicia la aplicación Spring Boot).

        Ejecutar el archivo directamente utilizando la configuración de ejecución de tu IDE.

Para pasar un archivo de propiedades externo:

        En la configuración de ejecución de tu IDE (por ejemplo, "Run/Debug Configurations" en IntelliJ IDEA o "Run Configurations" en Eclipse), busca la sección de "VM options" o "JVM arguments".

        Añade el siguiente argumento: -Dspring.config.additional-location=file:/ruta/a/tu/archivo/application-prod.properties

        (Nota: Reemplaza /ruta/a/tu/archivo/application-prod.properties con la ruta real de tu archivo de propiedades externo.)

Desde la terminal:

        Navegar al directorio raíz del proyecto (donde se encuentra pom.xml para Maven o build.gradle para Gradle).

        Versión sin archivo de propiedades separado (usando application.properties):

            Para el proyecto Maven: mvn spring-boot:run

            Para el proyecto Gradle: gradle bootRun

Versión con archivo de propiedades separado (pasando la ruta del archivo):

        Para el proyecto Maven:

            mvn spring-boot:run -Dspring-boot.run.arguments="spring.config.additional-location=file:/ruta/a/tu/archivo/application-prod.properties"

        Para el proyecto Gradle:

            gradle bootRun --args='spring.config.additional-location=file:/ruta/a/tu/archivo/application-prod.properties'

        (Nota: Reemplaza /ruta/a/tu/archivo/application-prod.properties con la ruta real de tu archivo de propiedades externo.)

## 🌐 Despliegue

Este proyecto es para fines educativos y está destinado para desarrollo local únicamente. No se requiere despliegue ni un entorno externo.

## 📦 Repositorio

Puedes encontrar el código fuente completo en GitHub:
🔗 👉 https://github.com/DiegoBalaguer/S0402-ApiRestWithSpringBoot.git

✅  Notas del Autor

Estos ejercicios están diseñados para brindarte experiencia práctica con los fundamentos de Spring Boot, la implementación de operaciones CRUD con diferentes bases de datos (H2, MySQL, MongoDB), el manejo de conexiones avanzadas (túnel SSH) y la gestión de configuraciones externas.

¡Siéntete libre de explorar, modificar y expandir el código base!

¡Feliz codificación! 🚀