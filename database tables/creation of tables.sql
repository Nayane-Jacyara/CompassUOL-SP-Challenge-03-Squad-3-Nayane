CREATE DATABASE IF NOT EXISTS ProductManagementSystem;

CREATE TABLE IF NOT EXISTS tb_category (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_user (
  id INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(20) NOT NULL,
  roles VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_product (
   id INT PRIMARY KEY AUTO_INCREMENT,
   date DATETIME NOT NULL,
   description TEXT NOT NULL,
   name VARCHAR(50) NOT NULL,
   imgUrl VARCHAR(255),
   price DECIMAL(10, 2) NOT NULL,
   category_id INT,
   FOREIGN KEY (category_id) REFERENCES tb_category(id)
);

CREATE TABLE IF NOT EXISTS tb_role (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_product_category (
   product_id INT,
   category_id INT,
   FOREIGN KEY (product_id) REFERENCES tb_product(id),
   FOREIGN KEY (category_id) REFERENCES tb_category(id),
   PRIMARY KEY (product_id, category_id)
);

CREATE TABLE IF NOT EXISTS tb_user_role (
   user_id INT,
   role_id INT,
   FOREIGN KEY (user_id) REFERENCES tb_user(id),
   FOREIGN KEY (role_id) REFERENCES tb_role(id),
   PRIMARY KEY (user_id, role_id)
);
