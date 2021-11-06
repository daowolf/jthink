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
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import com.jthink.cms.constants.PostsType;
import com.jthink.cms.constants.Taxonomy;
import com.jthink.cms.entity.JthinkTree;
import com.jthink.cms.entity.Post;
import com.jthink.cms.entity.Term;
import com.jthink.cms.service.PostService;
import com.jthink.cms.service.TermService;
import com.jthink.permission.utils.TreeUtils;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

public class NavMenuProcessor extends AbstractElementTagProcessor {
	private static final String TAG_NAME = "navmenu";// 标签名
	private static final String FIELD_SLUG = "slug";
	public static final int PRECEDENCE = 200;

	public NavMenuProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		String slug = tag.getAttributeValue(FIELD_SLUG);
		final IEngineConfiguration configuration = context.getConfiguration();

		/*
		 * Obtain the Thymeleaf Standard Expression parser 获取Thymeleaf的表达式转换器
		 */

		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		WebEngineContext ctx = (WebEngineContext) context;
		TermService termService = appCtx.getBean(TermService.class);
		PostService postService = appCtx.getBean(PostService.class);
		Example termExample = new Example(Term.class);
		Criteria criteria = termExample.createCriteria();
		criteria.andEqualTo("taxonomy", Taxonomy.navMenuType).andEqualTo("slug", slug);
		List<Term> navTerms = termService.selectByExample(termExample);
		if (null != navTerms && navTerms.size() > 0) {
			List<JthinkTree<Post>> treeNodes = new ArrayList<JthinkTree<Post>>();
			Term navTerm = navTerms.get(0);
			List<Post> navs = postService.queryPostsByTermId(PostsType.MENUITEM,navTerm.getId());
			navs.forEach(nav -> {
				JthinkTree<Post> node = new JthinkTree<Post>();
				node.setId(nav.getId().toString());
				node.setTitle(nav.getPostTitle());
				if(null != nav.getPostParent() ) {
					node.setParentId(nav.getPostParent().toString());
				}
				Map<String, Object> attrs = new HashMap<String, Object>();
				attrs.put("taxonomy", nav.getPostMimeType());
				attrs.put("postContent", nav.getPostContent());
				node.setAttributes(attrs);
				treeNodes.add(node);
			});
			JthinkTree<Post> rootNode = TreeUtils.buildTree(treeNodes);
			// 组织树形结构
			structureHandler.setLocalVariable("navs", rootNode.getChildren());
		}
		//
		// 只清除标签本身，但内容还存在
		structureHandler.removeTags();
		// 整个元素都删除，内容也不存在
		// structureHandler.removeElement();

	}

}
