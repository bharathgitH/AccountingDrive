package com.accounting_drive.company;

import java.util.Date;

import com.accounting_drive.user.Users;

public class Company {
	private String companyId;
	private String companyName;
	private Date createdOn;
	private Users createdBy;
	private Date modifiedOn;
	private Users modifiedBy;
	private Date deletedOn;
	private Users deletedBy;
	private int deleted;
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Users getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Users createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public Users getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Users modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getDeletedOn() {
		return deletedOn;
	}
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}
	public Users getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(Users deletedBy) {
		this.deletedBy = deletedBy;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
}
