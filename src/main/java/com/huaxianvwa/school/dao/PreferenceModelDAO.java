package com.huaxianvwa.school.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.huaxianvwa.school.entity.PreferenceModel;

public interface PreferenceModelDAO extends JpaRepository<PreferenceModel, Integer>{
	//某个会员偏好
//	@Query(value = "select z.id as item_id,count(*) as times, z.commodity_name, z.commodity_id,z.price from order_item z where  order_id in ( select id from commodity_order where mid = ?1 ) group by commodity_id  ",nativeQuery = true)
	@Query(value = "select z.id as item_id, count(*) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z  where order_id in ( select id from commodity_order where mid = ?1 ) group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreference(Integer mid);
	
//	-------------------范围偏好------------------------------
	
	// 年龄段偏好(age)
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c  where m.age between ?1 and ?2 and c.mid=m.id ) group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByAge(Integer age1, Integer age2);
	
	// 地区偏好（省市区）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.province=?1 and m.city=?2 and m.area=?3)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByPCA(String province, String city, String area);
	
	// 地区偏好（省）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.province=?1)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByP(String province);
	
	// 地区偏好（省市）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.province=?1 and m.city=?2)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByPC(String province, String city);
	
	
	// 地区偏好（省市区街道）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.province=?1 and m.city=?2 and m.area=?3 and m.town=?4)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByPCAT(String province, String city, String area, String town);
	
	
	// 地区偏好（省市区街道路段）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.province=?1 and m.city=?2 and m.area=?3 and m.town=?4 and m.road=?5)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByPCATR(String province, String city,String area, String town, String road);
	
	
	// 地区偏好（省市区街道路段小区）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.province=?1 and m.city=?2 and m.area=?3 and m.town=?4 and m.road=?5 and m.myfloor=?6)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByPCATRM(String province, String city,String area, String town, String road, String myfloor);
	
	
	// 性别偏好（sex）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.sex=?1)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySex(String sex);
	
	
	// 性别年龄偏好（sexage）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.sex=?1 and m.age between ?2 and ?3)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndAge(String sex,Integer age1, Integer age2);
	
	// 地区偏好（街道、路）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.town=?1 and m.road=?2)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByTR(String town, String road);
	
	// 地区偏好（街道）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.town=?1)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByTown(String town);
	
	
//	---------------------组合偏好------------------------
	
	// 年龄地区组合（省、age）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age between ?1 and ?2 and m.province=?3)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByAgeAndP(Integer age1, Integer age2, String province);
	
	// 年龄地区组合（省市、age）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age between ?1 and ?2 and m.province=?3 and m.city=?4)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByAgeAndPC(Integer age1, Integer age2, String province, String city);
	
	// 年龄地区组合（省市区、age）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age between ?1 and ?2 and m.province=?3 and m.city=?4 and m.area=?5)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByAgeAndPCA(Integer age1, Integer age2, String province, String city, String area);
	
	// 年龄地区组合（省市区街道、age）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age between ?1 and ?2 and m.province=?3 and m.city=?4 and m.area=?5 and m.town=?6)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByAgeAndPCAT(Integer age1, Integer age2, String province, String city, String area, String town);
	
	// 年龄地区组合（省市区街道大道、age）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age between ?1 and ?2 and m.province=?3 and m.city=?4 and m.area=?5 and m.town=?6 and m.road=?7)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByAgeAndPCATR(Integer age1, Integer age2, String province, String city, String area, String town, String road);
	
	// 年龄地区组合（省市区街道大道小区、age）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age between ?1 and ?2 and m.province=?3 and m.city=?4 and m.area=?5 and m.town=?6 and m.road=?7 and m.myfloor=?8)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByAgeAndPCATRM(Integer age1, Integer age2, String province, String city, String area, String town, String road, String myfloor);
	
	
	
	
	
	
	
	
	// 性别地区组合（省、sex）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age and m.sex=?1 and m.province=?2)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndP(String sex, String province);
	
	
	// 性别地区组合（省市、sex）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age and m.sex=?1 and m.province=?2 and m.city=?3)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndPC(String sex, String province, String city);
	
