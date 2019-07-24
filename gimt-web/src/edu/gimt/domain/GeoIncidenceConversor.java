package edu.gimt.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.gimt.common.Constants;
import edu.gimt.common.util.Utils;
import edu.gimt.persistence.Community;
import edu.gimt.persistence.GeoIncidence;
import edu.gimt.persistence.GeoIncidenceError;
import edu.gimt.persistence.IncidenceCauseSubtype;
import edu.gimt.persistence.IncidenceCauseType;
import edu.gimt.persistence.Province;
import edu.gimt.persistence.RoadLevel;
import edu.gimt.persistence.Town;
//import edu.gimt.model.Town;
import edu.gimt.validators.GeoIncidenceValidationItem;

/**
 * Class to cast GeoIncidence items.
 * @author Victor M. Sobrino - TFG
 */
public class GeoIncidenceConversor {
	
	EntityManager em = null;
	
	public GeoIncidenceConversor(EntityManager em) {
		this.em = em;
		loadCache();
	}

	private List<IncidenceCauseType> typeList = null;
	private List<IncidenceCauseSubtype> subtypeList = null;
	private List<Community> communityList = null;
	private List<Province> provinceList = null;
	private List<Town> townList = null;
	private List<RoadLevel> roadLevelList = null;
	//private List<MountainPortLevel> mountainPortLevelList = null;
	

	
	private void loadCache() {
		TypedQuery<IncidenceCauseType> typeQuery = null;
		TypedQuery<IncidenceCauseSubtype> subtypeQuery = null;
		TypedQuery<Community> communityQuery = null;
		TypedQuery<Province> provinceQuery = null;
		TypedQuery<Town> townQuery = null;
		TypedQuery<RoadLevel> roadLevelQuery = null;
		//TypedQuery<MountainPortLevel> mountainPortLevelQuery = null;
		
		typeQuery = em.createNamedQuery("IncidenceCauseType.findAll", IncidenceCauseType.class);
		typeList = typeQuery.getResultList();

		subtypeQuery = em.createNamedQuery("IncidenceCauseSubtype.findAll", IncidenceCauseSubtype.class);
		subtypeList = subtypeQuery.getResultList();

		communityQuery = em.createNamedQuery("Community.findAll", Community.class);
		communityList = communityQuery.getResultList();

		provinceQuery = em.createNamedQuery("Province.findAll", Province.class);
		provinceList = provinceQuery.getResultList();

		townQuery = em.createNamedQuery("Town.findAll", Town.class);
		townList = townQuery.getResultList();
		
		roadLevelQuery = em.createNamedQuery("RoadLevel.findAll", RoadLevel.class);
		roadLevelList = roadLevelQuery.getResultList();
		
		//mountainPortLevelQuery = em.createNamedQuery("RoadLevel.findAll", MountainPortLevel.class);
		//mountainPortLevelList = mountainPortLevelQuery.getResultList();
		
//		em.close();
	}
	
