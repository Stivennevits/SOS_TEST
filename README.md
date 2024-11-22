# TEST S.O.S ASISTENCIA

## Tecnologías usadas
Proyecto generado con Java 17, springBoot 3.1.0, Gradle 8.11.1,  SonarLint  y Postgresql 14.10 

## ARQUITECTURA DEL PROYECTO
- Modular con principios SOLID

Inicialmente descargue una imagen de docker con el siguiente comando:
docker pull postgres:14.10

luego ejecute el siguiente 
docker run --name postgres-container -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=root -e POSTGRES_DB=sos_assistance -p 5432:5432 -d postgres

O si lo prefiere, puede ingresar las siguientes credenciales: 
* username: admin
* password: root
* datasource.url: jdbc:postgresql://localhost:5432/sos_assistance

## Scripts de creación de tablas

- Crear la tabla orders
 ```sql 
create table orders
(
    id         serial
        primary key,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    updated_at timestamp,
    status     varchar(20)                         not null
        constraint orders_status_check
            check ((status)::text = ANY
                   ((ARRAY ['PENDING'::character varying, 'IN_PROCESS'::character varying, 'COMPLETED'::character varying, 'CANCELLED'::character varying])::text[]))
);
```

- Crear tabla order_product_quantity
```sql
create table order_product_quantity
(
    order_id   integer not null
        references orders
            on delete cascade,
    product_id integer not null
        references product
            on delete cascade,
    quantity   integer not null
        constraint order_product_quantity_quantity_check
            check (quantity > 0),
    primary key (order_id, product_id)
);

```

- Crear tabla product
```sql
create table product
(
    id    serial
        primary key,
    name  varchar(255)   not null,
    price numeric(10, 2) not null
);
```


##  MODULO PRODUCTOS

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

##  MODULO PEDIDOS
  
Para crear pedidos debe llenar dos listas, una llamada productIds con los ids de cada producto y la otra quantities con la cantidad de productos a solicitar por pedido

http://localhost:8080/api/sos-assistance/order
{
  "productIds": [1, 2],
  "quantities": [22, 80]
}
![image](https://github.com/user-attachments/assets/efd233c0-b58d-46f2-9714-e90f193baaad)

Manejo de errores 
![image](https://github.com/user-attachments/assets/5df9809a-4cf3-43d8-ba39-f2922f5352f6)

Si se ingresa un id de pedido errado, se hace rollback a toda la transacción
![image](https://github.com/user-attachments/assets/f00fcd8a-4178-4df7-9344-5d3eed780c63)






