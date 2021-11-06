package com.jthink.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import com.jthink.shop.entity.ProductSku;
import com.jthink.shop.entity.ProductSpu;
import com.jthink.shop.entity.ProductSpuValue;
import com.jthink.shop.service.ProductSkuService;
import com.jthink.shop.service.ProductSpuService;
import com.jthink.shop.service.ProductSpuValueService;

public class ProductSpuProcessor extends AbstractElementTagProcessor {
	private static final String TAG_NAME = "spuList";// 标签名
	private static final String FIELD_PRODUCTID="productId";
	public static final int PRECEDENCE = 200;

	public ProductSpuProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		String productIdStr = tag.getAttributeValue(FIELD_PRODUCTID);
		final IEngineConfiguration configuration = context.getConfiguration();
		/*
		 * Obtain the Thymeleaf Standard Expression parser 获取Thymeleaf的表达式转换器
		 */
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		WebEngineContext ctx = (WebEngineContext) context;
		HttpServletResponse response = ctx.getResponse();
		ProductSpuService productSpuService = appCtx.getBean(ProductSpuService.class);
		ProductSkuService productSkuService = appCtx.getBean(ProductSkuService.class);
		ProductSpuValueService productSpuValueService = appCtx.getBean(ProductSpuValueService.class);
		/*
		 * Parse the attribute value as a Thymeleaf Standard
		 * Expression,通过表达式可以获取categoryId="${category.id}" 变量中的值，否则直接获取productId="23"
		 */
		final IStandardExpression expression = parser.parseExpression(context, productIdStr);
		/*
		 * Execute the expression just parsed 使用得到的表达式，处理上下文内容，得到具体传入的参数值 Demo中传入的是一个数字
		 */
		Object productId = expression.execute(context);
		Integer numProId =Integer.parseInt(productId.toString());
		List<ProductSpu> spuList = productSpuService.getProductSpu(numProId);
		List<Map<String, Object>> selectSource = new ArrayList<Map<String, Object>>();
		StringJoiner valJoin=new StringJoiner(",");
		for (ProductSpu spu : spuList) {
			Map<String, Object> obj = new HashMap<String, Object>();
			obj.put("spu", spu);
			List<ProductSpuValue> vals = productSpuValueService.getSelVals(numProId,spu.getId());
			obj.put("vals", vals);
			selectSource.add(obj);
			StringJoiner join1=new StringJoiner(",");
		    for(ProductSpuValue val:vals) {
		    	join1.add("'"+val.getId().toString()+"'");
		    }
		    valJoin.add("["+join1.toString()+"]");
		}
		String keyStr = "var keys = ["+valJoin.toString()+"];";
		// sku 相关
		List<ProductSku> skus = productSkuService.getSkus(numProId);
		StringJoiner join2=new StringJoiner(",");
		for(ProductSku sku:skus) {
			join2.add(" \""+sku.getSpuvalIds()+"\": {\n"
					+ "        price:"+sku.getPrice()+",\n"
					+ "        oldPrice:"+sku.getOriginPrice()+",\n"
					+ "        count:"+sku.getStock()+"\n"
					+ "    }");
		}
		String skuStr = "var data = {"+join2.toString()+"};";
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append(keyStr);
			sb.append(skuStr);
			sb.append("</script>");
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		structureHandler.setLocalVariable("list", selectSource);
		// 只清除标签本身，但内容还存在
		structureHandler.removeTags();
	}

}
