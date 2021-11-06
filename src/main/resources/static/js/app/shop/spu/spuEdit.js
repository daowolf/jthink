function updateSpu(spuId){
	    $.get("/system/shop/spu/getSpu", {"spuId": spuId}, function (r) {
        if (r.code === 200) {
            var $form = $('#addSpuModal');
            $form.modal();
            var spu = r.msg.spu;
            $form.find("input[name='spu']").val(spu.spu);
            $form.find("input[name='oldName']").val(spu.spu);
            $form.find("input[name='value']").val(spu.value);
            $form.find("input[name='id']").val(spu.id);
            $('#spu-value').importTags(spu.value);
           $("#spuAddBtn").attr("name","update");
        } else {
            $Jtk.n_danger(r.msg);
        }
    });
	
}