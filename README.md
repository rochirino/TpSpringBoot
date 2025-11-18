# TpSpringBoot
API REST completa desarrollada con Spring Boot para la gestion de productos 
El objetivo es construir una API REST que permita 
-Crear productos
-Listarlos
-Consultarlos por ID
-Filtrar por categoria
-Actualizar datos 
-Eliminar productos
Esta practica fue desarrollada utilizando DTO, validaciones, excepciones, repositorios JPA y documentacion automatica mediante swagger
Tecnologia utilizada:
-Java 17
-Spring boot 3
-Spring Web
-Spring Data JPA
-H2 Database
-Lombok
-Bean Validation
-Spring openAPi
-Gradle
-Spring Boot DevTools
Para ejecutar el proyecto 
```bash
./gradlew bootRun
La aplicacion arrancara en
localhost:8080
una vez ejecutada la aplicacion se puede acceder a SWAGGER que es donde podremos probar los endpoint, ver los dato, etc
H2:
la base de datos esta en memoria , una vez ejecutado el proyecto, abrimos nuestra aplicacion de H2
Capturas:
<img width="1312" height="447" alt="image" src="https://github.com/user-attachments/assets/cba771ce-658f-466f-8a79-c55874529810" />
<img width="1308" height="588" alt="image" src="https://github.com/user-attachments/assets/db44e7dd-3c63-4331-9565-f86dc2f295db" />
Prueba error producto sin nombre:
<img width="1291" height="584" alt="image" src="https://github.com/user-attachments/assets/354588c2-2587-48e7-abea-3f3116c3dae5" />
Prueba error precio negativo:
<img width="1277" height="492" alt="image" src="https://github.com/user-attachments/assets/3949761d-d761-4014-9458-fba38019d134" />
Prueba obtener todos los productos:
<img width="1288" height="322" alt="image" src="https://github.com/user-attachments/assets/f2f7fca6-817a-4173-a867-d5ed0ad32c90" />



Rosario Chirino
50847

