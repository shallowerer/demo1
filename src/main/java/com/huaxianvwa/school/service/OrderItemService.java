package com.huaxianvwa.school.service;



import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.huaxianvwa.school.dao.CommodityDao;
import com.huaxianvwa.school.dao.CommodityOrderDAO;
import com.huaxianvwa.school.dao.FinanceModelDAO;
import com.huaxianvwa.school.dao.MemberDAO;
import com.huaxianvwa.school.dao.OrderItemDAO;
import com.huaxianvwa.school.dao.PreferenceModelDAO;

import com.huaxianvwa.school.entity.Commodity;
import com.huaxianvwa.school.entity.CommodityOrder;
import com.huaxianvwa.school.entity.Member;
import com.huaxianvwa.school.entity.OrderItem;
import com.huaxianvwa.school.entity.PreferenceModel;

@Service
public class OrderItemService {
	
	@Autowired
	CommodityOrderDAO commodityOrderDAO;

	@Autowired
	OrderItemDAO orderItemDAO;
	
	@Autowired
	CommodityDao commodityDao;
	
	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	PreferenceModelDAO preferenceModelDAO;
	
	@Autowired
	FinanceModelDAO financeModelDAO;
	
	// 二元关联
	public Map<String, String> getTwoAssociationAnalysis(int item1Id, int item2Id){
		Map<String, String> dataMap = new HashMap<String, String>();
		List<CommodityOrder> orderList = commodityOrderDAO.findAll();
		int size = orderList.size();
		// 同时被买出现次数
		int count = 0;
		for(int i = 0; i < size; i++){
			if(orderItemDAO.getAssociationAnalysis(item1Id, item2Id, orderList.get(i).getId()).size() == 2){
				count++;
			}
		}
		
		//出现1 + 出现2 总次数
		int allFrequency = 0;
		allFrequency = orderItemDAO.frequency(item1Id) + orderItemDAO.frequency(item1Id);
		// 项集 = 出现1 + 出现2 - 出现1、2
		int itemSet = allFrequency - count;
		float c = (float)count/(float)itemSet;
		float p = (float)count/(float)size;
		dataMap.put("count", count+"");
		dataMap.put("size", size+"");
		
		// 支持度：二者同时被买占总事务概率
		dataMap.put("p",String.format("%.2f",p));
		System.out.println(Double.isNaN(c));

		c = (float) (Double.isNaN(c)?0.00:c);
		// 置信度：二者同时被买占总事务概率
		dataMap.put("c",String.format("%.2f",c));
		

		return dataMap;
	}
	
	
	//获取所有关联分析
	public List<Map<String, String>> getAllAssociationAnalysis(){
		List<Commodity> commodities = commodityDao.findAll();
		List<Map<String, String>> all = new ArrayList<Map<String,String>>();
		int size = commodityOrderDAO.findAll().size();
		int count = 0;
		int length = commodities.size();
		int nowItem;
		int nextItem;
		String nowItemName;
		String nextItemName;
		
		for(int i = 0; i <= length; i++){
			for(int j = i+1; j < length ; j++){
				nowItem = commodities.get(i).getId();
				nextItem = commodities.get(j).getId();
				nowItemName = commodities.get(i).getTitle();
				nowItemName = commodities.get(j).getTitle();
//				System.out.println(nowItem);
//				System.out.println(nextItem);
				Map<String, String> twoMap = getTwoAssociationAnalysis(nowItem,nextItem);
				twoMap.put("item1", nowItem+"");
				twoMap.put("item2", nextItem+"");
//				twoMap.put("p",);
				
				all.add(twoMap);
			}
		}
		
		
//		Map<String, Integer> dataMap = new HashedMap();
//		
//		for(int i = 0; i <= size; i++){
//			if(orderItemDAO.getAssociationAnalysis(item1Id, item2Id, i).size() == 2){
//				count++;
//			}
//		}
//		dataMap.put("count", count);
//		dataMap.put("size", size);
		return all;
	}
//	
//	public List<Map<String, String>> getPreference(){
//		List<String> list = orderItemDAO.getPreference();
//		for (Object obj : list) {
//		    System.out.println();
//		}
//		return null;
//	}
	
	
//	// 错误的，获取订单表的所有商品的销售数据
//	public List<Map<String, String>> getAmountOfCommodity(){
//		List<Commodity> commodities = commodityDao.findAll();
//		List<CommodityOrder> commodityOrders = commodityOrderDAO.findAll();
//		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
//		int totalAmout2= orderItemDAO.getTotalAmount();
//		int size = commodityDao.findAll().size();
//		int cid;
//		int items;
//		int totalAmout = 0;
//		for (int i = 0; i <= size; i++) {
//			Map<String, String> map = new HashedMap();
//			int count = 0;
//			
//			cid = commodityOrders.get(i).getId();
//			items = orderItemDAO.getOrderItemOfOneOrder(cid).size();
//			totalAmout += items;
//			if(!(items == 0)){
////				System.out.println(items);
//				count += items;
////				System.out.println((float)count/(float)totalAmout2);
////				System.out.println(4/3);
//				String cname = commodities.get(i).getTitle();
//				
//				float proportion = (float)count/(float)totalAmout2;
//				
//				map.put("cname", "" + cname);
//				map.put("count", "" + count);
//				map.put("cid", "" + cid);
//				map.put("totalAmout2", "" + totalAmout2);
//				map.put("proportion", "" + String.format("%.2f",proportion* 100));
//				
//				
//				list.add(map);
////				System.out.println(list);
////				
//				
//			}else{
//				return list;
//			}
////			String.valueOf(count);
////			Integer.toString(count);
//			
//		}
//		
//		return list;
//	}
//	
	
