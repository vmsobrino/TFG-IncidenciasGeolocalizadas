package edu.gimt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import edu.gimt.model.GeoIncidenceModelJpa;
import edu.gimt.model.IncidenceCountBean;
import edu.gimt.model.PersistenceManager;
import edu.gimt.view.GeoIncidenceView;

/**
 * Search Action
 */
public class DownloadFileAction extends ActionSupport implements Preparable {
	
	private static final long serialVersionUID = -8295236008645412792L;
	private EntityManager em = null;
	List<String> year = null;
	GeoIncidenceView geoIncidenceView = null;
	
    private InputStream inputStream;
    private String fileName;
    private long contentLength;	
	
	public String execute() {
		String retValue = null;
		try {
			if (em == null) {
				retValue = ERROR;
			}
			else {
				// rest of incidence search criteria
				GeoIncidenceModelJpa gim = new GeoIncidenceModelJpa(em);
				geoIncidenceView = new GeoIncidenceView();
				geoIncidenceView.setDistinctYears(gim.getDistinctYears());
				
				// Do search in database
				GeoIncidenceModelJpa incModel = new GeoIncidenceModelJpa(em);
				ArrayList<IncidenceCountBean> countList = incModel.countGeoIncidences(year);
		        File fileToDownload = incModel.makeFileFromIncCount(countList);
		        
		        inputStream = new FileInputStream(fileToDownload);
		        fileName = fileToDownload.getName();
		        contentLength = fileToDownload.length();				
				
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

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

}

