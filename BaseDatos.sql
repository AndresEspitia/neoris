CREATE TABLE IF NOT EXISTS customers (
  client_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  address VARCHAR(100),
  phone_number VARCHAR(10) NOT NULL,
  password VARCHAR(50) NOT NULL,
  status BOOLEAN NOT NULL,
  age INT NOT NULL,
  gender VARCHAR(5) NOT NULL,
  identification VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS account (
  account_id INT PRIMARY KEY AUTO_INCREMENT,
  number_account INT,
  type VARCHAR(50) NOT NULL,
  initial_balance DOUBLE NOT NULL,
  status BOOLEAN NOT NULL,
  customer INT NOT NULL,
  available_balance DOUBLE NOT NULL,
  FOREIGN KEY (customer) REFERENCES customers (client_id)
);

CREATE TABLE IF NOT EXISTS movements (
  movement_id INT PRIMARY KEY AUTO_INCREMENT,
  number_account INT NOT NULL,
  type VARCHAR(50) NOT NULL,
  status BOOLEAN NOT NULL,
  movement VARCHAR(50) NOT NULL,
  value_mov DOUBLE NOT NULL,
  date DATE NOT NULL,
  account_id INT NOT NULL,
  FOREIGN KEY (account_id) REFERENCES account (account_id)
);

INSERT INTO customers (name, address, phone_number, password, status, age, gender, identification)
VALUES ('Jose Lema', 'Otavalo sn y principal', '098254785', '1234', 1, 20, 'M', '123456789'),
        ('Marianela Montalvo', 'Amazonas y NNUU', '097548965', '5678', 1, 25, 'F', '784545852'),
        ('Juan Osorio', '13 de junio y equinoccial', '098874587', '1245', 1, 26, 'M', '78524562');

INSERT INTO account (number_account, type, initial_balance, status, customer, available_balance)
VALUES
(748758, 'Ahorros', 2000, true, 1, 2000),
(225487, 'Corriente', 100, true, 2, 100),
(495878, 'Ahorros', 0, true, 3, 0);


INSERT INTO movements (number_account, type, status, movement, value_mov, date, account_id)
VALUES
(478758, 'DEBITO', true, 'Retiro de 575', 575, '2023-05-15', 1),
(225487, 'CREDITO', true, 'Deposito de 600', 600, '2023-05-15', 2),
(495878, 'CREDITO', true, 'Deposito de 150', 150, '2023-05-16', 3),
(496825, 'DEBITO', true, 'Retiro de 540', 540, '2023-05-16', 2);



