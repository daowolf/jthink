package com.jthink.directive;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

public class JthinkDialect extends AbstractProcessorDialect {
	private static final String NAME = "jthink dialect";
	private static final String PREFIX = "jtk";

	protected JthinkDialect() {
		super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new NavMenuProcessor(PREFIX));
		processors.add(new PostProcessor(PREFIX));
		processors.add(new ProductProcessor(PREFIX));
		processors.add(new LinkProcessor(PREFIX));
		processors.add(new PostTagProcessor(PREFIX));
		processors.add(new HotPostProcessor(PREFIX));
		processors.add(new HotTagProcessor(PREFIX));
		processors.add(new CommentsTag(PREFIX));
		processors.add(new UserProcessor(PREFIX));
		processors.add(new PostTaxonProcessor(PREFIX));
		processors.add(new MemberInfo(PREFIX));
		processors.add(new LoginProcessor(PREFIX));
		processors.add(new OrderProcessor(PREFIX));
		processors.add(new FavoriteProcessor(PREFIX));
		processors.add(new AddressProcessor(PREFIX));
		processors.add(new MyCommentsTag(PREFIX));
		processors.add(new ProductSpuProcessor(PREFIX));
		processors.add(new OutputSkuProcessor(PREFIX));
		processors.add(new NavChildProcessor(PREFIX));
		processors.add(new SaleTopProductProcessor(PREFIX));
		processors.add(new RelatedPostProcessor(PREFIX));
		return processors;
	}

}
