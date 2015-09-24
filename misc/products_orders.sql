create table products_orders(
	id int unsigned auto_increment not null unique,
	orders_id int(11),
	products_id int(11),
	primary key (id),
	foreign key (orders_id) references orders (id),
	foreign key (products_id) references products (id)
);
