CREATE DATABASE donation_system;
USE donation_system;

-- Create the 'users' table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    contact VARCHAR(15),
    role ENUM('Admin', 'User') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
ALTER TABLE users
ADD COLUMN status VARCHAR(50);

-- Create the 'admins' table (this is optional, as admins could be stored in the 'users' table)
CREATE TABLE admins (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES users(id)
);

-- Create the 'donations' table
CREATE TABLE donations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    donor_id INT ,
    beneficiary_id INT,
    amount DECIMAL(10, 2) NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (donor_id) REFERENCES users(id),
    FOREIGN KEY (beneficiary_id) REFERENCES beneficiaries(id)
);

ALTER TABLE donations DROP FOREIGN KEY donations_ibfk_2;
ALTER TABLE donations DROP COLUMN beneficiary_id;
ALTER TABLE donations DROP FOREIGN KEY donations_ibfk_1;
ALTER TABLE donations DROP COLUMN donor_id;
ALTER TABLE donations
ADD COLUMN donor_name VARCHAR(100);



-- Create the 'beneficiaries' table
CREATE TABLE beneficiaries (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    contact VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
select * from beneficiaries;
ALTER TABLE beneficiaries
ADD COLUMN email VARCHAR(100);

-- Create the 'donation_posters' table
CREATE TABLE donation_posters (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
select * from donation_posters;
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    contact VARCHAR(15),
    role VARCHAR(50),
    status VARCHAR(50)  -- Add this column if it doesn't exist
);


-- Example INSERT statements to populate the tables

-- Inserting sample user data
INSERT INTO users (name, email, password, contact, role) VALUES
('Admin User', 'usersadmin@example.com', 'password123', '1234567890', 'Admin'),
('John Doe', 'johndoe@example.com', 'password456', '9876543210', 'User');

-- Inserting sample beneficiary data
INSERT INTO beneficiaries (name, description, contact) VALUES
('Tharushi', 'Help', '12345'),
('Chamudi ','support', '0753657827'),
('tharu ','help', '6789'),
('Beneficiary 1', 'Helping the less fortunate', '555-0001'),
('Beneficiary 2', 'Supporting education for children', '555-0002');

-- Inserting sample donation data
INSERT INTO donations (donor_id, amount,donor_name) VALUES
(1, 1, 200.00,'dave');

-- Inserting sample donation posters
INSERT INTO donation_posters (title, description) VALUES
('Give Hope,Give Life', 'Join our mission to bring hope to those in need'),
('Light Op a Life Today', 'Change starts with you, make your contribution to improve lives'),
('Cancer Trust', 'Help Cancer Patients'),
('Helping Hands', 'Join us to help the less fortunate'),
('Children Education Fund', 'Help support the education of underprivileged children');