		// 稍微正确的，获取订单表的所有商品的销售数据
		@SuppressWarnings("unused")
		public List<Map<String, String>> getAmountOfCommodity(){
			
			List<Commodity> commodities = commodityDao.findAll();
			List<CommodityOrder> commodityOrders = commodityOrderDAO.findAll();
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			
			int totalAmout = orderItemDAO.getItemAmount();
			for(int i = 0; i < commodities.size(); i++){
				int cid = commodities.get(i).getId();
//				System.out.println(i);
//				System.out.println(cid);
				int s = orderItemDAO.getOrderItemOfOneCid(cid).size();
				if(!(s<= 0)){
					Map<String, String> map = new HashedMap();
					// 当前商品所有销量
					int count = orderItemDAO.getItemAmountByCommodityId(cid);
					String cname = commodities.get(i).getTitle();
					
					// 获取当前商品所有销售额 元
					float cmoney = orderItemDAO.getItemMoneyOfAll(cid);
					
					// 获取所有销售额
					float allmoney = orderItemDAO.getTotalMoney();
					
					String price = commodities.get(i).getSellPrice();
					
					map.put("cname", "" + cname);
					map.put("price", "" + price);
					map.put("count", "" + count);
					map.put("cid", "" + cid);
					map.put("cmoney", "" +  String.format("%.2f",cmoney));
					map.put("allmoney", "" +  String.format("%.2f",allmoney));
					//销售额占比
					map.put("p", "" +  String.format("%.2f",cmoney/allmoney *100));
					
					float proportion = (float)count/(float)totalAmout;
					map.put("totalAmout", "" + totalAmout);
					//销量占比
					map.put("proportion", "" + String.format("%.2f",proportion* 100));
					list.add(map);
//					System.out.println(list);
				}
			}
			
			
			return list;
		}
		
		
		// 获取所有商品销售信息
		@SuppressWarnings("unused")
		public List<Map<String, String>> getDataOfAllCommodity(){
			
			List<Commodity> commodities = commodityDao.findAll();
			List<CommodityOrder> commodityOrders = commodityOrderDAO.findAll();
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			
			Integer totalAmout = orderItemDAO.getItemAmount() != null? orderItemDAO.getItemAmount(): 0;
			for(int i = 0; i < commodities.size(); i++){
				Integer cid = commodities.get(i).getId();
//						System.out.println(i);
//						System.out.println(cid);

				Map<String, String> map = new HashedMap();
				// 当前商品所有销量
				Integer count = orderItemDAO.getItemAmountByCommodityId(cid) != null? orderItemDAO.getItemAmountByCommodityId(cid): 0;
//				System.out.println(count);
				String cname = commodities.get(i).getTitle();
				
				// 获取当前商品所有销售额 元
				Float cmoney = orderItemDAO.getItemMoneyOfAll(cid)!= null? orderItemDAO.getItemMoneyOfAll(cid): 0;
				
				// 获取所有销售额
				Float allmoney = orderItemDAO.getTotalMoney()!= null? orderItemDAO.getTotalMoney(): 0;
				
				String price = commodities.get(i).getSellPrice();
				
				map.put("cname", "" + cname);
				map.put("price", "" + price);
				map.put("count", "" + count);
				map.put("cid", "" + cid);
				map.put("cmoney", "" +  String.format("%.2f",cmoney));
				map.put("allmoney", "" +  String.format("%.2f",allmoney));
				//销售额占比
				map.put("p", "" +  String.format("%.2f",cmoney/allmoney *100));
				
				float proportion = (float)count/(float)totalAmout;
				map.put("totalAmout", "" + totalAmout);
				map.put("proportion", "" + String.format("%.2f",proportion* 100));
				list.add(map);
			}
//			System.out.println(list);
			return list;
		}
		
//	// 会员消费数据
//		@SuppressWarnings("unused")
//		public List<Map<String, String>> allMemberBuyData(){
//			
//			List<Commodity> commodities = commodityDao.findAll();
//			List<CommodityOrder> commodityOrders = commodityOrderDAO.findAll();
//			List<Member> members = memberDAO.findAll(); 
//			List<OrderItem> orderItems = orderItemDAO.findAll();
//			
//			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
//			for (int i = 0; i < members.size(); i++) {
//				Map<String, String> map = new HashedMap();
//				Integer mid = members.get(i).getId();
//				
////				某个会员选择某个商品的次数
//				for (int j = 0; j < commodities.size(); j++) {
//					
//				}
////				某个会员选择所有订单的所有商品的消费金额
//				Float allBuy = orderItemDAO.getMoneyOfAllItemByMemberId(mid) != null ? orderItemDAO.getMoneyOfAllItemByMemberId(mid) : 0;
//				
////				某个会员选择某个商品的数量
//				
//				
////				某个会员下单次数
//				Integer buyCount = orderItemDAO.getCountByMemberIdOfBuy(mid) != null ? orderItemDAO.getCountByMemberIdOfBuy(mid):0;
//				
//				map.put("allBuy", "" + allBuy);
//				map.put("buyCount", "" + buyCount);
////				map.put("count", "" + count);
////				map.put("cid", "" + cid);
////				map.put("cmoney", "" +  String.format("%.2f",cmoney));
////				map.put("allmoney", "" +  String.format("%.2f",allmoney));
////				//销售额占比
////				map.put("p", "" +  String.format("%.2f",cmoney/allmoney *100));
////				
////				float proportion = (float)count/(float)totalAmout;
////				map.put("totalAmout", "" + totalAmout);
////				map.put("proportion", "" + String.format("%.2f",proportion* 100));
//				list.add(map);
//				
//			}
//			
//			
//			return list;
//		}
//		
//	
		
