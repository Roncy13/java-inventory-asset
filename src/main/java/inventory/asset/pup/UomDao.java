package inventory.asset.pup;

import javax.validation.constraints.NotNull;

public class UomDao {
	
	@NotNull
	public String id;
	@NotNull
	public String description;
	public Float amount;
	public java.util.Date created_at;
	public java.util.Date updated_at;
}
