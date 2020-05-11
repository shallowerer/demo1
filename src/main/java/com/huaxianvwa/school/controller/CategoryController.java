package com.huaxianvwa.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.huaxianvwa.school.dao.CategoryDao;
import com.huaxianvwa.school.entity.Category;

import com.huaxianvwa.school.result.Result;
import com.huaxianvwa.school.result.ResultFactory;
import com.huaxianvwa.school.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryDao categoryDao;

	@Autowired
	CategoryService categoryService;
	@GetMapping("/api/cateInfo")
	public List<Category> findAll(){
		return categoryDao.findAll();
	}
	
	@PostMapping("/api/addCate")
	 public Result addMember(@RequestBody Category cate) {
        int status = categoryService.addCate(cate);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("类型已存在");
            case 1:
                return ResultFactory.buildSuccessResult("添加成功");
            case 2:
                return ResultFactory.buildFailResult("用户已存在");
        }
        return ResultFactory.buildFailResult("未知错误");
    }
	
	@PutMapping("/api/cate/status")
	public Result updateMemberStatus(@RequestBody Category requestCate) {
        if (categoryService.updateCateStatus(requestCate)) {
            return ResultFactory.buildSuccessResult("用户状态更新成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，更新失败");
        }
    }

	@DeleteMapping("/api/cate/deleteCate/{id}")
	public Result deleteCate(@PathVariable("id") Integer id) {
		System.out.println(id);
		try {
			categoryDao.delete(id);
		} catch (Exception e) {
			return ResultFactory.buildSuccessResult("服务器错误");
		}
        return ResultFactory.buildSuccessResult("删除成功");
    }
	
    @PutMapping("/api/cate/update")
    public Result editUser(@RequestBody Category requestCate) {
    	 if(categoryService.editCate(requestCate)) {
             return ResultFactory.buildSuccessResult("修改用户信息成功");
         } else {
             return ResultFactory.buildFailResult("参数错误，修改失败");
         }
    }
    
	@GetMapping("/api/cate/paging/{page}/{size}")
	public Page<Category> paging(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
		PageRequest res = new PageRequest(page, size);
		return categoryDao.findAll(res);
	}
	
	@PostMapping("/api/cate/searchInfo")
	public List<Category> searchInfo(@RequestBody Category requestCate) {
		System.out.println(requestCate);
		List<Category> cates = categoryService.searchCate(requestCate);
        return cates; 
   }

}
