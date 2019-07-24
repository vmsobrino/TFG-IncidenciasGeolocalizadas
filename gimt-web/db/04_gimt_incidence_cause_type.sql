# ------------------------------------------------------------
# Tipos de Causas de las incidencias geolocalizadas.
#
# Victor M. Sobrino
# TFG: ESI CR - UCLM
# ------------------------------------------------------------

CREATE TABLE `tfg`.`gimt_incidence_cause_type` (
  `id_cause_type` int NOT NULL AUTO_INCREMENT COMMENT 'Incidence Cause Type Identifier',
  `cause_type_name` varchar(50) NOT NULL COMMENT 'Incidence Cause Type Name',
  PRIMARY KEY (`id_cause_type`),
  UNIQUE KEY `id_cause_type_UNIQUE` (`id_cause_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Distinct Types of Incidence Causes';


INSERT INTO `tfg`.`gimt_incidence_cause_type` (`cause_type_name`)
VALUES
	('Meteorológica'),
	('Accidente'),
	('Retención'),
	('Seguridad Vial'),
	('Puertos de montaña'),
	('Vialidad invernal tramos'),
	('Obras'),
	('Otras incidencias'),
	('Pruebas deportivas');

/*
contamos las filas repetidas filtrando por causa de la incidencia y agrupando por las siguientes columnas:
	id_geo_community, id_geo_province, id_geo_cause_subtype, town_name, start_datetime, 
	mountain_port_level, road_code, initial_kilometer, final_kilometer, sense, name, longitude, latitude
	
	1	Meteorológica
	2	Accidente
	3	Retención
	4	Seguridad Vial
	5	Puertos de montaña
	6	Vialidad invernal tramos
	7	Obras
	8	Otras incidencias
	9	Pruebas deportivas
*/