package com.huaxianvwa.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.huaxianvwa.school.dao.SupplierDAO;
import com.huaxianvwa.school.entity.Supplier;

@Service
public class SupplierService {
	@Autowired
	SupplierDAO supplierDAO;
	
	public boolean isExist(String memberno) {
        Supplier member = supplierDAO.findByNo(memberno);
        return null != member;
    }

	public int addSupplier(Supplier member) {
		// TODO 自动生成的方法存根
//		System.out.println(member);
		String addr = member.getAddr();
		String no = member.getNo();
		String name = member.getName();
		String phone = member.getPhone();
		String email = member.getEmail();
		
		name = HtmlUtils.htmlEscape(name);
		member.setName(name);
		addr = HtmlUtils.htmlEscape(addr);
        member.setAddr(addr);
        no = HtmlUtils.htmlEscape(no);
        member.setNo(no);
        phone = HtmlUtils.htmlEscape(phone);
        member.setPhone(phone);
        email = HtmlUtils.htmlEscape(email);
        member.setEmail(email);
        
        member.setEnabled(true);
        
        // 必要属性为空
        if (addr.equals("") || phone.equals("") || name.equals("")) {
            return 0;
        }
        
        // 会员编号已存在
        boolean exist = isExist(no);
        if (exist) {
            return 2;
        }
        
        
        //成功
        supplierDAO.save(member);
		return 1;
             
	}

	public boolean updateMemberStatus(Supplier requestMember) {
		Supplier member = supplierDAO.findByNo(requestMember.getNo());
        member.setEnabled(requestMember.getEnabled());
        try {
        	supplierDAO.save(member);
        } catch (IllegalArgumentException e) {
            return false;
        } return true;
	}

	public boolean editMember(Supplier requestMember) {
//		System.out.println("当前id" + requestMember.getId());
		Supplier memberInDB = supplierDAO.findOne(requestMember.getId());
        memberInDB.setNo(requestMember.getNo());
        memberInDB.setName(requestMember.getName());

        memberInDB.setPhone(requestMember.getPhone());
        memberInDB.setEmail(requestMember.getEmail());
        memberInDB.setAddr(requestMember.getAddr());
        try {
        	supplierDAO.save(memberInDB);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

	public List<Supplier> searchMember(Supplier member) {
		// TODO 自动生成的方法存根
		System.out.println(member.getName());
		List<Supplier> members;
//		System.out.println("truename是否为null"+member.getTruename().toString());
		if(!(member.getName() == null || member.getName().length() <= 0)){ // 输入内容可能为null也可能为空串
			members = supplierDAO.findByNameLike(member.getName());
			if(members.size() != 0){
//				System.out.println("truename"+members);
				return members;
			}
		}else if(!(member.getNo() == null || member.getNo().length() <= 0)){
			members = supplierDAO.findByNoLike(member.getNo());
			if(members.size() != 0){
				System.out.println("no"+members);
				return members;
			}
		}else if(!(member.getPhone() == null || member.getPhone().length() <= 0)){
			members = supplierDAO.findByPhoneLike(member.getPhone());
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
