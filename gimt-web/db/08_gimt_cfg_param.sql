# ------------------------------------------------------------
# Parametros de configuracion almacenados en BBDD.
#
# Victor M. Sobrino
# TFG: ESI CR - UCLM
# ------------------------------------------------------------

CREATE TABLE `tfg`.`gimt_cfg_param` (
  `id_cfg_param` INT NOT NULL AUTO_INCREMENT COMMENT 'Configuration parameter Identifer (PK)',
  `cod_module` VARCHAR(45) NOT NULL COMMENT 'Module name for each configuration parameter',
  `cod_param` VARCHAR(45) NOT NULL COMMENT 'Parameter name',
  `param_value` VARCHAR(255) DEFAULT NULL COMMENT 'Value for configuration parameter',
  PRIMARY KEY (`id_cfg_param`),
  UNIQUE INDEX `id_cfg_params_UNIQUE` (`id_cfg_param` ASC),
  INDEX `module_param_idx` (`cod_module` ASC, `cod_param` ASC))
COMMENT = 'Configuration parameters for GIMT application.';

INSERT INTO `tfg`.`gimt_cfg_param` (`cod_module`, `cod_param`, `param_value`)
VALUES
	('INC_DOWNLOAD', 'MAX_DIST_BETWEEN_COORDINATES', '2000'),
	('INC_DOWNLOAD', 'MAX_MINUTES_BETWEEN_DATES', '120'),
	('INC_DOWNLOAD', 'PUBLIC_INC_URL', 'https://www.trafikoa.eus/servicios/IncidenciasTDT/IncidenciasTrafikoTDTHist'),
	('INC_DOWNLOAD', 'INC_INI_DATE_PARAM', 'fechaIni'),
	('INC_DOWNLOAD', 'INC_END_DATE_PARAM', 'fechaFin'),
	('INC_DOWNLOAD', 'TMP_FILE_PATH', 'D:/CAG/TFG/workspace/tmp'),
	('INC_DOWNLOAD', 'INC_FIRST_DATE', '2006-01-01'),
	('INC_DOWNLOAD', 'INC_LAST_DATE', null),
	('INC_DOWNLOAD', 'GEO_FILE_PATH', 'D:/CAG/TFG/workspace/json'),
	('INC_DOWNLOAD', 'GEO_FILE_NAME', 'municipios_es21.json'),
	('ZONE_DIAGNOSTIC', 'COUNT_FILE_PATH', 'D:/CAG/TFG/workspace/fileCount'),
	('ZONE_DIAGNOSTIC', 'COUNT_FILE_NAME', 'incidenceCount.csv')
;