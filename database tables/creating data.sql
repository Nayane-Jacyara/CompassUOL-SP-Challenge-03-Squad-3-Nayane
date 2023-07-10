
-- Inserting categories
INSERT INTO tb_category (name) VALUES ('Electronics');
INSERT INTO tb_category (name) VALUES ('Clothing');
INSERT INTO tb_category (name) VALUES ('Home and Kitchen');

-- Ininserting users
INSERT INTO tb_user (first_name, last_name, email, password, roles) VALUES ('John', 'Doe', 'john@gmail.com', 'john', 'ROLE_USER');
INSERT INTO tb_user (first_name, last_name, email, password, roles) VALUES ('Jane', 'Smith', 'jane@gmail.com', 'jane', 'ROLE_USER');
INSERT INTO tb_user (first_name, last_name, email, password, roles) VALUES ('Nayane', 'Jacyara', 'njcs@discente.ifpe.edu.br', 'admin123', 'ROLE_ADMIN');

-- Inserting products
INSERT INTO tb_product (date, description, name, imgUrl, price, category_id) VALUES ('2023-07-01 10:00:00', 'Smartphone with advanced features', 'iPhone 12', 'https://a-static.mlcdn.com.br/450x450/iphone-12-apple-128gb-roxo-tela-61-12mp-ios/magazineluiza/231148900/6c9edfdf13d754062abdb657e863721c.jpg', 3999.99, 1);
INSERT INTO tb_product (date, description, name, imgUrl, price, category_id) VALUES ('2023-07-02 15:30:00', 'Men\'s t-shirt made of high-quality fabric', 'Blue T-shirt', 'https://t4.ftcdn.net/jpg/02/88/00/87/360_F_288008736_L6GiaJC5ZZqgkoYSR9A8MB9CKWP59HlK.jpg', 29.99, 2);

-- inserting papers
INSERT INTO tb_role (name) VALUES ('ROLE_USER');
INSERT INTO tb_role (name) VALUES ('ROLE_ADMIN');

-- Linking products and categories
INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 2);

-- Relating users and roles
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);


