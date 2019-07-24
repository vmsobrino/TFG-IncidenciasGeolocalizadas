package edu.gimt.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.gimt.model.Authenticator;
import edu.gimt.model.GeoIncidenceModelJpa;
import edu.gimt.model.IncidenceTypeModelJpa;
import edu.gimt.model.PersistenceManager;
import edu.gimt.model.ProvinceModelJpa;
import edu.gimt.model.UserBean;
import edu.gimt.view.GeoIncidenceView;
import edu.gimt.view.IncidenceTypeView;
import edu.gimt.view.ProvinceView;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginControllerSinSTruts")
public class LoginControllerSinSTruts extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private EntityManager em = null;

    /**
     * Default constructor. 
     */
    public LoginControllerSinSTruts() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String inputUser = request.getParameter("inputUser");
	        String inputPassword = request.getParameter("inputPassword");
	                
			Authenticator authenticator = new Authenticator(em);
			UserBean userBean = null;
			userBean = authenticator.authenticate(inputUser, inputPassword);
			
			if (null != userBean) { // user logged
				HttpSession session = request.getSession();
				session.setAttribute("user", userBean.getUsername());
				// setting session expire in 30 mins.
				session.setMaxInactiveInterval(30 * 60);
				Cookie userName = new Cookie("user", userBean.getUsername());
				response.addCookie(userName);
				// Load data into request for drop-downs: provinces
				ProvinceModelJpa pm = new ProvinceModelJpa(em);
				ProvinceView pv = new ProvinceView(pm.getProvinces());
				request.setAttribute("ProvinceView", pv);
				// incidence types
				IncidenceTypeModelJpa im = new IncidenceTypeModelJpa(em);
				IncidenceTypeView itv = new IncidenceTypeView(im.getIncidenceTypes());
				request.setAttribute("IncidenceTypeView", itv);
				// rest of incidence search criteria
				GeoIncidenceModelJpa gim = new GeoIncidenceModelJpa(em);
				GeoIncidenceView giv = new GeoIncidenceView();
				giv.setDistinctYears(gim.getDistinctYears());
				request.setAttribute("GeoIncidenceView", giv);
				// Get the encoded URL string
		        request.getRequestDispatcher("./index.jsp").forward(request, response);
			}
			else {
				request.setAttribute("error", "Usuario / Password inválido.");
		        request.getRequestDispatcher("./login.jsp").forward(request, response);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public void init() throws ServletException {
		super.init();
		// Gestor de persistencia BBDD
		em = PersistenceManager.INSTANCE.getEntityManager();
	}

}

