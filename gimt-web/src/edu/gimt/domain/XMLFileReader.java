package edu.gimt.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.github.filosganga.geogson.gson.GeometryAdapterFactory;
import com.github.filosganga.geogson.jts.JtsAdapterFactory;
import com.github.filosganga.geogson.model.Feature;
import com.github.filosganga.geogson.model.FeatureCollection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import edu.gimt.common.Constants;
import edu.gimt.common.util.Utils;
import edu.gimt.model.TownAndGeometryBean;
import edu.gimt.persistence.CfgParam;
import edu.gimt.persistence.DownloadedFile;
import edu.gimt.persistence.GeoIncidence;
import edu.gimt.persistence.GeoIncidenceError;
import edu.gimt.persistence.Province;
import edu.gimt.persistence.Town;
import edu.gimt.validators.GeoIncidenceValidationItem;
import edu.gimt.validators.IValidator;
import edu.gimt.validators.TownLocationValidator;
import edu.gimt.validators.exception.ValidationException;
import edu.gimt.validators.factory.ValidatorFactory;

/**
 * Class to Read XML input file.
 * @author Victor M. Sobrino - TFG
 */
public class XMLFileReader {

	// Max values
	private int maxDistBetweenCoordinates = 0;
	private int maxMinBetweenDates = 0;
	
	/**
	 * Default constructor.
	 */
	public XMLFileReader() {
	}
	
