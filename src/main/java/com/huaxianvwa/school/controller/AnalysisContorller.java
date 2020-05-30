package com.huaxianvwa.school.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.huaxianvwa.school.dao.CommodityOrderDAO;
import com.huaxianvwa.school.dao.FinanceModelDAO;
import com.huaxianvwa.school.dao.OrderItemDAO;
import com.huaxianvwa.school.dao.PreferenceModelDAO;
import com.huaxianvwa.school.entity.CommodityOrder;
import com.huaxianvwa.school.entity.OrderItem;
import com.huaxianvwa.school.entity.PreferenceModel;
import com.huaxianvwa.school.model.FinanceModel;
import com.huaxianvwa.school.service.OrderItemService;

@RestController
public class AnalysisContorller {
	@Autowired
	OrderItemDAO orderItemDAO;
	
	@Autowired
	CommodityOrderDAO commodityorderDAO;
	
	@Autowired
	OrderItemService orderItemService;
	
	@Autowired
	PreferenceModelDAO preferenceModelDAO;
	
	@Autowired
	FinanceModelDAO financeModelDAO;
	
	
	

	
	@RequestMapping("/api/getOrder")
	public List<CommodityOrder> getOrder(){
		List<CommodityOrder> orders = commodityorderDAO.findAll();
		return orders;
	}
	
	
	@RequestMapping("/api/getOrderItem")
	public List<OrderItem> getOrderItem(){
		List<OrderItem> orderItems = orderItemDAO.findAll();
		return orderItems;
	}
	
	// 二连测试
	@GetMapping("/api/erlian")
	public Map<String, String> erlian(){
		Map<String,String> dataMap= orderItemService.getTwoAssociationAnalysis(1, 2);
		return dataMap;
	}
	
	
	// 所有测试，参数无所谓
	@GetMapping("/api/alllian")
	public List<Map<String, String>> alllian(){
		List<Map<String, String>> dataMap= orderItemService.getAllAssociationAnalysis();
		return dataMap;
	}
	
//	@RequestMapping("/api/erlian")
//	public int erlian(){
//		int count = orderItemService.getAllAssociationAnalysis(1, 2);
//		return count;
//	}
//	
	
	//偏好
	@RequestMapping("/api/pianhao")
	public List<PreferenceModel> pianhao(){
		List<PreferenceModel> orderItems = preferenceModelDAO.getPreference(1);
//		System.out.println(orderItems);
		return orderItems;
	}
	
	// 销售数据
	@GetMapping("/api/amount")
	public List<Map<String, String>> amount(){
		List<Map<String, String>> dataMap= orderItemService.getAmountOfCommodity();
		return dataMap;
	}
	
	
//	// 销售总量
//	@GetMapping("/api/aa")
//	public int aa(){
//		int i= orderItemDAO.getTotalAmount();
//		return i;
//	}
//	
	// 销售总量
		@GetMapping("/api/zongxiaoliang")
		public List<Map<String, String>> zongxiaoliang(){
			List<Map<String, String>> i= orderItemService.getDataOfAllCommodity();
			return i;
		}
	
	
	// 输入查关联
	@PostMapping("/api/handleAnalysis/{input1}/{input2}")
	public String handleAnalysis(@PathVariable("input1") Integer input1,@PathVariable("input2") Integer input2){
		Map<String, String> map = orderItemService.getTwoAssociationAnalysis(input1, input2);
		return map.get("p");
	}
	
	// 死的总量
	@RequestMapping("/api/amountt")
	public Map<String, String> amountt(){
		Map<String, String> dataMap = new HashMap();
		List<Map<String, String>> li= orderItemService.getAmountOfCommodity();
		dataMap.put("totalAmount",orderItemDAO.getTotalAmount()+"");
		dataMap.put("dataList",li.toString());
		return dataMap;
	}
	
	
	// 静态数据，所有销量和所有金额
	@GetMapping("/api/staticData")
	public Map<String, String> staticData(){
		return orderItemService.getAllStaticData();
	}
	
	
//	@RequestMapping("/api/pianhao2")
//	public String pianhao2(){
//		List<Map<String, String>> orderItems = orderItemService.getPreference();
//		System.out.println(orderItems);
//		return orderItems.toString();
//	}
//	
	
	
	@RequestMapping("/api/allMemberPianhao")
	public List<Map<String, Object>> bb(){
		return orderItemService.allMemberPianhao();
	}
	
	@RequestMapping("/api/oneMemberPianhao/{mid}")
	public List<Map<String, Object>> oneMemberPianhao(@PathVariable("mid")Integer mid){
		return orderItemService.oneMemberPianhao(mid);
	}
	
//	----------------------------------------销售收入-----------------
	// 静态数据，按年
	@GetMapping("/api/allShouru")
	public List<FinanceModel> allShouruOfYear(){
		return financeModelDAO.allShouruOfYear();
	}
	
	@GetMapping("/api/allShouruOfYearAndMonth")
	public List<FinanceModel> allShouruOfYearAndMonth(@RequestParam("year") Integer yearInteger,@RequestParam("month")  Integer monthInteger){
		return financeModelDAO.allShouruByMonth(yearInteger, monthInteger);
	}
	
	@GetMapping("/api/allShouruByYear")
	public List<FinanceModel> allShouruByYear(@RequestParam("year") Integer yearInteger){
		return financeModelDAO.allShouruByYear(yearInteger);
	}
	
}
