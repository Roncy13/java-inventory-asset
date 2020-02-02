package Core;

import java.sql.*;
import java.text.SimpleDateFormat;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import inventory.asset.pup.UomDao;

public class BaseModel {
	
	private MySqlProperties properties = new MySqlProperties();
	protected Connection connection;
	protected Statement statement;
	protected String table;
	protected ResultSet result;
	
	public BaseModel(String table) {
		
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
	
			inputStream = BaseModel.class.getClassLoader().getResourceAsStream(propFileName);
	
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
	
	protected ResultSet fetchAll() {
		String query = String.format("SELECT * FROM %s", this.table);
		
		try {
			this.result = this.statement.executeQuery(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.result;
	}
}
