CREATE VIEW vw_products AS
SELECT
p.id AS id,
p.title AS title,
p.image AS image,
p.created_at AS created_at,
u_created.email AS created_by,
p.updated_at AS updated_at,
u_updated.email AS updated_by,
p.content AS content
FROM
products p
JOIN users u_created ON p.created_by = u_created.id
JOIN users u_updated ON p.updated_by = u_updated.id