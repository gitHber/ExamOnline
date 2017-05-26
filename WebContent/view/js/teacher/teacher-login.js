jQuery(document).ready(function($) {
    $("#teacherno").blur(function(event) {
        var rg=/[0-9]+/;
        if ($("#teacherno").val().match(rg)) {
            right("#teacherno");
            $("#info").css("display",'none');
        }else{
            wrong("#teacherno");
            $("#info").text('用户名格式错误！');
            $("#info").show();
        }
    });

});
function right(id){
    $(id).parents(".form-group").removeClass('has-error');
    $(id).parents(".form-group").addClass('has-success');
    $(id).parents(".form-group").find('label').css("color","black");
}
function wrong(id){
    $(id).parents(".form-group").removeClass('has-success');
    $(id).parents(".form-group").addClass('has-error');
    $(id).parents(".form-group").find('label').css("color","black");
    $(id).attr('data-toggle', 'tooltip');
}
//登录
jQuery(document).ready(function($) {
    $("#login").click(function(event) {
        $.ajax({
            url: '/ExamOnline/teacherLogin.do',
            type: 'post',
            dataType: 'json',
            data: {"teacherno":$("#teacherno").val(),"password":$("#password").val()},
            success:function(data){
                if(data.code==200){
                    location.href="index.html";
                }else{
                    $("#info").text('用户名或密码错误');
                    $("#info").show();
                }
            }
        });
    });
});
jQuery(document).ready(function($) {
    if($.cookie("tkeep")=='true'){
        $("[type='checkbox']").attr("checked","checked");
        $("#teacherno").val($.cookie("teacherno"));
        $("#password").val($.cookie("tpassword"));
    }
    $("[type='checkbox']").change(function(event) {
        if ($("[type='checkbox']").is(":checked")) {
            $.cookie("tkeep","true");
            $.cookie("teacherno",$("#teacherno").val());
            $.cookie("tpassword",$("#password").val());
        }else{
            $.cookie("tkeep","false");
            $.cookie("teacherno",null);
            $.cookie("tpassword",null);
        }
    });
});