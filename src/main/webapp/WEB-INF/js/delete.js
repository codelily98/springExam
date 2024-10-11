$(function(){    
    //회원탈퇴
    $("#deleteBtn").on('click', function(){
        let id = $("#id").val();
        let pwd = $("#pwd").val();
        let checkPwd = $("#checkPwd").val();
        let pg = $("#pg").val();
    
        $.ajax({
            type: 'post',
            url: '/spring/user/delete',  
            data: {
                id: id,
                pwd: pwd,
                checkPwd: checkPwd
            },
            dataType: 'json',  
            success: function(response) {
                if (response.status === "success") {
                    alert("회원탈퇴가 성공적으로 처리되었습니다.");
                    window.location.href = "/spring/user/list?pg=" + pg;
                } else {
                    alert(response.message);
                }
            },
            error: function(e) {
                alert("오류 발생");
                console.log(e);
            }
        });
    });
});