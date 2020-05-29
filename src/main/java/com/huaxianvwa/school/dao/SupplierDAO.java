package com.huaxianvwa.school.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.huaxianvwa.school.entity.Member;
import com.huaxianvwa.school.entity.Supplier;

public interface SupplierDAO extends JpaRepository<Supplier, Integer>{

	@Query(value = "from Supplier m where m.name like %?1%")
	List<Supplier> findByNameLike(String name);
	
	@Query(value = "from Supplier m where m.no like %?1%")
	List<Supplier> findByNoLike(String no);
	
	@Query(value = "from Supplier m where m.phone like %?1%")
	List<Supplier> findByPhoneLike(String phone);
	
	Supplier findByNo(String no);

}
