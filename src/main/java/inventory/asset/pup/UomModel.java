package inventory.asset.pup;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Core.BaseModel;

public class UomModel extends BaseModel {
	
	List<String> columns = new ArrayList<String>();
	String selectQry = "";

	public UomModel() {
		super("uoms");
		
		this.selectQry = "id, amount, description";
		this.setColumnCount();
	}

	
	public void setColumnCount() {
		String query = selectQry.length() > 0 ? selectQry : "*";
		
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
