# ------------------------------------------------------------
# Ficheros de incidencias XML descargados.
#
# Victor M. Sobrino
# TFG: ESI CR - UCLM
# ------------------------------------------------------------

CREATE TABLE `tfg`.`gimt_downloaded_file` (
  `file_name` VARCHAR(50) NOT NULL COMMENT 'XML file name.',
  `initial_date` datetime NOT NULL COMMENT 'Initial date of incidences in file.',
  `final_date` datetime NOT NULL COMMENT 'Final date of incidences in file.',
  `processed` INT DEFAULT 0 COMMENT 'Processed file (1) or not (0).',
  `download_datetime` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp for download file.',
  PRIMARY KEY (`file_name`),
  UNIQUE INDEX `file_name_UNIQUE` (`file_name` ASC))
COMMENT = 'Downloaded GeoIncidences XML files';
