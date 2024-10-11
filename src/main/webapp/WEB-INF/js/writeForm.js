$(function() {
    //아이디 중복체크
    $('#id').focusout(function() {
        $('#idDiv').empty();
        id = $('#id').val();
        if (id == '') {
            $('#idDiv').html("<span style='color: red;'>먼저 아이디 입력</span>");
        } else {
            $.ajax({
                type: 'post',
                url: '/spring/user/userExistId',
                data: { 'id': id },
                dataType: 'text',
                success: function(data) {
                    if (data == 'exist') {
                        $('#idDiv').html("<span style='color: red;'>이미 사용중인 아이디입니다.</span>");
                    }
                    else {
                        $('#idDiv').html("<span style='color: blue;'>사용가능한 아이디입니다.</span>");
                        $("#checkId").val(id);
                    }
                },
                error: function(e) {
                    console.log(e);
                }
            });
        }
    });
    
    //회원가입
    $("#writeBtn").on('click', function(){
        $("#nameDiv").empty();
        $("#idDiv").empty();
        $("#pwdDiv").empty();
        
        let name = $("#name").val();
        let id = $("#id").val();
        let pwd = $("#pwd").val();
        
        if(name == '') {
            $("#nameDiv").html("<span style='color: red;'>이름을 입력해주세요.</span>");
        } else if(id == '') {
            $("#idDiv").html("<span style='color: red;'>아이디를 입력해주세요.</span>");
        } else if(pwd == '') {
            $("#pwdDiv").html("<span style='color: red;'>비밀번호를 입력해주세요.</span>");
        } else {
            $.ajax({
                type: 'post',
                url: '/spring/user/write',
                data: $("#writeForm").serialize(),
                success: function() {
                    alert("회원가입 완료");
                    location.href="/spring/user/list";
                },
                error: function(e) {
                    console.log(e);
                }
            });
        }
    });
});