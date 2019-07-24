package edu.gimt.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.gimt.persistence.User;

/**
 * @author VICTOR
 *
 */
public class Authenticator {

	private EntityManager em = null;
	 
	/**
	 * @param em
	 */
	public Authenticator(EntityManager em) {
		this.em = em;
	}

	public UserBean authenticate(String username, String password) {
		
		UserBean userBean = null;		
		TypedQuery<User> userQuery = null;
		userQuery = em.createNamedQuery("User.findByNameAndPassword", User.class);
		userQuery.setParameter("userName", username);
		userQuery.setParameter("password", password);
		List<User> userList = userQuery.getResultList();
		
		if (null != userList && userList.size() == 1) {
			User user = (User)userList.get(0);
			userBean = new UserBean(user.getUsername(), user.getPassword(), user.getFullName());
		}

		// return bean
		return userBean;
	}
	

}