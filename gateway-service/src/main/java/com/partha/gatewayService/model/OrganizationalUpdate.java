package com.partha.gatewayService.model;

public class OrganizationalUpdate {
	
	private String updateHeading;
	private String updateDesc;
		
	public OrganizationalUpdate() {
		super();
	}
	
	public OrganizationalUpdate(String updateHeading, String updateDesc) {
		super();
		this.updateHeading = updateHeading;
		this.updateDesc = updateDesc;
	}



	public String getUpdateHeading() {
		return updateHeading;
	}
	public void setUpdateHeading(String updateHeading) {
		this.updateHeading = updateHeading;
	}
	public String getUpdateDesc() {
		return updateDesc;
	}
	public void setUpdateDesc(String updateDesc) {
		this.updateDesc = updateDesc;
	}
	
	

}
