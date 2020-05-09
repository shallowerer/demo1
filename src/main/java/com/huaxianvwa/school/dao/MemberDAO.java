package com.huaxianvwa.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huaxianvwa.school.entity.Member;


public interface MemberDAO extends JpaRepository<Member, Integer>{
	Member findByMemberno(String memberno);
}
