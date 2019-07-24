package edu.gimt.view;

import java.util.List;

import edu.gimt.persistence.Province;

public class ProvinceView {

	private List<Province> provinces;
	private String[] selected;

	/**
	 * @param provinces
	 * @param selected
	 */
	public ProvinceView(List<Province> provinces) {
		this.provinces = provinces;
	}

	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}

	public void setSelected(String[] selected) {
		this.selected = selected;
	}

	public String getHtmlOptions() {
		StringBuilder html = new StringBuilder();
		for (Province province : provinces) {
			html.append("<OPTION ");
			if (null != selected) {
				for (String sel : selected) {
					if (province.getIdProvince().toString().equals(sel)) {
						html.append("SELECTED ");
						break;
					}
				}
			}
			html.append("VALUE=\"");
			html.append(province.getIdProvince());
			html.append("\">");
			html.append(province.getProvinceName());
			html.append("</OPTION>");
			html.append(System.getProperty("line.separator"));
		}
		return html.toString();
	}
}
