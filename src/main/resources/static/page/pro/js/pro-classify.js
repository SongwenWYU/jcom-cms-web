$(document).ready(function () {
    $('#classify-tree').jstree({
        'core' : {
            "animation" : 0,
            "check_callback" : true,
            "themes" : { "stripes" : true },
            'data' : {
                'url' : baseUrl + "/u/pro/classify/select",
                'type' : 'POST',
                'dataType': 'json',
                'data' : function (node) {
                    var id = 0;
                    if(node.id !== '#'){
                        id = node.id;
                    }
                    console.log(id)
                    var status = [1];
                    if($('#showCheckBox').is(":checked")){
                        status.push(-1);
                    }

                    var requestData = {
                        'id' : id ,
                        'status' : status
                    };

                    return requestData;
                },
                success: function(json){
                    // var formatJson = {};
                    $.each(json,function(idx,obj) {
                        obj.text = obj.classifyName;

                    });
                    return json;
                }
            }
        },
        "plugins" : [
            "contextmenu", "dnd", "search",
            "state", "types", "wholerow"
        ]});
});