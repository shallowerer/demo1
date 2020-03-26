package com.huaxianvwa.daoTest;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.huaxianvwa.school.GraduationApp;
import com.huaxianvwa.school.dao.BaseDao;
import com.huaxianvwa.school.dao.BaseRepository;
import com.huaxianvwa.school.entity.Commodity;
import com.huaxianvwa.school.repository.CommodityRepository;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraduationApp.class)
public class UserDaoTest {
	@Autowired
	private BaseRepository base;
	@Autowired
	private CommodityRepository commodityRe;


//	@Test
//	public void findAllCommodity(){
//		System.out.println(commodityRe.findAll());
//	}
	
	
	
//	@Test
//	public void cont(){
//		PageRequest pageRequest =new PageRequest(1,6);
//		Page<Commodity> page = commodityRe.findAll(pageRequest);
//		System.out.println(page);
//	}
	
	
	@Test
	public void cont(){
//		hibernateTemplate.findByCriteria(criteria)
//		commodityRe.
//		List<Commodity> aa = baseDao.getList("from Commodity c", new Object[]{});
//		if(c==null){
//			System.out.println("bull");
//			return;
//			
//		}
//		List<Commodity> commodities = (List<Commodity>) base.getById(Commodity.class,1);
		
		System.out.println("555");
	}
}
























