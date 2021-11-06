$(document).ready(function() {
	reloadCode();
});

function reloadCode() {
$.ajax({
        url: "/image/code?data=" + new Date() + "",
        type: 'GET',
        dataType: 'binary',
        contentType: 'image/jpeg',
        headers: {
            'Access-Control-Allow-Origin': '*'
        },
        processData: false,
        crossDomain: true,
        xhrFields: {
            withCredentials: true,
            responseType: "blob"
        },
        beforeSend: function(xhr) {
            xhr.setRequestHeader('Access-Control-Allow-Origin', '*');
        },
        success: function(result,textStatus,request) {
            var blob = result;
            var img = document.createElement("img");
            img.onload = function(e) {
                window.URL.revokeObjectURL(img.src); 
            };
            img.src = window.URL.createObjectURL(blob);
            $('#validateCodeImg').attr('src', img.src);
            //key
          //  console.log(request.getResponseHeader("KEY_IMAGE_CODE"));
           $("#codeKey").val(request.getResponseHeader("KEY_IMAGE_CODE"));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            errorMsg = "获取图片验证码失败！<br/>错误信息：" + textStatus + "错误码：" + XMLHttpRequest.status + '<br/>请点击图片验证码位置重试';
           console.error(errorMsg);
        }
    });	
}
    function submit() {
        var inputUserName = $("#inputUserName").val();
        var inputPassword = $("#inputPassword").val();
        var imageCode = $("#imageCode").val();
        var rememberMe = $("#rememberMe").prop('checked');
		if (inputUserName === "") {
			$Jtk.n_warning("请输入账号！");
			return;
		}
		if (inputPassword === "") {
			$Jtk.n_warning("请输入密码！");
			return;
		}
        $.ajax({
            url: "/system/dologin",
		    beforeSend: function(request) {
	            request.setRequestHeader("KEY_IMAGE_CODE",$("#codeKey").val());
	         },
            method: "post",
            data: {
                password: inputPassword,
                username: inputUserName,
                imageCode: imageCode,
                rememberMe: rememberMe
            },
            success: function (data) {
                if(data.code==200){
                 window.location.href = "/system";
                }else{
                	alert(data.msg);
                }
            }
        });

    }
$("#imageCode").keydown(function(evt){
	if(evt.keyCode==13){
		submit();
	}
})