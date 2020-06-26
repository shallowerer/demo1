package com.huaxianvwa.school.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.huaxianvwa.school.entity.Member;


public interface MemberDAO extends JpaRepository<Member, Integer>{
	@Query(value = "from Member m where m.account =?1 ")
	public List<Member> findByAccount(String account);
	
	@Query(value = "from Member m where m.truename like %?1%")
	public List<Member> findByTruenameLike(String name);
	
	@Query(value = "from Member m where m.memberno like %?1%")
	public List<Member> findByMembernoLike(String memberno);
	
	@Query(value = "from Member m where m.phone like %?1%")
	public List<Member> findByPhoneLike(String phone);
	
	public Member findByMemberno(String memberno);
	
	
//	获取地址详情
	@Query(value = "SELECT m.province FROM member m WHERE m.id in (SELECT mid FROM commodity_order) GROUP BY m.province",nativeQuery = true)
	public List<Object> getAllProvince();
	
//	获取地址详情
	@Query(value = "SELECT city FROM member m where province=?1 and  m.id in (SELECT mid FROM commodity_order) GROUP BY city",nativeQuery = true)
	public List<Object> getAllCity(String province);

	@Query(value = "SELECT area FROM member m where province=?1 and city=?2 and m.id in (SELECT mid FROM commodity_order) GROUP BY area",nativeQuery = true)
	public List<Object> getAllArea(String province, String city);

	@Query(value = "SELECT town FROM member m where province=?1 and city=?2 and area=?3 and  m.id in (SELECT mid FROM commodity_order) GROUP BY town",nativeQuery = true)
	public List<Object> getAllTown(String province, String city, String area);

	@Query(value = "SELECT road FROM member m where province=?1 and city=?2 and area=?3 and town=?4 and  m.id in (SELECT mid FROM commodity_order) GROUP BY road",nativeQuery = true)
	public List<Object> getAllRoad(String province, String city, String area, String town);

	@Query(value = "SELECT myfloor FROM member m where  province=?1 and city=?2 and area=?3 and town=?4 and road=?5 and  m.id in (SELECT mid FROM commodity_order) GROUP BY myfloor",nativeQuery = true)
	public List<Object> getAllMyfloor(String province, String city, String area, String town, String road);
}
