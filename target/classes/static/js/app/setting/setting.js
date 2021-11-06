var $settingForm = $("#settingForm");
$(function () {
    initOptionList()
});
function initOptionList(){
	$.getJSON("/system/setting/options",function(res){
		var options = res.msg;
		for(var i=0;i<options.length;i++){
			var opt = options[i];
			$settingForm.find("[name='"+opt.optionName+"']").val(opt.optionValue);
		}
	})
}
// 更新参数设置
function updateOptions(){
	var arrs=[];
	$settingForm.find(".optionEle").each(function(index,ele){
		console.log("name="+ele.name+",val="+ele.value);
		arrs.push({"optionName":ele.name,"optionValue":ele.value});
	})
	       var list = JSON.stringify(arrs);
           $.ajax({
               type:'post',
               url:'/system/setting/update',
               contentType :'application/json',
               data:list,
               success:function (r) {
                    if (r.code === 200) {
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
               }
           })
}
$("#updateSettingBtn").click(function(){
	updateOptions();
})