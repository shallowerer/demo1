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
import com.huaxianvwa.school.dao.MemberDAO;
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
	
	@Autowired
	MemberDAO memberDAO;
	

	
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
	public Map<String, String> handleAnalysis(@PathVariable("input1") Integer input1,@PathVariable("input2") Integer input2){
		Map<String, String> map = orderItemService.getTwoAssociationAnalysis(input1, input2);
		return map;
	}
	
	// 死的总量
	@RequestMapping("/api/amountt")
	public Map<String, String> amountt(){
		Map<String, String> dataMap = new HashMap<String, String>();
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
	
	
//	---------------------------群体偏好----------------------
	//首次加载展示所有订单偏好
	@GetMapping("/api/allPianhao")
	public List<PreferenceModel> allPianhao(){
		return preferenceModelDAO.getPreferenceByAll();
	}
	
	//年龄
	@GetMapping("/api/pianhaoOfAge")
	public List<PreferenceModel> pianhaoOfAge(@RequestParam("age1") Integer age1,@RequestParam("age2")  Integer age2){
		return preferenceModelDAO.getPreferenceByAge(age1, age2);
	}
	
	//省市区
	@GetMapping("/api/pianhaoOfPCA")
	public List<PreferenceModel> pianhaoOfPCA(@RequestParam("province") String province,@RequestParam("city") String city,@RequestParam("area") String area){
		return preferenceModelDAO.getPreferenceByPCA(province, city, area);
	}
	
	//性别
	@GetMapping("/api/pianhaoOfSex")
	public List<PreferenceModel> pianhaoOfSex(@RequestParam("sex") String sex){
		return preferenceModelDAO.getPreferenceBySex(sex);
	}
	
	//组合Combination
	@GetMapping("/api/pianhaoOfCombination")
	public List<PreferenceModel> pianhaoOfCombination(@RequestParam("sex") String sex,@RequestParam("age1") Integer age1,@RequestParam("age2")  Integer age2,@RequestParam("province") String province,@RequestParam("city") String city,@RequestParam("area") String area){
		return preferenceModelDAO.getPreferenceBySexAndAgeAndPCA(sex, age1, age2, province, city, area);
	}
	
	
//	---------------------事先绑定的数据--------------------
	@GetMapping("/api/getAllProvince")
	public List<Object> getAllProvince(){
		return memberDAO.getAllProvince();
	}
	
	@GetMapping("/api/getAllCity")
	public List<Object> getAllCity(@RequestParam("province") String province){
		return memberDAO.getAllCity(province);
	}
	
	@GetMapping("/api/getAllArea")
	public List<Object> getAllArea(@RequestParam("province") String province, @RequestParam("city") String city){
//		System.out.println(province);
//		System.out.println(city);
		return memberDAO.getAllArea(province,city);
	}
	
	@GetMapping("/api/getAllTown")
	public List<Object> getAllTown(@RequestParam("province") String province,@RequestParam("city") String city,@RequestParam("area") String area){
		return memberDAO.getAllTown(province,city,area);
	}
	
	@GetMapping("/api/getAllRoad")
	public List<Object> getAllRoad(@RequestParam("province") String province,@RequestParam("city") String city,@RequestParam("area") String area,@RequestParam("town") String town){
		return memberDAO.getAllRoad(province,city,area,town);
	}
	
	@GetMapping("/api/getAllMyfloor")
	public List<Object> getAllMyfloor(@RequestParam("province") String province,@RequestParam("city") String city,@RequestParam("area") String area,@RequestParam("town") String town,@RequestParam("road") String road){
		return memberDAO.getAllMyfloor(province,city,area,town,road);
	}
	
	
//	------------------输入查群体偏好------------------------
	
//	------单一判断-----
	//只有年龄
	@GetMapping("/api/getPreferenceByAge")
	public List<PreferenceModel> getPreferenceByAge(@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2){
		return preferenceModelDAO.getPreferenceByAge(age1,age2);
	}
	
	//只有性别
	@GetMapping("/api/getPreferenceBySex")
	public List<PreferenceModel> getPreferenceBySex(@RequestParam("sex") String sex){
		return preferenceModelDAO.getPreferenceBySex(sex);
	}
	
	//只有省
	@GetMapping("/api/getPreferenceByP")
	public List<PreferenceModel> getPreferenceByP(@RequestParam("province") String province){
		return preferenceModelDAO.getPreferenceByP(province);
	}
	
	//只有省市
	@GetMapping("/api/getPreferenceByPC")
	public List<PreferenceModel> getPreferenceByPC(@RequestParam("province") String province, @RequestParam("city") String city){
		return preferenceModelDAO.getPreferenceByPC(province, city);
	}
	
	//只有省市区
	@GetMapping("/api/getPreferenceByPCA")
	public List<PreferenceModel> getPreferenceByPCA(@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area){
		System.out.println(province);
		System.out.println(city);
		System.out.println(area);
		return preferenceModelDAO.getPreferenceByPCA(province, city,area);
	}
	
	//只有省市区街道
	@GetMapping("/api/getPreferenceByPCAT")
	public List<PreferenceModel> getPreferenceByPCAT(@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area, @RequestParam("town") String town){
		return preferenceModelDAO.getPreferenceByPCAT(province, city,area,town);
	}
	
	//只有省市区街道路段
	@GetMapping("/api/getPreferenceByPCATR")
	public List<PreferenceModel> getPreferenceByPCATR(@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area, @RequestParam("town") String town, @RequestParam("road") String road){
		return preferenceModelDAO.getPreferenceByPCATR(province, city,area,town,road);
	}
	
	//只有省市区街道路段小区
	@GetMapping("/api/getPreferenceByPCATRM")
	public List<PreferenceModel> getPreferenceByPCATRM(@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area, @RequestParam("town") String town, @RequestParam("road") String road, @RequestParam("myfloor") String myfloor){
		return preferenceModelDAO.getPreferenceByPCATRM(province, city,area,town,road,myfloor);
	}
	
//------------组合判断-----------
	//age和省
	@GetMapping("/api/getPreferenceByAgeAndP")
	public List<PreferenceModel> getPreferenceByAgeAndP(@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2,@RequestParam("province") String province){
		return preferenceModelDAO.getPreferenceByAgeAndP(age1, age2, province);
	}
	
	//age和省市
	@GetMapping("/api/getPreferenceByAgeAndPC")
	public List<PreferenceModel> getPreferenceByAgeAndPC(@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2,@RequestParam("province") String province, @RequestParam("city") String city){
		return preferenceModelDAO.getPreferenceByAgeAndPC(age1, age2, province, city);
	}
	
	//age和省市区
	@GetMapping("/api/getPreferenceByAgeAndPCA")
	public List<PreferenceModel> getPreferenceByAgeAndPCA(@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2,@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area){
		return preferenceModelDAO.getPreferenceByAgeAndPCA(age1, age2, province, city,area);
	}
	
	//age、省市区街道
	@GetMapping("/api/getPreferenceByAgeAndPCAT")
	public List<PreferenceModel> getPreferenceByAgeAndPCAT(@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2,@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area, @RequestParam("town") String town){
		return preferenceModelDAO.getPreferenceByAgeAndPCAT(age1, age2, province, city,area,town);
	}
	
	//age、省市区街道路段
	@GetMapping("/api/getPreferenceByAgeAndPCATR")
	public List<PreferenceModel> getPreferenceByAgeAndPCATR(@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2,@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area, @RequestParam("town") String town, @RequestParam("road") String road){
		return preferenceModelDAO.getPreferenceByAgeAndPCATR(age1, age2, province, city,area,town,road);
	}
	
	//age、省市区街道路段小区
	@GetMapping("/api/getPreferenceByAgeAndPCATRM")
	public List<PreferenceModel> getPreferenceByAgeAndPCATRM(@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2,@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area, @RequestParam("town") String town, @RequestParam("road") String road, @RequestParam("myfloor") String myfloor){
		return preferenceModelDAO.getPreferenceByAgeAndPCATRM(age1, age2, province, city,area,town,road,myfloor);
	}
	
//	-------sex和省-------
	//sex省
	@GetMapping("/api/getPreferenceBySexAndP")
	public List<PreferenceModel> getPreferenceBySexAndP(@RequestParam("sex") String sex,@RequestParam("province") String province){
		return preferenceModelDAO.getPreferenceBySexAndP(sex,province);
	}
	
	//sex省市
	@GetMapping("/api/getPreferenceBySexAndPC")
	public List<PreferenceModel> getPreferenceBySexAndPC(@RequestParam("sex") String sex,@RequestParam("province") String province, @RequestParam("city") String city){
		return preferenceModelDAO.getPreferenceBySexAndPC(sex,province, city);
	}
	
	//sex省市区
	@GetMapping("/api/getPreferenceBySexAndPCA")
	public List<PreferenceModel> getPreferenceBySexAndPCA(@RequestParam("sex") String sex,@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area){
		return preferenceModelDAO.getPreferenceBySexAndPCA(sex,province, city,area);
	}
	
	//sex省市区街道
	@GetMapping("/api/getPreferenceBySexAndPCAT")
	public List<PreferenceModel> getPreferenceBySexAndPCAT(@RequestParam("sex") String sex,@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area, @RequestParam("town") String town){
		return preferenceModelDAO.getPreferenceBySexAndPCAT(sex,province, city,area,town);
	}
	
	//sex省市区街道路段
	@GetMapping("/api/getPreferenceBySexAndPCATR")
	public List<PreferenceModel> getPreferenceBySexAndPCATR(@RequestParam("sex") String sex,@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area, @RequestParam("town") String town, @RequestParam("road") String road){
		return preferenceModelDAO.getPreferenceBySexAndPCATR(sex,province, city,area,town,road);
	}
	
	//sex省市区街道路段小区
	@GetMapping("/api/getPreferenceBySexAndPCATRM")
	public List<PreferenceModel> getPreferenceBySexAndPCATRM(@RequestParam("sex") String sex,@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area, @RequestParam("town") String town, @RequestParam("road") String road, @RequestParam("myfloor") String myfloor){
		return preferenceModelDAO.getPreferenceBySexAndPCATRM(sex,province, city,area,town,road,myfloor);
	}
	
//----sex、age、地
	
	//sexage省
	@GetMapping("/api/getPreferenceBySexAndAgeAndP")
	public List<PreferenceModel> getPreferenceBySexAndAgeAndP(@RequestParam("sex") String sex,@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2,@RequestParam("province") String province){
		return preferenceModelDAO.getPreferenceBySexAndAgeAndP(sex,age1, age2, province);
	}
	
	//sexage省市
	@GetMapping("/api/getPreferenceBySexAndAgeAndPC")
	public List<PreferenceModel> getPreferenceBySexAndAgeAndPC(@RequestParam("sex") String sex,@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2,@RequestParam("province") String province, @RequestParam("city") String city){
		return preferenceModelDAO.getPreferenceBySexAndAgeAndPC(sex,age1, age2, province, city);
	}
	
	//sexage省市区
	@GetMapping("/api/getPreferenceBySexAndAgeAndPCA")
	public List<PreferenceModel> getPreferenceBySexAndAgeAndPCA(@RequestParam("sex") String sex,@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2,@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area){
		return preferenceModelDAO.getPreferenceBySexAndAgeAndPCA(sex,age1, age2, province, city,area);
	}
	
	//sexage省市区街道
	@GetMapping("/api/getPreferenceBySexAndAgeAndPCAT")
	public List<PreferenceModel> getPreferenceBySexAndAgeAndPCAT(@RequestParam("sex") String sex,@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2,@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area, @RequestParam("town") String town){
		return preferenceModelDAO.getPreferenceBySexAndAgeAndPCAT(sex,age1, age2, province, city,area,town);
	}
	
	//sexage省市区街道路段
	@GetMapping("/api/getPreferenceBySexAndAgeAndPCATR")
	public List<PreferenceModel> getPreferenceBySexAndAgeAndPCATR(@RequestParam("sex") String sex,@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2,@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area, @RequestParam("town") String town, @RequestParam("road") String road){
		return preferenceModelDAO.getPreferenceBySexAndAgeAndPCATR(sex,age1, age2, province, city,area,town,road);
	}
	
	//sexage省市区街道路段小区
	@GetMapping("/api/getPreferenceBySexAndAgeAndPCATRM")
	public List<PreferenceModel> getPreferenceBySexAndAgeAndPCATRM(@RequestParam("sex") String sex,@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2,@RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("area") String area, @RequestParam("town") String town, @RequestParam("road") String road, @RequestParam("myfloor") String myfloor){
		return preferenceModelDAO.getPreferenceBySexAndAgeAndPCATRM(sex,age1, age2, province, city,area,town,road,myfloor);
	}
	
	
//	---age、sex
	//sexage
	@GetMapping("/api/getPreferenceBySexAndAge")
	public List<PreferenceModel> getPreferenceBySexAndAge(@RequestParam("sex") String sex,@RequestParam("age1") Integer age1,@RequestParam("age2") Integer age2){
		return preferenceModelDAO.getPreferenceBySexAndAge(sex,age1, age2);
	}
	

	
}
