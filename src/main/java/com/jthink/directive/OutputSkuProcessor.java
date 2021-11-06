package com.jthink.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import com.google.gson.Gson;

public class OutputSkuProcessor extends AbstractElementTagProcessor {
	private static final String TAG_NAME = "outputSku";// 标签名
	private static final String FIELD_RPODUCT = "productId";
	public static final int PRECEDENCE = 200;

	public OutputSkuProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		String productIdStr = tag.getAttributeValue(FIELD_RPODUCT);
		final IEngineConfiguration configuration = context.getConfiguration();

		/*
		 * Obtain the Thymeleaf Standard Expression parser 获取Thymeleaf的表达式转换器
		 */

		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		WebEngineContext ctx = (WebEngineContext) context;
		HttpServletResponse response = ctx.getResponse();
		System.out.println("Hello,Wordl json spec");
		List<Integer> item1 = new ArrayList<Integer>();
		item1.add(1);
		List<Integer> item2 = new ArrayList<Integer>();
		item2.add(2);
		List<Integer> item3 = new ArrayList<Integer>();
		item3.add(3);
		List<List<Integer>> allItem = new ArrayList<List<Integer>>();
		allItem.add(item1);
		allItem.add(item2);
		allItem.add(item3);
		Gson gson = new Gson();
		String jsonStr = gson.toJson(allItem);
		System.out.println("alljson:");
		System.out.println(jsonStr);
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("var keys =");
			sb.append(jsonStr+";");
			sb.append("</script>");
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
