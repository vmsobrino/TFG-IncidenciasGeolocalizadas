# ------------------------------------------------------------
# Comunidades Autónomas.
#
# Victor M. Sobrino
# TFG: ESI CR - UCLM
# ------------------------------------------------------------

CREATE TABLE `tfg`.`gimt_community` (
  `id_community` int NOT NULL COMMENT 'Community Identifier',
  `community_name` varchar(50) NOT NULL COMMENT 'Community name',
  `enabled` int NOT NULL COMMENT 'Flag to enable or disable community',
  PRIMARY KEY (`id_community`),
  UNIQUE KEY `id_community_UNIQUE` (`id_community`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Autonomous communities of Spain';


INSERT INTO `tfg`.`gimt_community` (`id_community`, `community_name`, `enabled`)
VALUES
	(1,'Andalucía', 0),
	(2,'Aragón', 0),
	(3,'Asturias, Principado de', 0),
	(4,'Balears, Illes', 0),
	(5,'Canarias', 0),
	(6,'Cantabria', 0),
	(7,'Castilla y León', 0),
	(8,'Castilla - La Mancha', 0),
	(9,'Catalunya', 0),
	(10,'Comunitat Valenciana', 0),
	(11,'Extremadura', 0),
	(12,'Galicia', 0),
	(13,'Madrid, Comunidad de', 0),
	(14,'Murcia, Región de', 0),
	(15,'Navarra, Comunidad Foral de', 0),
	(16,'Euskadi', 1),
	(17,'Rioja, La', 0),
	(18,'Ceuta', 0),
	(19,'Melilla', 0);
	
ALTER TABLE tfg.gimt_community
 ADD COLUMN nuts2 varchar(4) COMMENT 'NUTS2 Area Code' AFTER enabled;

UPDATE tfg.gimt_community SET nuts2 = 'ES61'
 WHERE id_community = 1;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES24'
 WHERE id_community = 2;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES12'
 WHERE id_community = 3;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES53'
 WHERE id_community = 4;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES70'
 WHERE id_community = 5;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES13'
 WHERE id_community = 6;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES41'
 WHERE id_community = 7;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES42'
 WHERE id_community = 8;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES51'
 WHERE id_community = 9;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES52'
 WHERE id_community = 10;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES43'
 WHERE id_community = 11;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES11'
 WHERE id_community = 12;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES30'
 WHERE id_community = 13;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES62'
 WHERE id_community = 14;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES22'
 WHERE id_community = 15;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES21'
 WHERE id_community = 16;

UPDATE tfg.gimt_community SET nuts2 = 'ES23'
 WHERE id_community = 17;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES63'
 WHERE id_community = 18;
 
UPDATE tfg.gimt_community SET nuts2 = 'ES64'
 WHERE id_community = 19;
 
COMMIT;