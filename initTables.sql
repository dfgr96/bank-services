USE bankdb;

-- Crear la tabla de cuentas si no existe
CREATE TABLE IF NOT EXISTS accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    saldo DECIMAL(10,2) NOT NULL DEFAULT 0.00
);

-- Crear la tabla de transacciones si no existe
CREATE TABLE IF NOT EXISTS transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    from_account INT NOT NULL,
    to_account INT NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (from_account) REFERENCES accounts(id) ON DELETE CASCADE,
    FOREIGN KEY (to_account) REFERENCES accounts(id) ON DELETE CASCADE
);