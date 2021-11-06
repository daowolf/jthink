function updateUser(userId){
	    $.get("system/user/getUser", {"userId": userId}, function (r) {
        if (r.code === 200) {
            var $form = $('#addUserModal');
            $form.modal();
            var user = r.msg.user;
            var roleIds = r.msg.roleIds;
            $form.find(".password-row").hide();
            $form.find("input[name='username']").val(user.username).attr("readonly", true);;
            $form.find("input[name='oldusername']").val(user.username);
            $form.find("input[name='nickname']").val(user.nickname);
            $form.find("input[name='phone']").val(user.phone);
            $form.find("input[name='email']").val(user.email);
            $form.find("input[name='id']").val(user.id);
            $('#rolesSelect').selectpicker('val',roleIds);
            var $status = $form.find("input[name='status']");
            if (user.status === '1') {
                $status.prop("checked", true);
            } else {
                $status.prop("checked", false);
            }
            $("input:radio[value='" + user.ssex + "']").prop("checked", true);
           $("#userAddBtn").attr("name","update");
        } else {
            $Jtk.n_danger(r.msg);
        }
    });
	
}