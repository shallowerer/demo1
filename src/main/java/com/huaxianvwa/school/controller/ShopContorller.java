package com.huaxianvwa.school.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.huaxianvwa.school.dao.CommodityOrderDAO;
import com.huaxianvwa.school.dao.MemberDAO;
import com.huaxianvwa.school.dao.OrderItemDAO;
import com.huaxianvwa.school.entity.CommodityOrder;
import com.huaxianvwa.school.entity.Member;
import com.huaxianvwa.school.entity.OrderItem;




@RestController
public class ShopContorller {
	
	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	CommodityOrderDAO commodityOrderDAO;
	
	@Autowired
	OrderItemDAO orderItemDAO;
	
	@PostMapping("/api/sunmitOrder")
	public String sunmitOrder(@RequestBody JSONObject jsonObject){
		Date date = new Date();
//		System.out.println(jsonObject.get("items"));
//		System.out.println(jsonObject.get("order"));
//		
		
		List<Object> items = (List<Object>) jsonObject.get("items");
		List<OrderItem> oi = new ArrayList<OrderItem>();
		for(int i = 0; i< items.size();i++){
			OrderItem item = JSONObject.parseObject(JSONObject.toJSONString(items.get(i)), OrderItem.class);
//			System.out.println(item);
			oi.add(item);
		}
//		System.out.println(oi);
		
		CommodityOrder order =  JSONObject.parseObject(JSONObject.toJSONString(jsonObject.get("order")), CommodityOrder.class);
		commodityOrderDAO.save(order);
		order.setOrderTime(date);
		order.setOrderNo(order.getId());
		order.setAddress(order.getAddress());
		order.setUid(1);
		order.setStatus("1");
		order.setPayStatus("1");
		commodityOrderDAO.save(order);
	
		for(OrderItem o: oi){
			o.setCommodityOrder(order);
			orderItemDAO.save(o);
		}

//		System.out.println(order);
		return "success";
	}
	
	@RequestMapping("/wx/user")
	public Object user(@RequestParam("no") String account,@RequestParam("pwd") String password){
		Map<String, Object> map = new HashedMap();
		List<Member> ml = memberDAO.findByAccount(account);
		System.out.println(ml);
		map.put("msg", "falsePassword");
		if(ml.size() == 0){
			map.put("msg", "noAccount");
			return map;
		}
		Member member = ml.get(0);
		if((member.getPassword()).equals(password)){
			map.put("msg", member);
			return map;
		}
		return map;
	}

}
