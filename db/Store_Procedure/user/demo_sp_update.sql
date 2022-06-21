CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_update_user`(
	IN user_id BIGINT(20),
	IN user_full_name NVARCHAR(150),
	IN user_mobile VARCHAR(15),
	IN user_email VARCHAR(50),
	IN user_password VARCHAR(32),
	IN user_address NVARCHAR(IN user_id BIGINT(20),
	IN user_full_name NVARCHAR(150),
	IN user_mobile VARCHAR(15),
	IN user_email VARCHAR(50),
	IN user_password VARCHAR(32),
	IN user_address NVARCHAR(150),
    IN user_role VARCHAR(40),
    IN user_status VARCHAR(40),
	OUT success_email BOOLEAN,
	OUT success_mobile BOOLEAN
    )
BEGIN
	DECLARE is_exists_email BOOLEAN;
	DECLARE is_exists_mobile BOOLEAN;
	DECLARE origin_email VARCHAR(50);
	DECLARE origin_mobile VARCHAR(50);

    SET is_exists_email =
		EXISTS
        (
		SELECT *
        FROM users AS u
        WHERE u.email = user_email
		);

    SET origin_email =
		(SELECT u.email
		FROM users AS u
		WHERE u.id = user_id);

	SET is_exists_mobile =
		EXISTS
		(
		SELECT *
		FROM users AS u
		WHERE u.email = user_email
		);

    SET origin_mobile =
		(SELECT u.mobile
		FROM users AS u
		WHERE u.id = user_id);




	-- CHECK EXISTS EMAIL
	SET success_email = false;

    IF (is_exists_email)
    THEN
		IF (origin_email = user_email)
		THEN
			SET success_email = TRUE;
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
			WHERE u.id = 3;
        END IF;
	ELSE
		SET success_email = TRUE;
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
		WHERE u.id = 3;
	END IF;

    -- CHECK EXISTS MOBILE
	SET success_mobile = false;

    IF (is_exists_mobile)
    THEN
		IF (origin_mobile = user_mobile)
		THEN
			SET success_mobile = TRUE;
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
			WHERE u.id = 3;
        END IF;
	ELSE
		SET success_mobile = TRUE;
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
		WHERE u.id = 3;
	END IF;
END