function updateMenuPost(postId){
	    $.get("/system/appearance/navmenu/getPostById", {"postId": postId}, function (r) {
        if (r.code === 200) {
            var $form = $('#addPostModal');
            $form.modal();
            var navmenu = r.msg.post;
            $("input:radio[value='" + navmenu.postMimeType + "']").trigger("click");
            $form.find("input[name='postTitle']").val(navmenu.postTitle);
            $form.find("input[name='oldName']").val(navmenu.postTitle);
            $form.find("input[name='slug']").val(navmenu.slug);
            $form.find("input[name='imgPreview']").val(navmenu.imgPreview);
            $form.find("input[name='description']").val(navmenu.description);
            $form.find("input[name='postOrder']").val(navmenu.postOrder);
            $form.find("input[name='id']").val(navmenu.id);

            $navTree.setSelectedItem([navmenu.postParent]);
            if(navmenu.postMimeType=='category'){
	          $termTree.setSelectedItem([navmenu.postContent]);
            }else if(navmenu.postMimeType=='page'){
              $('#navPage').selectpicker('val',navmenu.postContent);
            }else if(navmenu.postMimeType=='product_category'){
	          $productTree.setSelectedItem([navmenu.postContent]);
            }
            else{
	          $("#navUrl").val(navmenu.postContent);
            }
     
            $("#postAddBtn").attr("name","update");
        } else {
            $Jtk.n_danger(r.msg);
        }
    });
	
}