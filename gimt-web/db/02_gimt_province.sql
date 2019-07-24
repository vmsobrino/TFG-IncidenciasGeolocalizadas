# ------------------------------------------------------------
# Listado de provincias españolas 2016.
#
# Datos extraídos del INE (Instituo Nacional de Estadística).
#
# El campo id_provincia debería coincidir con los dos primeros dígitos del código postal
# de la provincia. Los que tienen un dígito, añadir el 0 delante.
#
# Ejemplo:
# Dado un código postal 08031, tomamos los dos primeros dígitos (08) y vemos que pertenence a prov. Barcelona,
# Similarmente el código postal 44652 => 44 => provincia de Teruel
#
# Victor M. Sobrino
# TFG: ESI CR - UCLM
# ------------------------------------------------------------

CREATE TABLE `tfg`.`gimt_province` (
  `id_province` int NOT NULL COMMENT 'Province Identifier',
  `province_name` varchar(50) NOT NULL COMMENT 'Province Name',
  `province_plate` varchar(2) COMMENT 'Province Plate',
  `enabled` int NOT NULL COMMENT 'Flag to enable or disable Province',
  PRIMARY KEY (`id_province`),
  UNIQUE KEY `id_province_UNIQUE` (`id_province`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Distinct Provinces of Spain';

INSERT INTO `tfg`.`gimt_province` (`id_province`, `province_name`, `province_plate`, `enabled`)
VALUES
	(2, 'Albacete', 'AB', '0'),
	(3, 'Alicante/Alacant', 'A', '0'),
	(4, 'Almería', 'AL', '0'),
	(1, 'Alava-Araba', 'VI', '1'),
	(33, 'Asturias', 'O', '0'),
	(5, 'Ávila', 'AV', '0'),
	(6, 'Badajoz', 'BA', '0'),
	(7, 'Balears, Illes', 'IB', '0'),
	(8, 'Barcelona', 'B', '0'),
	(48, 'Bizkaia', 'BI', '1'),
	(9, 'Burgos', 'BU', '0'),
	(10, 'Cáceres', 'CC', '0'),
	(11, 'Cádiz', 'CA', '0'),
	(39, 'Cantabria', 'S', '0'),
	(12, 'Castellón/Castelló', 'CS', '0'),
	(51, 'Ceuta', 'CE', '0'),
	(13, 'Ciudad Real', 'CR' , '0'),
	(14, 'Córdoba', 'CO', '0'),
	(15, 'Coruña, A', 'C', '0'),
	(16, 'Cuenca', 'CU', '0'),
	(20, 'Gipuzkoa', 'SS', '1'),
	(17, 'Girona', 'GI', '0'),
	(18, 'Granada', 'GR', '0'),
	(19, 'Guadalajara', 'GU', '0'),
	(21, 'Huelva', 'H', '0'),
	(22, 'Huesca', 'HU', '0'),
	(23, 'Jaén', 'J', '0'),
	(24, 'León', 'LE', '0'),
	(27, 'Lugo', 'LU', '0'),
	(25, 'Lleida', 'L', '0'),
	(28, 'Madrid', 'M', '0'),
	(29, 'Málaga', 'MA', '0'),
	(52, 'Melilla', 'ML', '0'),
	(30, 'Murcia', 'MU', '0'),
	(31, 'Navarra-Nafarroa', 'NA', '0'),
	(32, 'Ourense', 'OU', '0'),
	(34, 'Palencia', 'P', '0'),
	(35, 'Palmas, Las', 'GC', '0'),
	(36, 'Pontevedra', 'PO', '0'),
	(26, 'La Rioja', 'LO', '0'),
	(37, 'Salamanca', 'SA', '0'),
	(38, 'Santa Cruz de Tenerife', 'TF', '0'),
	(40, 'Segovia', 'SG', '0'),
	(41, 'Sevilla', 'SE', '0'),
	(42, 'Soria', 'SO', '0'),
	(43, 'Tarragona', 'T', '0'),
	(44, 'Teruel', 'TE', '0'),
	(45, 'Toledo', 'TO', '0'),
	(46, 'Valencia/València', 'V', '0'),
	(47, 'Valladolid', 'VA', '0'),
	(49, 'Zamora', 'ZA', '0'),
	(50, 'Zaragoza', 'Z', '0');
	
ALTER TABLE tfg.gimt_province
 ADD COLUMN id_cp_community int COMMENT 'Community Identifier' AFTER enabled,
 ADD KEY `id_cp_community_FK_idx` (`id_cp_community`),
 ADD CONSTRAINT `id_cp_community_FK` FOREIGN KEY (`id_cp_community`) REFERENCES `gimt_community` (`id_community`) ON DELETE NO ACTION ON UPDATE NO ACTION;

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Andalucía')
 WHERE province_name IN ('Almería','Cádiz','Córdoba','Granada','Huelva','Jaén','Málaga','Sevilla');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Aragón')
 WHERE province_name IN ('Teruel','Huesca','Zaragoza');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Asturias, Principado de')
 WHERE province_name IN ('Asturias');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Balears, Illes')
 WHERE province_name IN ('Balears, Illes');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Canarias')
 WHERE province_name IN ('Palmas, Las','Santa Cruz de Tenerife');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Cantabria')
 WHERE province_name IN ('Cantabria');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Castilla y León')
 WHERE province_name IN ('Ávila','Burgos','León','Salamanca','Segovia','Soria','Valladolid','Zamora','Palencia');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Castilla - La Mancha')
 WHERE province_name IN ('Albacete','Ciudad Real','Cuenca','Guadalajara','Toledo');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Catalunya')
 WHERE province_name IN ('Barcelona','Girona','Lleida','Tarragona');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Comunitat Valenciana')
 WHERE province_name IN ('Castellón/Castelló','Alicante/Alacant','Valencia/València');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Extremadura')
 WHERE province_name IN ('Cáceres','Badajoz');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Galicia')
 WHERE province_name IN ('Coruña, A','Lugo','Ourense','Pontevedra');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Madrid, Comunidad de')
 WHERE province_name IN ('Madrid');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Murcia, Región de')
 WHERE province_name IN ('Murcia');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Navarra, Comunidad Foral de')
 WHERE province_name IN ('Navarra-Nafarroa');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Euskadi')
 WHERE province_name IN ('Alava-Araba','Bizkaia','Gipuzkoa');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Rioja, La')
 WHERE province_name IN ('La Rioja');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Ceuta')
 WHERE province_name IN ('Ceuta');

UPDATE tfg.gimt_province SET id_cp_community = (select id_community from gimt_community where community_name = 'Melilla')
 WHERE province_name IN ('Melilla');

COMMIT;

ALTER TABLE tfg.gimt_province
 MODIFY COLUMN id_cp_community int NOT NULL;

ALTER TABLE tfg.gimt_province
 ADD COLUMN nuts3 varchar(5) COMMENT 'NUTS3 Area Code' AFTER id_cp_community;
 
UPDATE tfg.gimt_province SET nuts3 = 'ES211'
 WHERE province_name = 'Alava-Araba';

UPDATE tfg.gimt_province SET nuts3 = 'ES212'
 WHERE province_name = 'Gipuzkoa';

UPDATE tfg.gimt_province SET nuts3 = 'ES213'
 WHERE province_name = 'Bizkaia';

COMMIT;