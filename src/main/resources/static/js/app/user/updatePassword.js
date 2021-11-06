var $passwordForm = $("#passwordForm");
// 更新profile个人资料
$("#updatePwdBtn").click(function(evt){
	if($("#new-password").val() != $("#confirm-password").val()){
		$Jtk.n_danger("新密码和确认密码不一致")
		return false;
	}
                $.post("system/user/updatePassword", $passwordForm.serialize(), function (r) {
                    if (r.code === 200) {
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });	
})