	/**
	 * Cast geo-incidence item list to geo-incidence entity DB list.
	 * @param geoIncidenceItems Input item list.
	 * @return List of entity objects.
	 */
	public ArrayList<GeoIncidence> toEntity(ArrayList<GeoIncidenceValidationItem> geoIncidenceItems) {
		
		ArrayList<GeoIncidence> geoIncidenceEntities = null;
		GeoIncidence geoIncidenceEntity = null;
		
		try {
			if (null != geoIncidenceItems && geoIncidenceItems.size() > 0) {
				geoIncidenceEntities = new ArrayList<GeoIncidence>(geoIncidenceItems.size());
				for (GeoIncidenceValidationItem geoIncidenceItem : geoIncidenceItems) {
					geoIncidenceEntity = new GeoIncidence();
					// Tipo
					Integer idGeoCauseType = getIdCauseType(geoIncidenceItem.getType());
					geoIncidenceEntity.setIdGeoCauseType(idGeoCauseType);
					// Autonomia
					Integer idGeoCommunity = getIdCommunity(geoIncidenceItem.getCommunity());
					geoIncidenceEntity.setIdGeoCommunity(idGeoCommunity);
					// Provincia
					Integer idGeoProvince = getIdProvince(geoIncidenceItem.getProvince());
					geoIncidenceEntity.setIdGeoProvince(idGeoProvince);
					// Subtipo (causa)
					Integer idGeoCauseSubtype = getIdCauseSubtype(idGeoCauseType,
																  geoIncidenceItem.getCause());
					geoIncidenceEntity.setIdGeoCauseSubtype(idGeoCauseSubtype);
					// Id Poblacion
					if (null != geoIncidenceItem.getTown()) {
						geoIncidenceEntity.setIdTown(getIdTown(geoIncidenceItem.getTown()));
					}
					// Poblacion
					if (null != geoIncidenceItem.getTown()) {
						geoIncidenceEntity.setTownName(geoIncidenceItem.getTown());
					}
//					Integer idGeoTown = getIdTown(idGeoProvince, geoIncidenceItem.getTown());
//					geoIncidenceEntity.setIdGeoTown(idGeoTown);
					// Fecha-hora inicio/fin
					String[] buffer = null;
					buffer = geoIncidenceItem.getStartDatetime().split(" - ");
					if (buffer.length == 1) { // Date-Time
						geoIncidenceEntity.setStartDatetime(Utils.getDateTime(geoIncidenceItem.getStartDatetime()));
					}
					else { // Date - Date
						geoIncidenceEntity.setStartDatetime(Utils.getDate(buffer[0]));
						geoIncidenceEntity.setEndDatetime(Utils.getDate(buffer[1]));
					}
					// Nivel (Puerto de montaña)
					if (geoIncidenceItem.getType().equalsIgnoreCase(Constants.TYPE_INC_MOUNTAIN_PORT)) {
						geoIncidenceEntity.setMountainPortLevel(geoIncidenceItem.getLevel());
					}
					else {
						// Nivel (carretera)
						Integer idGeoRoadLevel = getIdLevel(geoIncidenceItem.getType(),
															geoIncidenceItem.getLevel());
						geoIncidenceEntity.setIdGeoRoadLevel(idGeoRoadLevel);
					}
					// Carretera
					if (null != geoIncidenceItem.getRoad()) {
						geoIncidenceEntity.setRoadCode(geoIncidenceItem.getRoad());
					}
					// Km Inicial
					if (null != geoIncidenceItem.getInitialKilometer()) {
						geoIncidenceEntity.setInitialKilometer(Float.valueOf(geoIncidenceItem.getInitialKilometer()));
					}
					// Km Final
					if (null != geoIncidenceItem.getFinalKilometer()) {
						geoIncidenceEntity.setFinalKilometer(Float.valueOf(geoIncidenceItem.getFinalKilometer()));
					}
					// Sentido
					if (null != geoIncidenceItem.getSense()) {
						geoIncidenceEntity.setSense(geoIncidenceItem.getSense());
					}
					// Nombre
					if (null != geoIncidenceItem.getName()) {
						geoIncidenceEntity.setName(geoIncidenceItem.getName());
					}
					// Longitud
					if (null != geoIncidenceItem.getLongitude()) {
						geoIncidenceEntity.setLongitude(new BigDecimal(geoIncidenceItem.getLongitude()));
					}
					// Latitud
					if (null != geoIncidenceItem.getLatitude()) {
						geoIncidenceEntity.setLatitude(new BigDecimal(geoIncidenceItem.getLatitude()));
					}
					// Nuts3
					if (null != geoIncidenceItem.getNuts3()) {
						geoIncidenceEntity.setNuts3(geoIncidenceItem.getNuts3());
					}
					// Judicial district
					if (null != geoIncidenceItem.getJudicialDistrict()) {
						geoIncidenceEntity.setIdJd(Integer.valueOf(geoIncidenceItem.getJudicialDistrict()));
					}
					
					// Adding
					geoIncidenceEntities.add(geoIncidenceEntity);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return geoIncidenceEntities;

	}

	
	/**
	 * Cast geo-incidence item list to geo-incidence error entity DB list.
	 * @param geoIncidenceItems Input item list.
	 * @return List of error entity objects.
	 */
	public ArrayList<GeoIncidenceError> toEntityError(ArrayList<GeoIncidenceValidationItem> geoIncidenceItems) {
		
		ArrayList<GeoIncidenceError> geoIncidenceErrorEntities = null;
		GeoIncidenceError geoIncidenceErrorEntity = null;
		
		try {
			if (null != geoIncidenceItems && geoIncidenceItems.size() > 0) {
				geoIncidenceErrorEntities = new ArrayList<GeoIncidenceError>(geoIncidenceItems.size());
				for (GeoIncidenceValidationItem geoIncidenceItem : geoIncidenceItems) {
					geoIncidenceErrorEntity = new GeoIncidenceError();
					geoIncidenceErrorEntity.setIncidenceType(geoIncidenceItem.getType());
					geoIncidenceErrorEntity.setCommunity(geoIncidenceItem.getCommunity());
					geoIncidenceErrorEntity.setProvince(geoIncidenceItem.getProvince());
					geoIncidenceErrorEntity.setPlate(geoIncidenceItem.getPlate());
					geoIncidenceErrorEntity.setCause(geoIncidenceItem.getCause());
					geoIncidenceErrorEntity.setTown(geoIncidenceItem.getTown());
					geoIncidenceErrorEntity.setStartDatetime(geoIncidenceItem.getStartDatetime());
					geoIncidenceErrorEntity.setIncidenceLevel(geoIncidenceItem.getLevel());
					geoIncidenceErrorEntity.setRoad(geoIncidenceItem.getRoad());
					geoIncidenceErrorEntity.setInitialKilometer(geoIncidenceItem.getInitialKilometer());
					geoIncidenceErrorEntity.setFinalKilometer(geoIncidenceItem.getFinalKilometer());
					geoIncidenceErrorEntity.setSense(geoIncidenceItem.getSense());
					geoIncidenceErrorEntity.setName(geoIncidenceItem.getName());
					geoIncidenceErrorEntity.setLatitude(geoIncidenceItem.getLatitude());
					geoIncidenceErrorEntity.setLongitude(geoIncidenceItem.getLongitude());
					geoIncidenceErrorEntity.setErrorCause(geoIncidenceItem.getErrorCause());
					// Adding
					geoIncidenceErrorEntities.add(geoIncidenceErrorEntity);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return geoIncidenceErrorEntities;
	}

	private Integer getIdLevel(String type, String level) {
		Integer idLevel = null;
		if (null != level && null != type) { // Solo carreteras, no puertos montaña
			if (!type.equalsIgnoreCase(Constants.TYPE_INC_MOUNTAIN_PORT)) {
				for (RoadLevel rl : roadLevelList) {
					if (rl.getLevelColour().equalsIgnoreCase(level)) {
						idLevel = rl.getIdRoadLevel();
						break;
					}
				}
			}
		}
		return idLevel;
	}

	private Integer getIdTown(String town) {
		Integer idTown = null;
		if (null != town) {
			for (Town t : townList) {
				if (t.getTownName().equalsIgnoreCase(town)) {
					idTown = t.getIdTown();
					break;
				}
			}
		}
		return idTown;
	}

	private Integer getIdCauseSubtype(Integer type, String cause) {
		Integer idCauseSubtype = null;
		if (null != type && null != cause) {
			// Hay algunas incidencias con causa 'Obra' en lugar de 'Obras', unificamos:
			if (cause.equalsIgnoreCase(Constants.TYPE_INC_WORK)) {
				cause = Constants.TYPE_INC_WORKS;
			}
			for (IncidenceCauseSubtype st : subtypeList) {
				if (st.getCauseSubtypeName().equalsIgnoreCase(cause) &&
					st.getIdStCauseType() == type) {
					idCauseSubtype = st.getIdCauseSubtype();
					break;
				}
			}
		}
		return idCauseSubtype;
	}

	private Integer getIdProvince(String province) {
		Integer idGeoProvince = null;
		if (null != province) {
			for (Province p : provinceList) {
				if (p.getProvinceName().toLowerCase().indexOf(province.toLowerCase()) >= 0) {
					idGeoProvince = p.getIdProvince();
					break;
				}
			}
		}
		return idGeoProvince;
	}

	private Integer getIdCommunity(String community) {
		Integer idGeoCommunity = null;
		if (null != community) {
			for (Community c : communityList) {
				if (c.getCommunityName().toLowerCase().indexOf(community.toLowerCase()) >= 0) {
					idGeoCommunity = c.getIdCommunity();
					break;
				}
			}
		}
		return idGeoCommunity;
	}

	private Integer getIdCauseType(String type) {
		Integer idGeoCauseType = null;
		if (null != type) {
			for (IncidenceCauseType ict : typeList) {
				if (ict.getCauseTypeName().equalsIgnoreCase(type)) {
					idGeoCauseType = ict.getIdCauseType();
					break;
				}
			}
		}
		return idGeoCauseType;
	}

}