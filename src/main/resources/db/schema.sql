DROP TABLE IF EXISTS tbarticle;
DROP TABLE IF EXISTS tbcategory;

CREATE TABLE tbcategory(
	id serial primary key,
    name VARCHAR(50)
);

CREATE TABLE tbarticle(
	id serial primary key,
	title VARCHAR(100),
	description VARCHAR(500),
	thumbnail VARCHAR(500),
	category_id int REFERENCES tbcategory(id)
);

/*
CREATE TABLE tbcategory(
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(50) 
);

CREATE TABLE tbarticle(
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	title VARCHAR(100),
	description VARCHAR(500),
	thumbnail VARCHAR(500),
	category_id INT REFERENCES tbcategory(id)
);
*/