package com.huaxianvwa.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Relations between users and role.
 *
 * @author zsj
 * @date 2020/3
 */
@Entity
@Table(name = "admin_user_role")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * User id.
     */
    private int uid;

    /**
     * Role id.
     */
    private int rid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

	@Override
	public String toString() {
		return "AdminUserRole [id=" + id + ", uid=" + uid + ", rid=" + rid + "]";
	}
    
}
