package com.huaxianvwa.school.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;


import com.huaxianvwa.school.dao.CommodityOrderDAO;

import com.huaxianvwa.school.entity.CommodityOrder;


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
		String status = "已完成";
		String addr = member.getAddress();
		String payStatus = member.getPayStatus();
			
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
		memberInDB.setUid(requestMember.getUid());
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

	public List<CommodityOrder> searchMember(String orderTime, String address) {
		// TODO 自动生成的方法存根

		List<CommodityOrder> members;
//		System.out.println("truename是否为null"+member.getTruename().toString());
		if(!(address == null || address.length() <= 0)){
			members = commodityOrderDAO.findByAddressLike(address);
			if(members.size() != 0){
				return members;
			}
		}else if(!(orderTime == null || orderTime.length() <= 0)){
			members = commodityOrderDAO.findByOrderTimeLike(orderTime);
			if(members.size() != 0 || members != null){
//				System.out.println("phone"+members);
				return members;
			}
		}
		System.out.println("------------null-------");
		return null;
	}
	
}
