package com.huaxianvwa.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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

	public List<Commodity> searchCommodity(String cname, Integer cno, String cdate) {
// TODO 自动生成的方法存根
		List<Commodity> members;
//		System.out.println("truename是否为null"+member.getTruename().toString());
		if(!(cname == null || cname.length() <= 0)){ // 输入内容可能为null也可能为空串
			members = commodityDao.findByTitleLike(cname);
			if(members.size() != 0){
				System.out.println("truename"+members);
				return members;
			}
		}else if(cno != null){
			members = commodityDao.findByCnoLike(cno);
			if(members.size() != 0){
				System.out.println("no"+members);
				return members;
			}
		}else if(!(cdate == null || cdate.length() <= 0)){
			members = commodityDao.findByDateLike(cdate);
			if(members.size() != 0 || members != null){
//						System.out.println("phone"+members);
				return members;
			}
		}
		System.out.println("------------null-------");
		return null;
	}



}