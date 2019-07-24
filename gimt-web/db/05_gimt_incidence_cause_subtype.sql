# ------------------------------------------------------------
# Subtipos de Causas de las incidencias Meteorológicas.
#
# Victor M. Sobrino
# TFG: ESI CR - UCLM
# ------------------------------------------------------------

CREATE TABLE `tfg`.`gimt_incidence_cause_subtype` (
  `id_cause_subtype` int NOT NULL AUTO_INCREMENT COMMENT 'Cause Subtype Identifier',
  `cause_subtype_name` varchar(50) NOT NULL COMMENT 'Weather Subtype Cause Name',
  `id_st_cause_type` int NOT NULL COMMENT 'Cause Type',
  PRIMARY KEY (`id_cause_subtype`),
  UNIQUE KEY `id_cause_subtype_UNIQUE` (`id_cause_subtype`),
  KEY `id_st_cause_type_FK_idx` (`id_st_cause_type`),
  CONSTRAINT `id_st_cause_type_FK` FOREIGN KEY (`id_st_cause_type`) REFERENCES `gimt_incidence_cause_type` (`id_cause_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Distinct Incidence Cause SubTypes';


INSERT INTO `tfg`.`gimt_incidence_cause_subtype` (`cause_subtype_name`, `id_st_cause_type`)
VALUES
	('Agua', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Meteorológica')),
	('Viento', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Meteorológica')),
	('Nieve / Hielo', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Meteorológica')),
	('Niebla', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Meteorológica')),
	
	('Alcance', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Accidente')),
	('Atropello', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Accidente')),
	('Salida', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Accidente')),
	('Tijera camión', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Accidente')),
	('Vuelco', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Accidente')),
	
	('Fiestas', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Retención')),
	('Prueba deportiva', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Retención')),
	
	('Aceite', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Seguridad Vial')),
	('Avería', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Seguridad Vial')),
	('Caída de objetos', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Seguridad Vial')),
	('Desprendimiento', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Seguridad Vial')),
	('Gasoil', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Seguridad Vial')),
	('Incendio', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Seguridad Vial')),
	('Socavón', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Seguridad Vial')),
	
	('Agua nieve', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Puertos de montaña')),
	('Hielo', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Puertos de montaña')),
	('Nevando', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Puertos de montaña')),
	('Niebla', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Puertos de montaña')),
	('Nieve', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Puertos de montaña')),
	('Nieve / Hielo', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Puertos de montaña')),
	('Desconocida', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Puertos de montaña')),
	('Obras', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Puertos de montaña')),
	('Otros', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Puertos de montaña')),
	
	('Agua nieve', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Vialidad invernal tramos')),
	('Hielo', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Vialidad invernal tramos')),
	('Nevando', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Vialidad invernal tramos')),
	('Niebla', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Vialidad invernal tramos')),
	('Nieve', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Vialidad invernal tramos')),
	('Nieve / Hielo', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Vialidad invernal tramos')),
	('Desconocida', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Vialidad invernal tramos')),
	('Obras', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Vialidad invernal tramos')),
	('Otros', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Vialidad invernal tramos')),

	('Obras', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Obras')),
	('Otra actividad', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Obras')),
	
	('Obras', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Otras incidencias')),
	('Otra actividad', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Otras incidencias')),
	('Otros', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Otras incidencias')),
	
	('Automovilismo', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Pruebas deportivas')),
	('Ciclismo', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Pruebas deportivas')),
	('Ciclocross', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Pruebas deportivas')),
	('Cross', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Pruebas deportivas')),
	('Maratón', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Pruebas deportivas')),
	('Biathlón', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Pruebas deportivas')),
	('Triatlón', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Pruebas deportivas')),
	('Pentatlón', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Pruebas deportivas')),
	('Motociclismo', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Pruebas deportivas')),
	('MotoCross', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Pruebas deportivas')),
	('Marcha ciclista', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Pruebas deportivas')),
	('Mixta', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Pruebas deportivas')),
	('Atletismo', (select id_cause_type from gimt_incidence_cause_type where cause_type_name = 'Pruebas deportivas'))
;

/*
1	Agua	1
2	Viento	1
3	Nieve / Hielo	1
4	Niebla	1
5	Alcance	2
6	Atropello	2
7	Salida	2
8	Tijera camión	2
9	Vuelco	2
10	Fiestas	3
11	Prueba deportiva	3
12	Aceite	4
13	Avería	4
14	Caída de objetos	4
15	Desprendimiento	4
16	Gasoil	4
17	Incendio	4
18	Socavón	4
19	Agua nieve	5
20	Hielo	5
21	Nevando	5
22	Niebla	5
23	Nieve	5
24	Nieve / Hielo	5
25	Desconocida	5
26	Obras	5
27	Otros	5
28	Agua nieve	6
29	Hielo	6
30	Nevando	6
31	Niebla	6
32	Nieve	6
33	Nieve / Hielo	6
34	Desconocida	6
35	Obras	6
36	Otros	6
37	Obra	7
38	Otra actividad	7
39	Obra	8
40	Obras	8
41	Otra actividad	8
42	Otros	8
43	Automovilismo	9
44	Ciclismo	9
45	Ciclocross	9
46	Cross	9
47	Maratón	9
48	Biathlón	9
49	Triatlón	9
50	Pentatlón	9
51	Motociclismo	9
52	MotoCross	9
53	Marcha ciclista	9
54	Mixta	9
55	Atletismo	9
56	Obras	7
*/