package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;


public class API {

	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

	/**
	 * method: call method get to the api
	 * @param url
	 * @param token
	 * @return string response from api
	 * @throws Exception
	 */
	public static String get(String url, String token) throws Exception {
		
		//set up
		HttpURLConnection conn = setupConnection(url, "GET", token);
		
		//read response
		return readResponse(conn);
	}

	/**
	 * method read data from server return
	 * @param conn
	 * @return string from server
	 * @throws IOException
	 */
	private static String readResponse(HttpURLConnection conn) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder respone = new StringBuilder(); // using StringBuilder for the sake of memory and performance
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		respone.append(inputLine + "\n");
		in.close();
		LOGGER.info("Respone Info: " + respone.substring(0, respone.length() - 1).toString());
		return respone.substring(0, respone.length() - 1).toString();
	}

	/**
	 * method establish connection to server
	 * @param url
	 * @param method
	 * @param token
	 * @return connection
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ProtocolException
	 */
	private static HttpURLConnection setupConnection(String url, String method, String token)
			throws MalformedURLException, IOException, ProtocolException {
		LOGGER.info("Request URL: " + url + "\n");
		URL line_api_url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + token);
		return conn;
	}

	int var;

	/**
	 * method call post api
	 * @param url
	 * @param data
	 * @param token
	 * @return response from server
	 * @throws IOException
	 */
	public static String post(String url, String data, String token) throws IOException {
		allowMethods("PATCH");
		
		//set up
		HttpURLConnection conn = setupConnection(url, "POST" ,token);
		
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(data);
		writer.close();
		
		//read response
		return readResponse(conn);
	}

	
	/**
	 * @param methods
	 */
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}
