jQuery(document).ready(function($) {
    $("#studentno").blur(function(event) {
        var rg=/[0-9]{10}/;
        if ($("#studentno").val().match(rg)) {
            right("#studentno");
            $("#info").css("display",'none');
        }else{
            wrong("#studentno");
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
            url: '/ExamOnline/studentLogin.do',
            type: 'post',
            dataType: 'json',
            data: {"studentno":$("#studentno").val(),"password":$("#password").val()},
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
    if($.cookie("skeep")=='true'){
        $("[type='checkbox']").attr("checked","checked");
        $("#studentno").val($.cookie("studentno"));
        $("#password").val($.cookie("spassword"));
    }
    $("[type='checkbox']").change(function(event) {
        if ($("[type='checkbox']").is(":checked")) {
            $.cookie("skeep","true");
            $.cookie("studentno",$("#studentno").val());
            $.cookie("spassword",$("#password").val());
        }else{
            $.cookie("skeep","false");
            $.cookie("studentno",null);
            $.cookie("spassword",null);
        }
    });
});