CREATE SCHEMA store AUTHORIZATION ecommerce;



-----
-- Coupon
-----
CREATE TABLE store.coupon (
  code varchar(14) NOT NULL,
  percentage int4 not null,
  expiration_date TIMESTAMP not null,
  primary KEY(code)
);

INSERT INTO store.coupon (code, percentage, expiration_date)VALUES('20OFF', 20, '2022-10-01T10:00:00');
INSERT INTO store.coupon (code, percentage, expiration_date)VALUES('50OFF', 50, '2022-10-01T10:00:00');



-----
-- ORDER
-----
CREATE TABLE store.order (
     id serial primary KEY,
     cpf varchar(14) NOT NULL,
     code varchar(12) NOT NULL,
     freight_value float4 NOT NULL,
     total_order float4 not null,
     coupon_id varchar(6) NULL
);


INSERT INTO store.order (id, cpf, code, freight_value, total_order, coupon_id) VALUES(1, '61923445073', '202200000001', 0.0, 159.99, null);
INSERT INTO store.order (id, cpf, code, freight_value, total_order, coupon_id) VALUES(2, '61923445073', '202200000001', 10.0, 113.99,'20OFF');
INSERT INTO store.order (id, cpf, code, freight_value, total_order, coupon_id) VALUES(3, '16029727028', '202200000002', 150.90, 1500, '50OFF');

ALTER SEQUENCE store.order_id_seq RESTART 10;



-----
-- ITEM
-----
create table store.item (
    id serial primary key,
    category text,
    description text,
    price numeric,
    width integer,
    height integer,
    length integer,
    weight integer
);

insert into store.item (id, category, description, price, width, height, length, weight) values (1, 'Instrumentos Musicais', 'Guitarra', 1000, 100, 50, 15, 3);
insert into store.item (id, category, description, price, width, height, length, weight) values (2, 'Instrumentos Musicais', 'Amplificador', 5000, 50, 50, 50, 22);
insert into store.item (id, category, description, price, width, height, length, weight) values (3, 'Acessórios', 'Cabo', 30, 10, 10, 10, 1);



-----
--Order Itens
-----
create table store.order_item (
  id_order integer,
  id_item integer,
  price numeric,
  quantity integer,
  primary key (id_order, id_item)
);

INSERT INTO store.order_item (id_order, id_item, price, quantity) VALUES(1, 1, 10.0, 1);
INSERT INTO store.order_item (id_order, id_item, price, quantity) VALUES(1, 2, 100.0, 1);
INSERT INTO store.order_item (id_order, id_item, price, quantity) VALUES(1, 3, 1000.0, 3);
INSERT INTO store.order_item (id_order, id_item, price, quantity) VALUES(2, 1, 500.0, 1);
INSERT INTO store.order_item (id_order, id_item, price, quantity) VALUES(3, 1, 200.0, 1);

