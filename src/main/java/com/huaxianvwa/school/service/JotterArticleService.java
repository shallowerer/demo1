package com.huaxianvwa.school.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.huaxianvwa.school.dao.JotterArticleDAO;
import com.huaxianvwa.school.entity.JotterArticle;

/**
 * @author Evan
 * @date 2020/1/14 21:00
 */
@Service
public class JotterArticleService {
    @Autowired
    JotterArticleDAO jotterArticleDAO;

    public Page list(int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        PageRequest res = new PageRequest(page, size,sort);
        return jotterArticleDAO.findAll(res);
    }

    public JotterArticle findById(int id) {
        return jotterArticleDAO.findById(id);
    }

    public boolean addOrUpdate(JotterArticle article) {
    	Date nowDate = new Date();
    	article.setArticleDate(nowDate);
        try {
            jotterArticleDAO.save(article);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
        try {
            jotterArticleDAO.delete(id);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

}
