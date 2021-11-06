function updateMember(memberId){
	    $.get("system/member/getMember", {"memberId": memberId}, function (r) {
        if (r.code === 200) {
            var $form = $('#addMemberModal');
            $form.modal();
            var member = r.msg.member;
            $form.find(".password-row").hide();
            $form.find("input[name='username']").val(member.username).attr("readonly", true);;
            $form.find("input[name='oldusername']").val(member.username);
            $form.find("input[name='nickname']").val(member.nickname);
            $form.find("input[name='phone']").val(member.phone);
            $form.find("input[name='email']").val(member.email);
            $form.find("input[name='id']").val(member.id);
            var $status = $form.find("input[name='status']");
            if (member.status === '1') {
                $status.prop("checked", true);
            } else {
                $status.prop("checked", false);
            }
            $("input:radio[value='" + member.ssex + "']").prop("checked", true);
           $("#memberAddBtn").attr("name","update");
        } else {
            $Jtk.n_danger(r.msg);
        }
    });
	
}