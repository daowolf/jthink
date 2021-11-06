package com.jthink.directive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.jthink.cms.entity.JthinkTree;
import com.jthink.cms.entity.Post;
import com.jthink.cms.service.PostService;
import com.jthink.cms.service.TermService;

public class NavChildProcessor extends AbstractElementTagProcessor {
	private static final String TAG_NAME = "navChild";// 标签名
	private static final String FIELD_NAVID = "navId";
	public static final int PRECEDENCE = 200;

	public NavChildProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		String postIdStr = tag.getAttributeValue(FIELD_NAVID);
		final IEngineConfiguration configuration = context.getConfiguration();
		/*
		 * Obtain the Thymeleaf Standard Expression parser 获取Thymeleaf的表达式转换器
		 */

		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		WebEngineContext ctx = (WebEngineContext) context;
		TermService termService = appCtx.getBean(TermService.class);
		PostService postService = appCtx.getBean(PostService.class);
		/*
		 * Parse the attribute value as a Thymeleaf Standard
		 * Expression,通过表达式可以获取categoryId="${category.id}" 变量中的值，否则直接获取categoryId="23"
		 */
		final IStandardExpression expression = parser.parseExpression(context, postIdStr);
		/*
		 * Execute the expression just parsed 使用得到的表达式，处理上下文内容，得到具体传入的参数值 Demo中传入的是一个数字
		 */
		Object postId = expression.execute(context);
		if(null==postId) {
			// 只清除标签本身，但内容还存在
			structureHandler.removeElement();
			return;
		}
		Post post = postService.selectPostWithChild(postId.toString());
		if (null == post) {
			// 只清除标签本身，但内容还存在
			structureHandler.removeTags();
		} else {
			JthinkTree<Post> rootNode = this.transTree(post);
			// 组织树形结构
			structureHandler.setLocalVariable("navs", rootNode.getChildren());
			//
			// 只清除标签本身，但内容还存在
			structureHandler.removeTags();
			// 整个元素都删除，内容也不存在
			// structureHandler.removeElement();
		}

	}

	public JthinkTree<Post> transTree(Post post) {
		JthinkTree<Post> rootNode = new JthinkTree<Post>();
		rootNode.setId(post.getId().toString());
		rootNode.setTitle(post.getPostTitle());
		if (null != post.getPostParent()) {
			rootNode.setParentId(post.getPostParent().toString());
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("taxonomy", post.getPostMimeType());
		attrs.put("postContent", post.getPostContent());
		rootNode.setAttributes(attrs);
		if (null != post.getChildren()) {
			List<JthinkTree<Post>> childs = new ArrayList<JthinkTree<Post>>();
			List<Post> listPosts = post.getChildren();
			for (Post subPost : listPosts) {
				JthinkTree<Post> sub = transTree(subPost);
				childs.add(sub);
			}
			rootNode.setChildren(childs);
			rootNode.setChildren(true);
		}
		return rootNode;
	}

}
