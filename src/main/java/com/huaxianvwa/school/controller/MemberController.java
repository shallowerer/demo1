package com.huaxianvwa.school.controller;
/**
 * @author zsj
 * @date 2020/3
 */
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

import com.huaxianvwa.school.dao.MemberDAO;
import com.huaxianvwa.school.entity.Member;
import com.huaxianvwa.school.result.Result;
import com.huaxianvwa.school.result.ResultFactory;
import com.huaxianvwa.school.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	MemberService memberService;
	
	
	@GetMapping("/api/memberInfo")
	public List<Member> findAll(){
		return memberDAO.findAll();
	}
	
	@PostMapping("/api/addMember")
	 public Result addMember(@RequestBody Member member) {
        int status = memberService.addMember(member);
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
	
	@PutMapping("/api/member/status")
	public Result updateMemberStatus(@RequestBody Member requestMember) {
        if (memberService.updateMemberStatus(requestMember)) {
            return ResultFactory.buildSuccessResult("用户状态更新成功");
        } else {
            return ResultFactory.buildFailResult("参数错误，更新失败");
        }
    }

	@DeleteMapping("/api/member/deleteMember/{id}")
	public Result deleteRole(@PathVariable("id") Integer id) {
		System.out.println(id);
		try {
			memberDAO.delete(id);
		} catch (Exception e) {
			return ResultFactory.buildSuccessResult("服务器错误");
		}
        return ResultFactory.buildSuccessResult("删除成功");
    }
	
    @PutMapping("/api/member/update")
    public Result editUser(@RequestBody Member requestMember) {
    	System.out.println(requestMember);
    	 if(memberService.editMember(requestMember)) {
             return ResultFactory.buildSuccessResult("修改用户信息成功");
         } else {
             return ResultFactory.buildFailResult("参数错误，修改失败");
         }
    }
    
	@GetMapping("/api/member/paging/{page}/{size}")
	public Page<Member> paging(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
		PageRequest res = new PageRequest(page, size);
		return memberDAO.findAll(res);
	}
	
	@PostMapping("/api/member/searchInfo")
	public List<Member> searchInfo(@RequestBody Member requestMember) {
		System.out.println(requestMember);
		List<Member> members = memberService.searchMember(requestMember);
        return members; 
   }

}
