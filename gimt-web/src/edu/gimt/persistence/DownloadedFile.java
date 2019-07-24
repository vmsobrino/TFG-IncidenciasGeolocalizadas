package edu.gimt.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Downloaded file Entity class implementation.
 * @author Victor M. Sobrino - TFG
 */
@Entity
@Table(name = "gimt_downloaded_file")
@NamedQueries({@NamedQuery(name = "DownloadedFile.findByFilename", query = "SELECT f FROM DownloadedFile f WHERE f.fileName = :fileName"),
			   @NamedQuery(name = "DownloadedFile.findNotProcessed", query = "SELECT f FROM DownloadedFile f WHERE f.processed = 0 ORDER BY f.fileName ASC"),
			   @NamedQuery(name = "DownloadedFile.findAll", query = "SELECT f FROM DownloadedFile f ORDER BY f.fileName ASC")})
public class DownloadedFile {
	
	@Id
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "initial_date")
	private Date initialDate;
	
	@Column(name = "final_date")
	private Date finalDate;
	
	@Column(name = "processed")
	private Integer processed;
	
	@Column(name = "download_datetime")
	private Date downloadDatetime;
	
	/**
	 * Default constructor.
	 */
	public DownloadedFile() {
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the initialDate
	 */
	public Date getInitialDate() {
		return initialDate;
	}

	/**
	 * @param initialDate the initialDate to set
	 */
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	/**
	 * @return the finalDate
	 */
	public Date getFinalDate() {
		return finalDate;
	}

	/**
	 * @param finalDate the finalDate to set
	 */
	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	/**
	 * @return the processed
	 */
	public Integer getProcessed() {
		return processed;
	}

	/**
	 * @param processed the processed to set
	 */
	public void setProcessed(Integer processed) {
		this.processed = processed;
	}

	/**
	 * @return the downloadDatetime
	 */
	public Date getDownloadDatetime() {
		return downloadDatetime;
	}

	/**
	 * @param downloadDatetime the downloadDatetime to set
	 */
	public void setDownloadDatetime(Date downloadDatetime) {
		this.downloadDatetime = downloadDatetime;
	}
	

}
