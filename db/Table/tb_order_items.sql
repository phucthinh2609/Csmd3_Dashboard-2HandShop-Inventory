CREATE TABLE order_items(
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    product_id BIGINT(20) NOT NULL,
    item_id BIGINT(20) NOT NULL,
    order_id BIGINT(20) NOT NULL,
    price DECIMAL(12,0) NOT NULL,
    quantity SMALLINT(6) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    content NVARCHAR(255),

    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (item_id) REFERENCES items(id),
    FOREIGN KEY (order_id) REFERENCES orders(id),

    CHECK (price >= 0),
    CHECK (quantity >= 0)

);