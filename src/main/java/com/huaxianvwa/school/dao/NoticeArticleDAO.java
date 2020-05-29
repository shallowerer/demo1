package com.huaxianvwa.school.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.huaxianvwa.school.entity.NoticeArticle;


public interface NoticeArticleDAO  extends JpaRepository<NoticeArticle,Integer> {
	NoticeArticle findById(int id);
}
