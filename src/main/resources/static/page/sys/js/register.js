// (function() {
//     'use strict';
//     window.addEventListener('load', function() {
//         // Fetch all the forms we want to apply custom Bootstrap validation styles to
//         var forms = document.getElementsByClassName('needs-validation');
//         // Loop over them and prevent submission
//         var validation = Array.prototype.filter.call(forms, function(form) {
//             form.addEventListener('submit', function(event) {
//                 if (form.checkValidity() === false) {
//                     event.preventDefault();
//                     event.stopPropagation();
//                 }
//                 form.classList.add('was-validated');
//             }, false);
//         });
//     }, false);
// })();

$(document).ready(function () {
    $("#registerBtn").click(function () {
        var usernameObj = $('#username');
        var nicknameObj = $('#nickname');
        var emailObj = $('#email');
        var passwordObj = $('#password');
        var username = usernameObj.val();
        var nickname = nicknameObj.val();
        var email = emailObj.val();
        var password = passwordObj.val();

        if(username === '' || !/^[0-9a-zA-Z_]{6,15}$/.test(username)){
            invalid(usernameObj);
        }else {
            valid(usernameObj);
        }

        if(nickname === ''){
            invalid(nicknameObj);
        }else {
            valid(nicknameObj);
        }

        if(email === '' || !/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/.test(email)){
            invalid(emailObj);
        }else {
            valid(emailObj);
        }

        if(password === '' || !/^[0-9a-zA-Z_]{6,15}$/.test(password)){
            invalid(passwordObj);
        }else {
            valid(passwordObj);
        }


        var pwdMd5 = md5(password);

        $('.alert').html("请求中……");
        $(".alert").removeAttr("hidden");
        $.ajax({
            type: "POST",
            dataType: "json",
            url: baseUrl + "/u/user/updatePwd",
            data: {
                oldPassword: oldMd5,
                newPassword: newMd5,
                newPassword2: new2Md5
            },
            success: function (msg) {
                // console.log(msg)
                // var jsonObj = JSON.parse(msg);
                if (msg.code === 200) {
                    $('.alert').html("更新成功！");
                    $.ajax({
                        type: "post",
                        url: baseUrl + "/logout",
                    });
                    $('#pwdModal').modal('show');
                } else {
                    $('.alert').html(msg.content);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $('.alert').html("请求出现错误！");
            }
        });
    });
    
    function valid(obj) {
        obj.removeClass("is-invalid");
        obj.addClass("is-valid");
    }
    function invalid(obj) {
        obj.removeClass("is-valid");
        obj.addClass("is-invalid");
    }

});