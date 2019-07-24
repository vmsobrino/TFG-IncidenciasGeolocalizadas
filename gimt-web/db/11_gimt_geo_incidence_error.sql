# ------------------------------------------------------------
# Incidencias Geolocalizadas con Error.
#
# Victor M. Sobrino
# TFG: ESI CR - UCLM
# ------------------------------------------------------------

CREATE TABLE `tfg`.`gimt_geo_incidence_error` (
  `id_geo_incidence_error` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'Geo Incidence with error Identifier',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Insert or Update timestamp',
  `incidence_type` varchar(50) DEFAULT NULL COMMENT 'Incidence Cause Type Name',
  `community` varchar(50) DEFAULT NULL COMMENT 'Community name',
  `province` varchar(50) DEFAULT NULL COMMENT 'Province Name',
  `plate` varchar(2) DEFAULT NULL COMMENT 'Province Plate',
  `cause` varchar(50) DEFAULT NULL COMMENT 'Weather Subtype Cause Name',
  `town` varchar(100) DEFAULT NULL  COMMENT 'Town name',
  `start_datetime` varchar(50) DEFAULT NULL COMMENT 'Incidence Start DateTime',
  `incidence_level` varchar(100) DEFAULT NULL COMMENT 'Incidence Level Description',
  `road` varchar(25) DEFAULT NULL COMMENT 'Road Code Name',
  `initial_kilometer` varchar(25) DEFAULT NULL COMMENT 'Incidence Initial Kilometer Point',
  `final_kilometer`  varchar(25) DEFAULT NULL COMMENT 'Incidence Final Kilometer Point',
  `sense` varchar(100) DEFAULT NULL COMMENT 'Incidence Sense in Road',
  `name` varchar(100) DEFAULT NULL COMMENT 'Incidence Occurrence Name',
  `longitude` varchar(25) DEFAULT NULL COMMENT 'Incidence GPS Longitude',
  `latitude` varchar(25) DEFAULT NULL COMMENT 'Incidence GPS Latitude',
  `error_cause` varchar(100) DEFAULT NULL COMMENT 'Error in Incidence',
  PRIMARY KEY (`id_geo_incidence_error`),
  UNIQUE KEY `id_geo_incidence_error_UNIQUE` (`id_geo_incidence_error`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Geo Incidences with Error Collection';

