function updatePermission(permId){
	    $.get("system/permission/getPermission", {"permId": permId}, function (r) {
        if (r.code === 200) {
            var $form = $('#addPermissionModal');
            $form.modal();
            var perm = r.msg;
            $("input:radio[value='" + perm.type + "']").trigger("click");
            $form.find("input[name='name']").val(perm.name);
            $form.find("input[name='oldName']").val(perm.name);
            $form.find("input[name='orderNum']").val(perm.orderNum);
            $form.find("input[name='id']").val(perm.id);
            $form.find("select[name='isShow']").val(perm.isShow);
            $("#show-mdi").text(perm.icon);
            $font_element.setIcon(perm.icon);
            $form.find("input[name='perms']").val(perm.perms == null ? "" : perm.perms);
            $form.find("input[name='url']").val(perm.url == null ? "" : perm.url);
            $menuTree.setSelectedItem([perm.pid])
            $("#permissionAddBtn").attr("name","update");
        } else {
            $Jtk.n_danger(r.msg);
        }
    });
	
}