# ------------------------------------------------------------
# Usuarios registrados para la aplicacion OnLine.
#
# Victor M. Sobrino
# TFG: ESI CR - UCLM
# ------------------------------------------------------------

CREATE TABLE `tfg`.`gimt_user` (
  `id_user` INT NOT NULL AUTO_INCREMENT COMMENT 'User Identifer (PK)',
  `user_name` VARCHAR(20) NOT NULL COMMENT 'Login string for user',
  `password` VARCHAR(20) NOT NULL COMMENT 'Password string for user',
  `full_name` VARCHAR(45) DEFAULT NULL COMMENT 'Full name for user',
  `enabled` int NOT NULL COMMENT 'Flag to enable or disable user',
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `id_user_UNIQUE` (`id_user` ASC)
) COMMENT = 'Distinct users for GIMT application.';

INSERT INTO `tfg`.`gimt_user` (`user_name`, `password`, `full_name`, `enabled`)
VALUES
	('vmsobrino', 'vmsobrino', 'Victor M. Sobrino', 1),
	('openData', 'openData', 'Usuario Open Data', 0)
;