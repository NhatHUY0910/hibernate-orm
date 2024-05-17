CREATE DATABASE orm;
USE orm;

CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    address VARCHAR(255)
);

INSERT INTO customers (name, email, address)
VALUES
    ('John Doe', 'john.doe@example.com', '123 Main St, Anytown USA'),
    ('Jane Smith', 'jane.smith@example.com', '456 Oak Rd, Someville USA'),
    ('Bob Johnson', 'bob@company.com', '789 Pine Ave, Metropolis USA'),
    ('Alice Williams', 'alice@gmail.com', '321 Elm St, Suburbia USA'),
    ('Charlie Brown', 'charlie@peanuts.com', '159 Acorn Ln, Treehouse USA');
    
    SELECT * FROM customers;

