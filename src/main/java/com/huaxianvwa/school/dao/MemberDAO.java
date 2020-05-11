package com.huaxianvwa.school.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.huaxianvwa.school.entity.Member;


public interface MemberDAO extends JpaRepository<Member, Integer>{
	
	@Query(value = "from Member m where m.truename like %?1%")
	List<Member> findByTruenameLike(String name);
	
	@Query(value = "from Member m where m.memberno like %?1%")
	List<Member> findByMembernoLike(String memberno);
	
	@Query(value = "from Member m where m.phone like %?1%")
	List<Member> findByPhoneLike(String phone);
	
	Member findByMemberno(String memberno);
}