	/**
	 * Perform incidences validation and save into gimt system.
	 * @param em Entity manager object.
	 * @return True if success, False otherwise.
	 */
	@SuppressWarnings("finally")
	public boolean validateAndSave(EntityManager em) {
		boolean done = true;
		String xmlFilePath = null;
		String jsonFilePath = null;
		String jsonFileName = null;
		ArrayList<GeoIncidenceValidationItem> geoList = null;
		ArrayList<GeoIncidenceValidationItem> geoValidList = null;
		ArrayList<GeoIncidenceValidationItem> geoErrorList = null;
		ArrayList<GeoIncidenceValidationItem> geoDuplicatedList = null;
		ValidatorFactory vFactory = ValidatorFactory.getInstance();
		List<IValidator> validators = null;
		// Lists for towns and provinces
		List<Province> provinceList = null;
		List<Town> townList = null;
		GeoIncidenceConversor conversor = null;
		ArrayList<GeoIncidence> geoIncidenceEntities = null;
		ArrayList<GeoIncidenceError> geoIncidenceErrorEntities = null;
		TypedQuery<DownloadedFile> downloadedFileQuery = null;
		List<DownloadedFile> downloadedFileList = null;
		Map<Integer, TownAndGeometryBean> townsMap = null;
		
		try {
			// Retrieve all towns and provinces
			TypedQuery<Province> provinceQuery = null;
			provinceQuery = em.createNamedQuery("Province.findAll", Province.class);
			provinceList = provinceQuery.getResultList();

			TypedQuery<Town> townQuery = null;
			townQuery = em.createNamedQuery("Town.findAll", Town.class);
			townList = townQuery.getResultList();
			
			// CfgParam for Json Towns Limits file
			TypedQuery<CfgParam> cfgQuery = null;
			List<CfgParam> cfgParams = null;
			cfgQuery = em.createNamedQuery("CfgParam.findByModuleAndParam", CfgParam.class);
			cfgQuery.setParameter("codModule", Constants.MODULE_INC_DOWNLOAD);
			cfgQuery.setParameter("codParam", Constants.GEO_FILE_PATH);
			cfgParams = cfgQuery.getResultList();
			for (CfgParam cp : cfgParams) {
				if (null != cp.getParamValue() ) {
					jsonFilePath = cp.getParamValue();
				}
			}
			cfgQuery = em.createNamedQuery("CfgParam.findByModuleAndParam", CfgParam.class);
			cfgQuery.setParameter("codModule", Constants.MODULE_INC_DOWNLOAD);
			cfgQuery.setParameter("codParam", Constants.GEO_FILE_NAME);
			cfgParams = cfgQuery.getResultList();
			for (CfgParam cp : cfgParams) {
				if (null != cp.getParamValue() ) {
					jsonFileName = cp.getParamValue();
				}
			}
			
			// Load town's geometry geoJson file
			List<Feature> featureList = readTownList(jsonFilePath, jsonFileName);
			
			// Retrieve province with zones and their coordinate polygons
			townsMap = setFeature2Town(em, townList, featureList);

			// CfgParam for file-path
			cfgQuery = em.createNamedQuery("CfgParam.findByModuleAndParam", CfgParam.class);
			cfgQuery.setParameter("codModule", Constants.MODULE_INC_DOWNLOAD);
			cfgQuery.setParameter("codParam", Constants.TMP_FILE_PATH);
			cfgParams = cfgQuery.getResultList();
			for (CfgParam cp : cfgParams) {
				if (null != cp.getParamValue() ) {
					xmlFilePath = cp.getParamValue();
				}
			}
			// CfgParam for MAX distance between coordinates
			cfgQuery = em.createNamedQuery("CfgParam.findByModuleAndParam", CfgParam.class);
			cfgQuery.setParameter("codModule", Constants.MODULE_INC_DOWNLOAD);
			cfgQuery.setParameter("codParam", Constants.MAX_DIST_BETWEEN_COORDINATES);
			cfgParams = cfgQuery.getResultList();
			for (CfgParam cp : cfgParams) {
				if (null != cp.getParamValue() ) {
					maxDistBetweenCoordinates = Integer.parseInt(cp.getParamValue());
				}
			}
			// CfgParam for MAX minutes between dates
			cfgQuery = em.createNamedQuery("CfgParam.findByModuleAndParam", CfgParam.class);
			cfgQuery.setParameter("codModule", Constants.MODULE_INC_DOWNLOAD);
			cfgQuery.setParameter("codParam", Constants.MAX_MINUTES_BETWEEN_DATES);
			cfgParams = cfgQuery.getResultList();
			for (CfgParam cp : cfgParams) {
				if (null != cp.getParamValue() ) {
					maxMinBetweenDates = Integer.parseInt(cp.getParamValue());
				}
			}
			
			// Not processed downloaded XML files
			downloadedFileQuery = em.createNamedQuery("DownloadedFile.findNotProcessed", DownloadedFile.class);
			downloadedFileList = downloadedFileQuery.getResultList();
			
			if (null != xmlFilePath) {
				
				if (downloadedFileList.size() > 0) {
					conversor = new GeoIncidenceConversor(em);
					
					for (DownloadedFile df : downloadedFileList) {
						
						// Lista de geo-incidencias a validar
						geoList = read(new File(xmlFilePath + "/" + df.getFileName()));
						geoValidList = new ArrayList<GeoIncidenceValidationItem>();
						geoErrorList = new ArrayList<GeoIncidenceValidationItem>();
						geoDuplicatedList = new ArrayList<GeoIncidenceValidationItem>();
						
						boolean validItem = true;
						long before = System.currentTimeMillis();
						for (GeoIncidenceValidationItem givItem : geoList) {
							validItem = true;
							// Lista de validaciones a pasar
							validators = vFactory.getValidators(em, XMLFileReader.class.getCanonicalName().toString() + ".doValidate", givItem);
							for (int i= 0; i < validators.size() && validItem; i++) {
								if (validators.get(i) instanceof TownLocationValidator) {
									((TownLocationValidator)validators.get(i)).setTownsMap(townsMap);
									((TownLocationValidator)validators.get(i)).setProvinceList(provinceList);
								}
								try {
									validators.get(i).validate(); // throw validation
								}
								catch (ValidationException ve) {
									givItem.setErrorCause(ve.getMessage());
									validItem = false;
								}
							}
							
							// Lista de items válidos e inválidos
							if (validItem) {
								if (geoValidList.isEmpty()) {
									geoValidList.add(givItem); // es el primer elemento añadido
								}
								else { // Comprobamos que el item no está repetido ya en la lista a insertar 
									if (geoValidList.get(geoValidList.size() - 1).equals(givItem)) {
										givItem.setErrorCause("Incidencia duplicada");
										geoDuplicatedList.add(givItem);
									}
									else {
										// Comparamos con el ultimo elemento insertado en la lista de validos, 
										// ya que esta ordenada por start_datetime (asc) y numero campos no nulos (desc)
										if (isSimilarItem(geoValidList.get(geoValidList.size() - 1), givItem)) {
											// Comprobamos si es una prueba deportiva,
											// ya que se marcan puntos cercanos en un mismo intervalo de fechas.
											if (null != givItem.getType() && givItem.getType().equals(Constants.TYPE_INC_SPORT_EVENTS )) { // Item valido
												geoValidList.add(givItem);
											}
											else {
												// Item duplicado
												givItem.setErrorCause("Descartada por similitud");
												geoDuplicatedList.add(givItem);
											}
										}
										else { // Item valido
											geoValidList.add(givItem);
										}
									}
								}
							}
							else {
								geoErrorList.add(givItem);
							}
						}
						
						long after = System.currentTimeMillis();
						System.out.println("tiempo: " + (after - before) / 1000 + " segundos.\n");
						
						System.out.println("\tGeoIncidencias OK: " + ((geoValidList != null)? geoValidList.size() : 0));
						System.out.println("\tGeoIncidencias KO: " + ((geoErrorList != null)? geoErrorList.size() : 0));
						System.out.println("\tGeoIncidencias Duplicadas: " + ((geoDuplicatedList != null)? geoDuplicatedList.size() : 0));
						System.out.println();

						em.getTransaction().begin();
						// Eliminamos las posibles incidencias existentes del mes tratado --> Quitamos esta eliminación de filas
						/*
						Query geoIncQuery = null;
						geoIncQuery = em.createNamedQuery("GeoIncidence.deleteByDate");
						geoIncQuery.setParameter("startDatetime", df.getInitialDate());
						geoIncQuery.setParameter("endDateTime", df.getFinalDate());
						int rowsAffected = geoIncQuery.executeUpdate();
						if (rowsAffected > 0) {
							System.out.println("\tIncidencias Correctas eliminadas entre " + df.getInitialDate() + " y " + df.getFinalDate() + " : " + rowsAffected);
						}
						Query geoIncErrorQuery = null;
						geoIncErrorQuery = em.createNamedQuery("GeoIncidenceError.deleteByDate");
						geoIncErrorQuery.setParameter("startDatetime", df.getInitialDate().toString());
						geoIncErrorQuery.setParameter("endDateTime", df.getFinalDate().toString());
						rowsAffected = geoIncErrorQuery.executeUpdate();
						if (rowsAffected > 0) {
							System.out.println("\tIncidencias ERROR eliminadas entre " + df.getInitialDate() + " y " + df.getFinalDate() + " : " + rowsAffected);
						}
						*/
						// Incidencias validas
						if (null != geoValidList && geoValidList.size() > 0) {
							geoIncidenceEntities = conversor.toEntity(geoValidList);
							for (GeoIncidence geoInc : geoIncidenceEntities) {
								// insertamos
								em.persist(geoInc);
							}
						}
						// Incidencias invalidas
						if (null != geoErrorList && geoErrorList.size() > 0) {
							geoIncidenceErrorEntities = conversor.toEntityError(geoErrorList);
							for (GeoIncidenceError geoIncErr : geoIncidenceErrorEntities) {
								em.persist(geoIncErr);
							}
						}
						// Incidencias duplicadas
						if (null != geoDuplicatedList && geoDuplicatedList.size() > 0) {
							geoIncidenceErrorEntities = conversor.toEntityError(geoDuplicatedList);
							for (GeoIncidenceError geoIncDup : geoIncidenceErrorEntities) {
								em.persist(geoIncDup);
							}
						}
						
						// Actualizamos el nombre del fichero XML como procesado
						df.setProcessed(1);
						df.setDownloadDatetime(Calendar.getInstance().getTime());
						em.persist(df);
						em.getTransaction().commit();
					}
				}
			}
		}
		catch (Exception e) {
			done = false;
			e.printStackTrace();
		}
		finally {
			return done;
		}
	}
	
