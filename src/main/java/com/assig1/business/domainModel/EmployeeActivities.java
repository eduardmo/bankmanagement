package com.assig1.business.domainModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmployeeActivities {
	@Id
	@Column
	private int activityId;
	@Column
	private long employeeActivityPersonalNumericalCode;
	@Column
	private String employeeActivity;
	@Column 
	private Date employeeActivityDate;
	
	private Date employeeActivityDate2;
	
	public long getEmployeeActivityPersonalNumericalCode() {
		return employeeActivityPersonalNumericalCode;
	}
	public void setEmployeeActivityPersonalNumericalCode(long employeeActivityPersonalNumericalCode) {
		this.employeeActivityPersonalNumericalCode = employeeActivityPersonalNumericalCode;
	}
	public String getEmployeeActivity() {
		return employeeActivity;
	}
	public void setEmployeeActivity(String employeeActivity) {
		this.employeeActivity = employeeActivity;
	}
	public Date getEmployeeActivityDate() {
		return employeeActivityDate;
	}
	public void setEmployeeActivityDate(Date date) {
		this.employeeActivityDate = date;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public Date getEmployeeActivityDate2() {
		return employeeActivityDate2;
	}
	public void setEmployeeActivityDate2(Date employeeActivityDate2) {
		this.employeeActivityDate2 = employeeActivityDate2;
	}
}
