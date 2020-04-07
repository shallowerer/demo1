package com.huaxianvwa.school.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.huaxianvwa.school.dao.CategoryDao;
import com.huaxianvwa.school.entity.Category;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDAO;

    public List<Category> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }

    public Category get(int id) {
        Category c= categoryDAO.findOne(id);
        return c;
    }

//    public Category get(int id) {
//        Category c= categoryDao.findById(id).orElse(null);
//        return c;
//    }
}




