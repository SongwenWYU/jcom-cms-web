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
    $("#updatePwdBtn").click(function () {
        var oldPasswordObj = $('#oldPassword');
        var newPasswordObj = $('#newPassword');
        var newPasswordObj2 = $('#newPassword2');
        var oldPassword = oldPasswordObj.val();
        var newPassword = newPasswordObj.val();
        var newPassword2 = newPasswordObj2.val();
        if (oldPassword === '') {
            oldPasswordObj.removeClass("is-valid");
            oldPasswordObj.addClass("is-invalid");
            return false;
        } else {
            oldPasswordObj.removeClass("is-invalid");
            oldPasswordObj.addClass("is-valid");
        }

        // 校验是否满足密码设置规则
        if (newPassword !== '' && /^[0-9a-zA-Z_]{6,15}$/.test(newPassword)) {
            newPasswordObj.removeClass("is-invalid");
            newPasswordObj.addClass("is-valid");
        } else {
            newPasswordObj.removeClass("is-valid");
            newPasswordObj.addClass("is-invalid");
            return false;
        }
        if (newPassword2 === '' || newPassword !== newPassword2) {
            newPasswordObj2.removeClass("is-valid");
            newPasswordObj2.addClass("is-invalid");
            return false;
        } else {
            newPasswordObj2.removeClass("is-invalid");
            newPasswordObj2.addClass("is-valid");
        }

        var oldMd5 = md5(oldPassword);
        var newMd5 = md5(newPassword);
        var new2Md5 = md5(newPassword2);

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
    
    $("#goHomeBtn").click(function () {
        parent.location.reload();
    })

});