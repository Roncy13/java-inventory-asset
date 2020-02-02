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
	
	public UomModel() {
		super("uoms", "id, amount, description, updated_at");
	}
}
