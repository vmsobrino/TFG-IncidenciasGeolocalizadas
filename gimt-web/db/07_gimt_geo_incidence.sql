# ------------------------------------------------------------
# Incidencias Geolocalizadas.
#
# Victor M. Sobrino
# TFG: ESI CR - UCLM
# ------------------------------------------------------------

CREATE TABLE tfg.gimt_geo_incidence (
  id_geo_incidence bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'Geo Incidence Identifier',
  update_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Insert or Update timestamp',
  id_geo_community int NOT NULL COMMENT 'Geo Incidence Community Identifier',
  id_geo_province int DEFAULT NULL COMMENT 'Geo Incidence Province Identifier',
  nuts3_code varchar(5) NOT NULL COMMENT 'NUTS3 Area Code',
  id_geo_cause_type int DEFAULT NULL COMMENT 'Incidence Cause Type Identifier',
  id_geo_cause_subtype int DEFAULT NULL COMMENT 'Incidence Cause SubType Identifier',
  id_geo_town int NOT NULL COMMENT 'Town Identifier',
  town_name varchar(100) DEFAULT NULL COMMENT 'Town of incidence',
  id_geo_jd int NOT NULL COMMENT 'Judicial District Identifier',
  start_datetime datetime NOT NULL COMMENT 'Incidence Start DateTime',
  end_datetime datetime DEFAULT NULL COMMENT 'Incidence End DateTime',
  id_geo_road_level int DEFAULT NULL COMMENT 'Road Incidences Level Identifier',
  mountain_port_level varchar(50) DEFAULT NULL COMMENT 'Mountain Port Incidences Level Description',
  road_code varchar(25) DEFAULT NULL COMMENT 'Road Code Name',
  initial_kilometer float DEFAULT NULL COMMENT 'Incidence Initial Kilometer Point',
  final_kilometer float DEFAULT NULL COMMENT 'Incidence Final Kilometer Point',
  sense varchar(100) DEFAULT NULL COMMENT 'Incidence Sense in Road',
  name varchar(100) DEFAULT NULL COMMENT 'Incidence Occurrence Name',
  longitude DECIMAL(11,8) NOT NULL COMMENT 'Incidence GPS Longitude',
  latitude DECIMAL(10,8) NOT NULL COMMENT 'Incidence GPS Latitude',
  month_of_year varchar(25) GENERATED ALWAYS AS (monthname(start_datetime)) STORED COMMENT 'Incidence Occurrence Month Name',
  day_of_week varchar(25) GENERATED ALWAYS AS (dayname(start_datetime)) STORED COMMENT 'Incidence Occurrence Day Name',
  hour_of_day int GENERATED ALWAYS AS (hour(start_datetime)) STORED COMMENT 'Incidence Occurrence Hour Of Day',
  num_day_of_week int GENERATED ALWAYS AS (dayofweek(start_datetime)) STORED COMMENT 'Incidence Occurrence Day Number of Week',
  num_month_of_year int GENERATED ALWAYS AS (month(start_datetime)) STORED COMMENT 'Incidence Occurrence Month Number Of Year',
  year_of_incidence int GENERATED ALWAYS AS (year(start_datetime)) STORED COMMENT 'Incidence Occurrence Year',
  PRIMARY KEY (id_geo_incidence),
  UNIQUE KEY id_geo_incidence_UNIQUE (id_geo_incidence),
  KEY id_geo_community_FK_idx (id_geo_community),
  KEY id_geo_province_FK_idx (id_geo_province),
  KEY id_geo_town_FK_idx (id_geo_town),
  KEY id_geo_cause_type_FK_idx (id_geo_cause_type),
  KEY id_geo_cause_subtype_FK_idx (id_geo_cause_subtype),
  KEY id_geo_road_level_FK_idx (id_geo_road_level),
  KEY comm_dttime_long_lat_idx (id_geo_community,start_datetime,longitude,latitude) COMMENT 'Index to fast search for community, incidence datetime, longitude and latitude.',
  CONSTRAINT id_geo_cause_subtype_FK FOREIGN KEY (id_geo_cause_subtype) REFERENCES gimt_incidence_cause_subtype (id_cause_subtype) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT id_geo_cause_type_FK FOREIGN KEY (id_geo_cause_type) REFERENCES gimt_incidence_cause_type (id_cause_type) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT id_geo_community_FK FOREIGN KEY (id_geo_community) REFERENCES gimt_community (id_community) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT id_geo_province_FK FOREIGN KEY (id_geo_province) REFERENCES gimt_province (id_province) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT id_geo_town_FK FOREIGN KEY (id_geo_town) REFERENCES gimt_town (id_town) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT id_geo_jd_FK FOREIGN KEY (id_geo_jd) REFERENCES gimt_judicial_district (id_jd) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT id_geo_road_level_FK FOREIGN KEY (id_geo_road_level) REFERENCES gimt_road_level (id_road_level) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Geo Incidences Collection';

ALTER TABLE tfg.gimt_geo_incidence 
ADD INDEX year_jd_subtype_idx (year_of_incidence ASC, id_geo_jd ASC, id_geo_cause_subtype ASC) VISIBLE;
;

ALTER TABLE tfg.gimt_geo_incidence 
ADD INDEX year_jd_type_month_idx (year_of_incidence ASC, id_geo_jd ASC, id_geo_cause_type ASC, num_month_of_year ASC) VISIBLE;
;

ALTER TABLE tfg.gimt_geo_incidence 
ADD INDEX year_jd_type_level_idx (year_of_incidence ASC, id_geo_jd ASC, id_geo_cause_type ASC, id_geo_road_level ASC) VISIBLE;
;

ALTER TABLE tfg.gimt_geo_incidence 
ADD INDEX year_jd_type_hour_idx (year_of_incidence ASC, id_geo_jd ASC, id_geo_cause_type ASC, hour_of_day ASC) VISIBLE;
;
