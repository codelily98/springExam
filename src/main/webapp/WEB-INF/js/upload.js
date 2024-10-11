$(function(){
    //이미지 미리보기
    $("#file").on('change', function(){
        $("#showImgList").empty();
        
        for(let i=0; i < this.files.length; i++) {
            readURL(this.files[i]);
        }
    });
    
    function readURL(file) {
        let reader = new FileReader();
        let show;
        reader.onload = function(e) {
            let img = document.createElement('img');
            img.src = e.target.result;
            img.width = 70;
            img.height = 70;
            $("#showImgList").append(img);
        }
        reader.readAsDataURL(file);
    }
    
    //이미지 업로드 요청(DB)
    $("#uploadBtn").on('click', function(){
       let formData = new FormData($("#uploadForm")[0]);
       
       let img = $("#file").val();
       
       if(img == "") {
           alert("파일을 선택해주세요"); 
       } else {
           $.ajax({
               type: "post",
               enctype: 'multipart/form-data',
               processData: false,
               contentType: false,
               url: "/spring/user/uploads",
               data: formData,
               success: function(data) {
                   //alert(data);
                   location.href="/spring/user/uploadList"
               }, error: function(e) {
                   alert("오류 발생");
                   console.log(e);
               }
           });
        }
    });
});

/*
FileReader 란?
FileReader는 type이 file인 input 태그 또는 API 요청과 같은 인터페이스를 통해 
File 또는 Blob 객체를 편리하게 처리할수있는 방법을 제공하는 객체이며
abort, load, error와 같은 이벤트에서 발생한 프로세스를 처리하는데 주로 사용되며,
File 또는 Blob 객체를 읽어서 result 속성에 저장한다.

FileReader도 비동기로 동작한다.

FileReader.onload()
load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료되었을 때마다 발생한다.
 */
 
 /*
processData
 - 기본값은 true
 - 기본적으로 Query String으로 변환해서 보내진다('변수=값&변수=값')
 - 파일 전송시에는 반드시 false로 해야 한다.(formData를 문자열로 변환하지 않는다)
 
contentType
  - 기본적으로 "application/x-www-form-urlencoded; charset=UTF-8"
  - 파일 전송시에는 'multipart/form-data'로 전송이 될 수 있도록 false로 설정한다
*/