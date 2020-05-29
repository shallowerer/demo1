package com.huaxianvwa.school.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FinanceModel {
	@Id
    private Integer id;
	private Integer year;
	private Integer month;
	private Integer day;
	private Float total;
	
	public FinanceModel(Integer id, Integer year, Integer month, Integer day, Float total) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.day = day;
		this.total = total;
	}
	
	public FinanceModel() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "FinanceModel [id=" + id + ", year=" + year + ", month=" + month + ", day=" + day + ", total=" + total
				+ "]";
	}
	
	
	
	

}
