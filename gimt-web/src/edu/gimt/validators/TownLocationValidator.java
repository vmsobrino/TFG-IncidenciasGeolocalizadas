package edu.gimt.validators;

import java.util.List;
import java.util.Map;

import com.github.filosganga.geogson.jts.MultiPolygonCodec;
import com.github.filosganga.geogson.jts.PolygonCodec;
import com.github.filosganga.geogson.model.Feature;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

import edu.gimt.model.TownAndGeometryBean;
import edu.gimt.persistence.Province;
import edu.gimt.persistence.Town;
import edu.gimt.validators.exception.ValidationException;


public class TownLocationValidator extends GeoIncidenceAbstractValidator implements IValidator {

	Map<Integer, TownAndGeometryBean> townsMap = null;
	List<Province> provinceList = null;

	public TownLocationValidator(Object validationItem) {
		super(validationItem);
	}

	@Override
	public void validate() throws ValidationException {
		StringBuilder errors = new StringBuilder();
		String latitude = null;
		String longitude = null;
		// Data from item
		latitude = (validationItem != null ? validationItem.getLatitude() : null);
		longitude = (validationItem != null ? validationItem.getLongitude() : null);
		try {
			Double lat = Double.parseDouble(latitude);
			Double lon = Double.parseDouble(longitude);
			// Search province for coordinates
			Town locTown = locationTown(lat, lon);
			if (null != locTown) {
				validationItem.setProvince(getProvinceName(locTown.getIdProvince()));
				validationItem.setTown(locTown.getTownName());
				validationItem.setJudicialDistrict(locTown.getIdJudicialDistrict().toString());
				validationItem.setNuts3(getNuts3(locTown.getIdProvince()));
//				System.out.println("Datos asignados por coordenadas: " + locTown.toString() + Constants.LINE_SEPARATOR);
			}
			else { // Out of bounds
				errors.append("Coordenadas fuera de las provincias parametrizadas.");
				throw new ValidationException (errors.toString());
			}
		}
		catch (NumberFormatException nfe) {
			errors.append("Latitud y/o Longitud no tiene un valor numérico.");
			throw new ValidationException (errors.toString());
		}
	}
	
	private String getProvinceName(Integer idProvince) {
		String provName = null;
		for (Province p : provinceList) {
			if (p.getIdProvince().intValue() == idProvince.intValue()) {
				provName = p.getProvinceName();
				break;
			}
		}
		return provName;
	}
	
	private String getNuts3(Integer idProvince) {
		String nuts3 = null;
		for (Province p : provinceList) {
			if (p.getIdProvince().intValue() == idProvince.intValue()) {
				nuts3 = p.getNuts3();
				break;
			}
		}
		return nuts3;
	}
	
	/**
	 * Obtains town location for input coordinates.
	 * @param latitude Latitude.
	 * @param longitude Longitude.
	 * @return Town object for location, or null if not found.
	 */
	private Town locationTown(Double latitude, Double longitude) {
		Town locatedTown = null;
		GeometryFactory gf = new GeometryFactory();
		Point point = null;
		TownAndGeometryBean tgBean = null;
		Feature feature = null;
		
		for (Integer idTown : townsMap.keySet()) {
			tgBean = townsMap.get(idTown);
			feature = tgBean.getTownGeometry();
			if (null != tgBean && null != feature) {
				point = gf.createPoint(new Coordinate(longitude, latitude));
				if (feature.geometry() instanceof com.github.filosganga.geogson.model.Polygon) {
					PolygonCodec pCodec = new PolygonCodec(gf);
					Polygon polygon = pCodec.fromGeometry((com.github.filosganga.geogson.model.Polygon)feature.geometry());
					if (polygon.contains(point)) {
						locatedTown = tgBean.getTown();
						break;
					}
				}
				if (feature.geometry() instanceof com.github.filosganga.geogson.model.MultiPolygon) {
					MultiPolygonCodec mpCodec = new MultiPolygonCodec(gf);
					MultiPolygon multiPolygon = mpCodec.fromGeometry((com.github.filosganga.geogson.model.MultiPolygon)feature.geometry());
					if (multiPolygon.contains(point)) {
						locatedTown = tgBean.getTown();
						break;
					}
				}
			}
		}
		return locatedTown;
	}

	public Map<Integer, TownAndGeometryBean> getTownsMap() {
		return townsMap;
	}

	public void setTownsMap(Map<Integer, TownAndGeometryBean> townsMap) {
		this.townsMap = townsMap;
	}

	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

}