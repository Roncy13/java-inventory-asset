package inventory.asset.pup;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Core.MySqlConfig;

public class UomModel extends MySqlConfig {

	public UomModel() {
		super("uoms");
	}
	
	public List<UomDao> getAll() {
		String query = String.format("SELECT * FROM %s", this.table);
		
		List<UomDao> uoms = new ArrayList<UomDao>();
		
		try {
			this.result = this.statement.executeQuery(query);
			
			while(this.result.next()) {
				UomDao uom = new UomDao();
				
				uom.amount = this.result.getFloat("amount");
				uom.id = this.result.getString("id");
				uom.description = this.result.getString("description");
				uom.created_at = new SimpleDateFormat("yyyy-MM-dd").parse(this.result.getString("created_at"));   ;
				uom.updated_at = new SimpleDateFormat("yyyy-MM-dd").parse(this.result.getString("updated_at"));
				
				uoms.add(uom);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return uoms;
	}
}
