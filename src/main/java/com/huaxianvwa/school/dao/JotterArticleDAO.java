package com.huaxianvwa.school.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.huaxianvwa.school.entity.JotterArticle;

/**
 * @author Evan
 * @date 2020/1/14 20:40
 */
public interface JotterArticleDAO  extends JpaRepository<JotterArticle,Integer> {
    JotterArticle findById(int id);
}
