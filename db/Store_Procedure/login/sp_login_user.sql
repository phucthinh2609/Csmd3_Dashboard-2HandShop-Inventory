delimiter //
CREATE PROCEDURE sp_login_user(
    IN user_email VARCHAR(50),
    IN user_password VARCHAR(32),
    OUT success BOOLEAN
)
BEGIN
	DECLARE count_email INT(2);
	DECLARE count_password INT(2);

    SET count_email =
		(
		SELECT COUNT(*) AS count
		FROM users AS u
		WHERE u.email = user_email
		);

	SET count_password =
		(
		SELECT COUNT(*) AS count
		FROM users AS u
		WHERE u.email = user_password
		);

	SET success = FALSE;


	IF(count_email > 0 AND count_password > 0)
    THEN
		SET success = TRUE;
    END IF;

END;//
delimiter ;