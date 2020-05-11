USE shopping_list;

CREATE TABLE IF NOT EXISTS user (
    user_id int NOT NULL AUTO_INCREMENT,
    first_name varchar(50) DEFAULT NULL,
    last_name varchar(50) DEFAULT NULL,
    username varchar(50) NOT NULL,
    password varchar(100) NOT NULL,
    role varchar(10) NOT NULL,
    PRIMARY KEY(user_id),
    UNIQUE (username)
    );
    
CREATE TABLE IF NOT EXISTS product (
    product_id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    name varchar(50) NOT NULL,
    PRIMARY KEY(product_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    UNIQUE (user_id, name)
    );
    
CREATE TABLE IF NOT EXISTS shopping_list (
	shopping_list_id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	date DATE NOT NULL,
	time TIME NOT NULL,
    PRIMARY KEY(shopping_list_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
    );    
    
CREATE TABLE IF NOT EXISTS shopping_card(
	shopping_card_id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
    name varchar(50) NOT NULL,
    PRIMARY KEY(shopping_card_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    UNIQUE (user_id, name)
    );

CREATE TABLE IF NOT EXISTS shopping_list_product(
    shopping_list_product_id int NOT NULL AUTO_INCREMENT,
	shopping_list_id int NOT NULL,
    product_id int NOT NULL,
	quantity DECIMAL(10,2),
	PRIMARY KEY(shopping_list_product_id),
	FOREIGN KEY (shopping_list_id) REFERENCES shopping_list(shopping_list_id),
	FOREIGN KEY (product_id) REFERENCES product(product_id)
    ); 
    
CREATE TABLE IF NOT EXISTS shopping_card_product(
	shopping_card_id int NOT NULL,
    product_id int NOT NULL,
	FOREIGN KEY (shopping_card_id) REFERENCES shopping_card(shopping_card_id),
	FOREIGN KEY (product_id) REFERENCES product(product_id)
    );    
    
    