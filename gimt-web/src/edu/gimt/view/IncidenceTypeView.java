package edu.gimt.view;

import java.util.List;

import edu.gimt.persistence.IncidenceCauseType;

public class IncidenceTypeView {

	private List<IncidenceCauseType> types;
	private String[] selected;

	/**
	 * @param types
	 * @param selected
	 */
	public IncidenceTypeView(List<IncidenceCauseType> types) {
		this.types = types;
	}

	public void setTypes(List<IncidenceCauseType> types) {
		this.types = types;
	}

	public void setSelected(String[] selected) {
		this.selected = selected;
	}

	public String getHtmlOptions() {
		StringBuilder html = new StringBuilder();
		for (IncidenceCauseType type : types) {
			html.append("<OPTION ");
			if (null != selected) {
				for (String sel : selected) {
					if (type.getIdCauseType().toString().equals(sel)) {
						html.append("SELECTED ");
						break;
					}
				}
			}
			html.append("VALUE=\"");
			html.append(type.getIdCauseType());
			html.append("\">");
			html.append(type.getCauseTypeName());
			html.append("</OPTION>");
			html.append(System.getProperty("line.separator"));
		}
		return html.toString();
	}
}
