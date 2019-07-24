# ------------------------------------------------------------
# Configuracion de validadores almacenada en BBDD.
#
# Victor M. Sobrino
# TFG: ESI CR - UCLM
# ------------------------------------------------------------

CREATE TABLE `tfg`.`gimt_validator` (
  `id_validator` INT NOT NULL AUTO_INCREMENT COMMENT 'Validator Identifer (PK)',
  `service` VARCHAR(150) NOT NULL COMMENT 'Class name to validation',
  `validator` VARCHAR(150) NOT NULL COMMENT 'Class name used to validate',
  `params` VARCHAR(150) DEFAULT NULL COMMENT 'Values to check for each validator',
  PRIMARY KEY (`id_validator`, `service`),
  UNIQUE INDEX `id_validator_service_UNIQUE` (`id_validator` ASC, `service` ASC),
  INDEX `id_validator_service_idx` (`id_validator` ASC, `service` ASC))
COMMENT = 'Validators configuration parameters for GIMT application.';


INSERT INTO `tfg`.`gimt_validator` (`service`, `validator`, `params`)
VALUES
	('edu.gimt.domain.XMLFileReader.doValidate', 'edu.gimt.validators.DateTimeRequiredValidator', null),
	('edu.gimt.domain.XMLFileReader.doValidate', 'edu.gimt.validators.DateTimeFormatValidator', null),
	('edu.gimt.domain.XMLFileReader.doValidate', 'edu.gimt.validators.LatAndLongValidator', '-90;90;-180;180'),
	('edu.gimt.domain.XMLFileReader.doValidate', 'edu.gimt.validators.KilometerValidator', null),
	('edu.gimt.domain.XMLFileReader.doValidate', 'edu.gimt.validators.TownLocationValidator', null)
;
