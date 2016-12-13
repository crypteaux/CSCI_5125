public class Job {
	private String code;
	private String compId;
	private String jpCode;
	private String type;
  private String rate;
  private String payType;

	public String getCode() {
		return code;
	}

	public String getCompId() {
		return compId;
	}

	public String getjpCode() {
		return jpCode;
	}
  public String getType() {
    return type;
  }

	public String getRate() {
		return rate;
	}

  public String getPayType() {
    return payType;
  }

	public void setCode(String code) {
		this.code = code;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

  public void setjpCode(String jpCode) {
    this.jpCode = jpCode;
  }

	public void setType(String type) {
		this.type = type;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
  public void setPayType(String payType) {
    this.payType = payType;
  }
}
