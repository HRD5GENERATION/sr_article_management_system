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