package com.partha.gatewayService.model;

public class Partner {
	

	//private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String name;
	private String lineOfBusiness;
	private String remarks;
	
	public Partner() {
		super();
	}

	public Partner(Integer id, String name, String lineOfBusiness, String remarks) {
		super();
		this.id = id;
		this.name = name;
		this.lineOfBusiness = lineOfBusiness;
		this.remarks = remarks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLineOfBusiness() {
		return lineOfBusiness;
	}

	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	

}
