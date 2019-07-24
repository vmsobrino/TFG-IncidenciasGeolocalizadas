package edu.gimt.model;

import com.github.filosganga.geogson.model.Feature;

import edu.gimt.persistence.Town;

public class TownAndGeometryBean {

	public TownAndGeometryBean(Town town, Feature townGeometry) {
			this.town = town;
			this.townGeometry = townGeometry;
	}

	private Town town;
	private Feature townGeometry;
	
	public Town getTown() {
		return town;
	}
	public void setTown(Town town) {
		this.town = town;
	}
	public Feature getTownGeometry() {
		return townGeometry;
	}
	public void setTownGeometry(Feature townGeometry) {
		this.townGeometry = townGeometry;
	}
}
