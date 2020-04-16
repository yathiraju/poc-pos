MySQL Type      Java Type
----------      ---------
CHAR            String
VARCHAR         String
LONGVARCHAR     String
NUMERIC         java.math.BigDecimal
DECIMAL         java.math.BigDecimal
BIT             boolean
TINYINT         byte
SMALLINT        short
INTEGER         int
BIGINT          long
REAL            float
FLOAT           double
DOUBLE          double
BINARY          byte[]
VARBINARY       byte[]
LONGVARBINARY   byte[]
DATE            java.sql.Date
TIME            java.sql.Time
TIMESTAMP       java.sql.Tiimestamp


Tables:

CREATE TABLE Product (
id INT,displayName CHAR(200), vendor CHAR(200) ,catagory CHAR(20),
brand CHAR(20),description CHAR(200),weight DECIMAL(10,3),
barcode CHAR(100), PRIMARY KEY ( id) );

CREATE TABLE Stock (id INT,stockEntryDate DATE,stockExpireDate DATE,
purchasePrice DECIMAL(10,3),salePrice DECIMAL(10,3),quantiy INT,product_id INT,
PRIMARY KEY ( id), FOREIGN KEY (product_id) REFERENCES Product(id) ON DELETE CASCADE );

CREATE TABLE Invoice(id INT,invoiceQuantiy INT,uniquePrice DECIMAL(10,3),
totalPrice DECIMAL(10,3),accountNumber INT,invoiceDate DATE,stock_id INT,PRIMARY KEY ( id),
FOREIGN KEY (stock_id) REFERENCES Stock(id) ON DELETE CASCADE );


CREATE TABLE User(id INT,email CHAR(40),password CHAR(40),firstName CHAR(40),lastName CHAR(40),
active INT,PRIMARY KEY ( id));

CREATE TABLE Role(id INT,role CHAR(40),PRIMARY KEY ( id));


CREATE TABLE user_roles(user_id INT,roles_id INT,PRIMARY KEY (user_id,roles_id),CONSTRAINT fk_role FOREIGN KEY (roles_id) REFERENCES Role (id));

********************due to encryption we have to modify password size.***********************

ALTER TABLE User MODIFY password CHAR(255) ;



CREATE TABLE Image(id INT,name CHAR(255),PRIMARY KEY ( id));
  
