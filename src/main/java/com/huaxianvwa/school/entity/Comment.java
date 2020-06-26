package com.huaxianvwa.school.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "comment")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
	private Integer servicestar;
	private Integer costar;
	private Integer roomstar;
	private String givecontent;
	private String no;
	private String name;
	
	@JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date givetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getServicestar() {
		return servicestar;
	}

	public void setServicestar(Integer servicestar) {
		this.servicestar = servicestar;
	}

	public Integer getCostar() {
		return costar;
	}

	public void setCostar(Integer costar) {
		this.costar = costar;
	}

	public Integer getRoomstar() {
		return roomstar;
	}

	public void setRoomstar(Integer roomstar) {
		this.roomstar = roomstar;
	}

	public String getGivecontent() {
		return givecontent;
	}

	public void setGivecontent(String givecontent) {
		this.givecontent = givecontent;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getGivetime() {
		return givetime;
	}

	public void setGivetime(Date givetime) {
		this.givetime = givetime;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", servicestar=" + servicestar + ", costar=" + costar + ", roomstar=" + roomstar
				+ ", givecontent=" + givecontent + ", no=" + no + ", name=" + name + ", givetime=" + givetime + "]";
	}
	
	
	
}
