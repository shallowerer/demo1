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

import com.huaxianvwa.school.dao.SupplierDAO;
import com.huaxianvwa.school.entity.Supplier;
import com.huaxianvwa.school.result.Result;
import com.huaxianvwa.school.result.ResultFactory;
import com.huaxianvwa.school.service.SupplierService;

@RestController
public class SupplierContorller {

	@Autowired
	SupplierDAO supplierDAO;
	
	@Autowired
	SupplierService supplierService;
	
	@GetMapping("/api/findAllSupplier")
	public List<Supplier> findAll(){
		return supplierDAO.findAll();
	}
	
	
	
	@PostMapping("/api/addSupplier")
	 public Result addMember(@RequestBody Supplier member) {
//		System.out.println(member);	
        int status = supplierService.addSupplier(member);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("真实姓名、电话号码、地址不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("添加成功");
            case 2:
                return ResultFactory.buildFailResult("用户已存在");
        }
        return ResultFactory.buildFailResult("未知错误");
    }
	
	@PutMapping("/api/supplier/status")
	public Result updateMemberStatus(@RequestBody Supplier requestMember) {
		System.out.println(requestMember);
        if (supplierService.updateMemberStatus(requestMember)) {
            return ResultFactory.buildSuccessResult("用户状态更新成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，更新失败");
        }
    }

	@DeleteMapping("/api/supplier/deleteSupplier/{id}")
	public Result deleteRole(@PathVariable("id") Integer id) {
		System.out.println(id);
		try {
			supplierDAO.delete(id);
		} catch (Exception e) {
			return ResultFactory.buildSuccessResult("服务器错误");
		}
        return ResultFactory.buildSuccessResult("删除成功");
    }
	
    @PutMapping("/api/supplier/update")
    public Result editUser(@RequestBody Supplier requestMember) {
    	System.out.println(requestMember);
    	 if(supplierService.editMember(requestMember)) {
             return ResultFactory.buildSuccessResult("修改用户信息成功");
         } else {
             return ResultFactory.buildFailResult("参数错误，修改失败");
         }
    }
    
	@GetMapping("/api/supplier/paging/{page}/{size}")
	public Page<Supplier> paging(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
		PageRequest res = new PageRequest(page, size);
		return supplierDAO.findAll(res);
	}
	
	@PostMapping("/api/supplier/searchInfo")
	public List<Supplier> searchInfo(@RequestBody Supplier requestMember) {
		System.out.println(requestMember);
		List<Supplier> members = supplierService.searchMember(requestMember);
        return members; 
   }
}
