$(document).ready(function () {
    var navUl = $('#navUl');
    $('#refresh').click(function () {
        refreshMenu();
    });
    $('#add').click(function () {
        $('#menuModal h5').html('新增菜单');
        $('#menuModal').modal({
            keyboard: false,
            backdrop: 'static'
        });
    });
    $('#menuModal').on('hidden.bs.modal', function (e) {
        $('#url').attr("disabled", false);
        $('#menuType').attr("disabled", false);

        document.getElementById("menuForm").reset();
    });
    $('#formSubmit').click(function () {
        var t = $('#menuForm').serialize();
        var url = baseUrl + "/au/menu/add";
        if ($('#id').val() !== '') {
            url = baseUrl + "/au/menu/update";
        }

        $.confirm({
            title: '操作结果',
            icon: 'fa fa-info',
            theme: 'modern',
            closeIcon: true,
            animation: 'scale',
            buttons: {
                ok: {
                    text: "确定",
                    btnClass: "btn btn-success",
                    keys: ['enter', 'esc']
                }
            },
            // type: "blue",
            content: function () {
                var self = this;
                return $.ajax({
                    method: 'POST',
                    dataType: "json",
                    url: url,
                    data: t
                }).done(function (msg) {
                    if (msg.code === 200) {
                        self.setContent('操作成功！');
                        self.setIcon('fa fa-smile-o');
                        self.setType("green");
                        $('#menuModal').modal('hide');
                        refreshMenu();
                    } else {
                        self.setContent(msg.content);
                        self.setIcon('fa fa-frown-o');
                        self.setType("red");
                    }
                }).fail(function () {
                    self.setContent('操作出错！');
                    self.setIcon('fa fa-frown-o');
                    self.setType("red");
                });
            }
        });
    });


    $("#menuType").change(function () {
        var val = $(this).val();
        if (val === 'TYPE_DROPDOWN') {
            $('#url').attr("disabled", true);
        } else {
            $('#url').attr("disabled", false);
        }
    });

    function refreshMenu() {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: baseUrl + "/au/menu/getAll",
            success: function (msg) {
                var html = formatMenu(msg);
                navUl.empty();
                navUl.html(html);
                $('.ttt').click(function () {
                    operation($(this));
                });
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.confirm({
                    content: '刷新出错！',
                    icon: 'fa fa-frown-o',
                    theme: 'modern',
                    closeIcon: true,
                    animation: 'scale',
                    type: 'red',
                    buttons: {
                        ok: {
                            text: "确定",
                            btnClass: "btn btn-danger",
                            keys: ['enter']
                        }
                    }
                });
            }
        });
        $.ajax({
            type: "POST",
            dataType: "json",
            url: baseUrl + "/au/menu/select/parent",
            success: function (msg) {
                var parent = $("#parentId");
                parent.empty();
                parent.append("<option value=\"0\">无</option>");
                for (var menuParent in msg) {
                    var menu = msg[menuParent];
                    parent.append("<option value=\"" + menu.id + "\">" + menu.manuName + "</option>");

                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.error(errorThrown);
            }
        });

    }


    function formatMenu(sysMenuMaps) {
        var menuHtml = "";
        var i = 1;
        for (var sysMenuMap in sysMenuMaps) {
            var menu = sysMenuMaps[sysMenuMap];
            var item = "";
            if (!menu.parent) {
                item = "<li class=\"nav-item\" data-id='" + menu.sysMenu.id + "'>" +
                    "    <a href=\"#\" class=\"nav-link\">" +
                    "        <i class=\"nav-icon fa " + menu.sysMenu.cssIcon + " \"></i>" +
                    "        <p>" + menu.sysMenu.manuName +
                    "            <div class=\"float-right\">" +
                    "               <i data-type='up' class=\"fa fa-arrow-up ttt\"></i>" +
                    "               <i data-type='down' class=\"fa fa-arrow-down ttt\"></i>" +
                    "               <i data-type='edit' class=\"fa fa-pencil-square-o ttt\"></i>" +
                    "               <i data-type='remove' class=\"fa fa-times ttt\"></i>" +
                    "            </div>" +
                    "        </p>" +
                    "    </a>" +
                    "</li>";
            } else {
                var itemChild = formatMenu(menu.sysMenuMaps);
                item = "<li class=\"nav-item has-treeview\" data-id='" + menu.sysMenu.id + "'>" +
                    "    <a href=\"#\" class=\"nav-link\">" +
                    "        <i class=\"nav-icon fa " + menu.sysMenu.cssIcon + "\"></i>" +
                    "        <p>" +
                    "            <span>" + menu.sysMenu.manuName + "</span>" +
                    "            <div class=\"float-right\">" +
                    "               <i data-type='up' class=\"fa fa-arrow-up ttt\"></i>" +
                    "               <i data-type='down' class=\"fa fa-arrow-down ttt\"></i>" +
                    "               <i data-type='edit' class=\"fa fa-pencil-square-o ttt\"></i>" +
                    "               <i data-type='remove' class=\"fa fa-times ttt\"></i>" +
                    "               <i class=\"right fa fa-angle-left\"></i>" +
                    "            </div>" +
                    "        </p>" +
                    "    </a>" +
                    "    <ul class=\"nav nav-treeview\">" +
                    itemChild +
                    "    </ul>" +
                    "</li>";

            }
            menuHtml += item;
            i++;
        }
        return menuHtml;
    }

    var stopMoveConfirm = $.confirm({
        content: '已经无法移动了喔！',
        icon: 'fa fa-hand-paper-o',
        theme: 'modern',
        closeIcon: true,
        animation: 'scale',
        type: 'green',
        lazyOpen: true,
        buttons: {
            ok: {
                text: "确定",
                btnClass: "btn btn-success",
                keys: ['enter', 'esc']
            }
        }
    });

    function operation(obj) {
        var operatObj = $(obj);
        var type = operatObj.attr('data-type');
        var url = "";
        var id = "";
        var oId = "";
        var parentsObj = $(obj).parents("li.nav-item");
        if (parentsObj.length > 1) {
            parentsObj = $(parentsObj[0]);
        }
        id = parentsObj.attr("data-id");
        var prevObj = parentsObj.prev();
        var nextObj = parentsObj.next();

        var data;
        if (type === 'up') {
            if (prevObj.length <= 0) {
                stopMoveConfirm.open();
                return;
            }
            oId = prevObj.attr("data-id");
            data = {id: id, oId: oId};
            url = baseUrl + "/au/menu/order";
        } else if (type === 'down') {
            if (nextObj.length <= 0) {
                stopMoveConfirm.open();
                return;
            }
            oId = nextObj.attr("data-id");
            data = {id: id, oId: oId};
            url = baseUrl + "/au/menu/order";
        } else if (type === 'edit') {
            url = baseUrl + "/au/menu/update";
            data = {id: id};
        } else if (type === 'remove') {
            url = baseUrl + "/au/menu/delete";
            data = {id: id};
        } else {
            return;
        }

        if (type === "edit") {
            edit(id);
        } else if (type === "remove") {
            $.confirm({
                title: '删除!',
                content: '确认继续执行删除操作？',
                icon: 'fa fa-exclamation',
                theme: 'modern',
                closeIcon: true,
                animation: 'scale',
                type: 'orange',
                buttons: {
                    ok: {
                        text: "删除",
                        btnClass: "btn btn-warning",
                        // keys: ['enter'],
                        action: function () {
                            $.confirm({
                                title: '操作结果',
                                icon: 'fa fa-info',
                                theme: 'modern',
                                closeIcon: true,
                                animation: 'scale',
                                type: "blue",
                                buttons: {
                                    ok: {
                                        text: "确定",
                                        btnClass: "btn btn-success",
                                        keys: ['enter', 'esc']
                                    }
                                },
                                content: function () {
                                    var self = this;
                                    return $.ajax({
                                        method: 'POST',
                                        dataType: "json",
                                        url: url,
                                        data: data
                                    }).done(function (msg) {
                                        if (msg.code === 200) {
                                            self.setContent('操作成功！');
                                            self.setIcon('fa fa-smile-o');
                                            self.setType("green");
                                            refreshMenu();
                                        } else if (msg.code === 10203) {
                                            self.close();
                                            deleteAll(id);
                                        } else {
                                            self.setContent(msg.content);
                                            self.setIcon('fa fa-frown-o');
                                            self.setType("red");
                                        }
                                    }).fail(function () {
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

        } else {
            $.confirm({
                title: '操作结果',
                icon: 'fa fa-info',
                theme: 'modern',
                closeIcon: true,
                animation: 'scale',
                // type: "blue",
                autoClose: 'ok|3000',
                buttons: {
                    ok: {
                        text: "确定",
                        btnClass: "btn btn-primary",
                        keys: ['enter', 'esc']
                    }
                },
                content: function () {
                    var self = this;
                    return $.ajax({
                        method: 'POST',
                        dataType: "json",
                        url: url,
                        data: data
                    }).done(function (msg) {
                        if (msg.code === 200) {
                            self.setContent('操作成功！');
                            self.setIcon('fa fa-smile-o');
                            self.setType("green");
                            refreshMenu();
                        } else {
                            self.setContent(msg.content);
                            self.setIcon('fa fa-frown-o');
                            self.setType("red");
                        }
                    }).fail(function () {
                        self.setContent('操作出错！');
                        self.setIcon('fa fa-frown-o');
                        self.setType("red");
                    });
                }
            });
        }
    }

    function edit(id) {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: baseUrl + "/au/menu/select",
            data: {
                id: id
            },
            success: function (msg) {
                for (var field in msg) {
                    var id = "" + field;
                    var data = msg[field];
                    $("#" + id).val(data);
                }
                $("#menuType").change();
                $('#menuModal h5').html('编辑菜单');
                $('#menuType').attr("disabled", true);
                $('#menuModal').modal({
                    keyboard: false,
                    backdrop: 'static'
                });
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.confirm({
                    content: '获取数据出错！',
                    icon: 'fa fa-frown-o',
                    theme: 'modern',
                    closeIcon: true,
                    animation: 'scale',
                    type: 'red',
                    buttons: {
                        ok: {
                            text: "确定",
                            btnClass: "btn btn-danger",
                            keys: ['enter']
                        }
                    }
                });
            }
        });
    }

    function deleteAll(id) {
        $.confirm({
            title: '删除!',
            content: '含有子菜单，是否一同删除子菜单？',
            icon: 'fa fa-exclamation-triangle',
            theme: 'modern',
            closeIcon: true,
            animation: 'scale',
            type: 'orange',
            buttons: {
                ok: {
                    text: "删除所有",
                    btnClass: "btn btn-warning",
                    // keys: ['enter'],
                    action: function () {
                        $.confirm({
                            title: '操作结果',
                            icon: 'fa fa-info',
                            theme: 'modern',
                            closeIcon: true,
                            animation: 'scale',
                            type: "blue",
                            buttons: {
                                ok: {
                                    text: "确定",
                                    btnClass: "btn btn-success",
                                    keys: ['enter', 'esc']
                                }
                            },
                            content: function () {
                                var self = this;
                                return $.ajax({
                                    method: 'POST',
                                    dataType: "json",
                                    url: baseUrl + "/au/menu/delete/all",
                                    data: {id: id}
                                }).done(function (msg) {
                                    if (msg.code === 200) {
                                        self.setContent('操作成功！');
                                        self.setIcon('fa fa-smile-o');
                                        self.setType("green");
                                        refreshMenu();
                                    } else {
                                        self.setContent(msg.content);
                                        self.setIcon('fa fa-frown-o');
                                        self.setType("red");
                                    }
                                }).fail(function () {
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
    }

    refreshMenu();

});