function updatePost(postId){
	    $.get("/system/cms/post/getPostById", {"postId": postId}, function (r) {
        if (r.code === 200) {
            var $form = $('#addPostModal');
            $form.modal();
            var post = r.msg.post;
            var taxids = r.msg.taxids;
            var tagStr = r.msg.tags;
            $termTree.setSelectedItem(taxids);
            $('#tags').importTags(tagStr);
            $form.find("input[name='postTitle']").val(post.postTitle);
            $form.find("input[name='template']").val(post.template);
            $form.find("input[name='imgPreview']").val(post.imgPreview);
            $form.find("input[name='termIds']").val(taxids.join(","));
            $form.find("input[name='seoKeywords']").val(post.seoKeywords);
            $form.find("input[name='postOrder']").val(post.postOrder);
            postEditor.setData(post.postContent);
            $form.find("textarea[name='postExcerpt']").val(post.postExcerpt);
            $form.find("input[name='id']").val(post.id);
            var $status = $form.find("input[name='commentStatus']");
            if (post.commentStatus === '1') {
                $status.prop("checked", true);
            } else {
                $status.prop("checked", false);
            }
            $("input:radio[value='" + post.commentStatus + "']").prop("checked", true);
            // meta相关
            if(r.msg.metas){
	            var metas  = r.msg.metas;
               // $form.find("input[name='articlePreview']").val(metas['articlePreview']);
            }

           $("#postAddBtn").attr("name","update");
        } else {
            $Jtk.n_danger(r.msg);
        }
    });
	
}