		// 所有会员偏好，带信息
		@SuppressWarnings("unused")
		public List<Map<String, Object>> allMemberPianhao(){
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();			
			List<Member> members = memberDAO.findAll();
			for (int i = 0; i < members.size(); i++) {
				Map<String, Object> map = new HashMap();
//				Member map2 = new HashMap();
				
				Member map2 = members.get(i);
				List<PreferenceModel> preferenceModels = preferenceModelDAO.getPreference(members.get(i).getId());
				map.put("memberlove",preferenceModels);
				map.put("memberInfo",map2);
				list.add(map);
//				System.out.println(list);
			}
			
			return list;
		}
		
		//	指定会员编号，带会员信息
		@SuppressWarnings("unused")
		public List<Map<String, Object>> oneMemberPianhao(Integer mid){
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();	
			
			Member members = memberDAO.getOne(mid);
			Map<String, Object> map = new HashMap();
			
			List<PreferenceModel> preferenceModels = preferenceModelDAO.getPreference(mid);
			map.put("memberlove",preferenceModels);
			map.put("menberInfo",members);
			list.add(map);
			
			return list;
		}
		
		
//	--------------------------群体偏好	------------------------------
//		组合偏好Combination
//		@SuppressWarnings("unused")
//		public List<PreferenceModel> pianhaoOfCombination(String sex, Integer age1, Integer age2, String province, String city, String area){
//			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();	
//			
//			Member members = memberDAO.getOne(mid);
//			Map<String, Object> map = new HashMap();
//			
//			List<PreferenceModel> preferenceModels = preferenceModelDAO.getPreference(mid);
//			map.put("memberlove",preferenceModels);
//			map.put("menberInfo",members);
//			list.add(map);
//			
//			return list;
//		}
//		
		
		
		
		
//  -------------------------销售数据------------------------
	// 销售数据汇总
	public Map<String, String> getAllStaticData(){
		Map<String, String> map = new HashMap();
		
		// 所有商品销量
		Integer allSalesvolume = orderItemDAO.getItemAmount();
		
		// 销售的所有金额 元
		Float aLLMoney = orderItemDAO.getTotalMoney();
		
		map.put("allSalesvolume",allSalesvolume+ "");
		map.put("aLLMoney",aLLMoney+ "");
		return map;
	}
	
	
	
	
//	-----------------------------------------------------------
//	增删改查

