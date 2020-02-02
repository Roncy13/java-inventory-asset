package inventory.asset.pup;

public class UomDao {
	
	private String id;
	private String description;
	private Float amount;
	private java.util.Date created_at;
	private java.util.Date updated_at;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public java.util.Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(java.util.Date created_at) {
		this.created_at = created_at;
	}
	public java.util.Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(java.util.Date updated_at) {
		this.updated_at = updated_at;
	}
}
