package Core;

import java.sql.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class MySqlConfig {
	
	private MySqlProperties properties = new MySqlProperties();
	protected Connection connection;
	protected Statement statement;
	protected String table;
	protected ResultSet result;
	
	public MySqlConfig(String table) {
		
		this.table = table;
		try {
			this.fetchDBConfig();
			this.setDbConfig();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void fetchDBConfig() throws IOException {
		InputStream inputStream = null;
		String result;
		try {
			Properties prop = new Properties();
			String propFileName = "database.properties";
	
			inputStream = MySqlConfig.class.getClassLoader().getResourceAsStream(propFileName);
	
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
	
			// get the property value and save it
			String url = prop.getProperty("datasource.url");
			String username = prop.getProperty("datasource.username");
			String password = prop.getProperty("datasource.password");
			String driver = prop.getProperty("datasource.driver-class-name");
			
			properties.setUrl(url);
			properties.setPassword(password);
			properties.setUsername(username);
			properties.setDriver(driver);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			inputStream.close();
		}
	}
	
	private void setDbConfig() {
		
		String url = properties.getUrl();
		String username = properties.getUsername();
		String password = properties.getPassword();
		String driver = properties.getDriver();
		
		 try {
			 Class.forName(driver);
			 this.connection = DriverManager.getConnection(url, username, password);
			 this.statement = this.connection.createStatement();  
	     }
		 catch(Exception e) {
	         e.printStackTrace();
	     }
	}
}
