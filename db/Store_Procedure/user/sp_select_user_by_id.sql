delimiter //
CREATE PROCEDURE sp_select_user_by_id(IN user_id BIGINT(20))
BEGIN
SELECT
    u.id,
    u.full_name,
    u.mobile,
    u.email,
    u.`password`,
    u.address,
    u.`role`,
    u.created_at,
    u.updated_at,
    u.last_login,
    u.`status`

FROM users AS u
WHERE u.id = user_id;
END;//
delimiter ;