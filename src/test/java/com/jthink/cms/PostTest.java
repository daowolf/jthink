package com.jthink.cms;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jthink.JthinkApplication;
import com.jthink.cms.entity.Post;
import com.jthink.cms.service.PostService;

@SpringBootTest(classes = JthinkApplication.class)
public class PostTest {
	@Autowired
	private PostService postService;
	//@Test
	public void testQuery() {
		List<Post> posts = postService.queryPostsByTermId("product","20");
		System.out.println("数量:"+posts.size());
	}
	@Test
	public void testChildQuery() {
		Post post=postService.selectPostWithChild(57);
		System.out.println("child数量:"+post.getChildren().size());
	}

}
