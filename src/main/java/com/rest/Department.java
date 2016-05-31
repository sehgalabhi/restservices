package com.rest;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "DEPARTMENTS")
@XmlRootElement
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Short getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Short departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Short getLocationId() {
		return locationId;
	}
	public void setLocationId(Short locationId) {
		this.locationId = locationId;
	}
	
	@Override
	public String toString(){
		return this.departmentId + this.managerId+this.departmentName+this.locationId;
	}
	
	
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "DEPARTMENT_ID")
	private Short departmentId;

	@NotNull
	@Basic(optional = false)
	@Size(min = 1, max = 30)
	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;

	@Column(name = "MANAGER_ID")
	private Integer managerId;
	@Column(name = "LOCATION_ID")
	private Short locationId;
}
