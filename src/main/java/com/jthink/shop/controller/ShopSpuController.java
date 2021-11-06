package com.jthink.shop.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jthink.common.entity.QueryRequest;
import com.jthink.controller.BaseController;
import com.jthink.shop.entity.ProductSpu;
import com.jthink.shop.entity.ProductSpuValue;
import com.jthink.shop.service.ProductSpuService;
import com.jthink.shop.service.ProductSpuValueService;
import com.jthink.utils.ResponseBo;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/system/shop/spu")
public class ShopSpuController extends BaseController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductSpuService productSpuService;
	@Autowired
	private ProductSpuValueService productSpuValService;

	@RequestMapping({ "", "/index" })
	public String index() {
		return "shop/spu/spu";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> roleList(QueryRequest request, ProductSpu spu) {
		return super.selectByPageNumSize(request, () -> this.productSpuService.selectAll());
	}

	@RequestMapping("/getSpu")
	@ResponseBody
	public ResponseBo getSpu(Integer spuId) {
		ProductSpu spu = this.productSpuService.selectByKey(spuId);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("spu", spu);
		return ResponseBo.ok(data);
	}

	@RequestMapping("/add")
	@ResponseBody
	public ResponseBo addSpu(ProductSpu spuObj) {
		try {
			spuObj.setCreateTime(new Date());
			this.productSpuService.save(spuObj);
			Integer spuId = spuObj.getId();
			// 将规格拆分存储
			String[] vals = spuObj.getValue().split(",");
			for (int i = 0; i < vals.length; i++) {
				ProductSpuValue spuVal = new ProductSpuValue();
				spuVal.setSpuId(spuId);
				spuVal.setCreateTime(new Date());
				spuVal.setSpuValue(vals[i]);
				spuVal.setSeqNum(i);
				this.productSpuValService.save(spuVal);
			}
			return ResponseBo.ok("新增规格成功！");
		} catch (Exception e) {
			log.error("新增规格失败", e);
			return ResponseBo.error("新增规格失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/update")
	@ResponseBody
	public ResponseBo updateSpu(ProductSpu spu) {
		try {
			spu.setUpdateTime(new Date());
			this.productSpuService.updateNotNull(spu);
			Integer spuId = spu.getId();
			Example ex = new Example(ProductSpuValue.class);
			ex.createCriteria().andEqualTo("specificationId", spuId);
			this.productSpuValService.deleteByExample(ex);
			// 将规格拆分存储
			String[] vals = spu.getValue().split(",");
			for (int i = 0; i < vals.length; i++) {
				ProductSpuValue spuVal = new ProductSpuValue();
				spuVal.setSpuId(spuId);
				spuVal.setCreateTime(new Date());
				spuVal.setSpuValue(vals[i]);
				spuVal.setSeqNum(i);
				this.productSpuValService.save(spuVal);
			}
			return ResponseBo.ok("更新规格成功！");
		} catch (Exception e) {
			log.error("更新规格失败", e);
			return ResponseBo.error("更新规格失败，请联系网站管理员！");
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public ResponseBo deleteSpus(String spuIds) {
		try {
			List<String> ids = Arrays.asList(spuIds.split(","));
			this.productSpuService.batchDelete(ids, "id", ProductSpu.class);
			Example ex = new Example(ProductSpuValue.class);
			ex.createCriteria().andIn("specificationId", ids);
			this.productSpuValService.deleteByExample(ex);
			return ResponseBo.ok("删除规格成功！");
		} catch (Exception e) {
			log.error("删除规格失败", e);
			return ResponseBo.error("删除规格失败，请联系网站管理员！");
		}
	}
}
