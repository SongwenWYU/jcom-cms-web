var state = [];
state["STATE_ACCOUNTEXPIRED"] = " 账号过期";
state["STATE_LOCK"] = "账号锁定";
state["STATE_TOKENEXPIRED"] = "TOKEN过期";
state["STATE_NORMAL"] = "账号正常";

$(document).ready(function () {
    var table = $('#userTable').DataTable({
        "language": dataTableLanguage,
        "dom": '<"top"l>t<"bottom"ip><"clear">',
        "info": true,
        "deferRender": true,
        "processing": true,
        "serverSide": true,
        // "pageLength": 10,
        "ajax": {
            "url": baseUrl + "/au/user/getAll",
            "type": "post",
            "data": function (d) {
                var obj = {};
                obj.username = $('#username').val();
                obj.nickname = $('#nickname').val();
                obj.start = d.start;
                obj.length = d.length;
                return obj;
            }
        },
        "searching": false,
        "ordering": false,
        "columns": [
            // { "data": "id", "title":"ID" },
            {"data": "username", "title": "用户名"},
            {"data": "nickname", "title": "昵称"},
            {
                "data": "state", "title": "账号状态", "render": function (data, type, row) {
                    return state[data];
                }
            },
            {"data": "email", "title": "邮箱"},
            {"data": "gmtCreate", "title": "创建时间"},
            {"data": "gmtModified", "title": "修改时间"},
            {
                "data": "username", "title": "操作", "render": function (data, type, row) {
                    var buttonGroup = "<div class=\"btn-group\" role=\"group\" aria-label=\"操作\">\n";
                    if (row.state === "STATE_NORMAL") {
                        buttonGroup += "  <button type=\"button\" class=\"btn btn-warning btn-sm user-operate\" _type=\"lock\" _data='"+data+"'>锁定</button>\n";
                    } else {
                        buttonGroup += "  <button type=\"button\" class=\"btn btn-success btn-sm user-operate\" _type=\"state\" _data='"+data+"'>恢复</button>\n";
                    }

                    buttonGroup += "  <button type=\"button\" class=\"btn btn-danger btn-sm user-operate\" _type=\"delete\" _data='"+data+"'>删除</button>\n" +
                        "</div>";
                    return buttonGroup;
                }
            }
        ]
    });
    $("#query").click(function () {
        table.ajax.reload(null, false);
    });

    $("#userTable").on('click', '.user-operate',function() {
        var typeName;
        var url;
        var themeColor;
        var icon;
        var btnTheme;
        var datas;

        var type = $(this).attr("_type");
        var data = $(this).attr("_data");
        if ("state" === type) {
            datas = {"username": data, "state":"STATE_NORMAL"};
            typeName = "恢复";
            url = baseUrl + "/au/user/update/state";
            themeColor = "green";
            icon = "fa-unlock-alt";
            btnTheme = "btn-success";
        } else if ("delete" === type) {
            datas = {"username": data};
            url = baseUrl + "/au/user/delete";
            typeName = "删除";
            themeColor = "red";
            icon = "fa-trash-o";
            btnTheme = "btn-danger";
        } else if ("lock" === type) {
            datas = {"username": data, "state":"STATE_LOCK"};
            typeName = "锁定";
            url = baseUrl + "/au/user/update/state";
            themeColor = "orange";
            icon = "fa-lock";
            btnTheme = "btn-warning";
        } else {
            return;
        }


        var jc = $.confirm({
            title: typeName+'!',
            content: '确认继续执行操作？',
            icon: 'fa '+icon,
            theme: 'modern',
            closeIcon: true,
            animation: 'scale',
            type: themeColor,
            buttons: {
                ok: {
                    text: typeName,
                    btnClass: btnTheme,
                    // keys: ['enter'],
                    action: function(){
                        $.confirm({
                            title: '操作结果',
                            icon: 'fa fa-info',
                            theme: 'modern',
                            closeIcon: true,
                            animation: 'scale',
                            type: "blue",
                            content: function () {
                                var self = this;
                                return $.ajax({
                                    method: 'POST',
                                    dataType: "json",
                                    url: url,
                                    data: datas
                                }).done(function (msg) {
                                    if (msg.code === 200) {
                                        self.setContent('操作成功！');
                                        self.setIcon('fa fa-smile-o');
                                        self.setType("green");
                                        table.ajax.reload();
                                    } else {
                                        self.setContent(content);
                                        self.setIcon('fa fa-frown-o');
                                        self.setType("red");
                                    }
                                }).fail(function(){
                                    self.setContent('操作出错！');
                                    self.setIcon('fa fa-frown-o');
                                    self.setType("red");
                                });
                            }
                        });
                    }
                },
                cancel: {
                    text: "取消",
                    keys: ['esc']
                }
            }
        });
    });


});