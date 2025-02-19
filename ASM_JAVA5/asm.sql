CREATE DATABASE java5_demo_lab
USE java5_demo_lab

--drop database java5_demo_lab
CREATE TABLE categories (
    id INT IDENTITY(1,1) NOT NULL,
    name NVARCHAR(150) NULL,
    image VARCHAR(MAX) NULL,
    CONSTRAINT pk_categories PRIMARY KEY CLUSTERED (id ASC)
);

CREATE TABLE users (
    id INT IDENTITY(1,1) NOT NULL,
    username VARCHAR(100) NULL,
    password VARCHAR(255) NULL,
    name NVARCHAR(255) NULL,
    email VARCHAR(100) NULL,
    is_active BIT NULL,
    role INT NULL,
    CONSTRAINT pk_users PRIMARY KEY CLUSTERED (id ASC)
);

CREATE TABLE addresses (
    id INT IDENTITY(1,1) NOT NULL,
    user_id INT NOT NULL,
    name NVARCHAR(255) NULL,
    address NVARCHAR(MAX) NULL,
    phone VARCHAR(15) NULL,
    CONSTRAINT pk_addresses PRIMARY KEY CLUSTERED (id ASC),
    CONSTRAINT fk_addresses_users FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE carts (
    id INT IDENTITY(1,1) NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT pk_carts PRIMARY KEY CLUSTERED (id ASC),
    CONSTRAINT fk_carts_users FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE products (
    id INT IDENTITY(1,1) NOT NULL,
    name NVARCHAR(MAX) NULL,
    [desc] NTEXT NULL,
    price INT NULL,
    quantity INT NULL,
    is_active BIT NULL,
    cat_id INT NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY CLUSTERED (id ASC),
    CONSTRAINT fk_products_categories FOREIGN KEY (cat_id) REFERENCES categories (id)
);

CREATE TABLE images (
    id INT IDENTITY(1,1) NOT NULL,
    product_id INT NOT NULL,
    image VARCHAR(MAX) NULL,
    CONSTRAINT pk_images PRIMARY KEY CLUSTERED (id ASC),
    CONSTRAINT fk_images_products FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE cart_items (
    id INT IDENTITY(1,1) NOT NULL,
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NULL,
    CONSTRAINT pk_cart_items PRIMARY KEY CLUSTERED (id ASC),
    CONSTRAINT fk_cart_items_carts FOREIGN KEY (cart_id) REFERENCES carts (id),
    CONSTRAINT fk_cart_items_products FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE orders (
    id INT IDENTITY(1,1) NOT NULL,
    user_id INT NOT NULL,
    address NVARCHAR(MAX) NULL,
    name NVARCHAR(255) NULL,
    phone VARCHAR(15) NULL,
    status INT NULL,
    CONSTRAINT pk_orders PRIMARY KEY CLUSTERED (id ASC),
    CONSTRAINT fk_orders_users FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE order_details (
    id INT IDENTITY(1,1) NOT NULL,
    product_id INT NOT NULL,
    order_id INT NOT NULL,
    quantity INT NULL,
    price INT NULL,
    CONSTRAINT pk_order_details PRIMARY KEY CLUSTERED (id ASC),
    CONSTRAINT fk_order_details_orders FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT fk_order_details_products FOREIGN KEY (product_id) REFERENCES products (id)
);
INSERT INTO categories (name, image) 
VALUES 
    (N'San Pham 1', 'phone.jpg'),
    (N'San Pham 2', 'laptop.jpg'),
    (N'San Pham 3', 'tablet.jpg'),
    (N'San Pham 4', 'accessories.jpg'),
    (N'San Pham 5', 'smartwatch.jpg');
