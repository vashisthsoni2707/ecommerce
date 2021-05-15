CREATE TABLE customer (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  phone varchar(255) NOT NULL
);

CREATE TABLE product (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  price double NOT NULL
);

CREATE TABLE item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity BIGINT,
    cart_id BIGINT,
    product_id BIGINT);
