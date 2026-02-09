CREATE DATABASE IF NOT EXISTS tecnostore_db;
USE tecnostore_db;

-- CLIENTE

CREATE TABLE cliente (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(80) NOT NULL,
    identificacion VARCHAR(30) NOT NULL UNIQUE,
    correo VARCHAR(120) NOT NULL,
    telefono VARCHAR(20) NOT NULL
);

-- CELULAR
CREATE TABLE celular (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    marca VARCHAR(60) NOT NULL,
    modelo VARCHAR(60) NOT NULL,
    sistema_os VARCHAR(40) NOT NULL,
    gama ENUM('ALTA', 'MEDIA', 'BAJA') NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL
);

-- VENTA
CREATE TABLE venta (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total_sin_iva DOUBLE NOT NULL,
    iva DOUBLE NOT NULL,
    total_con_iva DOUBLE NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

--ITEM VENTA
CREATE TABLE item_venta (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_venta INT NOT NULL,
    id_celular INT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (id_venta)   REFERENCES venta(id)
        ON DELETE CASCADE,
    FOREIGN KEY (id_celular) REFERENCES celular(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);
