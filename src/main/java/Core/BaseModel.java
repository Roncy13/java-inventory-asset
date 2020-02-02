package Core;

import java.sql.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseModel {
	
	private MySqlProperties properties = new MySqlProperties();
	protected Connection connection;
	protected Statement statement;
	protected String table;
	protected String selectQry = "";
	protected ResultSet result;
	protected List<String> columns = new ArrayList<String>();
	
	public BaseModel(String table) {
		this.table = table;
		this.selectQry = "*";
		
		this.initialize();
	}
	
	public BaseModel(String table, String fields) {
		this.table = table;
		this.selectQry = fields;
		
		this.initialize();
	}
	
	public void initialize() {
		try {
			// For DB model setup
			this.fetchDBConfig();
			this.setDbConfig();
			
			// Gets Columns
			this.setColumns();
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
	

	public void setColumns() {
		String query = selectQry.length() > 0 ? selectQry : "*";
		
		System.out.println(String.format("%s %s", query, this.table));
		
		ResultSet rs;
		try {
			rs = this.statement.executeQuery(String.format("SELECT %s FROM %s", query, this.table));
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int columnCount = rsmd.getColumnCount();
			
			for(int i = 1; i <= columnCount; i++) {
				String name = rsmd.getColumnName(i);
				this.columns.add(name);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public List<HashMap<String, String>> getAll() {
		String query = String.format("SELECT * FROM %s", this.table);
		
		List<HashMap<String, String>> uoms = new ArrayList<HashMap<String, String>>();
		
		try {
			this.result = this.statement.executeQuery(query);
			
			while(this.result.next()) {
				HashMap<String, String> hashMap = new HashMap<String, String>();
				
				for(int keyIndex = 0; keyIndex < this.columns.size(); keyIndex++) {
					String keyName = this.columns.get(keyIndex);
					
					hashMap.put(keyName, (this.result.getString(keyName)));
				}
				
				uoms.add(hashMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return uoms;
	}
}
