package edu.gimt.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.gimt.persistence.IncidenceCauseType;

public class IncidenceTypeModelJpa {

	private EntityManager em = null;

	/**
	 * Constructor.
	 * @param em EntityManager instance object.
	 */
	public IncidenceTypeModelJpa(EntityManager em) {
		this.em = em;
	}

	public IncidenceCauseType getIncidenceTypeById(Integer idCauseType) {
		return null;
	}

	public List<IncidenceCauseType> getIncidenceTypes() {
		TypedQuery<IncidenceCauseType> incidenceTypeQuery = null;
		incidenceTypeQuery = em.createNamedQuery("IncidenceCauseType.findAll", IncidenceCauseType.class);
		return incidenceTypeQuery.getResultList();
	}

}
