package com.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="BorrowingRecord")
public class BorrowingRecord {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Borrowing_Record_Id")
	private Integer borrowingRecordId;

	@Column(name = "User_Id")
	private Integer userId;
	
	@Column(name = "Inventory_Id")
	private Integer inventoryId;
	
	@Column(name = "Borrowing_Time")
	private Date borrowingTime;

	@Column(name = "Return_Time")
	private Date returnTime;

	public Integer getBorrowingRecordId() {
		return borrowingRecordId;
	}

	public void setBorrowingRecordId(Integer borrowingRecordId) {
		this.borrowingRecordId = borrowingRecordId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Date getBorrowingTime() {
		return borrowingTime;
	}

	public void setBorrowingTime(Date borrowingTime) {
		this.borrowingTime = borrowingTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BorrowingRecord [borrowingRecordId=" + borrowingRecordId + ", userId=" + userId + ", inventoryId="
				+ inventoryId + ", borrowingTime=" + borrowingTime + ", returnTime=" + returnTime + "]";
	}

	
	
	
	
}
