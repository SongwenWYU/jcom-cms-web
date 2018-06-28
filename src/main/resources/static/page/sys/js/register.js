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

        if(username === '' || !/^[a-zA-Z0-9_-]{3,16}$/.test(username)){
            invalid(usernameObj);
            return false;
        }else {
            valid(usernameObj);
        }

        if(nickname === '' || /^[\s]/.test(nickname)){
            invalid(nicknameObj);
            return false;
        }else {
            valid(nicknameObj);
        }

        if(email === '' || !/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/.test(email)){
            invalid(emailObj);
            return false;
        }else {
            valid(emailObj);
        }

        if(password === '' || !/^[0-9a-zA-Z_]{6,15}$/.test(password)){
            invalid(passwordObj);
            return false;
        }else {
            valid(passwordObj);
        }


        var pwdMd5 = md5(password);

        var jc = $.confirm({
            // title: '操作结果',
            content: '正在注册……',
            icon: 'fa fa-refresh',
            theme: 'modern',
            closeIcon: true,
            animation: 'scale',
            buttons: {
                ok: {
                    text: '去登录',
                    isHidden: true,
                    btnClass: 'btn-success',
                    action: function (b) {
                        window.location.href=baseUrl+"/login";
                    }
                },
                error: {
                    text: '再次尝试',
                    isHidden: true,
                    btnClass: 'btn-info'
                }
            },
            content: function () {
                var self = this;

                return $.ajax({
                    method: 'POST',
                    dataType: "json",
                    url: baseUrl+"/register/commit",
                    data: {
                        username: username,
                        nickname: nickname,
                        email: email,
                        password: pwdMd5
                    }
                }).done(function (msg) {
                    if (msg.code === 200) {
                        self.setContent('注册成功！');
                        self.setIcon('fa fa-smile-o');
                        self.setType("green");
                        jc.buttons.ok.show()
                    } else {
                        self.setContent(content);
                        self.setIcon('fa fa-frown-o');
                        self.setType("red");
                        jc.buttons.error.show()
                    }
                }).fail(function(){
                    self.setContent('操作出错！');
                    self.setIcon('fa fa-frown-o');
                    self.setType("red");
                    jc.buttons.error.show()
                });
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