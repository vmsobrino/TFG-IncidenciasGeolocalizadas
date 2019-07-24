package edu.gimt.view;

import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Vector;

import edu.gimt.persistence.GeoIncidence;

public class GeoIncidenceView {

	private String[] yearsSelected = null;
	private String[] monthsSelected = null;
	private String[] daysSelected = null;
	private String[] scheduleSelected = null;
	private Vector<String> schedule = null;
	private Integer[] distinctYears = null;

	/**
	 * @param types
	 * @param selected
	 */
	public GeoIncidenceView() {
		this.schedule = new Vector<String>(4);
		this.schedule.add("mañana (06:00 - 12:00)");
		this.schedule.add("tarde (12:00 - 18:00)");
		this.schedule.add("noche (18:00 - 00:00)");
		this.schedule.add("madrugada (00:00 - 06:00)");
	}
	
	public void setYearsSelected(String[] yearsSelected) {
		this.yearsSelected = yearsSelected;
	}

	public void setMonthsSelected(String[] monthsSelected) {
		this.monthsSelected = monthsSelected;
	}

	public void setDaysSelected(String[] daysSelected) {
		this.daysSelected = daysSelected;
	}

	public void setScheduleSelected(String[] scheduleSelected) {
		this.scheduleSelected = scheduleSelected;
	}

	/**
	 * @param distinctYears the distinctYears to set
	 */
	public void setDistinctYears(Integer[] distinctYears) {
		this.distinctYears = distinctYears;
	}

	public String getDistinctYears() {
		StringBuilder html = null;
		html = new StringBuilder();
		for (Integer year : distinctYears) {
			html.append("<OPTION ");
			if (null != yearsSelected) {
				for (String sel : yearsSelected) {
					if (sel.equals(year.toString())) {
						html.append("SELECTED ");
						break;
					}
				}
			}
			html.append("VALUE=\"");
			html.append(year.toString());
			html.append("\">");
			html.append(year.toString());
			html.append("</OPTION>");
			html.append(System.getProperty("line.separator"));
		}
		return html.toString();
	}
	
	public String getMonths() {
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		StringBuilder html = new StringBuilder();
		for (int i = 0; i < months.length && !months[i].trim().equals(""); i++) {
			html.append("<OPTION ");
			if (null != monthsSelected) {
				for (String sel : monthsSelected) {
					if (sel.trim().equals(Integer.toString(i + 1))) {
						html.append("SELECTED ");
						break;
					}
				}
			}
			html.append("VALUE=\"");
			html.append(i + 1);
			html.append("\">");
			html.append(months[i]);
			html.append("</OPTION>");
			html.append(System.getProperty("line.separator"));
		}
		return html.toString();
	}
	

	public String getDays() {
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] days = dfs.getWeekdays();
		StringBuilder html = new StringBuilder();
		for (int i = 1; i < days.length ; i++) {
			html.append("<OPTION ");
			if (null != daysSelected) {
				for (String sel : daysSelected) {
					if (sel.trim().equals(Integer.toString(i))) {
						html.append("SELECTED ");
						break;
					}
				}
			}
			html.append("VALUE=\"");
			html.append(i);
			html.append("\">");
			html.append(days[i]);
			html.append("</OPTION>");
			html.append(System.getProperty("line.separator"));
		}
		return html.toString();
	}

	public String getSchedule() {
		StringBuilder html = new StringBuilder();
		for (int i = 0; i < schedule.size() ; i++) {
			html.append("<OPTION ");
			if (null != scheduleSelected) {
				for (String sel : scheduleSelected) {
					if (sel.trim().equals(Integer.toString(i + 1))) {
						html.append("SELECTED ");
						break;
					}
				}
			}
			html.append("VALUE=\"");
			html.append(i + 1);
			html.append("\">");
			html.append(schedule.get(i));
			html.append("</OPTION>");
			html.append(System.getProperty("line.separator"));
		}
		return html.toString();
	}

	public List<GeoIncidence> searchGeoIncidences(String[] provinceSel,
											      String[] incidenceTypeSel,
											      String[] yearSel,
											      String[] monthSel,
											      String[] dayOfWeekSel,
											      String dateSel,
											      String[] scheduleSel) {
		List<GeoIncidence> geoIncidences = null;
		
		return geoIncidences;
	}
}
