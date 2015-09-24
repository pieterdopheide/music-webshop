DROP TABLE IF EXISTS order_details, orders;

CREATE TABLE orders(
	id int unsigned auto_increment not null UNIQUE,
	user_id int unsigned not null,
	orderDate datetime,
	orderTotal decimal(19,2),
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE order_details(
	id int unsigned auto_increment not null UNIQUE,
	product_id int unsigned not null,
	order_id int unsigned not null,
	PRIMARY KEY (id),
	FOREIGN KEY (product_id) REFERENCES products (id),
	FOREIGN KEY (order_id) REFERENCES orders (id)
);
