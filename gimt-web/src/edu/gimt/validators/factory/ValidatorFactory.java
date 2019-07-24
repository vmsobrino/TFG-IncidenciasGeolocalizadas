package edu.gimt.validators.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.gimt.persistence.Validator;
import edu.gimt.validators.IValidator;
import edu.gimt.validators.exception.ValidationException;

/**
 * Factory Class to create every system Validator.
 * @author Victor M. Sobrino - TFG
 */
public class ValidatorFactory {

	private static ValidatorFactory instance;

	/**
	 * Default constructor.
	 */
	private ValidatorFactory () {
	}

	/**
	 * Returns the instance (thread safe).
	 * @return The unique instance object of this Factory.
	 */
	public static ValidatorFactory getInstance() {

		if (instance == null) {
			synchronized (ValidatorFactory.class) {
				if (instance == null) {
					instance = new ValidatorFactory();
				}
			}
		}
		return instance;
	}

	/** 
	 * Returns validator objects from GIMT_VALIDATOR for requested class.
	 * @param em EntityManager object to access database. 
	 * @param className The string name for Class to validate. 
	 * @param validationObject instance of object for validation.
	 * @return Validators from GIMT_VALIDATOR wich service equals className (sort ascending) by reflection.
	 * @throws ValidationException Exception generate on error.
	 */
	public synchronized List<IValidator> getValidators(EntityManager em, String className, Object validationObject) throws ValidationException {

		List<IValidator> validatorList = new ArrayList<IValidator>();
		TypedQuery<Validator> query = null;
		List<Validator> validators = null;

		try {
			query = em.createNamedQuery("Validator.findByService", Validator.class);
			query.setParameter("service", className);
			validators = query.getResultList();
			for (Validator v : validators) {
				String stValidator = v.getValidator();
				String stParams = v.getParams();
				Class<?> clazz;
				try {
					// get class
					clazz = Class.forName(stValidator);
					if (IValidator.class.isAssignableFrom(clazz)) {
						// instanciate class
						Constructor<?> constructor = clazz.getConstructor(Object.class);
						Object validatorObject = constructor.newInstance(validationObject);
						// add to the list if is instanceof IValidator
						if (validatorObject instanceof IValidator) {
							IValidator validator = (IValidator) validatorObject;
							validator.setParams(stParams);
							validatorList.add(validator);
						}
					}
				}
				catch (ClassNotFoundException cnfe) {
					// La clase no existe
					throw new ValidationException("La clase no existe", cnfe);
				}
				catch (NoSuchMethodException nsme) {
					// El constructor de la clase no existe
					throw new ValidationException("El constructor de la clase no existe", nsme);
				}
				catch (InvocationTargetException ite) {
					// Error al invocar al constructor
					throw new ValidationException("Error al invocar al constructor", ite);
				}
				catch (IllegalAccessException iac) {
					// Error al invocar al constructor (private/protected?)
					throw new ValidationException("Error al invocar al constructor (private/protected?)", iac);
				}
				catch (InstantiationException ie) {
					// Error al instanciar la clase
					throw new ValidationException("Error al instanciar la clase", ie);
				}
			}
		}
		catch (Exception e) {
			// Error al obtener la conexion
			throw new ValidationException("Error conectando a base de datos..", e);
		}
		return validatorList;
	}


}
