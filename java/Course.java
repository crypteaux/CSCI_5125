public class Course {
	private String code;
	private String title;
	private String description;
	private String level;
	private String status;
	private double retailPrice;

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getLevel() {
		return level;
	}

	public String getStatus() {
		return status;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
}