	/**
	 * Set geographic zones to town List.
	 * @param em Entity Manager instance.
	 * @param townList Town list from persistence.
	 * @param Geometry town limits list.
	 * @return Map idTown, TownAndGeometryBean.
	 */
	private Map<Integer, TownAndGeometryBean> setFeature2Town(EntityManager em, List<Town> townList, List<Feature> featureList) {
		Map<Integer, TownAndGeometryBean> townsMap = null;
		final String PROP_NATCODE = "NATCODE";
		final int POS_NATCODE = 6;
		String natCode = null;
		
		if (!townList.isEmpty() && !featureList.isEmpty()) {
			townsMap = new HashMap<Integer, TownAndGeometryBean>();
			Map<String, JsonElement> propMap = null;
			
			for (Town town : townList) {
				for (Feature feature : featureList) {
					propMap = feature.properties();
					natCode = propMap.get(PROP_NATCODE).toString();
					natCode = natCode.replace("\"", "");
					Integer townCode = Integer.valueOf(natCode.substring(POS_NATCODE));
					if (townCode.intValue() == town.getIdTown().intValue()) {
						townsMap.put(town.getIdTown(), new TownAndGeometryBean(town, feature));
						break;
					}
				} // for each feature
			} // for each town
		}
		return townsMap;
	}

