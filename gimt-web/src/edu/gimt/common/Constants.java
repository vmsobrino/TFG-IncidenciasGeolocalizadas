/**
 * 
 */
package edu.gimt.common;

/**
 * Constantes globales a toda la aplicación.
 * @author Victor M. Sobrino - TFG
 */
public final class Constants {

	/**
	 * No instanciable, patrón singleton.
	 */
	private Constants() {
	}

	// Distintos módulos de la aplicación
	// INC_DOWNLOAD >> Descarga de Incidencias desde OpenData Euskadi
	public static final String MODULE_INC_DOWNLOAD = "INC_DOWNLOAD";
	public static final String MODULE_ZONE_DIAGNOSTIC = "ZONE_DIAGNOSTIC";

	// Distintos nombres de parámetro aplicables a cada módulo
	public static final String PUBLIC_INC_URL = "PUBLIC_INC_URL";
	public static final String INC_INI_DATE_PARAM = "INC_INI_DATE_PARAM";
	public static final String INC_END_DATE_PARAM = "INC_END_DATE_PARAM";
	public static final String TMP_FILE_PATH = "TMP_FILE_PATH";
	public static final String INC_FIRST_DATE = "INC_FIRST_DATE";
	public static final String INC_LAST_DATE = "INC_LAST_DATE";
	public static final String RANGE_BETWEEN_DATES = "RANGE_BETWEEN_DATES";
	// Distancia maxima entre 2 coordenadas para considerarlas similares o iguales (en metros)
	public static final String MAX_DIST_BETWEEN_COORDINATES = "MAX_DIST_BETWEEN_COORDINATES";
	// Diferencia maxima en minutos entre 2 fecha+hora
	public static final String MAX_MINUTES_BETWEEN_DATES = "MAX_MINUTES_BETWEEN_DATES";

	// Distintos tipos de incidencias
	public static final String TYPE_INC_METEOROLOGY = "Meteorológica";
	public static final String TYPE_INC_ACCIDENT = "Accidente";
	public static final String TYPE_INC_RETENTION = "Retención";
	public static final String TYPE_INC_ROAD_SAFETY = "Seguridad Vial";
	public static final String TYPE_INC_MOUNTAIN_PORT = "Puertos de montaña";
	public static final String TYPE_INC_WINTER_ROAD_SECTIONS = "Vialidad invernal tramos";
	public static final String TYPE_INC_WORK = "Obra";
	public static final String TYPE_INC_WORKS = "Obras";
	public static final String TYPE_INC_OTHERS = "Otras incidencias";
	public static final String TYPE_INC_SPORT_EVENTS = "Pruebas deportivas";

	// Salto de carro
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	// GeoJson Constants
	public static final String GEO_FILE_PATH = "GEO_FILE_PATH";
	public static final String GEO_FILE_NAME = "GEO_FILE_NAME";
	public static final String NAME_GEOMETRIES = "geometries";
	public static final String NAME_CRS = "crs";
	public static final String NAME_PROPERTIES = "properties";
	public static final String NAME_NAME = "name";
	public static final String NAME_TYPE = "type";
	public static final String NAME_POINT = "Point";
	public static final String NAME_LINESTRING = "LineString";
	public static final String NAME_POLYGON = "Polygon";
	public static final String NAME_COORDINATES = "coordinates";
	public static final String NAME_GEOMETRYCOLLECTION = "GeometryCollection";
	public static final String NAME_MULTIPOLYGON = "MultiPolygon";
	public static final String NAME_MULTILINESTRING = "MultiLineString";
	public static final String NAME_MULTIPOINT = "MultiPoint";

	// Inc Count File Download
	public static final String COUNT_FILE_PATH = "COUNT_FILE_PATH";
	public static final String COUNT_FILE_NAME = "COUNT_FILE_NAME";
	

}
