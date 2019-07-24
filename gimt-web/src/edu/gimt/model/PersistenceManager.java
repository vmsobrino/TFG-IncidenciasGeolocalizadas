package edu.gimt.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Persistence Manager class.
 * @author Victor M. Sobrino - TFG
 *
 */
public enum PersistenceManager {
	INSTANCE;
	private EntityManagerFactory emFactory;
	private PersistenceManager() {
		// "tfg-jpa-gimt" was the value of the name attribute of the persistence-unit element.
		try {
			emFactory = Persistence.createEntityManagerFactory("tfg-jpa-gimt");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}
	public void close() {
		emFactory.close();
	}
	
}