package com.huaxianvwa.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.huaxianvwa.school.dao.MemberDAO;
import com.huaxianvwa.school.entity.Member;
import com.huaxianvwa.school.entity.User;

@Service
public class MemberService {
	@Autowired
	MemberDAO memberDAO;
	
	public boolean isExist(String memberno) {
        Member member = memberDAO.findByMemberno(memberno);
        return null != member;
    }

	public int addMember(Member member) {
		// TODO 自动生成的方法存根
		String membername = member.getMembername();
		String memberaddr = member.getMemberaddr();
		String memberno = member.getMemberno();
		String truename = member.getTruename();
		String phone = member.getPhone();
		String email = member.getEmail();
		
		membername = HtmlUtils.htmlEscape(membername);
		member.setMembername(membername);
		memberaddr = HtmlUtils.htmlEscape(memberaddr);
        member.setMemberaddr(memberaddr);
        memberno = HtmlUtils.htmlEscape(memberno);
        member.setMemberno(memberno);
        truename = HtmlUtils.htmlEscape(truename);
        member.setTruename(truename);
        phone = HtmlUtils.htmlEscape(phone);
        member.setPhone(phone);
        email = HtmlUtils.htmlEscape(email);
        member.setEmail(email);
        member.setEnabled(true);
        
        // 必要属性为空
        if (memberaddr.equals("") || phone.equals("") || truename.equals("")) {
            return 0;
        }
        
        // 会员编号已存在
        boolean exist = isExist(memberno);
        if (exist) {
            return 2;
        }
        
        
        //成功
        memberDAO.save(member);
		return 1;
             
	}

	public boolean updateMemberStatus(Member requestMember) {
        Member member = memberDAO.findByMemberno(requestMember.getMemberno());
        member.setEnabled(requestMember.isEnabled());
        try {
        	memberDAO.save(member);
        } catch (IllegalArgumentException e) {
            return false;
        } return true;
	}

	public boolean editMember(Member requestMember) {
//		System.out.println("当前id" + requestMember.getId());
        Member memberInDB = memberDAO.findOne(requestMember.getId());
        memberInDB.setMemberno(requestMember.getMemberno());
        memberInDB.setMembername(requestMember.getMembername());
        memberInDB.setTruename(requestMember.getTruename());
        memberInDB.setPhone(requestMember.getPhone());
        memberInDB.setEmail(requestMember.getEmail());
        memberInDB.setMemberaddr(requestMember.getMemberaddr());
        try {
        	memberDAO.save(memberInDB);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
	
	
	
}
