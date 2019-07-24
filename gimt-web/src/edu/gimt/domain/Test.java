package edu.gimt.domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Test {

	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("https://www.trafikoa.eus/servicios/IncidenciasTDT/IncidenciasTrafikoTDTHist?fechaIni=20060101&fechaFin=20060131");
			String file = "D:/CAG/TFG/workspace/tmp/prueba.xml";
			Test.foo(url, file);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public static final void foo(URL url, String file){
		BufferedReader in;
		String readLine;
		try {
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
			
			InputStreamReader isr = new InputStreamReader(url.openStream());
			String encoding = isr.getEncoding(); //if you actually need it, which I don't suppose
			System.out.println(isr.getEncoding());
			in = new BufferedReader(isr);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file) , "windows-1252"));
			while ((readLine = in.readLine()) != null) {
				out.write(readLine + "\n");
			}
			out.flush();
			out.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
