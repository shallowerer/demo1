package com.huaxianvwa.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huaxianvwa.school.dao.CommodityOrderDAO;
import com.huaxianvwa.school.dao.OrderItemDAO;
import com.huaxianvwa.school.entity.CommodityOrder;
import com.huaxianvwa.school.entity.OrderItem;
import com.huaxianvwa.school.result.Result;
import com.huaxianvwa.school.result.ResultFactory;
import com.huaxianvwa.school.service.CommodityOrderService;
import com.huaxianvwa.school.service.OrderItemService;


@RestController
public class OrderContorller {
	
	@Autowired
	CommodityOrderDAO commodityOrderDAO;
	
	@Autowired
	OrderItemDAO orderItemDAO;
	
	@Autowired
	CommodityOrderService commodityOrderService;
	
	@Autowired
	OrderItemService orderItemService;
	
	// --------------------------订单主表信息管理----------------------
	
	@GetMapping("/api/finAllOrderOfAll")
	public List<CommodityOrder> findAll(){
		return commodityOrderDAO.findAll();
	}
	
	
	
	@PostMapping("/api/addCommodityOrder")
	 public Result addMember(@RequestBody CommodityOrder member) {
		System.out.println(member);
        int status = commodityOrderService.addMember(member);
        switch (status) {
            case 0:
                return ResultFactory.buildFailResult("真实姓名、电话号码、地址不能为空");
            case 1:
                return ResultFactory.buildSuccessResult("添加成功");
            case 2:
                return ResultFactory.buildFailResult("订单编号已存在");
        }
        return ResultFactory.buildFailResult("未知错误");
    }
	
//	@PutMapping("/api/commodityOrder/status")
//	public Result updateMemberStatus(@RequestBody Supplier requestMember) {
//        if (commodityOrderService.updateMemberStatus(requestMember)) {
//            return ResultFactory.buildSuccessResult("用户状态更新成功");
//        } else {
//            return ResultFactory.buildFailResult("参数错误，更新失败");
//        }
//    }

	@DeleteMapping("/api/commodityOrder/deleteCommodityOrder/{id}")
	public Result deleteRole(@PathVariable("id") Integer id) {
		System.out.println(id);
		try {
			commodityOrderDAO.delete(id);
			orderItemDAO.deleteOrderByOrderId(id);
			
		} catch (Exception e) {
			return ResultFactory.buildSuccessResult("服务器错误");
		}
        return ResultFactory.buildSuccessResult("删除成功");
    }
	
    @PutMapping("/api/commodityOrder/update")
    public Result editUser(@RequestBody CommodityOrder requestMember) {
    		System.out.println(requestMember);
    	 if(commodityOrderService.editMember(requestMember)) {
             return ResultFactory.buildSuccessResult("修改用户信息成功");
         } else {
             return ResultFactory.buildFailResult("参数错误，修改失败");
         }
    }
    
	@GetMapping("/api/commodityOrder/paging/{page}/{size}")
	public Page<CommodityOrder> paging(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
		PageRequest res = new PageRequest(page, size);
		return commodityOrderDAO.findAll(res);
	}
	
	@GetMapping("/api/commodityOrder/searchInfoCommodityOrder")
	public List<CommodityOrder> searchInfoCommodityOrder(@RequestParam("order") String orderTime,@RequestParam("address") String address) {
		System.out.println(orderTime);
		System.out.println(address);
		List<CommodityOrder> members = commodityOrderService.searchMember(orderTime, address);
        return members; 
   }
	
	//----------------------订单明细表信息管理-------------------
	@GetMapping("/api/finAllOrderItemOfAll")
	public List<OrderItem> finAllOrderItemOfAll(){
		return orderItemDAO.findAll();
	}
	
	
	
	@PostMapping("/api/addOrderItem")
	 public String addOrderItem(@RequestBody OrderItem orderItem) {
		System.out.println(orderItem);
        boolean status = orderItemService.addOrUpdate(orderItem);
        if(status){
        	return "success";
        }
        return "fail";
    }
	
//	@PutMapping("/api/commodityOrder/status")
//	public Result updateMemberStatus(@RequestBody Supplier requestMember) {
//        if (commodityOrderService.updateMemberStatus(requestMember)) {
//            return ResultFactory.buildSuccessResult("用户状态更新成功");
//        } else {
//            return ResultFactory.buildFailResult("参数错误，更新失败");
//        }
//    }

	@DeleteMapping("/api/orderItem/deleteOrderItem/{id}")
	public Result deleteOrder(@PathVariable("id") Integer id) {
		System.out.println(id);
		try {
			orderItemDAO.delete(id);
		} catch (Exception e) {
			return ResultFactory.buildSuccessResult("服务器错误");
		}
        return ResultFactory.buildSuccessResult("删除成功");
    }
	
    @PutMapping("/api/orderItem/update")
    public Result updateOrderItem(@RequestBody OrderItem requestMember) {
    	System.out.println(requestMember);
		if(orderItemService.editMember(requestMember)) {
		    return ResultFactory.buildSuccessResult("修改用户信息成功");
		} else {
		    return ResultFactory.buildFailResult("参数错误，修改失败");
		}
    }
    
	@GetMapping("/api/orderItem/paging/{page}/{size}")
	public Page<OrderItem> orderpaging(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
		PageRequest res = new PageRequest(page, size);
		return orderItemDAO.findAll(res);
	}
	
	@PostMapping("/api/orderItem/searchInfo")
	public List<OrderItem> orderItemSearchInfo(@RequestBody OrderItem requestMember) {
		System.out.println(requestMember);
		List<OrderItem> members = orderItemService.searchMember(requestMember);
        return members; 
   }
	
}