	// 性别地区组合（省市区、sex）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age and m.sex=?1 and m.province=?2 and m.city=?3 and m.area=?4)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndPCA(String sex, String province, String city, String area);
	
	// 性别地区组合（省市区镇、sex）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age and m.sex=?1 and m.province=?2 and m.city=?3 and m.area=?4 and m.town=?5)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndPCAT(String sex, String province, String city, String area, String town);
	
	// 性别地区组合（省市区镇村、sex）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age and m.sex=?1 and m.province=?2 and m.city=?3 and m.area=?4 and m.town=?5 and m.road=?6)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndPCATR(String sex, String province, String city, String area, String town, String road);
	
	// 性别地区组合（省市区镇村屯、sex）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age and m.sex=?1 and m.province=?2 and m.city=?3 and m.area=?4 and m.town=?5 and m.road=?6 and m.myfloor=?7)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndPCATRM(String sex, String province, String city, String area, String town, String road, String myfloor);
	
	
	
	
	
	
	// 性别地区年龄组合（省、sex、age）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age and m.sex=?1 and m.age between ?2 and ?3 and m.province=?4)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndAgeAndP(String sex, Integer age1, Integer age2, String province);
	
	// 性别地区年龄组合（省市、sex、age）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age and m.sex=?1 and m.age between ?2 and ?3 and m.province=?4 and m.city=?5)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndAgeAndPC(String sex, Integer age1, Integer age2, String province, String city);
	
	// 性别地区年龄组合（省市区、sex、age）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age and m.sex=?1 and m.age between ?2 and ?3 and m.province=?4 and m.city=?5 and m.area=?6)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndAgeAndPCA(String sex, Integer age1, Integer age2, String province, String city, String area);
	
	// 性别地区年龄组合（省市区镇、sex、age）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age and m.sex=?1 and m.age between ?2 and ?3 and m.province=?4 and m.city=?5 and m.area=?6 and m.town=?7)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndAgeAndPCAT(String sex, Integer age1, Integer age2, String province, String city, String area, String town);
	
	// 性别地区年龄组合（省市区镇村、sex、age）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age and m.sex=?1 and m.age between ?2 and ?3 and m.province=?4 and m.city=?5 and m.area=?6 and m.town=?7 and m.road=?8)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndAgeAndPCATR(String sex, Integer age1, Integer age2, String province, String city, String area, String town, String road);
	
	// 性别地区年龄组合（省市区镇村小区、sex、age）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age and m.sex=?1 and m.age between ?2 and ?3 and m.province=?4 and m.city=?5 and m.area=?6 and m.town=?7 and m.road=?8 and m.myfloor=?9)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndAgeAndPCATRM(String sex, Integer age1, Integer age2, String province, String city, String area, String town, String road, String myfloor);

	
//	--------------------------详细地址------------------------------
	// 性别地区组合（街道、sex）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age and m.sex=?1 and m.town=?2)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndT(String sex, String town);
	
	// 年龄地区组合（街道、age）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age between ?1 and ?2 and m.town=?3)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByAgeAndT(Integer age1, Integer age2, String town);
	
	// 性别地区年龄组合（街道、sex、age）
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id and m.age and m.sex=?1 and m.age between ?2 and ?3 and m.town=?4)  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceBySexAndAgeAndT(String sex, Integer age1, Integer age2, String town);
	
	
//	--------------------------总偏好--------------------------
	//所有订单偏好汇总
	@Query(value = "select z.id as item_id, count(z.id) as times, z.commodity_name, z.commodity_id, z.price, sum(z.amount) as allamount, sum(z.ltt_account) as totalmoney from order_item z,member m where order_id in ( select c.id from commodity_order c where c.mid=m.id )  group by commodity_id",nativeQuery = true)
	public List<PreferenceModel> getPreferenceByAll();
	
}
