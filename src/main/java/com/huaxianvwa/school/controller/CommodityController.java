package com.huaxianvwa.school.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huaxianvwa.school.dao.BaseDao;
import com.huaxianvwa.school.dao.BaseRepository;
import com.huaxianvwa.school.entity.Commodity;
import com.huaxianvwa.school.repository.CommodityRepository;

@RestController
@RequestMapping("/api")
public class CommodityController {
	@Autowired
	private CommodityRepository commodityRe;
	@Autowired
	private BaseRepository base;

//	@Autowired
//	private BaseDao baseDao;
	
	@GetMapping("/findAllCommodity")
	@ResponseBody
	public List<Commodity> findAll(){
		return commodityRe.findAll();
	}
	
	@GetMapping("/paging/{page}/{size}")
	public Page<Commodity> paging(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
		PageRequest res = new PageRequest(page, size);
		return commodityRe.findAll(res);
	}
	
	@GetMapping("/p")
	public List pa(){
		System.out.println("444");
		System.out.println(base.listBySql("select * from commodity"));
		return (List<Commodity>)base.listBySql("select * from commodity");
	}
	
	
//	@GetMapping("categories/{cid}/commodities")
//	public List<Commodity> listByCategory(@PathVariable("cid") int cid) {
//        if (0 != cid) {
//            return commodityRe.find
//        } else {
//            return ;
//        }
//    }

}
