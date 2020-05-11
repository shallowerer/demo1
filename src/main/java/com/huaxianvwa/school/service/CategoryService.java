package com.huaxianvwa.school.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

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


    
	public boolean isExist(String name) {
        Category cate = categoryDAO.findByName(name);
        return null != cate;
    }

	public int addCate(Category cate) {
		// TODO 自动生成的方法存根
		String name = cate.getName();
		

		name = HtmlUtils.htmlEscape(name);
		cate.setName(name);
        cate.setEnabled(true);
        
        // 必要属性为空
        if (name.equals("") ) {
            return 0;
        }
        
        // 类型已存在
        boolean exist = isExist(name);
        if (exist) {
            return 2;
        }
        
        
        //成功
        categoryDAO.save(cate);
		return 1;
             
	}

	public boolean updateCateStatus(Category requestCate) {
        Category cate = categoryDAO.findByName(requestCate.getName());
        cate.setEnabled(requestCate.isEnabled());
        try {
        	categoryDAO.save(cate);
        } catch (IllegalArgumentException e) {
            return false;
        } return true;
	}

	public boolean editCate(Category requestCate) {
//		System.out.println("当前id" + requestMember.getId());
        Category cateInDB = categoryDAO.findOne(requestCate.getId());
        cateInDB.setName(requestCate.getName());
//        memberInDB.setMembername(requestMember.getMembername());
//        memberInDB.setTruename(requestMember.getTruename());
//        memberInDB.setPhone(requestMember.getPhone());
//        memberInDB.setEmail(requestMember.getEmail());
//        memberInDB.setMemberaddr(requestMember.getMemberaddr());
        try {
        	categoryDAO.save(cateInDB);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

	public List<Category> searchCate(Category requestCate) {
		// TODO 自动生成的方法存根
		List<Category> cates;
//		System.out.println("truename是否为null"+member.getTruename().toString());
		if(!(requestCate.getName() == null || requestCate.getName().length() <= 0)){ // 输入内容可能为null也可能为空串
			cates = categoryDAO.findByNameLike(requestCate.getName());
			if(cates.size() != 0){
				System.out.println("name"+cates);
				return cates;
			}
		}
//		else if(!(member.getMemberno() == null || member.getMemberno().length() <= 0)){
//			members = memberDAO.findByMembernoLike(member.getMemberno());
//			if(members.size() != 0){
//				System.out.println("no"+members);
//				return members;
//			}
//		}else if(!(member.getPhone() == null || member.getPhone().length() <= 0)){
//			members = memberDAO.findByPhoneLike(member.getPhone());
//			System.out.println(member.getPhone());
//			if(members.size() != 0 || members != null){
////				System.out.println("phone"+members);
//				return members;
//			}
//		}
		System.out.println("------------null-------");
		return null;
	}
	
}




