package com.huaxianvwa.school.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.huaxianvwa.school.entity.NoticeArticle;

/**
 * @author Evan
 * @date 2020/1/14 20:40
 */
public interface NoticeArticleDAO  extends JpaRepository<NoticeArticle,Integer> {
	NoticeArticle findById(int id);
}
