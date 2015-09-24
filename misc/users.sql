DROP TABLE IF EXISTS user_roles, users;

CREATE TABLE users(
	id int unsigned auto_increment not null UNIQUE,
	username varchar(45) not null UNIQUE,
	password varchar(45) not null,
	enabled boolean not null,
	PRIMARY KEY (id)
);

CREATE TABLE user_roles(
	id int unsigned auto_increment not null UNIQUE,
	username varchar(45) NOT NULL,
	role varchar(45) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (username) REFERENCES users (username)
);
