delimiter //
CREATE PROCEDURE sp_search_product(
    IN key_search NVARCHAR(150)
)
BEGIN
SELECT
    p.id AS id,
    p.title AS title,
    p.image AS image,
    p.created_at AS created_at,
    u_created.email AS created_by,
    p.updated_at AS updated_at,
    u_updated.email AS updated_by,
    p.content AS content
FROM products AS p
         JOIN users AS u_created ON p.created_by = u_created.id
         JOIN users AS u_updated ON p.updated_by = u_updated.id
WHERE
        p.title LIKE key_search
   OR p.content LIKE key_search
   OR u_created.email LIKE key_search
   OR u_updated.email LIKE key_search;
END;//
delimiter ;