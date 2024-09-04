CREATE DATABASE prealert_db;

CREATE TABLE Prealert (
    id INT PRIMARY KEY IDENTITY (1,1),
    name NVARCHAR(255) NOT NULL,
    status NVARCHAR (50) NOT NULL,
    guide NVARCHAR(100) NOT NULL,
    creationDate DATETIME NOT NULL,
    collected NVARCHAR(100),
    origin NVARCHAR(100)
);

CREATE TABLE DeviceType (
    id INT PRIMARY KEY IDENTITY (1,1),
    name NVARCHAR(255) NOT NULL,
    serialLength INT NOT NULL,
    macLength INT NOT NULL
);



INSERT INTO Prealert (name, status, guide, creationDate, collected, origin)
VALUES ('Prealerta 1', 'CARGADO', 'g-0001', GETDATE(), NULL, NULL);

INSERT INTO Prealert (name, status, guide, creationDate, collected, origin)
VALUES ('Prealerta 2', 'PENDIENTE', 'g-0002', GETDATE(), NULL, NULL);

select * from Prealert;

INSERT INTO DeviceType (name, serialLength, macLength)
VALUES ('Tipo A', 12, 6);

INSERT INTO DeviceType (name, serialLength, macLength)
VALUES ('Tipo B', 16, 8);

select * from DeviceType;

CREATE TABLE Device (
    id INT PRIMARY KEY IDENTITY (1,1),
    name VARCHAR(255) NOT NULL,
    type INT,
    serial VARCHAR(255) NOT NULL,
    mac VARCHAR(255) NOT NULL,
    observations TEXT,
    FOREIGN KEY (type) REFERENCES DeviceType(id)
);

INSERT INTO Device (name, type, serial, mac, observations)
VALUES ('Dispositivo Tipo A', 1, '123456789012', '00:1A:2B:3C:4D:5E', 'Observaciones del dispositivo Tipo A');

INSERT INTO Device (name, type, serial, mac, observations)
VALUES ('Dispositivo Tipo B', 2, '1234567890123456', '00:1A:2B:3C:4D:5E:6F', 'Observaciones del dispositivo Tipo B');



