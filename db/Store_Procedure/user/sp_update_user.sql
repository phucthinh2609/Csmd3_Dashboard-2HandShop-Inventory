delimiter //
CREATE PROCEDURE sp_update_user(
    IN user_id BIGINT(20),
    IN user_full_name NVARCHAR(150),
    IN user_mobile VARCHAR(15),
    IN user_email VARCHAR(50),
    IN user_password VARCHAR(32),
    IN user_address NVARCHAR(150),
    IN user_role VARCHAR(40),
    IN user_status VARCHAR(40)
        )
BEGIN
UPDATE users AS u
SET
    u.full_name = user_full_name,
    u.mobile = user_mobile,
    u.email = user_email,
    u.`password` = user_password,
    u.address = user_address,
    u.`role` = user_role,
    u.updated_at = NOW(),
    u.`status` = user_status
WHERE u.id = user_id;
END;//
delimiter ;