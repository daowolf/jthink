function updateTerm(termId){
	    $.get("/system/shop/category/getCategory", {"termId": termId}, function (r) {
        if (r.code === 200) {
            var $form = $('#addTermModal');
            $form.modal();
            var category = r.msg;
            $form.find("input[name='name']").val(category.name);
            $form.find("input[name='oldName']").val(category.name);
            $form.find("input[name='slug']").val(category.slug);
            $form.find("input[name='template']").val(category.template);
            $form.find("input[name='icon']").val(category.icon);
            $form.find("input[name='linkImage']").val(category.linkImage);
            $form.find("input[name='description']").val(category.description);
            $form.find("input[name='id']").val(category.id);
            $termTree.setSelectedItem([category.parentId])
            $("#termAddBtn").attr("name","update");
        } else {
            $Jtk.n_danger(r.msg);
        }
    });
	
}