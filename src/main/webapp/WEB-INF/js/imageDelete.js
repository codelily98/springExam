$(document).ready(function() {
    $('#selectAll').change(function() {
        $('input[name="selectedImages"]').prop('checked', this.checked);
    });

    $('input[name="selectedImages"]').change(function() {
        let allChecked = $('input[name="selectedImages"]').length === $('input[name="selectedImages"]:checked').length;
        $('#selectAll').prop('checked', allChecked);
    });
    
    $("#deleteBtn").on('click', function() {
        let checkArr = [];
        
        $("input[name='selectedImages']:checked").each(function(i) {
            checkArr.push($(this).val());
        });
        
        $.ajax({
            url: '/spring/user/deleteImage',
            type: 'post',
            dataType: 'text',
            data: { 'seq[]': checkArr },
            success: function(response) {
                let count = response.trim();
                alert(count + "건의 항목이 삭제되었습니다.")
                location.href="/spring/user/uploadList"
            },
            error: function(e) {
                alert("통신 오류");
                console.error(error);
            }
        });
    });

});