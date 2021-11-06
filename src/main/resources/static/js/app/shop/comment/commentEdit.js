function updateComment(commentId){
	    $.get("/system/cms/comments/getCommentById", {"commentId": commentId}, function (r) {
        if (r.code === 200) {
            var $form = $('#addCommentModal');
            $form.modal();
            var comment = r.msg;
            $form.find("input[name='id']").val(comment.id);
            $form.find("textarea[name='commentContent']").val(comment.commentContent);
            // 为comment.html中定义的全局变量
            $form.find("input[name='commentPostId']").val(postId);
            $form.find("input[name='postTitle']").val(postTitle);
            $('#commentApproved').selectpicker('val',comment.commentApproved);
           $("#commentAddBtn").attr("name","update");
        } else {
            $Jtk.n_danger(r.msg);
        }
    });
	
}