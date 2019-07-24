/**
 * 
 */
package edu.gimt.domain;

import javax.persistence.EntityManager;

import edu.gimt.model.PersistenceManager;

/**
 * @author VICTOR
 *
 */
public class GimtMain {
	
	private EntityManager em = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GimtMain gimt = new GimtMain();
		gimt.downloadAndRead();
	}
	
	private void initialize() {
		// Database persistence manager
		em = PersistenceManager.INSTANCE.getEntityManager();
	}
	
	private void close() {
		if (null != em && em.isOpen()) {
			em.close();
		}
		if (PersistenceManager.INSTANCE != null) {
			PersistenceManager.INSTANCE.close();
		}
	}
	
	public void downloadAndRead() {
		DownloadXmlFile downloader = new DownloadXmlFile();
		XMLFileReader reader = new XMLFileReader();
		
		try {
			initialize();
			// Download pending incidences
			if (downloader.download(em)) {
				System.out.println("Main - Descarga de incidencias XML pendientes correcta.");
				// Read and save XML downloaded files
				if (reader.validateAndSave(em)) {
					System.out.println("Main - Carga en el sistema de incidencias XML pendientes correcta.");
				}
			}
		}
		catch (Exception e) {
			System.out.println("Error en downloadAndRead()");
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

}
