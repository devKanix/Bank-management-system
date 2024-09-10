CREATE DATABASE
IF NOT EXISTS KMB;

USE KMB;

CREATE TABLE
IF NOT EXISTS login
(
    formno VARCHAR
(20),
    c_number VARCHAR
(20),
    p_number VARCHAR
(40)
);

CREATE TABLE
IF NOT EXISTS signup
(
    formno VARCHAR
(20),
    fullname VARCHAR
(20),
    dob VARCHAR
(20),
    gender VARCHAR
(20),
    email VARCHAR
(40),
    phonno VARCHAR
(20),
    address VARCHAR
(100),
    pincode VARCHAR
(20)
);

CREATE TABLE
IF NOT EXISTS signup2
(
    formno VARCHAR
(20),
    religion VARCHAR
(20),
    income VARCHAR
(20),
    education VARCHAR
(40),
    pan_no VARCHAR
(20),
    aadhar_no VARCHAR
(12),
    e_account VARCHAR
(5),
    citizen VARCHAR
(5)
);

CREATE TABLE
IF NOT EXISTS signup3
(
    formno VARCHAR
(20),
    a_type VARCHAR
(200),
    c_number VARCHAR
(200),
    p_number VARCHAR
(40),
    services VARCHAR
(200)
);

CREATE TABLE
IF NOT EXISTS bank
(
    p_number VARCHAR
(40),
    date VARCHAR
(50),
    type VARCHAR
(20),
    amount VARCHAR
(20)
);