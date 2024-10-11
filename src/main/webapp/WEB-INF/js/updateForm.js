$(function() {
    
    //회원정보 수정
    $("#writeBtn").on('click', function(){
        $("#nameDiv").empty();
        $("#pwdDiv").empty();
        
        let name = $("#name").val();
        let id = $("#id").val();
        let pwd = $("#pwd").val();
        let pg = $("#pg").val();
        
        if(name == '') {
            $("#nameDiv").html("<span style='color: red;'>이름을 입력해주세요.</span>");
        } else if(pwd == '') {
            $("#pwdDiv").html("<span style='color: red;'>비밀번호를 입력해주세요.</span>");
        } else {
            $.ajax({
                type: 'post',
                url: '/spring/user/update',
                data: $("#updateForm").serialize(),
                success: function() {
                    alert("회원정보 수정완료");
                    location.href="/spring/user/list?pg=" + pg;
                },
                error: function(e) {
                    console.log(e);
                }
            });
        }
    });
    
    // 회원탈퇴 페이지 이동
    $("#deleteBtn").on('click', function(){
        let id = $("#id").val();
        let pg = $("#pg").val();
        
        $.ajax({
            type: 'POST',
            url: '/spring/user/deleteForm',
            data: $("#updateForm").serialize(),
            dataType: 'json',
            success: function(response) {
                if (response.status === 'success') {
                    alert("회원탈퇴 페이지로 이동합니다.");
                    location.href = "/spring/user/deleteFormView?pg=" + response.pg + "&id=" + response.id;
                } else {
                    alert(response.message);  // 에러 메시지를 사용자에게 보여줌
                }
            },
            error: function(e) {
                alert("오류 발생");
                console.log(e);
            }
        });
    });
});