package edu.gimt.controller;

import java.util.List;

import javax.persistence.EntityManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import edu.gimt.model.GeoIncidenceModelJpa;
import edu.gimt.model.PersistenceManager;
import edu.gimt.view.GeoIncidenceView;

/**
 * Search Action
 */
public class ShowDownloadFileAction extends ActionSupport implements Preparable {
	
	
	private static final long serialVersionUID = 2051247868129532569L;
	private EntityManager em = null;
	List<String> year = null;
	GeoIncidenceView geoIncidenceView = null;
	
	public String execute() {
		String retValue = null;
		try {
			if (em == null) {
				retValue = ERROR;
			}
			else {
				// Years
				GeoIncidenceModelJpa gim = new GeoIncidenceModelJpa(em);
				geoIncidenceView = new GeoIncidenceView();
				geoIncidenceView.setDistinctYears(gim.getDistinctYears());
				
				retValue = SUCCESS;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			retValue = ERROR;
		}
		return retValue;

	}


	public void destroy() {
		if (null != em && em.isOpen()) {
			em.close();
		}
		if (PersistenceManager.INSTANCE != null) {
			PersistenceManager.INSTANCE.close();
		}
	}


	public void prepare() throws Exception {
		em = PersistenceManager.INSTANCE.getEntityManager();
	}


	public List<String> getYear() {
		return year;
	}


	public void setYear(List<String> year) {
		this.year = year;
	}


	public GeoIncidenceView getGeoIncidenceView() {
		return geoIncidenceView;
	}


	public void setGeoIncidenceView(GeoIncidenceView geoIncidenceView) {
		this.geoIncidenceView = geoIncidenceView;
	}

}

