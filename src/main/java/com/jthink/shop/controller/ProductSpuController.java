package com.jthink.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.cms.entity.Post;
import com.jthink.cms.service.PostService;
import com.jthink.common.entity.QueryRequest;
import com.jthink.controller.BaseController;
import com.jthink.shop.entity.ProductSpu;
import com.jthink.shop.entity.ProductSpuValue;
import com.jthink.shop.service.ProductSpuService;
import com.jthink.shop.service.ProductSpuValueService;
import com.jthink.utils.ResponseBo;

@Controller
@RequestMapping("/system/shop/productspu")
public class ProductSpuController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductSpuService productSpuService;
	@Autowired
	private ProductSpuValueService productSpuValueService;
	@Autowired
	private PostService productService;

	@RequestMapping({ "", "/index" })
	public String index(Integer productId, Model ui) {
		Post product = productService.selectByKey(productId);
		ui.addAttribute("productId", productId);
		ui.addAttribute("product", product);
		return "shop/product/spu";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> spuList(QueryRequest request, Integer productId) {
		return super.selectByPageNumSize(request, () -> this.productSpuService.getProductSpu(productId));
	}

	@RequestMapping("/selectList")
	@ResponseBody
	public ResponseBo spuSelectList(Integer productId) {
		List<ProductSpu> spuList = this.productSpuService.getProductSpu(productId);
		List<Map<String, Object>> selectSource = new ArrayList<Map<String, Object>>();
		for (ProductSpu spu : spuList) {
			Map<String, Object> obj = new HashMap<String, Object>();
			obj.put("spu", spu);
			List<ProductSpuValue> vals = productSpuValueService.getValsBySpu(spu.getId());
			obj.put("vals", vals);
			selectSource.add(obj);
		}
		return ResponseBo.ok(selectSource);
	}

	@RequestMapping("/getspuList")
	@ResponseBody
	public ResponseBo getProductSpuList(Integer productId) {
		List<ProductSpu> spuList = this.productSpuService.getProductSpu(productId);
		List<Map<String, Object>> selectSource = new ArrayList<Map<String, Object>>();
		for (ProductSpu spu : spuList) {
			Map<String, Object> obj = new HashMap<String, Object>();
			obj.put("spu", spu);
			List<ProductSpuValue> vals = productSpuValueService.getSelVals(productId,spu.getId());
			obj.put("vals", vals);
			selectSource.add(obj);
		}
		return ResponseBo.ok(selectSource);
	}

	@RequestMapping("/savespu")
	@ResponseBody
	public ResponseBo saveSpuRelation(Integer productId, String ids_str) {
		productSpuService.saveSpuRelation(productId, ids_str);
		return ResponseBo.ok("规格关联成功");
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseBo deleteRelations(String idStr) {
		int counts = productSpuService.batchDeleteSpuRelation(idStr);
		return ResponseBo.ok("成功删除" + counts + "个关联");
	}
}
