package edu.gimt.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import edu.gimt.common.Constants;
import edu.gimt.common.util.Utils;
import edu.gimt.persistence.CfgParam;
import edu.gimt.persistence.DownloadedFile;
 

/**
 * Class to download XML file from OpenData Website.
 * @author Victor M. Sobrino - TFG
 */
public class DownloadXmlFile {
		
	/**
	 * Class constructor.
	 */
	public DownloadXmlFile() {
	}

	/**
	 * Dowonload pending geo-incidence XML files from server.
	 * @param em Entity Manager for DataBase.
	 * @return True if success, False otherwise.
	 */
	@SuppressWarnings("finally")
	public boolean download(EntityManager em) {
		boolean done = true;
		URL url = null;
		String xmlFilePath = null;
		String xmlFileName = null;
		File xmlFile = null;
		URLConnection urlCon = null;
		TypedQuery<CfgParam> query = null;
		List<CfgParam> cfgParams = null;
		StringBuilder urlString = null;
		StringBuilder urlParams = null;
		String iniDateParam = null;
		String endDateParam = null;
		Date firstDate = null;
		Date lastDate = null;
		Calendar initialDate = Calendar.getInstance();
		Calendar finalDate = Calendar.getInstance();
		Calendar eomDate = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		TypedQuery<DownloadedFile> downloadedFileQuery = null;
		List<DownloadedFile> downloadedFileList = null;
		final int MAX_RETRY = 5;
		
		try {
			// Consulta a BBDD
			query = em.createNamedQuery("CfgParam.findByModule", CfgParam.class);
			query.setParameter("codModule", Constants.MODULE_INC_DOWNLOAD);
			cfgParams = query.getResultList();
			
			for (CfgParam cp : cfgParams) {
				if (null != cp.getParamValue() ) {
					switch (cp.getCodParam()) {
					case Constants.PUBLIC_INC_URL: // Obtenemos la URL de la web de publicacion
						urlString = new StringBuilder(cp.getParamValue());
						break;
	
					case Constants.INC_INI_DATE_PARAM: // Nombre parametro Fecha Inicio
						iniDateParam = cp.getParamValue();
						break;
						
					case Constants.INC_END_DATE_PARAM: // Nombre parametro Fecha Fin
						endDateParam = cp.getParamValue();
						break;
						
					case Constants.TMP_FILE_PATH: // Directorio temporal descarga
						xmlFilePath = cp.getParamValue();
						break;
						
					case Constants.INC_FIRST_DATE: // Primera fecha historica de incidencias
						if (Utils.validDate(cp.getParamValue())) {
							firstDate = Utils.getDate(cp.getParamValue());
						}
						break;
	
					case Constants.INC_LAST_DATE: // Última fecha de incidencias recuperada
						if (Utils.validDate(cp.getParamValue())) {
							lastDate = Utils.getDate(cp.getParamValue());
						}
						break;
					}
				}
			}
			
			if (null == urlString || null == iniDateParam || null == endDateParam || null == xmlFilePath || null == firstDate) {
				System.out.println("Faltan parametros de configuración en BBDD...");
				xmlFile = null; // No podemos obtener fichero, se devuelve nulo.
			}
			else {
				if (null == lastDate) { // Recuperar desde 2006 hasta ultimo dia de mes anterior (hoy si es ultimo dia de mes)
					// fecha inicial: 2006-01-01 --> firstDate
					// fecha final: hasta fin mes actual o anterior
					int lastDayOfMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
					int currentDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
					if (currentDayOfMonth == lastDayOfMonth) { // hoy es el ultimo dia del mes actual
						lastDate = Calendar.getInstance().getTime();
					}
					else { // Último dia del mes anterior
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MONTH, -1);
						cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
						lastDate = cal.getTime();
					}
				}
				else { // Hay registros anteriores
					// fecha inicial: día siguiente a lastDate
					Calendar cal = Calendar.getInstance();
					cal.setTime(lastDate);
					cal.add(Calendar.DAY_OF_MONTH, 1);
					firstDate = cal.getTime();
					// fecha final: último día mes anterior al actual, se descarga el mes vencido
					cal = Calendar.getInstance();
					cal.add(Calendar.MONTH, -1);
					cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
					lastDate = cal.getTime();
				}
				
				initialDate.setTime(firstDate);
				initialDate.set(Calendar.HOUR_OF_DAY, 0);
				initialDate.set(Calendar.MINUTE, 0);
				initialDate.set(Calendar.SECOND, 0);
				initialDate.set(Calendar.MILLISECOND, 0);
				
				finalDate.setTime(lastDate);
				finalDate.set(Calendar.HOUR_OF_DAY, 0);
				finalDate.set(Calendar.MINUTE, 0);
				finalDate.set(Calendar.SECOND, 0);
				finalDate.set(Calendar.MILLISECOND, 0);
				
				while (initialDate.before(finalDate)) {
					eomDate.setTime(initialDate.getTime());
					eomDate.set(Calendar.DAY_OF_MONTH, eomDate.getActualMaximum(Calendar.DAY_OF_MONTH));
					eomDate.set(Calendar.HOUR_OF_DAY, 23);
					eomDate.set(Calendar.MINUTE, 59);
					eomDate.set(Calendar.SECOND, 59);
					
					// Create a new trust manager that trust all certificates
					TrustManager[] trustAllCerts = new TrustManager[] {
					    new X509TrustManager() {
					        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					            return null;
					        }
					        public void checkClientTrusted(
					            java.security.cert.X509Certificate[] certs, String authType) {
					        }
					        public void checkServerTrusted(
					            java.security.cert.X509Certificate[] certs, String authType) {
					        }
					    }
					};

					// Activate the new trust manager
					try {
					    SSLContext sc = SSLContext.getInstance("SSL");
					    sc.init(null, trustAllCerts, new java.security.SecureRandom());
					    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
					} catch (Exception e) {
						e.printStackTrace();
					}					
					
					// Build URL
					urlParams = new StringBuilder();
					urlParams.append("?");
					urlParams.append(iniDateParam);
					urlParams.append("=");
					urlParams.append(dateFormat.format(initialDate.getTime()));
					urlParams.append("&");
					urlParams.append(endDateParam);
					urlParams.append("=");
					urlParams.append(dateFormat.format(eomDate.getTime()));
					url = new URL(urlString.toString() + urlParams.toString());
					
					InputStream is = null;
					FileOutputStream fos = null;
					boolean download = false;
					for (int retryTimes = 0; retryTimes < MAX_RETRY && !download; retryTimes++) { // try download file
						try {
							// establecemos conexion
							urlCon = url.openConnection();
							// Se obtiene el inputStream del recurso web y se abre el fichero local
							is = urlCon.getInputStream();
							// Mostramos el tipo de fichero a descargar
							System.out.println("Downloading from " + dateFormat.format(initialDate.getTime()) + " to " + dateFormat.format(eomDate.getTime()));
							// Abrimos el stream de salida
							xmlFileName = "IncTDTgeo" + dateFormat.format(initialDate.getTime()) + "-" + dateFormat.format(eomDate.getTime()) + ".xml";
							xmlFile = new File(xmlFilePath + "/" + xmlFileName);
							// si existe sobreescribimos
							if (xmlFile.exists()) {
								boolean deleted = Files.deleteIfExists(xmlFile.toPath());
								if (deleted) {
									xmlFile = new File(xmlFilePath + "/" + xmlFileName);
								}
							}
							fos = new FileOutputStream(xmlFile);
							// Escribimos sobre el archivo
							byte[] array = new byte[1000]; // buffer temporal de lectura.
							int leido = is.read(array);
							while (leido > 0) {
								fos.write(array, 0, leido);
								leido = is.read(array);
							}
							// cierre de conexion y fichero.
							is.close();
							fos.close();
							download = true;
							// format xml file
							prettyXmlFile(xmlFile);
						}
						catch (IOException ioe) {
							retryTimes++;
							System.out.println(ioe.getMessage());
							System.out.println("-----------");
							System.out.println(ioe.getLocalizedMessage());
							ioe.printStackTrace();
							if (null != is) {
								is.close();
							}
							if (null != fos) {
								fos.close();
							}
							ioe.printStackTrace();
						}
					} // loop for retries
					
					if (download) {
						// registramos el fichero descargado
						downloadedFileQuery = em.createNamedQuery("DownloadedFile.findByFilename", DownloadedFile.class);
						downloadedFileQuery.setParameter("fileName", xmlFileName);
						downloadedFileList = downloadedFileQuery.getResultList();
						em.getTransaction().begin();
						if (downloadedFileList.size() > 0) { // ya existe, actualizamos
							for (DownloadedFile df : downloadedFileList) {
								df.setProcessed(0);
								df.setDownloadDatetime(Calendar.getInstance().getTime());
								em.persist(df);
							}
						}
						else { // insertamos
							DownloadedFile df = new DownloadedFile();
							df.setFileName(xmlFileName);
							df.setInitialDate(initialDate.getTime());
							df.setFinalDate(eomDate.getTime());
							df.setProcessed(0);
							em.persist(df);
						}
						// Registramos la fecha final del último fichero descargado
						query = em.createNamedQuery("CfgParam.findByModuleAndParam", CfgParam.class);
						query.setParameter("codModule", Constants.MODULE_INC_DOWNLOAD);
						query.setParameter("codParam", Constants.INC_LAST_DATE);
						cfgParams = query.getResultList();
						for (CfgParam cp : cfgParams) {
							cp.setParamValue(Utils.getStringDate(eomDate.getTime()));
							em.persist(cp);
						}					
						em.getTransaction().commit();						
					}
					else {
						System.out.println("ERROR: Archivo " + xmlFileName + " no ha sido posible descargar tras " + MAX_RETRY + " intentos.");
					}
					// incrementamos la fecha
					initialDate.add(Calendar.MONTH, 1);
				}
				
			}
			// cierre entity-manager
//			em.close();
//			PersistenceManager.INSTANCE.close();

		} catch (Exception e) {
			done = false;
			e.printStackTrace();
//			if (em.isOpen()) {
//				em.close();
//				PersistenceManager.INSTANCE.close();
//			}
		}
		finally {
			return done;
		}
	} // download()

	private void prettyXmlFile(File xmlFile) throws Exception {
		try {
			// Replace bad characters !!
			Charset charset = StandardCharsets.ISO_8859_1;
			String content = new String(Files.readAllBytes(xmlFile.toPath()), charset);
			
			content = content.replace("Ã¡", "á");
			content = content.replace("Ã©", "é");
			content = content.replace("Ã­", "í");
			content = content.replace("Ã³", "ó");
			content = content.replace("Ã±", "ñ");
			content = content.replace("Ãº", "ú");
			content = content.replace("Ã¼", "ü");

//			content = content.replace("Ã&#129;", "Á");
//			content = content.replace("Ã&#141;", "Í");
//			content = content.replace("Ã&#145;", "Ñ");
//			content = content.replace("Ã&#154;", "Ú");
//			content = content.replace("Ã&#147;", "Ó");
			
			content = content.replace("Ã&#129;", "A");
			content = content.replace("Ã&#141;", "I");
			content = content.replace("Ã&#145;", "N");
			content = content.replace("Ã&#154;", "U");
			content = content.replace("Ã&#147;", "O");
			
			Files.write(xmlFile.toPath(), content.getBytes(charset));
			
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        dbf.setValidating(false);
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document doc = db.parse(new FileInputStream(xmlFile));
	        System.out.println(doc.getXmlEncoding());

	        Transformer tf = TransformerFactory.newInstance().newTransformer();
	        tf.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
	        tf.setOutputProperty(OutputKeys.INDENT, "yes");
	        tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
	        StreamResult out = new StreamResult(xmlFile);
	        tf.transform(new DOMSource(doc), out);			
		}
		catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
			e.printStackTrace();
			throw(e);
		}
	}

}
