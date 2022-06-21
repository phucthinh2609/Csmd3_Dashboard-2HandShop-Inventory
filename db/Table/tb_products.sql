CREATE TABLE products(
	id BIGINT(20) NOT NULL AUTO_INCREMENT,
    title VARCHAR(75) NOT NULL,
    image VARCHAR(255) NOT NULL DEFAULT 'none',
    created_at DATETIME NOT NULL,
    created_by BIGINT(20) NOT NULL,
    updated_at DATETIME,
    updated_by BIGINT(20) NOT NULL,
    content TEXT,

    PRIMARY KEY(id),
    FOREIGN KEY (created_by) REFERENCES users(id),
);
