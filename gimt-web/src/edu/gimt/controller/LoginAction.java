package edu.gimt.controller;

import java.util.Map;

import javax.persistence.EntityManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import edu.gimt.model.GeoIncidenceModelJpa;
import edu.gimt.model.IncidenceTypeModelJpa;
import edu.gimt.model.PersistenceManager;
import edu.gimt.model.ProvinceModelJpa;
import edu.gimt.view.GeoIncidenceView;
import edu.gimt.view.IncidenceTypeView;
import edu.gimt.view.ProvinceView;


/**
 * Login action
 */
public class LoginAction extends ActionSupport implements Preparable {
	
	private static final long serialVersionUID = -6123556823917820962L;
	
	private EntityManager em = null;
	private Map<String, Object> userSession;
	
	private ProvinceView provinceView = null;
	private IncidenceTypeView incidenceTypeView = null;
	private GeoIncidenceView geoIncidenceView = null;

	public void setSession(Map<String, Object> session) {
	   userSession = session ;
	}

	public String execute() {
		String retValue = null;
		try {
			
			if (null != em) { // user logged
//				HttpSession session = request.getSession();
//				session.setAttribute("user", userBean.getUsername());
				
				// setting session expire in 30 mins.
//				session.setMaxInactiveInterval(30 * 60);
//				Cookie userName = new Cookie("user", userBean.getUsername());
//				response.addCookie(userName);
				
				// Load data into request for drop-downs: provinces
				ProvinceModelJpa pm = new ProvinceModelJpa(em);
				provinceView = new ProvinceView(pm.getProvinces());
				
				// incidence types
				IncidenceTypeModelJpa im = new IncidenceTypeModelJpa(em);
				incidenceTypeView = new IncidenceTypeView(im.getIncidenceTypes());
				
				// rest of incidence search criteria
				GeoIncidenceModelJpa gim = new GeoIncidenceModelJpa(em);
				geoIncidenceView = new GeoIncidenceView();
				geoIncidenceView.setDistinctYears(gim.getDistinctYears());
				
				// Get the encoded URL string
				retValue = SUCCESS;
			}
			else {
				retValue = ERROR;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			retValue = ERROR;
		}
		return retValue;

	}


	@Override
	public void prepare() throws Exception {
		em = PersistenceManager.INSTANCE.getEntityManager();
	}
	
	public void destroy() throws Exception {
		if (null != em && em.isOpen()) {
			em.close();
		}
		if (PersistenceManager.INSTANCE != null) {
			PersistenceManager.INSTANCE.close();
		}
	}
	
	public ProvinceView getProvinceView() {
		return provinceView;
	}

	public IncidenceTypeView getIncidenceTypeView() {
		return incidenceTypeView;
	}

	public GeoIncidenceView getGeoIncidenceView() {
		return geoIncidenceView;
	}

	public void setProvinceView(ProvinceView provinceView) {
		this.provinceView = provinceView;
	}

	public void setIncidenceTypeView(IncidenceTypeView incidenceTypeView) {
		this.incidenceTypeView = incidenceTypeView;
	}

	public void setGeoIncidenceView(GeoIncidenceView geoIncidenceView) {
		this.geoIncidenceView = geoIncidenceView;
	}

}

