package com.huaxianvwa.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.huaxianvwa.school.dao.CommodityDao;
import com.huaxianvwa.school.entity.Category;
import com.huaxianvwa.school.entity.Commodity;

@Service
public class CommodityService {
    @Autowired
    CommodityDao commodityDao;
    @Autowired
    CategoryService categoryService;

    public List<Commodity> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return commodityDao.findAll(sort);
    }

    public boolean addOrUpdate(Commodity commodity) {
        try {
        	commodityDao.save(commodity);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean deleteById(int id) {
        try {
        	commodityDao.delete(id);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public List<Commodity> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return commodityDao.findAllByCategory(category);
    }

    public List<Commodity> Search(String keywords) {
        return commodityDao.findAllByTitleLikeOrIdLike('%' + keywords + '%', '%' + keywords + '%');
    }



}