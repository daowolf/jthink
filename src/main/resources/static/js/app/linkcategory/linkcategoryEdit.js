function updateTerm(termId){
	    $.get("/system/appearance/linkcategory/getCategory", {"termId": termId}, function (r) {
        if (r.code === 200) {
            var $form = $('#addTermModal');
            $form.modal();
            var category = r.msg;
            $form.find("input[name='name']").val(category.name);
            $form.find("input[name='termOrder']").val(category.termOrder);
            $form.find("input[name='oldName']").val(category.name);
            $form.find("input[name='slug']").val(category.slug);
            $form.find("input[name='icon']").val(category.icon);
            $form.find("input[name='description']").val(category.description);
            $form.find("input[name='id']").val(category.id);
            $("#termAddBtn").attr("name","update");
        } else {
            $Jtk.n_danger(r.msg);
        }
    });
	
}