DROP DATABASE IF EXISTS DeliveringManagement;
CREATE DATABASE IF NOT EXISTS DeliveringManagement;
SHOW DATABASES;
USE DeliveringManagement;

#-----------------------#01#-------------------------------

DROP TABLE IF EXISTS User;
CREATE TABLE IF NOT EXISTS User(
	userName VARCHAR(15),
	password VARCHAR(10),
	CONSTRAINT PRIMARY KEY (userName)
);
SHOW TABLES;
DESCRIBE User;

#----------------------#02#--------------------------------

DROP TABLE IF EXISTS Employee;
CREATE TABLE IF NOT EXISTS Employee(
	employeeID VARCHAR(10),
	name VARCHAR(30),
	address VARCHAR(30),
	contactNumber VARCHAR(15),
	position VARCHAR(30),
	CONSTRAINT PRIMARY KEY (employeeID)
);
SHOW TABLES;
DESCRIBE Employee;

#----------------------------#03#----------------------------------

DROP TABLE IF EXISTS Vehicle;
CREATE TABLE IF NOT EXISTS Vehicle(
	vehicleID VARCHAR(10),
	vehicleNumber VARCHAR(20),
	vehicleType VARCHAR(20),
	description VARCHAR(50),
	CONSTRAINT PRIMARY KEY (vehicleID)
);
SHOW TABLES;
DESCRIBE Vehicle;

#------------------------#04#--------------------------------

DROP TABLE IF EXISTS EmpAttendance;
CREATE TABLE IF NOT EXISTS EmpAttendance(
	employeeID VARCHAR(10),
	attendDate DATE,
	attend VARCHAR(15),
	CONSTRAINT FOREIGN KEY (employeeID) REFERENCES Employee (employeeID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE EmpAttendance;

#----------------------------#05#--------------------------------------

DROP TABLE IF EXISTS CompanyOrder;
CREATE TABLE IF NOT EXISTS CompanyOrder(
	orderNumber VARCHAR(15),
	orderDate DATE,
	orderItemQTY INT(10),
	CONSTRAINT PRIMARY KEY (orderNumber)
);
SHOW TABLES;
DESCRIBE CompanyOrder;

#-----------------------------#06#----------------------------------------

DROP TABLE IF EXISTS StockItem;
CREATE TABLE IF NOT EXISTS StockItem(
	itemCode VARCHAR(10),
	itemName VARCHAR(20),
	itemDescription VARCHAR(50),
	unitPrice DOUBLE DEFAULT 0.00,
	qtyOnStock INT(10),
	CONSTRAINT PRIMARY KEY (itemCode)
);
SHOW TABLES;
DESCRIBE StockItem;

#-------------------------------#07#-----------------------------------

DROP TABLE IF EXISTS PlaceOrder;
CREATE TABLE IF NOT EXISTS PlaceOrder(
	orderNumber VARCHAR(15),
	itemCode VARCHAR(10),
	itemQTY INT(10),
	CONSTRAINT PRIMARY KEY (orderNumber, itemCode),
	CONSTRAINT FOREIGN KEY (orderNumber) REFERENCES CompanyOrder (orderNumber) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY (itemCode) REFERENCES StockItem (itemCode) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE PlaceOrder;

#---------------------------------#08#-----------------------------------------

DROP TABLE IF EXISTS VehicleStock;
CREATE TABLE IF NOT EXISTS VehicleStock(
	vehicleID VARCHAR(10),
	vStoreDate DATE,
	qtyOnVehicle INT(10),
	CONSTRAINT PRIMARY KEY (vehicleID, vStoreDate),
	CONSTRAINT FOREIGN KEY (vehicleID) REFERENCES Vehicle (vehicleID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE VehicleStock;

#---------------------------------#09#-------------------------------------

DROP TABLE IF EXISTS Store;
CREATE TABLE IF NOT EXISTS Store(
	itemCode VARCHAR(10),
	vehicleID VARCHAR(10),
	vStoreDate DATE,
	storeItemQTY INT(10),
	CONSTRAINT PRIMARY KEY (itemCode, vehicleID, vStoreDate),
	CONSTRAINT FOREIGN KEY (itemCode) REFERENCES StockItem (itemCode) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY (vehicleID, vStoreDate ) REFERENCES VehicleStock (vehicleID, vStoreDate) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE Store;

#-------------------------------#10#--------------------------------------------------------

DROP TABLE IF EXISTS CustomerShop;
CREATE TABLE IF NOT EXISTS CustomerShop(
	customerID VARCHAR(10),
	customerName VARCHAR(30),
	shopName VARCHAR(30),
	address VARCHAR(30),
	contactNumber VARCHAR(15),
	CONSTRAINT PRIMARY KEY (customerID)
);
SHOW TABLES;
DESCRIBE CustomerShop;

#----------------------------------#11#-----------------------------------------------------

DROP TABLE IF EXISTS SoldItem;
CREATE TABLE IF NOT EXISTS SoldItem(
	customerID VARCHAR(10),
	soldDate DATE,
	totalQty INT(10),
	totalPrice DOUBLE,
	CONSTRAINT PRIMARY KEY (customerID, soldDate),
	CONSTRAINT FOREIGN KEY (customerID) REFERENCES CustomerShop (customerID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE SoldItem;

#------------------------------------#12#-------------------------------------------------

DROP TABLE IF EXISTS Sold;
CREATE TABLE IF NOT EXISTS Sold(
	customerID VARCHAR(10),
	soldDate DATE,
	itemCode VARCHAR(10),
	soldItemQty INT(10),
	CONSTRAINT PRIMARY KEY (customerID, soldDate, itemCode),
	CONSTRAINT FOREIGN KEY (itemCode) REFERENCES StockItem (itemCode) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY (customerID, soldDate) REFERENCES SoldItem (customerID, soldDate) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE Sold;

#------------------------------------#13#-----------------------------------------

DROP TABLE IF EXISTS Returns;
CREATE TABLE IF NOT EXISTS Returns(
	customerID VARCHAR(10),
	itemCode VARCHAR(10),
	returnDate DATE,
	qtyOnReturn INT(10),
	CONSTRAINT FOREIGN KEY (customerID) REFERENCES CustomerShop (customerID) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY (itemCode) REFERENCES StockItem (itemCode) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE Returns;