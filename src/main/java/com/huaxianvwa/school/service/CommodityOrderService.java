package com.huaxianvwa.school.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.huaxianvwa.school.dao.CommodityOrderDAO;
import com.huaxianvwa.school.dao.MemberDAO;
import com.huaxianvwa.school.entity.Commodity;
import com.huaxianvwa.school.entity.CommodityOrder;
import com.huaxianvwa.school.entity.Member;

@Service
public class CommodityOrderService {
	@Autowired
	CommodityOrderDAO commodityOrderDAO;
	
	public boolean isExist(Integer memberno) {
        CommodityOrder member = commodityOrderDAO.findByOrderNo(memberno);
        return null != member;
    }

	public int addMember(CommodityOrder member) {
		// TODO 自动生成的方法存根
		
		Integer orderNo = member.getOrderNo();		
		Integer mid = member.getMid();
		Float totalPrice = member.getTotalPrice();
		Date orderTime = new Date();
		String status = member.getStatus();
		String addr = member.getStatus();
		String payStatus = member.getStatus();
			
		Integer uid = 1;
		
//		orderNo = HtmlUtils.htmlEscape(orderNo);
		member.setOrderNo(orderNo);
		
//		mid = HtmlUtils.htmlEscape(mid);
        member.setMid(mid);
        
//        totalprice = HtmlUtils.htmlEscape(totalprice);
        member.setTotalPrice(totalPrice);
        
//        orderTime = HtmlUtils.htmlEscape(orderTime);
        member.setOrderTime(orderTime);
        
        status = HtmlUtils.htmlEscape(status);
        member.setStatus(status);
        
        addr = HtmlUtils.htmlEscape(addr);
        member.setAddress(addr);
        
        
        payStatus = HtmlUtils.htmlEscape(payStatus);
        member.setPayStatus(payStatus);
        
       
        
        // 必要属性为空
        if (totalPrice.equals("") || mid.equals("") || addr.equals("")) {
            return 0;
        }
        
        // 会员编号已存在
        boolean exist = isExist(orderNo);
        if (exist) {
            return 2;
        }
        
        
        //成功
        commodityOrderDAO.save(member);
		return 1;
             
	}

//	public boolean updateMemberStatus(CommodityOrder requestMember) {
//		CommodityOrder member = commodityOrderDAO.getOne(requestMember.getId());
//        member.setEnabled(requestMember.isEnabled());
//        try {
//        	memberDAO.save(member);
//        } catch (IllegalArgumentException e) {
//            return false;
//        } return true;
//	}

	public boolean editMember(CommodityOrder requestMember) {
//		System.out.println("当前id" + requestMember.getId());
		CommodityOrder memberInDB = commodityOrderDAO.findOne(requestMember.getId());
        memberInDB.setOrderNo(requestMember.getOrderNo());
        memberInDB.setAddress(requestMember.getAddress());
        memberInDB.setMid(requestMember.getMid());
        memberInDB.setOrderTime(requestMember.getOrderTime());
        memberInDB.setPayStatus(requestMember.getPayStatus());
        memberInDB.setTotalPrice(requestMember.getTotalPrice());
        try {
        	commodityOrderDAO.save(memberInDB);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

//	public List<CommodityOrder> searchMember(CommodityOrder member) {
//		// TODO 自动生成的方法存根
//		List<CommodityOrder> members;
////		System.out.println("truename是否为null"+member.getTruename().toString());
//		if(!(member.getMid() == null || member.getMid().length() <= 0)){ // 输入内容可能为null也可能为空串
//			members = commodityOrderDAO.findByTruenameLike(member.getTruename());
//			if(members.size() != 0){
//				System.out.println("truename"+members);
//				return members;
//			}
//		}else if(!(member.getMemberno() == null || member.getMemberno().length() <= 0)){
//			members = commodityOrderDAO.findByMembernoLike(member.getMemberno());
//			if(members.size() != 0){
//				System.out.println("no"+members);
//				return members;
//			}
//		}else if(!(member.getPhone() == null || member.getPhone().length() <= 0)){
//			members = commodityOrderDAO.findByPhoneLike(member.getPhone());
//			System.out.println(member.getPhone());
//			if(members.size() != 0 || members != null){
////				System.out.println("phone"+members);
//				return members;
//			}
//		}
//		System.out.println("------------null-------");
//		return null;
//	}
//	
}
