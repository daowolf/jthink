function updatePost(postId){
	// 清空原有图片
	$uploadUtil.clearFiles();
	    $.get("/system/shop/product/getPostById", {"postId": postId}, function (r) {
        if (r.code === 200) {
            var $form = $('#addProductModal');
            $form.modal();
            var post = r.msg.post;
            var taxids = r.msg.taxids;
            var tagStr = r.msg.tags;
            var metas = r.msg.metas;
            var imgs = r.msg.imgs;
            // 价格原数据
            if(metas){
	          for(var key in metas){
		       $("#"+key).val(metas[key]);
	          }
             } 
            if(imgs){
	          //   var picList = ['图片url','图片url','图片url','图片url' ]
                var imgIds=[];
				$.each(imgs, function(index,item){
				  imgIds.push(item.id);
				  $uploadUtil.getFileObject(item.src, function (fileObject) {
				    //console.log(fileObject);
				    var wuFile = new WebUploader.Lib.File(item.id,fileObject);
				    var file = new WebUploader.File(wuFile);
				    $uploadUtil.uploader.addFiles(file)
				  })
				});
				$("#imgIdList").val(imgIds.join(","));
            }
            $termTree.setSelectedItem(taxids);
            $('#tags').importTags(tagStr);
            $form.find("input[name='postTitle']").val(post.postTitle);
            $form.find("input[name='template']").val(post.template);
            $form.find("input[name='imgPreview']").val(post.imgPreview);
            $form.find("input[name='price']").val(post.price);
            $form.find("input[name='originPrice']").val(post.originPrice);
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
           $("#productAddBtn").attr("name","update");
        } else {
            $Jtk.n_danger(r.msg);
        }
    });	
}

//需要编辑的图片列表

