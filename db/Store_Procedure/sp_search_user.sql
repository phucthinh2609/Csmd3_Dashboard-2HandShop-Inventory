delimiter //
CREATE PROCEDURE sp_search_user(
	IN key_search NVARCHAR(150)
)
BEGIN
SELECT
    u.id,
    u.full_name,
    u.mobile,
    u.email,
    u.address,
    u.`role`,
    u.`status`
FROM users AS u
WHERE
    u.full_name LIKE key_search
   OR u.mobile LIKE key_search
   OR u.email LIKE key_search
   OR u.address LIKE key_search
   OR u.`role` LIKE key_search
   OR u.`status` LIKE key_search;
END;//
delimiter ;