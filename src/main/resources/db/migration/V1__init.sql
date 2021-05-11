create table categories (id bigserial primary key, title varchar (255));
insert into categories (title) values
('Food');

create table products (id bigserial primary key, title varchar(255), price int, category_id bigint references categories(id));
insert into products (title, price, category_id) values
('Bread', 25, 1),
('Milk', 80, 1),
('Cheese1', 325, 1),
('Cheese2', 325, 1),
('Cheese3', 325, 1),
('Cheese4', 325, 1),
('Cheese5', 325, 1),
('Cheese6', 325, 1),
('Cheese7', 325, 1),
('Cheese8', 325, 1),
('Cheese9', 325, 1),
('Cheese10', 325, 1),
('Cheese11', 325, 1),
('Cheese12', 325, 1),
('Cheese13', 325, 1),
('Cheese14', 325, 1),
('Cheese15', 325, 1),
('Banana16', 100, 1);