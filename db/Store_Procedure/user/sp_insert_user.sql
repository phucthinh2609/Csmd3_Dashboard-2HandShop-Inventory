delimiter //
CREATE PROCEDURE sp_insert_user(
    IN user_full_name NVARCHAR(150),
    IN user_mobile VARCHAR(15),
    IN user_email VARCHAR(50),
    IN user_password VARCHAR(32),
    IN user_address  NVARCHAR(150),
    IN user_role VARCHAR(40),
    IN user_status VARCHAR(40)
)
BEGIN
    INSERT INTO users(
        full_name,
        mobile,
        email,
        `password`,
        address,
        `role`,
        created_at,
        `status`
    )
    VALUES(
          user_full_name,
          user_mobile,
          user_email,
          user_password,
          user_address,
          user_role,
          NOW(),
          user_status
          );
END;//
delimiter ;