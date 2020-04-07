package com.huaxianvwa.school.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "commodity")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Commodity {
    @ManyToOne
    @JoinColumn(name="cate_id")
    private Category category;
	
	@Id
	private Integer id;
	private String title;
	private String desc;
	private String cover;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	@Override
	public String toString() {
		return "Commodity [id=" + id + ", title=" + title + ", desc=" + desc + ", cover=" + cover + "]";
	}

	

}
