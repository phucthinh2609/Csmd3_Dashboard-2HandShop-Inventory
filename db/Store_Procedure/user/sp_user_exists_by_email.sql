delimiter //
CREATE PROCEDURE sp_user_exists_by_email (
    OUT isExists BOOLEAN,
    IN user_email VARCHAR(50)
)
BEGIN
	IF (NOT EXISTS(
		SELECT *
        FROM users AS u
        WHERE u.email = user_email
    ))
    THEN
		SET isExists = false;
ELSE
		SET isExists = true;
END IF;
END;//
delimiter ;