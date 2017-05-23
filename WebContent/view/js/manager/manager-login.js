jQuery(document).ready(function($) {
    $("#managerno").blur(function(event) {
        var rg=/[0-9]+/;
        if ($("#managerno").val().match(rg)) {
            right("#managerno");
            $("#info").css("display",'none');
        }else{
            wrong("#managerno");
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
        $.post('/ExamOnline/managerLogin.do', {"managerno":$("#managerno").val(),"password":$("#password").val()}, function(data) {
            console.log(data);
            if(data.code==200){
                location.href="index.html";
            }else{
                $("#info").text('用户名或密码错误');
                $("#info").show();
            }
        },'json');
    });
});
jQuery(document).ready(function($) {
    if($.cookie("mkeep")=='true'){
        $("[type='checkbox']").attr("checked","checked");
        $("#managerno").val($.cookie("managerno"));
        $("#password").val($.cookie("mpassword"));
    }
    $("[type='checkbox']").change(function(event) {
        if ($("[type='checkbox']").is(":checked")) {
            $.cookie("mkeep","true");
            $.cookie("managerno",$("#managerno").val());
            $.cookie("mpassword",$("#password").val());
        }else{
            $.cookie("mkeep","false");
            $.cookie("managerno",null);
            $.cookie("mpassword",null);
        }
    });
});