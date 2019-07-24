package edu.gimt.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.gimt.persistence.Province;

public class ProvinceModelJpa {

	private EntityManager em = null;

	/**
	 * Constructor.
	 * @param em EntityManager instance object.
	 */
	public ProvinceModelJpa(EntityManager em) {
		this.em = em;
	}

	public Province getProvinceById(Integer idProvince) {
		TypedQuery<Province> provinceQuery = null;
		provinceQuery = em.createNamedQuery("Province.findById", Province.class);
		provinceQuery.setParameter("idProvince", idProvince);
		return provinceQuery.getSingleResult();
	}

	public List<Province> getProvinces() {
		TypedQuery<Province> provinceQuery = null;
		provinceQuery = em.createNamedQuery("Province.findAll", Province.class);
		return provinceQuery.getResultList();
	}

}
