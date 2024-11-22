TEST S.O.S ASISTENCIA

Proyecto generado con Java 17, springBoot 3.1.0, Gradle 8.11.1 y Postgresql 14.10 

Inicialmente descargue una imagen de docker con el siguiente comando:
docker pull postgres:14.10

luego ejecute el siguiente 
docker run --name postgres-container -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=root -e POSTGRES_DB=sos_assistance -p 5432:5432 -d postgres

O si lo prefiere, puede ingresar las siguientes credenciales: 
* username: admin
* password: root
* datasource.url: jdbc:postgresql://localhost:5432/sos_assistance

* MODULO PRODUCTOS

Inicialmente cree productos siguiendo el siguiente path: 

Tipo Post: http://localhost:8080/api/sos-assistance/product
{"name": "pintura", "price": 140}
![image](https://github.com/user-attachments/assets/a604a496-f618-4deb-a712-7ea8d540db73)


también puede consultar los diferentes productos que haya creado 
Tipo Get: http://localhost:8080/api/sos-assistance/product
![image](https://github.com/user-attachments/assets/e1cb86ab-ae4b-4774-9701-22b54c855e54)

Para actualizar el precio de los servicios ingrese a
http://localhost:8080/api/sos-assistance/product/1
![image](https://github.com/user-attachments/assets/afcacc6d-0cb9-4c17-8387-32c4dbfc8baf)

* MODULO PEDIDOS
  
Para crear pedidos






