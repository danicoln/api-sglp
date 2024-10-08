CREATE TABLE usuarios (
    id VARCHAR(36) PRIMARY KEY UNIQUE NOT NULL,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    login_inclusao VARCHAR(255),
    login_atualizacao VARCHAR(255),
    data_inclusao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX idx_usuario_login ON usuarios(login);