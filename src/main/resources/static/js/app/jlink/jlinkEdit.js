function updatePost(jlinkId){
	    $.get("/system/appearance/jlink/getLinkById", {"jlinkId": jlinkId}, function (r) {
        if (r.code === 200) {
            var $form = $('#addJlinkModal');
            $form.modal();
            var jlink = r.msg;
            $form.find("input[name='linkName']").val(jlink.linkName);
            $form.find("input[name='linkUrl']").val(jlink.linkUrl);
            $form.find("input[name='linkImage']").val(jlink.linkImage);
            $form.find("input[name='linkDescription']").val(jlink.linkDescription);
            $form.find("input[name='id']").val(jlink.id);
            $('#linkTarget').selectpicker('val',jlink.linkTarget);
            $("input:radio[value='" + jlink.linkVisible + "']").prop("checked", true);
            if(jlink.linkImage && jlink.linkImage !=""){
	           $("#previewImg").attr("src",jlink.linkImage);
               $("#previewRow").show();
            }else{
	           $("#previewRow").hide();
            }
            $("#jlinkAddBtn").attr("name","update");
        } else {
            $Jtk.n_danger(r.msg);
        }
    });
	
}