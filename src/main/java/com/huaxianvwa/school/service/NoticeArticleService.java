package com.huaxianvwa.school.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.huaxianvwa.school.dao.NoticeArticleDAO;
import com.huaxianvwa.school.entity.NoticeArticle;

/**
 * @author Evan
 * @date 2020/1/14 21:00
 */
@Service
public class NoticeArticleService {
    @Autowired
    NoticeArticleDAO noticeArticleDAO;

//    public Page list(int page, int size) {
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        return noticeArticleDAO.findAll(PageRequest.of(page, size, sort));
//    }

    public NoticeArticle findById(int id) {
        return noticeArticleDAO.findById(id);
    }

    public boolean addOrUpdate(NoticeArticle article) {
        try {
        	noticeArticleDAO.save(article);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean delete(int id) {
        try {
        	noticeArticleDAO.delete(id);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

}
