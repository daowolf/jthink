package com.jthink.shop.controller;

import java.util.Arrays;
import java.util.Date;
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
import com.jthink.shop.entity.ProductSku;
import com.jthink.shop.entity.ProductSpuValue;
import com.jthink.shop.entity.SpuvalRelation;
import com.jthink.shop.service.ProductSkuService;
import com.jthink.shop.service.ProductSpuValueService;
import com.jthink.shop.service.SpuvalRelationService;
import com.jthink.utils.ResponseBo;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/system/shop/product/sku")
public class ProductSkuController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PostService productService;
	@Autowired
	private ProductSkuService skuService;
	@Autowired
	private SpuvalRelationService spuvalRelationService;
	@Autowired
	private ProductSpuValueService productSpuValueService;

	@RequestMapping({ "", "/index" })
	public String index(Integer productId, Model ui) {
		Post product = productService.selectByKey(productId);
		ui.addAttribute("productId", productId);
		ui.addAttribute("product", product);
		return "shop/sku/sku";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> skuList(QueryRequest request, Integer productId) {
		Example example = new Example(ProductSku.class);
		example.createCriteria().andEqualTo("productId", productId);
		return super.selectByPageNumSize(request, () -> skuService.selectByExample(example));
	}

	@RequestMapping("/add")
	@ResponseBody
	public ResponseBo addSpu(Integer productId, ProductSku sku) {
		sku.setCreateTime(new Date());
		try {
			sku.setCreateTime(new Date());
			this.skuService.save(sku);
			syncSpuValRelation(productId);
			return ResponseBo.ok("?????????????????????");
		} catch (Exception e) {
			log.error("??????????????????", e);
			return ResponseBo.error("????????????????????????????????????????????????");
		}

	}
	// ????????????sku?????? ????????????????????????

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseBo deleteSkus(Integer productId, String idStr) {
		List<String> ids = Arrays.asList(idStr.split(","));
		int counts = skuService.batchDelete(ids, "id", ProductSku.class);
		// ?????????????????????
		syncSpuValRelation(productId);
		return ResponseBo.ok("????????????" + counts);
	}

	public void syncSpuValRelation(Integer productId) {
		Example rex = new Example(SpuvalRelation.class);
		rex.createCriteria().andEqualTo("productId", productId);
		spuvalRelationService.deleteByExample(rex);
		// ????????????SKU??????
		List<ProductSku> skus = skuService.getSkus(productId);
		for (ProductSku sku : skus) {
			String idStr = sku.getSpuvalIds();
			String[] ids = idStr.split(";");
			for (int i = 0; i < ids.length; i++) {
				String spuValId = ids[i];
				ProductSpuValue spuVal = productSpuValueService.selectByKey(spuValId);
				Example spuEx = new Example(SpuvalRelation.class);
				spuEx.createCriteria().andEqualTo("productId", productId).andEqualTo("spuvalId", spuValId);
				int count = spuvalRelationService.selectCountByExample(spuEx);
				if (count == 0) {
					SpuvalRelation relation = new SpuvalRelation();
					relation.setProductId(productId);
					relation.setSpuId(spuVal.getSpuId());
					relation.setSpuvalId(spuVal.getId());
					spuvalRelationService.save(relation);
				}
			}
		}
	}
}
