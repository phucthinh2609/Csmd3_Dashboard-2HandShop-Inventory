delimiter //
CREATE PROCEDURE sp_select_product_by_id(IN product_id BIGINT(20))
BEGIN
SELECT
    p.id,
    p.title,
    p.image,
    p.created_at,
    u_created.email,
    p.updated_at,
    u_updated.email,
    p.content

FROM products AS p
         JOIN users u_created ON p.created_by = u_created.id
         JOIN users u_updated ON p.updated_by = u_updated.id
WHERE p.id = product_id;

END;//
delimiter ;