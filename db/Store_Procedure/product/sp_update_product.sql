delimiter //
CREATE PROCEDURE sp_update_user(
    IN product_id BIGINT(20),
    IN product_title VARCHAR(75),
    IN product_image VARCHAR(255),
    IN product_updated_by BIGINT(20),
    IN product_content TEXT,
    )
BEGIN
UPDATE products AS p
SET
    p.title = product_title,
    p.image = product_image,
    p.updated_at = NOW(),
    p.updated_by = product_updated_by,
    p. content = product_content
WHERE p.id = product_id;
END;//
delimiter ;