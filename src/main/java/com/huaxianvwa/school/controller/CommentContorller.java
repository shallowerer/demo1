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

import com.huaxianvwa.school.dao.CommentDAO;
import com.huaxianvwa.school.entity.Comment;
import com.huaxianvwa.school.entity.Member;
import com.huaxianvwa.school.result.Result;
import com.huaxianvwa.school.result.ResultFactory;
import com.huaxianvwa.school.service.CommentService;

@RestController
public class CommentContorller {
	@Autowired
	CommentDAO commentDAO;
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("/api/findAllComment")
	public List<Comment> findAll(){
		return commentDAO.findAll();
	}
	
	
	@PostMapping("/api/addComment")
	 public Result addComment(@RequestBody Comment member) {
        int status = commentService.addMember(member);
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
	
//	@PutMapping("/api/comment/status")
//	public Result updateMemberStatus(@RequestBody Member requestMember) {
//        if (commentService.updateMemberStatus(requestMember)) {
//            return ResultFactory.buildSuccessResult("用户状态更新成功");
//        } else {
//            return ResultFactory.buildFailResult("参数错误，更新失败");
//        }
//    }

	@DeleteMapping("/api/comment/deleteMember/{id}")
	public Result deleteRole(@PathVariable("id") Integer id) {
		System.out.println(id);
		try {
			commentDAO.delete(id);
		} catch (Exception e) {
			return ResultFactory.buildSuccessResult("服务器错误");
		}
        return ResultFactory.buildSuccessResult("删除成功");
    }
	
    @PutMapping("/api/comment/update")
    public Result editUser(@RequestBody Comment requestMember) {
    	 if(commentService.editMember(requestMember)) {
             return ResultFactory.buildSuccessResult("修改用户信息成功");
         } else {
             return ResultFactory.buildFailResult("参数错误，修改失败");
         }
    }
    
	@GetMapping("/api/comment/paging/{page}/{size}")
	public Page<Comment> paging(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
		PageRequest res = new PageRequest(page, size);
		return commentDAO.findAll(res);
	}
	
	@PostMapping("/api/comment/searchInfo")
	public List<Comment> searchInfo(@RequestBody Comment requestMember) {
		System.out.println(requestMember);
		List<Comment> members = commentService.searchMember(requestMember);
        return members; 
   }

}