	/**
	 * Reads one XML file and returns a class list.
	 * @param xmlFile Input XML file.
	 * @return List of objects from XML file nodes. Sorted by start_datetime and not empty fields number.
	 */
	public ArrayList<GeoIncidenceValidationItem> read(File xmlFile) {
		DocumentBuilderFactory dbFactory = null;
		DocumentBuilder dBuilder = null;
		Document doc = null;
		NodeList nList = null;
		Node node = null;
		Element element = null;
		ArrayList<GeoIncidenceValidationItem> itemList = null;
		GeoIncidenceValidationItem item = null;
		
		try {
			if (null != xmlFile && xmlFile.exists() && xmlFile.isFile()) {
					
				dbFactory = DocumentBuilderFactory.newInstance();
				dBuilder = dbFactory.newDocumentBuilder();
				doc = dBuilder.parse(xmlFile);
				itemList = new ArrayList<GeoIncidenceValidationItem>();

				//optional, but recommended
				doc.getDocumentElement().normalize();
				//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

				nList = doc.getElementsByTagName("incidenciaGeolocalizada");
				System.out.println("#Tratando fichero: " + xmlFile.getName());
				System.out.println(" Número de nodos a tratar: " + nList.getLength());
				
				for (int temp = 0; temp < nList.getLength(); temp++) {
					node = nList.item(temp);
					item = new GeoIncidenceValidationItem();

					if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("incidenciaGeolocalizada")) {
						element = (Element)node;
						if (!element.getElementsByTagName("tipo").item(0).getTextContent().isEmpty()) {
							item.setType(element.getElementsByTagName("tipo").item(0).getTextContent());
						}
						
						if (!element.getElementsByTagName("autonomia").item(0).getTextContent().isEmpty()) {
							item.setCommunity(element.getElementsByTagName("autonomia").item(0).getTextContent());
						}
						
						if (!element.getElementsByTagName("provincia").item(0).getTextContent().isEmpty()) {
							item.setProvince(element.getElementsByTagName("provincia").item(0).getTextContent());
						}
						
						if (!element.getElementsByTagName("matricula").item(0).getTextContent().isEmpty()) {
							item.setPlate(element.getElementsByTagName("matricula").item(0).getTextContent());
						}
						
						if (!element.getElementsByTagName("causa").item(0).getTextContent().isEmpty()) {
							item.setCause(element.getElementsByTagName("causa").item(0).getTextContent());
						}
						
						if (!element.getElementsByTagName("poblacion").item(0).getTextContent().isEmpty()) {
							item.setTown(element.getElementsByTagName("poblacion").item(0).getTextContent());
						}
						if (!element.getElementsByTagName("fechahora_ini").item(0).getTextContent().isEmpty()) {
							item.setStartDatetime(element.getElementsByTagName("fechahora_ini").item(0).getTextContent());	
						}
						
						if (!element.getElementsByTagName("nivel").item(0).getTextContent().isEmpty()) {
							item.setLevel(element.getElementsByTagName("nivel").item(0).getTextContent());
						}
						
						if (!element.getElementsByTagName("carretera").item(0).getTextContent().isEmpty()) {
							item.setRoad(element.getElementsByTagName("carretera").item(0).getTextContent());
						}

						if (!element.getElementsByTagName("pk_inicial").item(0).getTextContent().isEmpty()) {
							item.setInitialKilometer(element.getElementsByTagName("pk_inicial").item(0).getTextContent());
						}
						
						if (!element.getElementsByTagName("pk_final").item(0).getTextContent().isEmpty()) {
							item.setFinalKilometer(element.getElementsByTagName("pk_final").item(0).getTextContent());
						}
						
						if (element.getElementsByTagName("nombre").getLength() > 0) {
							if (!element.getElementsByTagName("nombre").item(0).getTextContent().isEmpty()) {
								item.setName(element.getElementsByTagName("nombre").item(0).getTextContent());
							}
						}
						
						if (!element.getElementsByTagName("sentido").item(0).getTextContent().isEmpty()) {
							item.setSense(element.getElementsByTagName("sentido").item(0).getTextContent());
						}
						
						// Validate longitude range and zero value
						if (!element.getElementsByTagName("longitud").item(0).getTextContent().isEmpty()) {
							item.setLongitude(element.getElementsByTagName("longitud").item(0).getTextContent());
						}
						
						// Validate latitude range and zero value
						if (!element.getElementsByTagName("latitud").item(0).getTextContent().isEmpty()) {
							item.setLatitude(element.getElementsByTagName("latitud").item(0).getTextContent());
						}
						itemList.add(item);
					} // for every node type
				} // for every nodes
				// Sorting items
				Collections.sort(itemList);
			}
			else {
				System.out.println("No se ha informado la ruta del fichero XML a procesar..");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return itemList;
	}
	
	/**
	 * Comprueba si el item pasado es similar al anterior (coordenadas, comunidad y fecha de la incidencia).
	 * @param lastAddedItem Ultimo item insertado en la lista. Se tiene en cuenta que la lista esta ordenada.
	 * @param item Item a comparar con el anterior.
	 * @return True si el item es similar al ultimo insertado, False en otro caso.
	 */
	private boolean isSimilarItem(GeoIncidenceValidationItem lastAddedItem, GeoIncidenceValidationItem item) {

		boolean similar = false;
		
		
		try { // Comprobamos si la incidencia es identica
			if ((lastAddedItem.getCommunity().equalsIgnoreCase(item.getCommunity())) &&
				(Utils.minutesBetween(lastAddedItem.getStartDatetime(),
									  item.getStartDatetime()) <= maxMinBetweenDates) &&
				(Utils.distanceBetween(lastAddedItem.getLatitude(),
									   lastAddedItem.getLongitude(),
						               item.getLatitude(),
						               item.getLongitude()) <= maxDistBetweenCoordinates)) {
				similar = true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return similar;
	}
	
	private List<Feature> readTownList(String jsonFilePath, String jsonFileName) {
		List<Feature> featureList = null;
		
		try {
//			GeometryFactory gf = new GeometryFactory();
			Gson gson = new GsonBuilder()
			   .registerTypeAdapterFactory(new JtsAdapterFactory())
			   .registerTypeAdapterFactory(new GeometryAdapterFactory())
			   .create();
			File fileDir = new File(jsonFilePath + "/" + jsonFileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
			FeatureCollection collection = gson.fromJson(br, FeatureCollection.class);
			featureList = collection.features();
			
//			Point point = gf.createPoint(new Coordinate(-1.998995, 43.321636));
//			Map<String, JsonElement> propMap = null;
//			for (Feature feature : featureList) {
//				if (feature.geometry() instanceof com.github.filosganga.geogson.model.Polygon) {
//					PolygonCodec pCodec = new PolygonCodec(gf);
//					Polygon polygon = pCodec.fromGeometry((com.github.filosganga.geogson.model.Polygon)feature.geometry());
//					if (polygon.contains(point)) {
//						propMap = feature.properties();
//						System.out.println(propMap.get("NATCODE"));
//						System.out.println(propMap.get("NAMEUNIT"));
//						break;
//					}
//				}
//				if (feature.geometry() instanceof com.github.filosganga.geogson.model.MultiPolygon) {
//					MultiPolygonCodec mpCodec = new MultiPolygonCodec(gf);
//					MultiPolygon multiPolygon = mpCodec.fromGeometry((com.github.filosganga.geogson.model.MultiPolygon)feature.geometry());
//					if (multiPolygon.contains(point)) {
//						propMap = feature.properties();
//						System.out.println(propMap.get("NATCODE"));
//						System.out.println(propMap.get("NAMEUNIT"));
//						break;
//					}
//				}
//			}

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return featureList;
	}
	

}