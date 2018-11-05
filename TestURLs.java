/**
 * 
 */
package testurls;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** TestURLs.java
 * @author gvillahermosac
 *
 */
public class TestURLs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        HttpURLConnection httpConn = null;
        List<String> urls = leerUrls();
        for (String url : urls) {
        	if (!"".equals(url)) {
                String tabulador = "";
                if (url.length() < 88 ) tabulador = "\t";
                if (url.length() < 80 ) tabulador = "\t\t";
                if (url.length() < 72 ) tabulador = "\t\t\t";
                if (url.length() < 64 ) tabulador = "\t\t\t\t";
                if (url.length() < 56 ) tabulador = "\t\t\t\t\t";
                if (url.length() < 48 ) tabulador = "\t\t\t\t\t\t";
                if (url.length() < 40 ) tabulador = "\t\t\t\t\t\t\t";
                if (url.length() < 32 ) tabulador = "\t\t\t\t\t\t\t\t";
                if (url.length() < 24 ) tabulador = "\t\t\t\t\t\t\t\t\t";
                if (url.length() < 16 ) tabulador = "\t\t\t\t\t\t\t\t\t\t";
		        try {
		            // Comprobación de disponibilidad del sistema externo
		            URL urlSolicitud = new URL(url);
		            Date inicio = new Date();
		            java.net.URLConnection conn = urlSolicitud.openConnection();
		            if (conn instanceof HttpURLConnection) {
		                httpConn = (HttpURLConnection) conn;
		                httpConn.setReadTimeout(3000);
		                int responseCode = httpConn.getResponseCode();
			            Date fin = new Date();
		                if (responseCode == HttpURLConnection.HTTP_OK) {
		                	System.out.println(url + tabulador+"--> " + responseCode + " OK ("+(fin.getTime()-inicio.getTime())+"ms)");
		                } else {
		                	System.out.println(url + tabulador+"--> " + responseCode + " ERROR DE CONEXIÓN!!");
		                }
		            }
		           
		        } catch (ConnectException ce) {
                	System.out.println(url + tabulador+"--> ERROR DE CONEXIÓN!! " + ce.getMessage());
		        } catch (MalformedURLException mue) {
                	System.out.println(url + tabulador+"--> ERROR DE CONEXIÓN!! " + mue.getMessage());
		        } catch (IOException ioe) {
                	System.out.println(url + tabulador+"--> ERROR DE CONEXIÓN!! " + ioe.getMessage());
		        } finally {
		        	if (httpConn!=null){
		        		httpConn.disconnect();
		        	}
		        }
        	} else {
        		System.out.println();
        	}
		}
    }
    
    private static List<String> leerUrls() {
    	List<String> result = new ArrayList<String>();
    	FileReader fr = null;
    	BufferedReader br = null;
    	try {
	    	File archivo = new File ("testurls/urls.txt");
	    	fr = new FileReader(archivo);
	    	br = new BufferedReader(fr);
	    	String nuevaLinea = br.readLine();
	    	while(nuevaLinea != null) {
	    		result.add(nuevaLinea);
	    		nuevaLinea = br.readLine();
	    	}
    	} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} finally {
			try {
				if (null != br) br.close();
				if (null != fr) fr.close();
			} catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			}
		}
    	return result;
    }

}
