package edu.gimt.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.gimt.common.Constants;
import edu.gimt.persistence.CfgParam;

/**
 * @author VICTOR
 *
 */
public class GeoIncidenceModelJpa {

	private EntityManager em = null;

	/**
	 * @param em
	 */
	public GeoIncidenceModelJpa(EntityManager em) {
		this.em = em;
	}

	/**
	 * Return the distinct years in which incidents occurred.
	 * @return Array with distinct years.
	 */
	public Integer[] getDistinctYears() {
		Integer[] distinctYears = null;
		TypedQuery<Integer> incQuery = em.createQuery("SELECT DISTINCT(YEAR(gi.startDatetime)) AS year FROM GeoIncidence gi ORDER BY year DESC", Integer.class);
		List<Integer> list = incQuery.getResultList();
		if (null != list && list.size() > 0) {
			distinctYears = new Integer[list.size()];
			distinctYears = list.toArray(distinctYears);
		}
		// return array
		return distinctYears;
	}

	/**
	 * Create file with incidence list count.
	 * @param countList The incidence list count.
	 * @return The created file.
	 */
	public File makeFileFromIncCount(List<IncidenceCountBean> countList) {
		TypedQuery<CfgParam> cfgQuery = null;
		List<CfgParam> cfgParams = null;
		String countFilePath = null;
		String countFileName = null;
        FileWriter writer = null;
        File fileCount = null;
		
		cfgQuery = em.createNamedQuery("CfgParam.findByModuleAndParam", CfgParam.class);
		cfgQuery.setParameter("codModule", Constants.MODULE_ZONE_DIAGNOSTIC);
		cfgQuery.setParameter("codParam", Constants.COUNT_FILE_PATH);
		cfgParams = cfgQuery.getResultList();
		for (CfgParam cp : cfgParams) {
			if (null != cp.getParamValue() ) {
				countFilePath = cp.getParamValue();
			}
		}
		cfgQuery = em.createNamedQuery("CfgParam.findByModuleAndParam", CfgParam.class);
		cfgQuery.setParameter("codModule", Constants.MODULE_ZONE_DIAGNOSTIC);
		cfgQuery.setParameter("codParam", Constants.COUNT_FILE_NAME);
		cfgParams = cfgQuery.getResultList();
		for (CfgParam cp : cfgParams) {
			if (null != cp.getParamValue() ) {
				countFileName = cp.getParamValue();
			}
		}
		// First delete file, if exists previously
		fileCount = new File(countFilePath + "/" + countFileName);
		if (fileCount.exists()) {
			fileCount.delete();
		}
		// Write file
		try {
			writer = new FileWriter(fileCount);
			IncidenceCountBean row = null;
	        for (int i = 0; i < countList.size(); i++) {
	        	row = countList.get(i);
	        	if (i == 0) { //header
	        		writer.write(row.getHeader());
	        	}
	            writer.write(row.toString());
	            if (i + 1 < countList.size()) {
	            	writer.write(Constants.LINE_SEPARATOR);
	            }
	        }
	        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileCount;
	}

	/**
	 * Performs a search for incidences based on the specified criteria.
	 * @param provinceSel Province/s selected.
	 * @param incidenceTypeSel Type/s of incidences.
	 * @param yearSel Year/s selected.
	 * @param monthSel Month/s selected.
	 * @param dayOfWeekSel Day/s of the week to search.
	 * @param dateSel One concrete date to search.
	 * @param scheduleSel Schedule to search.
	 * @return List of incidence/s found.
	 */
	public ArrayList<IncidenceCountBean> countGeoIncidences(List<String> yearSel) {
		ArrayList<IncidenceCountBean> incList = null;
//		StringBuilder selectFields = new StringBuilder();
//		StringBuilder fromTables = new StringBuilder();
//		StringBuilder searchCriteria = new StringBuilder();
		StringBuilder select = new StringBuilder();

		if (null != yearSel) {
			try {
//				selectFields.append("SELECT m.yearOfIncidence, ");
//				selectFields.append("	    m.idJd, ");
//				selectFields.append("	    jd.jdName, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseSubtype = 5) AS alcances, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseSubtype = 6) AS atropellos, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseSubtype = 7) AS salidasVia, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseSubtype = 8) AS tijeraCamion, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseSubtype = 9) AS vuelcos, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseType = 2 AND i.numMonthOfYear IN (12, 1, 2) ) AS invierno, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseType = 2 AND i.numMonthOfYear IN (3, 4, 5) ) AS primavera, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseType = 2 AND i.numMonthOfYear IN (6, 7, 8) ) AS verano, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseType = 2 AND i.numMonthOfYear IN (9, 10, 11) ) AS otono, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseType = 2 AND i.idGeoRoadLevel IN (1, 2) ) AS verdeBlanco, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseType = 2 AND i.idGeoRoadLevel IN (3) ) AS amarillo, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseType = 2 AND i.idGeoRoadLevel IN (4) ) AS rojo, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseType = 2 AND i.idGeoRoadLevel IN (1, 2) ) AS negro, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseType = 2 AND i.hourOfDay BETWEEN 0 and 5 ) AS hora0006, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseType = 2 AND i.hourOfDay BETWEEN 6 and 11 ) AS hora0612, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseType = 2 AND i.hourOfDay BETWEEN 12 and 17 ) AS hora1218, ");
//				selectFields.append("       (SELECT COUNT(*) FROM GeoIncidence AS i WHERE m.yearOfIncidence = i.yearOfIncidence AND m.idJd = i.idJd AND i.idGeoCauseType = 2 AND i.hourOfDay BETWEEN 18 and 23 )  AS hora1800 ");
//
//				fromTables.append("    FROM (SELECT DISTINCT i.yearOfIncidence, i.idJd FROM GeoIncidence AS i ORDER BY i.yearOfIncidence, i.idJd) AS m, ");
//				fromTables.append("         JudicialDistrict AS jd ");
//				fromTables.append("   WHERE ");
//				
//				searchCriteria.append("     m.idJd = jd.idJudicialDistrict ");
//				searchCriteria.append("	    AND m.yearOfIncidence IN (" + yearSel.toString().substring(1, yearSel.toString().length() - 1) + ") ");
//				searchCriteria.append(" ORDER BY m.yearOfIncidence, m.idJd");
//
//				// compose Select sentence
//				select.append(selectFields);
//				select.append(fromTables);
//				select.append(searchCriteria);
				
				select.append("SELECT m.year_of_incidence ano, "); 
				select.append("	   m.id_geo_jd id_dj, "); 
				select.append("	   jd.jd_name distrito_judicial, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_subtype = 5) alcances, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_subtype = 6) atropellos, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_subtype = 7) salidas_via, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_subtype = 8) tijera_camion, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_subtype = 9) vuelcos, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_type = 2 AND num_month_of_year in (12, 1, 2) ) accidentes_invierno, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_type = 2 AND num_month_of_year in (3, 4, 5) ) accidentes_primavera, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_type = 2 AND num_month_of_year in (6, 7, 8) ) accidentes_verano, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_type = 2 AND num_month_of_year in (9, 10, 11) ) accidentes_otono, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_type = 2 AND id_geo_road_level in (1, 2) ) nivel_verde_blanco, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_type = 2 AND id_geo_road_level in (3) ) nivel_amarillo, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_type = 2 AND id_geo_road_level in (4) ) nivel_rojo, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_type = 2 AND id_geo_road_level in (1, 2) ) nivel_negro, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_type = 2 AND hour_of_day between 0 and 5 ) hora_00_06, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_type = 2 AND hour_of_day between 6 and 11 ) hora_06_12, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_type = 2 AND hour_of_day between 12 and 17 ) hora_12_18, "); 
				select.append("       (SELECT COUNT(*) FROM gimt_geo_incidence WHERE m.year_of_incidence = year_of_incidence AND m.id_geo_jd = id_geo_jd AND id_geo_cause_type = 2 AND hour_of_day between 18 and 23 ) hora_18_00 "); 
				select.append("  FROM (SELECT DISTINCT year_of_incidence, id_geo_jd FROM gimt_geo_incidence ORDER BY year_of_incidence, id_geo_jd) m, "); 
				select.append("       gimt_judicial_district jd "); 
				select.append(" WHERE m.id_geo_jd = jd.id_jd "); 
				select.append("	   AND m.year_of_incidence IN (" + yearSel.toString().substring(1, yearSel.toString().length() - 1) + ") "); 
				select.append("ORDER BY m.year_of_incidence, m.id_geo_jd");				
				
				javax.persistence.Query q = em.createNativeQuery(select.toString());
				@SuppressWarnings("unchecked")
				List<Object[]> resultList = (List<Object[]>)q.getResultList();
				
//				TypedQuery<Object[]> query = em.createQuery(select.toString(), Object[].class);
//				List<Object[]> resultList = query.getResultList();
				incList = makeIncidenceCountList(resultList);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return incList;
	}

	private ArrayList<IncidenceCountBean> makeIncidenceCountList(List<Object[]> list) {
		ArrayList<IncidenceCountBean> incList = null;
		IncidenceCountBean bean = null;
		if (null != list && list.size() > 0) {
			incList = new ArrayList<IncidenceCountBean>();
			for (Object[] row : list) {
				Integer yearOfIncidence = row[0]==null?null:(Integer)row[0];
				Integer jdId = row[1]==null?null:(Integer)row[1];
				String judicialDistrict = row[2]==null?null:(String)row[2];
				BigInteger hits = row[3]==null?null:(BigInteger)row[3];
				BigInteger runover = row[4]==null?null:(BigInteger)row[4];
				BigInteger wayouts = row[5]==null?null:(BigInteger)row[5];
				BigInteger scissorTracks = row[6]==null?null:(BigInteger)row[6];
				BigInteger rollovers = row[7]==null?null:(BigInteger)row[7];
				BigInteger winterAccidents = row[8]==null?null:(BigInteger)row[8];
				BigInteger springAccidents = row[9]==null?null:(BigInteger)row[9];
				BigInteger summerAccidents = row[10]==null?null:(BigInteger)row[10];
				BigInteger autumnAccidents = row[11]==null?null:(BigInteger)row[11];
				BigInteger greenWhiteLevel = row[12]==null?null:(BigInteger)row[12];
				BigInteger yellowLevel = row[13]==null?null:(BigInteger)row[13];
				BigInteger redLevel = row[14]==null?null:(BigInteger)row[14];
				BigInteger blackLevel = row[15]==null?null:(BigInteger)row[15];
				BigInteger hour0006 = row[16]==null?null:(BigInteger)row[16];
				BigInteger hour0612 = row[17]==null?null:(BigInteger)row[17];
				BigInteger hour1218 = row[18]==null?null:(BigInteger)row[18];
				BigInteger hour1800 = row[19]==null?null:(BigInteger)row[19];

				bean = new IncidenceCountBean(yearOfIncidence, jdId,
											  judicialDistrict, hits, runover,
											  wayouts, scissorTracks, rollovers,
												winterAccidents, springAccidents,
												summerAccidents, autumnAccidents,
												greenWhiteLevel, yellowLevel, redLevel,
												blackLevel, hour0006, hour0612,
												hour1218, hour1800);
				incList.add(bean);
			}
		}
		return incList;
	}

	private ArrayList<IncidenceBean> makeIncidenceList(List<Object[]> list) {
		ArrayList<IncidenceBean> incList = null;
		IncidenceBean bean = null;
		if (null != list && list.size() > 0) {
			incList = new ArrayList<IncidenceBean>();
			for (Object[] row : list) {
				String communityName = row[0]==null?null:(String)row[0];
				String provinceName = row[1]==null?null:(String)row[1];
				String causeTypeName = row[2]==null?null:(String)row[2];
				String causeSubtypeName = row[3]==null?null:(String)row[3];
				String townName = row[4]==null?null:(String)row[4];
				java.util.Date startDatetime = row[5]==null?null:(java.util.Date)row[5];
				java.util.Date endDatetime = row[6]==null?null:(java.util.Date)row[6];
				String levelColour = row[7]==null?null:(String)row[7];
				String levelFlow = row[8]==null?null:(String)row[8];
				String mountainPortLevel = row[9]==null?null:(String)row[9];
				String roadCode = row[10]==null?null:(String)row[10];
				Float initialKilometer = row[11]==null?null:(Float)row[11];
				Float finalKilometer = row[12]==null?null:(Float)row[12];
				String sense = row[13]==null?null:(String)row[13];
				String name = row[14]==null?null:(String)row[14];
				BigDecimal longitude = row[15]==null?null:(BigDecimal)row[15];
				BigDecimal latitude = row[16]==null?null:(BigDecimal)row[16];

				bean = new IncidenceBean(communityName, provinceName, causeTypeName,
						causeSubtypeName, townName, startDatetime,
						endDatetime, levelColour, levelFlow,
						mountainPortLevel, roadCode, initialKilometer, 
						finalKilometer, sense, name, 
						longitude, latitude);
				incList.add(bean);
			}
		}
		return incList;
	}


	public ArrayList<IncidenceBean> searchGeoIncidences(List<String> provinceSel,
			List<String> incidenceTypeSel,
			List<String> yearSel,
			List<String> monthSel,
			List<String> dayOfWeekSel,
			String dateSel,
			List<String> scheduleSel) {
		ArrayList<IncidenceBean> incList = null;
		StringBuilder selectFiedls = new StringBuilder();
		StringBuilder fromTables = new StringBuilder();
		StringBuilder searchCriteria = new StringBuilder();
		StringBuilder select = new StringBuilder();

		if (null != provinceSel || null != incidenceTypeSel || null != yearSel || null != monthSel || 
				null != dayOfWeekSel || null != scheduleSel || (null != dateSel && !dateSel.trim().equals(""))) {
			try {
				selectFiedls.append("SELECT c.communityName, p.provinceName, ct.causeTypeName, cs.causeSubtypeName, gi.townName, ");
				selectFiedls.append("       gi.startDatetime, gi.endDatetime, rl.levelColour, rl.levelFlow, gi.mountainPortLevel, ");
				selectFiedls.append("       gi.roadCode, gi.initialKilometer, gi.finalKilometer, gi.sense, gi.name, gi.longitude, gi.latitude ");

				fromTables.append("  FROM GeoIncidence gi ");
				fromTables.append("       LEFT JOIN Community c ON gi.idGeoCommunity = c.idCommunity ");
				fromTables.append("       LEFT JOIN Province p ON gi.idGeoProvince = p.idProvince ");
				fromTables.append("       LEFT JOIN IncidenceCauseType ct ON gi.idGeoCauseType = ct.idCauseType ");
				fromTables.append("       LEFT JOIN IncidenceCauseSubtype cs ON gi.idGeoCauseSubtype = cs.idCauseSubtype ");
				fromTables.append("       LEFT JOIN RoadLevel rl ON gi.idGeoRoadLevel = rl.idRoadLevel ");
				fromTables.append(" WHERE ");

				// Province
				if (null != provinceSel && provinceSel.size() > 0) {
					searchCriteria.append("gi.idGeoProvince IN (");
					for (int i = 0; i < provinceSel.size(); i++) {
						searchCriteria.append(provinceSel.get(i));
						if ((i + 1) < provinceSel.size()) {
							searchCriteria.append(",");
						}
					}
					searchCriteria.append(") ");
				}

				// Incidence Type
				if (null != incidenceTypeSel && incidenceTypeSel.size() > 0) {
					if (searchCriteria.length() > 0) {
						searchCriteria.append(" AND ");
					}
					searchCriteria.append("gi.idGeoCauseType IN (");
					for (int i = 0; i < incidenceTypeSel.size(); i++) {
						searchCriteria.append(incidenceTypeSel.get(i));
						if ((i + 1) < incidenceTypeSel.size()) {
							searchCriteria.append(",");
						}
					}
					searchCriteria.append(") ");
				}

				// Year, month and say OR concrete date
				if ((null != yearSel && yearSel.size() > 0) || (null != monthSel && monthSel.size() > 0) || (null != dayOfWeekSel && dayOfWeekSel.size() > 0)) {
					// Year
					if (null != yearSel && yearSel.size() > 0) {
						if (searchCriteria.length() > 0) {
							searchCriteria.append(" AND ");
						}
						searchCriteria.append("YEAR(gi.startDatetime) IN (");
						for (int i = 0; i < yearSel.size(); i++) {
							searchCriteria.append(yearSel.get(i));
							if ((i + 1) < yearSel.size()) {
								searchCriteria.append(",");
							}
						}
						searchCriteria.append(") ");
					}		
					// Month
					if (null != monthSel && monthSel.size() > 0) {
						if (searchCriteria.length() > 0) {
							searchCriteria.append(" AND ");
						}
						searchCriteria.append("gi.numMonthOfYear IN (");
						for (int i = 0; i < monthSel.size(); i++) {
							searchCriteria.append(monthSel.get(i));
							if ((i + 1) < monthSel.size()) {
								searchCriteria.append(",");
							}
						}
						searchCriteria.append(") ");
					}		
					// day Of Week
					if (null != dayOfWeekSel && dayOfWeekSel.size() > 0) {
						if (searchCriteria.length() > 0) {
							searchCriteria.append(" AND ");
						}
						searchCriteria.append("gi.numDayOfWeek IN (");
						for (int i = 0; i < dayOfWeekSel.size(); i++) {
							searchCriteria.append(dayOfWeekSel.get(i));
							if ((i + 1) < dayOfWeekSel.size()) {
								searchCriteria.append(",");
							}
						}
						searchCriteria.append(") ");
					}		
				}
				else { // concrete date
					if (null != dateSel && !dateSel.trim().equals("")) {
						if (searchCriteria.length() > 0) {
							searchCriteria.append(" AND ");
						}
						searchCriteria.append("DATE_FORMAT(gi.startDatetime, '%d/%m/%Y') = '");
						searchCriteria.append(dateSel);
						searchCriteria.append("' ");
					}		

				}

				// schedule: morning, afternoon, night, early night
				if (null != scheduleSel && scheduleSel.size() > 0) {
					if (searchCriteria.length() > 0) {
						searchCriteria.append(" AND ");
					}
					searchCriteria.append("(");
					for (int i = 0; i < scheduleSel.size(); i++) {
						switch (Integer.parseInt(scheduleSel.get(i))) {
						case 1: // morning
							searchCriteria.append("DATE_FORMAT(gi.startDatetime, '%H:%i:%S') BETWEEN '06:00:00' AND '11:59:59'");
							break;
						case 2: // afternoon
							searchCriteria.append("DATE_FORMAT(gi.startDatetime, '%H:%i:%S') BETWEEN '12:00:00' AND '17:59:59'");
							break;					
						case 3: // night
							searchCriteria.append("DATE_FORMAT(gi.startDatetime, '%H:%i:%S') BETWEEN '18:00:00' AND '23:59:59'");
							break;
						case 4: // early night
							searchCriteria.append("DATE_FORMAT(gi.startDatetime, '%H:%i:%S') BETWEEN '00:00:00' AND '05:59:59'");
							break;
						}
						if ((i + 1) < scheduleSel.size()) {
							searchCriteria.append(" OR ");
						}
					}
					searchCriteria.append(") ");
				}
				// compose Select sentence
				select.append(selectFiedls);
				select.append(fromTables);
				select.append(searchCriteria);
				TypedQuery<Object[]> query = em.createQuery(select.toString(), Object[].class);
				List<Object[]> resultList = query.getResultList();
				incList = makeIncidenceList(resultList);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return incList;
	}

}