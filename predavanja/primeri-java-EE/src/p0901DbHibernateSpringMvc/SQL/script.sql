CREATE TABLE ItemsMaster (
item_code int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
item_name varchar(50) DEFAULT NULL, 
price decimal DEFAULT 0, 
qty int DEFAULT 0, 
createdOn timestamp);
