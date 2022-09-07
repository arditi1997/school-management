//package com.schoolManagement.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.GenericGenerator;
//
//@Entity
//@Table(name="parents")
//public class Parents {
//
//	@Id
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid2")
//	@Column(name="parentId")
//	private String parentId;
//	@Column(name="parentEmail")
//	private String parentEmail;
//	@Column(name="ParentName")
//	private String parentName;
//	@Column(name="parentSurname")
//	private String parentSurname;
//	@Column(name="parentPassword")
//	private String parentPassword;
//	@Column(name="parentgender")
//	private String parentGender;
//	@Column(name = "file_name")
//	private String fileName;
//	@Column(name = "file_type")
//	private String fileType;
//	@Column(name = "data")
//	private byte[] data;
//	@Column(name="parentNationality")
//	private String parentNationality;
//	@Column(name="parentaddress")
//	private String parentAddress;
//	@Column(name = "reset_token")
//	private String resetToken;
//	
//	@ManyToOne
//	@JoinColumn
//	private Students students;
//
//	public Parents() {
//	}
//
//	public String getParentId() {
//		return parentId;
//	}
//
//	public void setParentId(String parentId) {
//		this.parentId = parentId;
//	}
//
//	public String getParentEmail() {
//		return parentEmail;
//	}
//
//	public void setParentEmail(String parentEmail) {
//		this.parentEmail = parentEmail;
//	}
//
//	public String getParentName() {
//		return parentName;
//	}
//
//	public void setParentName(String parentName) {
//		this.parentName = parentName;
//	}
//
//	public String getParentSurname() {
//		return parentSurname;
//	}
//
//	public void setParentSurname(String parentSurname) {
//		this.parentSurname = parentSurname;
//	}
//
//	public String getParentPassword() {
//		return parentPassword;
//	}
//
//	public void setParentPassword(String parentPassword) {
//		this.parentPassword = parentPassword;
//	}
//
//	public String getParentGender() {
//		return parentGender;
//	}
//
//	public void setParentGender(String parentGender) {
//		this.parentGender = parentGender;
//	}
//
//	public String getFileName() {
//		return fileName;
//	}
//
//	public void setFileName(String fileName) {
//		this.fileName = fileName;
//	}
//
//	public String getFileType() {
//		return fileType;
//	}
//
//	public void setFileType(String fileType) {
//		this.fileType = fileType;
//	}
//
//	public byte[] getData() {
//		return data;
//	}
//
//	public void setData(byte[] data) {
//		this.data = data;
//	}
//
//	public String getParentNationality() {
//		return parentNationality;
//	}
//
//	public void setParentNationality(String parentNationality) {
//		this.parentNationality = parentNationality;
//	}
//
//	public String getParentAddress() {
//		return parentAddress;
//	}
//
//	public void setParentAddress(String parentaddress) {
//		this.parentAddress = parentaddress;
//	}
//
//	public String getResetToken() {
//		return resetToken;
//	}
//
//	public void setResetToken(String resetToken) {
//		this.resetToken = resetToken;
//	}
//
//	public Students getStudents() {
//		return students;
//	}
//
//	public void setStudents(Students students) {
//		this.students = students;
//	}
//	
//}
