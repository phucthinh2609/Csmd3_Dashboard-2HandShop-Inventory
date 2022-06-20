CREATE TABLE orders(
   id BIGINT(20) NOT NULL AUTO_INCREMENT,
   user_id BIGINT(20) NOT NULL,
   `type` SMALLINT(6) NOT NULL,
   `status` SMALLINT(6) NOT NULL,
   grand_total DECIMAL(12,0) NOT NULL,
   created_at DATETIME NOT NULL,
   updated_at DATETIME,
   content TEXT,

   PRIMARY KEY(id),
   FOREIGN KEY (user_id) REFERENCES users(id),

   CHECK(grand_total > 0)
);