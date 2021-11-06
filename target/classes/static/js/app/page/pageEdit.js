function updatePage(postId){
	    $.get("/system/cms/page/getPageById", {"pageId": postId}, function (r) {
        if (r.code === 200) {
            var $form = $('#addPageModal');
            $form.modal();
            var post = r.msg.post;
            $form.find("input[name='postTitle']").val(post.postTitle);
            $form.find("input[name='template']").val(post.template);
            $form.find("input[name='seoKeywords']").val(post.seoKeywords);
            pageEditor.setData(post.postContent);
            $form.find("textarea[name='postExcerpt']").val(post.postExcerpt);
            $form.find("input[name='id']").val(post.id);
            var $status = $form.find("input[name='commentStatus']");
            if (post.commentStatus === '1') {
                $status.prop("checked", true);
            } else {
                $status.prop("checked", false);
            }
            $("input:radio[value='" + post.commentStatus + "']").prop("checked", true);
           $("#pageAddBtn").attr("name","update");
        } else {
            $Jtk.n_danger(r.msg);
        }
    });
	
}