CREATE TABLE users (
    id BIGINT  PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'CLIENT') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE menu_categories (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(100) NOT NULL,
 description TEXT
);

CREATE TABLE menu_items (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 category_id BIGINT NOT NULL,
 name VARCHAR(100) NOT NULL,
 description TEXT,
 price DECIMAL(10,2) NOT NULL,
 available BOOLEAN DEFAULT TRUE,
 FOREIGN KEY (category_id) REFERENCES menu_categories(id)
);

CREATE TABLE orders (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 user_id BIGINT NOT NULL,
 order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 status ENUM('PENDING', 'CONFIRMED', 'COMPLETED', 'CANCELLED') DEFAULT 'PENDING',
 total_amount DECIMAL(10,2) NOT NULL,
 FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
 id BIGINT  PRIMARY KEY AUTO_INCREMENT,
 order_id BIGINT NOT NULL,
 menu_item_id BIGINT NOT NULL,
 quantity INT NOT NULL,
 price DECIMAL(10,2) NOT NULL,
 FOREIGN KEY (order_id) REFERENCES orders(id),
 FOREIGN KEY (menu_item_id) REFERENCES menu_items(id)
);