package com.huaxianvwa.school.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.huaxianvwa.school.dao.CommentDAO;
import com.huaxianvwa.school.dao.MemberDAO;
import com.huaxianvwa.school.entity.Comment;
import com.huaxianvwa.school.entity.Member;

@Service
public class CommentService {
	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	CommentDAO commentDAO;
	
	public boolean isExist(String memberno) {
        Comment member = commentDAO.findByNo(memberno);
        return null != member;
    }

	public int addMember(Comment member) {
		// TODO 自动生成的方法存根
		Integer servicestar = member.getServicestar();
		Integer costar = member.getCostar();
		Integer roomstar = member.getRoomstar();
		String name = member.getName();
		String no = member.getNo();
		String givecontent = member.getGivecontent();
		Timestamp givetime = member.getGivetime();
		
		givecontent = HtmlUtils.htmlEscape(givecontent);
		member.setName(name);
		no = HtmlUtils.htmlEscape(no);
        member.setNo(no);
        name = HtmlUtils.htmlEscape(name);
        member.setGivecontent(givecontent);
//        roomstar = HtmlUtils.htmlEscape(roomstar);
        member.setCostar(costar);
//        phone = HtmlUtils.htmlEscape(phone);
        member.setRoomstar(roomstar);
//        email = HtmlUtils.htmlEscape(email);
        member.setServicestar(servicestar);
        
        member.setGivetime((Timestamp) new Date());
        
        // 必要属性为空
        if (no.equals("") || name.equals("") || givecontent.equals("")) {
            return 0;
        }
        
        // 会员编号已存在
        boolean exist = isExist(no);
        if (exist) {
            return 2;
        }
        
        
        //成功
        commentDAO.save(member);
		return 1;
             
	}

//	public boolean updateMemberStatus(Comment requestMember) {
//        Comment member = commentDAO.findByNo(requestMember.getNo());
//        member.setEnabled(requestMember.isEnabled());
//        try {
//        	memberDAO.save(member);
//        } catch (IllegalArgumentException e) {
//            return false;
//        } return true;
//	}

	public boolean editMember(Comment requestMember) {
//		System.out.println("当前id" + requestMember.getId());
		Comment memberInDB = commentDAO.findOne(requestMember.getId());
        memberInDB.setServicestar(requestMember.getServicestar());
        memberInDB.setCostar(requestMember.getCostar());
        memberInDB.setRoomstar(requestMember.getRoomstar());
        memberInDB.setGivecontent(requestMember.getGivecontent());
        memberInDB.setName(requestMember.getName());
        memberInDB.setNo(requestMember.getNo());
        try {
        	commentDAO.save(memberInDB);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

	public List<Comment> searchMember(Comment member) {
		// TODO 自动生成的方法存根
		List<Comment> members;
//		System.out.println("truename是否为null"+member.getTruename().toString());
		if(!(member.getNo() == null || member.getNo().length() <= 0)){ // 输入内容可能为null也可能为空串
			members = commentDAO.findByNoLike(member.getNo());
			if(members.size() != 0){
				System.out.println("truename"+members);
				return members;
			}
		}else if(!(member.getName() == null || member.getName().length() <= 0)){
			members = commentDAO.findByNameLike(member.getName());
			if(members.size() != 0){
				System.out.println("no"+members);
				return members;
			}
		}else if(!(member.getGivecontent() == null || member.getGivecontent().length() <= 0)){
			members = commentDAO.findByGivecontentLike(member.getGivecontent());
			
			if(members.size() != 0 || members != null){
//				System.out.println("phone"+members);
				return members;
			}
		}
		System.out.println("------------null-------");
		return null;
	}
	
	
}
