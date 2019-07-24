package edu.gimt.domain;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;

import java.net.URL;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import javax.xml.transform.*;

import java.io.*;

import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

public class AnotherTest {

	public static void main(String[] args){

		try{	
			String pre_apiURL = "https://www.trafikoa.eus/servicios/IncidenciasTDT/IncidenciasTrafikoTDTHist?fechaIni=20060101&fechaFin=20060131";        
			System.out.println("url "+ pre_apiURL);
			URL url = new URL(pre_apiURL);

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

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(url.openStream());

			printDocument(doc, System.out);

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void printDocument(Document doc, OutputStream out) throws IOException, TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		//		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		//		transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");

		transformer.transform(new DOMSource(doc), 
//			new StreamResult(new OutputStreamWriter(out, "UTF-8")));
			new StreamResult(new OutputStreamWriter(out, "ISO-8859-1")));
	}
	
	/**
	 * Here doc is your document

	TransformerFactory tf = TransformerFactory.newInstance();
	Transformer transformer = tf.newTransformer();
	transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	StringWriter writer = new StringWriter();
	transformer.transform(new DOMSource(doc), new StreamResult(writer));
	String output = writer.getBuffer().toString().replaceAll("\n|\r", "");

	 */

}