	public int addMember(OrderItem member) {
		// TODO 自动生成的方法存根
		String commodityName = member.getCommodityName();
		Float price = member.getPrice();
		Float lttAccount = member.getLttAccount();
		Integer amount = member.getAmount();
		Integer commodityId = member.getCommodityId();
//		Integer OrderId = 
		
		commodityName = HtmlUtils.htmlEscape(commodityName);
		member.setCommodityName(commodityName);
//		price = HtmlUtils.htmlEscapeHex(price);
        member.setPrice(price);
//        memberno = HtmlUtils.htmlEscape(memberno);
        member.setAmount(amount);
//        truename = HtmlUtils.htmlEscape(truename);
        member.setLttAccount(lttAccount);
//        phone = HtmlUtils.htmlEscape(phone);
        member.setCommodityId(commodityId);
//        email = HtmlUtils.htmlEscape(email);

        
        // 必要属性为空
        if (commodityName.equals("") || price.equals("") || lttAccount.equals("") || amount.equals("") || commodityId.equals("")) {
            return 0;
        }
        

        
        //成功
        orderItemDAO.save(member);
		return 1;
             
	}

//	public boolean updateMemberStatus(OrderItem requestMember) {
//        Member member = memberDAO.findByMemberno(requestMember.getMemberno());
//        member.setEnabled(requestMember.isEnabled());
//        try {
//        	memberDAO.save(member);
//        } catch (IllegalArgumentException e) {
//            return false;
//        } return true;
//	}

	public boolean editMember(OrderItem requestMember) {
//		System.out.println("当前id" + requestMember.getId());
		OrderItem memberInDB = orderItemDAO.findOne(requestMember.getId());
        memberInDB.setAmount(requestMember.getAmount());
        memberInDB.setCommodityId(requestMember.getCommodityId());
        memberInDB.setCommodityName(requestMember.getCommodityName());
        memberInDB.setLttAccount(requestMember.getLttAccount());
        memberInDB.setPrice(requestMember.getPrice());

        try {
        	orderItemDAO.save(memberInDB);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

	
//-----------------------------------订单明细表设置------------------------------------------------
	
	
	 public List<OrderItem> list() {
	        Sort sort = new Sort(Sort.Direction.DESC, "id");
	        return orderItemDAO.findAll(sort);
	    }

	    public boolean addOrUpdate(OrderItem orderItem) {
	        try {
	        	orderItemDAO.save(orderItem);
	        } catch (IllegalArgumentException e) {
	            return false;
	        }
	        return true;
	    }

	    public boolean deleteById(int id) {
	        try {
	        	orderItemDAO.delete(id);
	        } catch (IllegalArgumentException e) {
	            return false;
	        }
	        return true;
	    }

//	    public List<OrderItem> listByCategory(int cid) {
//	        Category category = categoryService.get(cid);
//	        Sort sort = new Sort(Sort.Direction.DESC, "id");
//	        return commodityDao.findAllByCategory(category);
//	    }

	    public List<OrderItem> searchMember(OrderItem member) {
			// TODO 自动生成的方法存根
			List<OrderItem> members;
//			System.out.println("truename是否为null"+member.getTruename().toString());
			if(!(member.getCommodityName() == null || member.getCommodityName().length() <= 0)){
				members = orderItemDAO.findByCommodityNameLike(member.getCommodityName());
				if(members.size() != 0){
					return members;
				}
			}
//			else if(!(member.getCommodityOrder().getOrderNo() == null || member.getCommodityOrder().getOrderNo().toString().length() <= 0)){
//				members = orderItemDAO.findByOrderNoLike(member.getCommodityOrder().getOrderNo());
//				if(members.size() != 0 || members != null){
////					System.out.println("phone"+members);
//					return members;
//				}
//			}
			System.out.println("------------null-------");
			return null;
		}
	
	
	
}
