CREATE TABLE users(
      id BIGINT(20) NOT NULL AUTO_INCREMENT,
      full_name NVARCHAR(150),
      mobile VARCHAR(20) UNIQUE,
      email VARCHAR(50) UNIQUE,
      `password` VARCHAR(32) NOT NULL,
      address NVARCHAR(150),
      `role` VARCHAR(40) NULL DEFAULT 'USER',
      created_at DATETIME NOT NULL,
      updated_at DATETIME,
      last_login DATETIME,
      `status` VARCHAR(40) NOT NULL DEFAULT 'ACTIVE',

      PRIMARY KEY(id)
);