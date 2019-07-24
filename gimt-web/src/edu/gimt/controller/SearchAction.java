package edu.gimt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import edu.gimt.model.GeoIncidenceModelJpa;
import edu.gimt.model.IncidenceBean;
import edu.gimt.model.IncidenceTypeModelJpa;
import edu.gimt.model.PersistenceManager;
import edu.gimt.model.ProvinceModelJpa;
import edu.gimt.view.GeoIncidenceView;
import edu.gimt.view.IncidenceTypeView;
import edu.gimt.view.ProvinceView;

/**
 * Search Action
 */
public class SearchAction extends ActionSupport implements Preparable {
	
	private static final long serialVersionUID = -2241663105457203545L;
	
	private EntityManager em = null;
	List<String> province = null;
	List<String> incidenceType = null;
	List<String> year = null;
	List<String> month = null;
	List<String> dayOfWeek = null;
	List<String> schedule = null;
	String datePicker = null;
	String visualization = null;
	
	ProvinceView provinceView = null;
	IncidenceTypeView incidenceTypeView = null;
	GeoIncidenceView geoIncidenceView = null;
	
	ArrayList<IncidenceBean> matches = null;


	public String execute() {
		String retValue = null;
		try {
//			HttpSession session = request.getSession();
//			if (null == session.getAttribute("user")) {
//				request.setAttribute("error", "Usuario / Password inválido.");
//		        request.getRequestDispatcher("./login.jsp").forward(request, response);
//			}
			if (em == null) {
				retValue = ERROR;
			}
			else {
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
				
				// Do search in database
				GeoIncidenceModelJpa incModel = new GeoIncidenceModelJpa(em);
				
				matches = incModel.searchGeoIncidences(province, incidenceType, year, month, dayOfWeek, datePicker, schedule);
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


	public List<String> getProvince() {
		return province;
	}


	public void setProvince(List<String> province) {
		this.province = province;
	}


	public List<String> getIncidenceType() {
		return incidenceType;
	}


	public void setIncidenceType(List<String> incidenceType) {
		this.incidenceType = incidenceType;
	}


	public List<String> getYear() {
		return year;
	}


	public void setYear(List<String> year) {
		this.year = year;
	}


	public List<String> getMonth() {
		return month;
	}


	public void setMonth(List<String> month) {
		this.month = month;
	}


	public List<String> getDayOfWeek() {
		return dayOfWeek;
	}


	public void setDayOfWeek(List<String> dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}


	public List<String> getSchedule() {
		return schedule;
	}


	public void setSchedule(List<String> schedule) {
		this.schedule = schedule;
	}


	public String getDatePicker() {
		return datePicker;
	}


	public void setDatePicker(String datePicker) {
		this.datePicker = datePicker;
	}


	public ProvinceView getProvinceView() {
		return provinceView;
	}


	public void setProvinceView(ProvinceView provinceView) {
		this.provinceView = provinceView;
	}


	public IncidenceTypeView getIncidenceTypeView() {
		return incidenceTypeView;
	}


	public void setIncidenceTypeView(IncidenceTypeView incidenceTypeView) {
		this.incidenceTypeView = incidenceTypeView;
	}


	public GeoIncidenceView getGeoIncidenceView() {
		return geoIncidenceView;
	}


	public void setGeoIncidenceView(GeoIncidenceView geoIncidenceView) {
		this.geoIncidenceView = geoIncidenceView;
	}


	public ArrayList<IncidenceBean> getMatches() {
		return matches;
	}


	public void setMatches(ArrayList<IncidenceBean> matches) {
		this.matches = matches;
	}


	public String getVisualization() {
		return visualization;
	}


	public void setVisualization(String visualization) {
		this.visualization = visualization;
	}

}

