# ------------------------------------------------------------
# Niveles fluidez tráfico para carreteras.
#
# Victor M. Sobrino
# TFG: ESI CR - UCLM
# ------------------------------------------------------------

CREATE TABLE `tfg`.`gimt_road_level` (
  `id_road_level` int NOT NULL AUTO_INCREMENT COMMENT 'Road Level Identifier',
  `level_colour` varchar(25) NOT NULL COMMENT 'Level Colour Description',
  `level_flow` varchar(25) NOT NULL COMMENT 'Flow Level Description',
  PRIMARY KEY (`id_road_level`),
  UNIQUE KEY `id_road_level_UNIQUE` (`id_road_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Road Traffic Flow Levels';


INSERT INTO `tfg`.`gimt_road_level` (`level_colour`, `level_flow`)
VALUES
	('Verde', 'Normal'),
	('Blanco', 'Fluido'),
	('Amarillo', 'Lento'),
	('Rojo', 'Muy lento'),
	('Negro', 'Parado')
;

/*
1	Verde		Normal
2	Blanco		Fluido
3	Amarillo	Lento
4	Rojo		Muy lento
5	Negro		Parado
*/