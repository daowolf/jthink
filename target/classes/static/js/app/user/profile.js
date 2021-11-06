var $profileForm = $("#profileForm");
// 更新profile个人资料
$("#updateProfileBtn").click(function(evt){
                $.post("system/user/updateUserProfile", $profileForm.serialize(), function (r) {
                    if (r.code === 200) {
                        $Jtk.n_success(r.msg);
                    } else $Jtk.n_danger(r.msg);
                });	
})