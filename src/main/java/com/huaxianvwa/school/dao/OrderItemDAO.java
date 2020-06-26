package com.huaxianvwa.school.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.huaxianvwa.school.entity.OrderItem;
import com.huaxianvwa.school.entity.PreferenceModel;

public interface OrderItemDAO extends JpaRepository<OrderItem, Integer>{
//	--------------模糊查询
//	@Query(value = "from OrderItem m where m.order_no like %?1%")
//	public List<OrderItem> findByOrderNoLike(Integer orderNo);
	
	@Query(value = "from OrderItem m where m.commodityName like %?1%")
	public List<OrderItem> findByCommodityNameLike(String commodityName);
//	--------------分析
	
	
	
	// 二连关联分析
	@Query(value = "select * from order_item o where commodity_id in(?1,?2) and order_id = ?3",nativeQuery = true)
	public List<OrderItem> getAssociationAnalysis(int item1Id, int item2Id, int orderId);
	
	// 出现次数
	@Query(value = "select count(*) from order_item o where commodity_id =?1",nativeQuery = true)
	public Integer frequency(int itemId);
	
	
	//偏好
	@Query(value = "select count(*) as times, z.commodity_name, z.commodity_id,z.price from order_item z where  order_id in ( select id from commodity_order where mid = 2 ) group by commodity_id  ",nativeQuery = true)
	public List<PreferenceModel> getPreference();
	
//	----------------------
	
	// 获取某个订单的所有item
	@Query(value = "select * from order_item o where commodity_id in(?1) ",nativeQuery = true)
	public List<OrderItem> getOrderItemOfOneOrder(int commodityId);
	
	// 通过商品id获取某个的所有item
	@Query(value = "select * from order_item o where commodity_id = ?1 ",nativeQuery = true)
	public List<OrderItem> getOrderItemOfOneCid(int commodityId);
	
	
//	-----------------
	// 获取订单明细的数量 
	@Query(value = "select count(*) from order_item ",nativeQuery = true)
	public Integer getTotalAmount();
	
	
	// 获取某个订单所有商品销售数量
	@Query(value = "select sum(amount) from order_item where order_id = ?",nativeQuery = true)
	public Integer getItemAmountByOrderId(int orderId);
	
	// 获取所有商品销售数量
	@Query(value = "select sum(amount) from order_item",nativeQuery = true)
	public Integer getItemAmount();
	
	// 获取某个订单某个商品销售数量
	@Query(value = "select sum(amount) from order_item where order_id = ?1 and commodity_id = ?2",nativeQuery = true)
	public Integer getItemAmountOfOneOrder(int orderId,int commodityId);
	
	// 获取某个商品销售数量
	@Query(value = "select sum(amount) from order_item where  commodity_id = ?1",nativeQuery = true)
	public Integer getItemAmountByCommodityId(int commodityId);
	
//	------------------------
	
	// 获取销售的所有金额 元
	@Query(value = "select sum(ltt_account) from order_item  ",nativeQuery = true)
	public Float getTotalMoney();
	
	
	// 获取某个订单所有销售额 元
	@Query(value = "select sum(ltt_account) from order_item where order_id = ?1",nativeQuery = true)
	public Integer getTotalMoneyByOrderId(int orderId);
	
	// 获取某个商品所有销售额 元
	@Query(value = "select sum(ltt_account) from order_item where commodity_id = ?1",nativeQuery = true)
	public Float getItemMoneyOfAll(int commodityId);
	
	// 获取某个商品在某个订单的销售额 元
	@Query(value = "select sum(ltt_account) from order_item where order_id = ?1 and commodity_id = ?2",nativeQuery = true)
	public Integer getItemMoneyOfOneOrder(int orderId,int commodityId);
	
//	@Query(value = "select count(*) as times, z.commodity_name from order_item z where  order_id in ( select id from commodity_order where uid = 1 ) group by commodity_id  ",nativeQuery = true)
//	public List<> dd();
	
	
//	-------------------------
	
//	某个会员选择所有订单的所有商品的数量
	@Query(value = "select sum(amount) from order_item where order_id in (select id from Commodity_order where mid = ?1)",nativeQuery = true)
	public Integer getAmountOfAllItemByMemberId(int commodityId,int memberId);
	
//	某个会员选择所有订单的所有商品的消费额度
	@Query(value = "select sum(ltt_account) from order_item where order_id in (select id from Commodity_order where mid = ?1)",nativeQuery = true)
	public Float getMoneyOfAllItemByMemberId(int memberId);
	
	
//	某个会员选择某个商品的数量
	@Query(value = "select sum(amount) from order_item where order_id in (select id from Commodity_order where mid = ?1 and commodity_id = ?2)",nativeQuery = true)
	public Integer getAmountByCommodityIdAndMemberId(int memberId, int commodityId);
	
//	某个会员选择某个商品的次数
	@Query(value = "select count(*) from order_item where order_id in (select id from Commodity_order where mid = ?1 and commodity_id = ?2)",nativeQuery = true)
	public Integer getCountByCommodityIdAndMemberId(int memberId, int commodityId);
	
//	某个会员下单次数
	@Query(value = "select count(*) from commodity_order where  mid = ?1 )",nativeQuery = true)
	public Integer getCountByMemberIdOfBuy(int memberId);
	
//	某个会员在所有订单购买某商品次数
	@Query(value = "select count(*) from commodity_order where  mid = ?1 )",nativeQuery = true)
	public Integer getCountByMemberIdOfBuyForOrder(int memberId);

	@Transactional
	@Modifying
	@Query(value = "delete from order_item where order_id in (?1)",nativeQuery = true)
	public void deleteOrderByOrderId(Integer id);






	
	
}
