CREATE TABLE products(
	id BIGINT(20) NOT NULL AUTO_INCREMENT,
    title VARCHAR(75) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    content TEXT,

    PRIMARY KEY(id)
);
