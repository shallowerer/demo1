package com.huaxianvwa.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * @author zsj
 * @date 2020/3
 */
@Entity
@Table(name = "member")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String membername;
    private String memberaddr;
    private String memberno;
    private String truename;
    private String phone;
    private String email;
    private boolean enabled;
    private Integer age;
    private String province;
    private String city;
    private String area;
    private String town;
    private String road;
    private String myfloor;
    private String sex;
    private String account;
    private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public String getMemberaddr() {
		return memberaddr;
	}
	public void setMemberaddr(String memberaddr) {
		this.memberaddr = memberaddr;
	}
	public String getMemberno() {
		return memberno;
	}
	public void setMemberno(String memberno) {
		this.memberno = memberno;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public String getMyfloor() {
		return myfloor;
	}
	public void setMyfloor(String myfloor) {
		this.myfloor = myfloor;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", membername=" + membername + ", memberaddr=" + memberaddr + ", memberno="
				+ memberno + ", truename=" + truename + ", phone=" + phone + ", email=" + email + ", enabled=" + enabled
				+ ", age=" + age + ", province=" + province + ", city=" + city + ", area=" + area + ", town=" + town
				+ ", road=" + road + ", myfloor=" + myfloor + ", sex=" + sex + ", account=" + account + ", password="
				+ password + "]";
	}
    
   
	
}
