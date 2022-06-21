delimiter //
CREATE PROCEDURE sp_insert_product(
    IN product_title VARCHAR (75),
    IN product_image VARCHAR (255),
    IN product_created_by BIGINT(20),
    IN product_content TEXT)
BEGIN
    INSERT INTO products
        (title,
         image,
         created_at,
         created_by,
         updated_by,
         content)
    VALUES
        (product_title,
         product_image,
         NOW(),
         product_created_by,
         product_created_by,
         product_content);
END;//
delimiter ;