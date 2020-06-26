package com.huaxianvwa.school.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.huaxianvwa.school.entity.Comment;

public interface CommentDAO extends JpaRepository<Comment, Integer>{

	Comment findByNo(String memberno);

	@Query(value = "from Comment m where m.name like %?1%")
	List<Comment> findByNameLike(String name);
	
	@Query(value = "from Comment m where m.no like %?1%")
	List<Comment> findByNoLike(String no);
	
	@Query(value = "from Comment m where m.givecontent like %?1%")
	List<Comment> findByGivecontentLike(String givecontent);

}
