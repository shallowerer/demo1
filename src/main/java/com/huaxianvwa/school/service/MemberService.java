package com.huaxianvwa.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.huaxianvwa.school.dao.MemberDAO;
import com.huaxianvwa.school.entity.Member;




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
		
		String province = member.getProvince();
		String city = member.getCity();
		String area = member.getArea();
		String town = member.getTown();
		String road = member.getRoad();
		String myfloor = member.getMyfloor();
		String sex = member.getSex();
		Integer age = member.getAge();
		
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
        
        
        member.setProvince(province);
        member.setCity(city);
        member.setArea(area);
        member.setTown(town);
        member.setRoad(road);
        member.setMyfloor(myfloor);
        member.setSex(sex);
        member.setAge(age);
        
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
        
        memberInDB.setProvince(requestMember.getProvince());
        memberInDB.setCity(requestMember.getCity());
        memberInDB.setArea(requestMember.getArea());
        memberInDB.setTown(requestMember.getTown());
        memberInDB.setRoad(requestMember.getRoad());
        memberInDB.setMyfloor(requestMember.getMyfloor());
        memberInDB.setSex(requestMember.getSex());
        memberInDB.setAge(requestMember.getAge());
        memberInDB.setAccount(requestMember.getAccount());
        memberInDB.setPassword(requestMember.getPassword());
        try {
        	memberDAO.save(memberInDB);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

	public List<Member> searchMember(Member member) {
		// TODO 自动生成的方法存根
		List<Member> members;
//		System.out.println("truename是否为null"+member.getTruename().toString());
		if(!(member.getTruename() == null || member.getTruename().length() <= 0)){ // 输入内容可能为null也可能为空串
			members = memberDAO.findByTruenameLike(member.getTruename());
			if(members.size() != 0){
				System.out.println("truename"+members);
				return members;
			}
		}else if(!(member.getMemberno() == null || member.getMemberno().length() <= 0)){
			members = memberDAO.findByMembernoLike(member.getMemberno());
			if(members.size() != 0){
				System.out.println("no"+members);
				return members;
			}
		}else if(!(member.getPhone() == null || member.getPhone().length() <= 0)){
			members = memberDAO.findByPhoneLike(member.getPhone());
			System.out.println(member.getPhone());
			if(members.size() != 0 || members != null){
//				System.out.println("phone"+members);
				return members;
			}
		}
		System.out.println("------------null-------");
		return null;
	}
	
	
	
}
