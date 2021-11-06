$(document).ready(function() {
	reloadCode();
	$("#button-comment").click(function(){
		submit();
	})
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
            $('#captcha').attr('src', img.src);
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
        var commentContent = $("#commentContent").val();
        var imageCode = $("#imageCode").val();
        var commentPostId=$("#commentPostId").val();
        var taxonomy = $("#taxonomy").val();
		if (commentContent === "") {
			alert("请输入评论内容！");
			return;
		}
		if (imageCode === "") {
			alert("请输入验证码！");
			return;
		}
        $.ajax({
            url: "/member/post/sendComment",
		    beforeSend: function(request) {
	            request.setRequestHeader("KEY_IMAGE_CODE",$("#codeKey").val());
	         },
            method: "post",
            data: {
	            commentPostId:commentPostId,
                commentContent: commentContent,
                imageCode: imageCode,
                taxonomy: taxonomy
            },
            success: function (data) {
                if(data.code==200){
                 window.location.reload();
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