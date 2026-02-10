DROP DATABASE IF EXISTS tecnostore_db;
CREATE DATABASE tecnostore_db;
USE tecnostore_db;

-- Marcas
CREATE TABLE marcas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- Celulares
CREATE TABLE celulares (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL,
    sistema_os VARCHAR(50) NOT NULL,
    gama VARCHAR(20) NOT NULL CHECK (gama IN ('ALTA', 'MEDIA', 'BAJA')),
    stock INT NOT NULL DEFAULT 0,
    precio DOUBLE NOT NULL,
    id_marca INT NOT NULL,
    FOREIGN KEY (id_marca) REFERENCES marcas(id) ON DELETE CASCADE
);

-- Clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    identificacion VARCHAR(20) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL
);

-- Ventas
CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha VARCHAR(20) NOT NULL,
    total_sin_iva DOUBLE NOT NULL,
    total_con_iva DOUBLE NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id) ON DELETE CASCADE
);

-- Items de venta
CREATE TABLE items_venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_celular INT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES ventas(id) ON DELETE CASCADE,
    FOREIGN KEY (id_celular) REFERENCES celulares(id) ON DELETE RESTRICT
);

-- =========================
-- TRIGGERS (IVA)
-- =========================

DELIMITER //

CREATE TRIGGER calcular_iva_insert
BEFORE INSERT ON ventas
FOR EACH ROW
BEGIN
    SET NEW.total_con_iva = NEW.total_sin_iva * 1.19;
END//

CREATE TRIGGER calcular_iva_update
BEFORE UPDATE ON ventas
FOR EACH ROW
BEGIN
    SET NEW.total_con_iva = NEW.total_sin_iva * 1.19;
END//

DELIMITER ;

-- =========================
-- DATOS DE PRUEBA
-- =========================

-- Marcas
INSERT INTO marcas (nombre) VALUES 
('Samsung'),
('Apple'),
('Xiaomi'),
('Motorola'),
('Huawei');

-- Celulares
INSERT INTO celulares (modelo, sistema_os, gama, stock, precio, id_marca) VALUES
-- Samsung
('Galaxy S24 Ultra', 'Android 14', 'ALTA', 15, 5499000, 1),
('Galaxy A54', 'Android 13', 'MEDIA', 30, 1899000, 1),
('Galaxy A14', 'Android 13', 'BAJA', 50, 699000, 1),

-- Apple
('iPhone 15 Pro Max', 'iOS 17', 'ALTA', 10, 6299000, 2),
('iPhone 14', 'iOS 16', 'MEDIA', 20, 3999000, 2),
('iPhone SE', 'iOS 16', 'BAJA', 25, 2199000, 2),

-- Xiaomi
('Xiaomi 14 Pro', 'Android 14', 'ALTA', 12, 4299000, 3),
('Redmi Note 13 Pro', 'Android 13', 'MEDIA', 40, 1299000, 3),
('Redmi 12', 'Android 13', 'BAJA', 60, 599000, 3),

-- Motorola
('Edge 40 Pro', 'Android 13', 'ALTA', 8, 3799000, 4),
('Moto G84', 'Android 13', 'MEDIA', 35, 1099000, 4),
('Moto E13', 'Android 13', 'BAJA', 45, 449000, 4);

-- Clientes
INSERT INTO clientes (nombre, identificacion, correo, telefono) VALUES
('Juan Pérez', '1234567890', 'juan.perez@email.com', '3001234567'),
('María García', '0987654321', 'maria.garcia@email.com', '3109876543'),
('Carlos Rodríguez', '1122334455', 'carlos.rodriguez@email.com', '3201122334'),
('Ana López', '5544332211', 'ana.lopez@email.com', '3155443322');

-- Venta (el trigger calcula total_con_iva)
INSERT INTO ventas (id_cliente, fecha, total_sin_iva, total_con_iva) VALUES
(1, '2026-02-09', 1899000, 0);

-- Items de la venta
INSERT INTO items_venta (id_venta, id_celular, cantidad, subtotal) VALUES
(1, 2, 1, 1899000);

-- =========================
-- CONSULTAS DE VERIFICACIÓN
-- =========================

SELECT * FROM marcas;

SELECT c.id, m.nombre AS marca, c.modelo, c.sistema_os, c.gama, c.stock, c.precio
FROM celulares c
INNER JOIN marcas m ON c.id_marca = m.id;

SELECT * FROM clientes;

SELECT v.id, c.nombre AS cliente, v.fecha, v.total_sin_iva, v.total_con_iva
FROM ventas v
INNER JOIN clientes c ON v.id_cliente = c.id;
