CREATE TABLE items(
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  product_id BIGINT(20) NOT NULL,
  order_id BIGINT(20) NOT NULL,
  sku VARCHAR(100) NOT NULL,
  price DECIMAL(12,0) NOT NULL,
  quantity SMALLINT(6) NOT NULL,
  sold SMALLINT(6) NOT NULL,
  available SMALLINT(6) NOT NULL,
  defective SMALLINT(6) NOT NULL,
  created_by DATETIME NOT NULL,
  updated_by DATETIME,
  created_at DATETIME NOT NULL,
  updated_at DATETIME,

  PRIMARY KEY (id),
  FOREIGN KEY (product_id) REFERENCES products(id),
  FOREIGN KEY (order_id) REFERENCES orders(id),

  CHECK (price >= 0),
  CHECK (quantity >= 0),
  CHECK (sold >= 0),
  CHECK (available >= 0),
  CHECK (defective >= 0)
);