# ------------------------------------------------------------
# Partidos Judiciales de Euskadi
#
# Ley 1/1990, de 6 de abril
# BOPV núm. 94 de 14 de Mayo de 1990 y BOE núm. 51 de 29 de Febrero de 2012
#
# Victor M. Sobrino
# TFG: ESI CR - UCLM
# ------------------------------------------------------------

CREATE TABLE tfg.gimt_judicial_district (
  id_jd int NOT NULL COMMENT 'Judicial District Identifier',
  id_jd_province int NOT NULL COMMENT 'Province of belonging',
  jd_name varchar(100) NOT NULL DEFAULT '' COMMENT 'Judicial District name',
  PRIMARY KEY (id_jd),
  UNIQUE KEY id_jd_UNIQUE (id_jd),
  KEY id_jd_province_FK_idx (id_jd_province),
  CONSTRAINT id_jd_province_FK FOREIGN KEY (id_jd_province) REFERENCES gimt_province (id_province) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Judicial Districts in Euskadi';

INSERT INTO tfg.gimt_judicial_district (id_jd, id_jd_province, jd_name)
VALUES
	(11,(SELECT id_province FROM tfg.gimt_province WHERE province_name = 'Alava-Araba'),'Alava-Araba Nº 1. Partido Judicial de Amurrio'),
	(12,(SELECT id_province FROM tfg.gimt_province WHERE province_name = 'Alava-Araba'),'Alava-Araba Nº 2. Partido Judicial de Vitoria-Gasteiz'),
	
	(201,(SELECT id_province FROM tfg.gimt_province WHERE province_name = 'Gipuzkoa'),'Gipuzkoa Nº 1. Partido Judicial de Tolosa'),
	(202,(SELECT id_province FROM tfg.gimt_province WHERE province_name = 'Gipuzkoa'),'Gipuzkoa Nº 2. Partido Judicial de Azpeitia'),
	(203,(SELECT id_province FROM tfg.gimt_province WHERE province_name = 'Gipuzkoa'),'Gipuzkoa Nº 3. Partido Judicial de Bergara'),
	(204,(SELECT id_province FROM tfg.gimt_province WHERE province_name = 'Gipuzkoa'),'Gipuzkoa Nº 4. Partido Judicial de Eibar'),
	(205,(SELECT id_province FROM tfg.gimt_province WHERE province_name = 'Gipuzkoa'),'Gipuzkoa Nº 5. Partido Judicial de Donostia-San Sebastián'),
	(206,(SELECT id_province FROM tfg.gimt_province WHERE province_name = 'Gipuzkoa'),'Gipuzkoa Nº 6. Partido Judicial de Irun'),

	(481,(SELECT id_province FROM tfg.gimt_province WHERE province_name = 'Bizkaia'),'Bizkaia Nº 1. Partido Judicial de Durango'),
	(482,(SELECT id_province FROM tfg.gimt_province WHERE province_name = 'Bizkaia'),'Bizkaia Nº 2. Partido Judicial de Barakaldo'),
	(483,(SELECT id_province FROM tfg.gimt_province WHERE province_name = 'Bizkaia'),'Bizkaia Nº 3. Partido Judicial de Gernika-Lumo'),
	(484,(SELECT id_province FROM tfg.gimt_province WHERE province_name = 'Bizkaia'),'Bizkaia Nº 4. Partido Judicial de Bilbao'),
	(485,(SELECT id_province FROM tfg.gimt_province WHERE province_name = 'Bizkaia'),'Bizkaia Nº 5. Partido Judicial de Balmaseda'),
	(486,(SELECT id_province FROM tfg.gimt_province WHERE province_name = 'Bizkaia'),'Bizkaia Nº 6. Partido Judicial de Getxo');
commit;