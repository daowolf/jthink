function updateRole(roleId){
	    $.get("system/role/getRole", {"roleId": roleId}, function (r) {
        if (r.code === 200) {
            var $form = $('#addRoleModal');
            $form.modal();
            var role = r.msg.role;
            var permIds = r.msg.permissionIds;
            $form.find("input[name='name']").val(role.name);
            $form.find("input[name='oldName']").val(role.name);
            $form.find("input[name='note']").val(role.note);
            $form.find("input[name='id']").val(role.id);
            $rolePermsTree.setSelectedItem(permIds)
           $("#roleAddBtn").attr("name","update");
        } else {
            $Jtk.n_danger(r.msg);
        }
    });
	
}