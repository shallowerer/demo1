package com.huaxianvwa.school.controller;
/**
 * @author zsj
 * @date 2020/3
 */
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.huaxianvwa.school.dao.CommodityDao;
import com.huaxianvwa.school.entity.Commodity;
import com.huaxianvwa.school.entity.Member;
import com.huaxianvwa.school.repository.CommodityRepository;
import com.huaxianvwa.school.result.Result;
import com.huaxianvwa.school.result.ResultFactory;
import com.huaxianvwa.school.service.CommodityService;

@RestController
public class CommodityController {
	@Autowired
	private CommodityRepository commodityRe;
	
	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private CommodityDao commodityDao;

//	@Autowired
//	private BaseDao baseDao;
	
	@GetMapping("/api/findAllCommodity")
	@ResponseBody
	public List<Commodity> findAll(){
		return commodityService.list();
	}
	
	@GetMapping("/api/paging/{page}/{size}")
	public Page<Commodity> paging(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
		PageRequest res = new PageRequest(page, size);
		return commodityRe.findAll(res);
	}

	
	
	@PostMapping("/api/admin/content/commodities")
    public Result addOrUpdateCommodities(@RequestBody Commodity commodity) {
        if(commodityService.addOrUpdate(commodity)) {   	
            return ResultFactory.buildSuccessResult("修改成功");
        }
        return ResultFactory.buildFailResult("参数错误，修改失败");
    }

    @PostMapping("/api/admin/content/commodities/delete")
    public Result deleteBook(@RequestBody Commodity commodity) {
        if (commodityService.deleteById(commodity.getId())) {
            return ResultFactory.buildSuccessResult("删除成功");
        }
        return ResultFactory.buildFailResult("参数错误，删除失败");
    }

    @GetMapping("/api/search")
    public List<Commodity> searchResult(@RequestParam("keywords") String keywords) {
        if ("".equals(keywords)) {
            return commodityService.list();
        } else {
            return commodityService.Search(keywords);
        }
    }
    
	@GetMapping("/api/commodity/searchInfo")
	public List<Commodity> searchInfo(@RequestParam("cname") String cname,@RequestParam("cno") Integer cno,@RequestParam("cdate") String cdate) {
		System.out.println(cname);
		System.out.println(cno);
		System.out.println(cdate);
		List<Commodity> cs = commodityService.searchCommodity(cname,cno,cdate);
        return cs; 
   }
    
    

    @GetMapping("/api/categories/{cid}/commodities")
    public List<Commodity> listByCategory(@PathVariable("cid") int cid) {
        if (0 != cid) {
            return commodityService.listByCategory(cid);
        } else {
            return commodityService.list();
        }
    }

    @PostMapping("/api/admin/content/commodities/covers")
    public String coversUpload(MultipartFile file) {
        String folder = "D:/workspace/graduration/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 7));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8899/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
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
