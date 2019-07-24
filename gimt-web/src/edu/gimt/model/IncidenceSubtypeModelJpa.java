package edu.gimt.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.gimt.persistence.IncidenceCauseSubtype;

public class IncidenceSubtypeModelJpa {

	private EntityManager em = null;

	/**
	 * Constructor.
	 * @param em EntityManager instance object.
	 */
	public IncidenceSubtypeModelJpa() {
		this.em = PersistenceManager.INSTANCE.getEntityManager();
	}

	public IncidenceCauseSubtype getIncidenceSubtypeById(Integer idCauseType) {
		return null;
	}

	public List<IncidenceCauseSubtype> getIncidenceSubtypes() {
		TypedQuery<IncidenceCauseSubtype> incidenceSubtypeQuery = null;
		incidenceSubtypeQuery = em.createNamedQuery("IncidenceCauseSubtype.findAll", IncidenceCauseSubtype.class);
		return incidenceSubtypeQuery.getResultList();
	}

	public List<IncidenceCauseSubtype> getIncidenceSubtypesByType(String[] types) {
		List<IncidenceCauseSubtype> result = null;
		TypedQuery<IncidenceCauseSubtype> incidenceSubtypeQuery = null;
		incidenceSubtypeQuery = em.createNamedQuery("IncidenceCauseSubtype.findByType", IncidenceCauseSubtype.class);
		if (types.length > 0) {
			result = new ArrayList<IncidenceCauseSubtype>();
			for (int i = 0; i < types.length; i++) {
				incidenceSubtypeQuery.setParameter("idStCauseType", Integer.parseInt(types[i]));
				result.addAll(incidenceSubtypeQuery.getResultList());
			}
		}
		return result;
	}

}
