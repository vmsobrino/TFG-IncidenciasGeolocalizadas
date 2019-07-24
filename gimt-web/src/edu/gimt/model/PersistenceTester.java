package edu.gimt.model;


public class PersistenceTester {

	public static void main(String[] args) {
		
//		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
//		
//		// *** INSERT & UPDATE *** */
//		Community community = new Community();
//		community.setIdCommunity(20);
//		community.setCommunityName("Comunidad Prueba");
//		community.setEnabled(0);
//
//		EntityTransaction et = em.getTransaction();
//		et.begin();
//		em.persist(community);
//		et.commit();
//		//em.close();
//		
//		
//		// *** SELECT ALL *** */
//		TypedQuery<Community> query = em.createQuery("SELECT c FROM Community c", Community.class);
//		List<Community> communities = query.getResultList();
//		for (Community c : communities) {
//		      System.out.println("ID Community: " + c.getIdCommunity());
//		      System.out.println("  Comm. Name: " + c.getCommunityName());
//		      System.out.println("     Enabled: " + c.getEnabled() + "\n");
//		}
//		
//
//		// *** DELETE *** */
//		et.begin();
//		em.remove(community);
//		et.commit();
//		em.close();
//		
//		PersistenceManager.INSTANCE.close();
		
	}

}
