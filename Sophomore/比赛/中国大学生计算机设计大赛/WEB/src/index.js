var page = 1;
var record = [];
var checked_record_id = [];
var currPage;
var id;
var total;
onload = function () {
    $.ajax({
        type: 'POST',
        url: 'http://api.kernelylz.cn/get?page=' + 1,
        dataType: 'JSON',
        success: function (data) {
            // record = organizeData(data.content);
            // console.log(data.content);
            total = data.total
            // console.log(total)
            initHtml(data);
            add_function();
            layui.use('element', function () {
                var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

                //监听导航点击
                element.on('nav(demo)', function (elem) {
                    //console.log(elem)
                    layer.msg(elem.text());
                });
            });
            layui.use(['form', 'layedit', 'laydate'], function () {
                var form = layui.form
                    , layer = layui.layer
                    , layedit = layui.layedit
                    , laydate = layui.laydate;
            });
            layui.use('form', function () {
                var form = layui.form;
                var checked_id;
                //这里的 menu　就是 HTML上面的lay-filter值，就固定一个值就好
                form.on('checkbox(all)', function (data) {
                    var isChecked = data.elem.checked;
                    //这里实现勾选
                    if (isChecked == true) {
                        $("input[name='checkbox']").prop("checked", true);
                        $("#selected_record_number").text(5);
                        checked_record_id = ['1', '2', '3', '4', '5'];
                        form.render('checkbox');
                    } else {
                        $("input[name='checkbox']").prop("checked", false);
                        $("#selected_record_number").text(0);
                        checked_record_id = [];
                        form.render('checkbox');
                    }
                });
                form.on('checkbox(checkbox)', function (data) {
                    var isChecked = data.elem.checked;
                    if (isChecked == true) {
                        checked_record_id.push(data.value);
                    } else {
                        var index = checked_record_id.indexOf(data.value);
                        if (index != -1) {
                            checked_record_id.splice(index, 1);
                        }
                    }
                    if (checked_record_id.length == 5) {
                        $("input[name='checkbox_all']").prop("checked", true);
                        form.render('checkbox');
                    } else {
                        $("input[name='checkbox_all']").prop("checked", false);
                        form.render('checkbox');
                    }
                    $("#selected_record_number").text(checked_record_id.length);
                });
                form.on('submit(add_text)', function (data) {
                    // console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                    // console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                    // console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}

                    var new_record = [];
                    new_record.push(data.field.title)

                    $.ajax({
                        type: 'POST',
                        url: 'http://api.kernelylz.cn/import',
                        dataType: 'JSON',
                        data: {
                            data: new_record
                        },
                        success: function (data) {
                            alert(JSON.stringify(data))
                        }
                    })
                    return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
                });
                layui.use(['laypage', 'layer'], function () {
                    var laypage = layui.laypage
                        , layer = layui.layer;

                    console.log(total)
                    //自定义样式
                    laypage.render({
                        elem: 'demo2'
                        , count: total
                        , theme: '#1E9FFF'
                        , limit: 5
                        , jump: function (obj, first) {
                            //obj包含了当前分页的所有参数，比如：
                            // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                            // console.log(obj.limit); //得到每页显示的条数
                            currPage = obj.curr;
                            // console.log(currPage);
                            //首次不执行
                            if (!first) {
                                $.ajax({
                                    type: 'POST',
                                    url: 'http://api.kernelylz.cn/get?page=' + obj.curr,
                                    dataType: 'JSON',
                                    success: function (data) {
                                        initHtml(data);
                                        // record = [];
                                        // organizeData(data.content);
                                    }
                                })
                            }
                        }
                    });
                });
                layui.use('upload', function () {
                    var $ = layui.jquery
                        , upload = layui.upload;
                    upload.render({ //允许上传的文件后缀
                        elem: '#upload'
                        , url: 'http://api.kernelylz.cn/import_file'
                        , accept: 'file' //普通文件
                        , auto: false
                        , exts: 'csv|xlsx' //只允许上传压缩文件
                        , bindAction: '#add_file' //指向一个按钮触发上传
                        , done: function (res) {
                            console.log(res)
                        }
                    });
                });

            });
        },
        error: function (data) {
            alert("网络出小差了，请刷新网页");
        }
    })




    // for(var i = 0; i < (checked_record_id.length == 0 ? 1 : checked_record_id.length); i++){

    // }
}

//返回首页
function return_home(type) {
    if(type == 1){
        $("#view_wrap")[0].style.display = "none";
        $("#edit_wrap")[0].style.display = "none";
        $("#text_extraction_wrap")[0].style.display = "flex";
    }else{
        $("#view_wrap")[0].style.display = "none";
        $("#show_wrap")[0].style.display = "block";
    }

}

//重组数据，便于查看
// function organizeData(data) {
//     var i = 0;
//     var j;
//     var k = 0;
//     var record_item;
//     var item = {};
//     var nature = ['标本', '病理', '上切端是否累及', '下切端是否累及', '基底切端是否累及', '病理等级', '肿瘤大小', '分化等级', '浸润', '淋巴结是否转移', '转移比例', '脉管侵犯', '神经侵犯', '性状'];
//     for (j = 0; j < data.length; j++) {
//         i = 0;
//         k = j;
//         record_item = [];
//         for (; i < 14; i++) {
//             for (; k < data.length;) {
//                 item = {};
//                 item['id'] = i + 1;
//                 item['nature'] = nature[i];
//                 item['result'] = data[k][i + 1];
//                 record_item.push(item);
//                 break;
//             }
//         }
//         record.push(record_item);
//     }
//     return record;
// }

function search() {
    var keyword = $("input:text[name='search']").val();
    $("input:text[name='search']").val("")
    // $.ajax({
    //     type: 'POST',
    //     url: 'http://api.kernelylz.cn/get?page='
    // })
    console.log(currPage)
    $.ajax({
        type: 'POST',
        url: 'http://api.kernelylz.cn/search?page=' + currPage + "&keyword=" + keyword,
        dataType: 'JSON',
        success: function (data) {
            $("#keyword")[0].innerText = '"' + keyword + '"';
            $("#search_total")[0].innerText = data.total;
            $("#tip").css("visibility", "visible");
            layui.use(['laypage', 'layer'], function () {
                var laypage = layui.laypage
                    , layer = layui.layer;

                //自定义样式
                laypage.render({
                    elem: 'demo2'
                    , count: data.total
                    , theme: '#1E9FFF'
                    , limit: 5
                    , jump: function (obj, first) {
                        //obj包含了当前分页的所有参数，比如：
                        // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                        // console.log(obj.limit); //得到每页显示的条数
                        currPage = obj.curr;
                        console.log("外面")
                        // console.log(currPage);
                        //首次不执行
                         if (!first) {
                             console.log("里面")
                             $.ajax({
                                 type: 'POST',
                                 url: 'http://api.kernelylz.cn/search?page=' + currPage + "&keyword=" + keyword,
                                 dataType: 'JSON',
                                 success: function (data) {
                                     initHtml(data);
                                 }
                             })
                         }
                    }
                });
            });
            console.log("最外面")
            initHtml(data);

        }
    })

}

function initHtml(data) {
    var i;
    // console.log(data)
    //for循环添加html代码
    // for (i = 0; i < data.content.length; i++) {
    //     // $("#record").append("<input type='checkbox' lay-filter='checkbox'  id='checkbox_" + (i + 1) + "' name='checkbox' lay-skin='primary' title=' '>")
    //     $("#record").append(
    //         "<div class='record_" + (i + 1) + "'>\
    //                         <input type='checkbox' lay-filter='checkbox'  id='checkbox_" + (i + 1) + "' name='checkbox' lay-skin='primary' title=' '>\
    //                         <div class='record_detail'>\
    //                             <div class='id_title' id='record_id_" + (i + 1) + "'>\
    //                             记录" + data.content[i][0] + "\
    //                             </div>\
    //                             <div class='changeTime'>\
    //                                 <div id='time_" + (i + 1) + "' >\
    //                                 " + data.content[i][15] + "\
    //                                 </div>\
    //                             </div>\
    //                             <div class='operate'>\
    //                                 <div>查看</div>\
    //                                 <div>编辑</div>\
    //                                 <div>导出</div>\
    //                                 <div>删除</div>\
    //                             </div>\
    //                         </div>\
    //                     </div>"
    //     );
    // }
    var temp = 0
    for (i = 0; i < 5; i++) {
        document.getElementById("record_id_" + (i + 1)).innerText = data.content[i][1] + ". . .";
        document.getElementById("time_" + (i + 1)).innerText = data.content[i][15];
        document.getElementById("checkbox_" + (i + 1)).value = data.content[i][0];
    }
    $("div[list_number='1']").attr("number", data.content[0][0]);
    $("div[list_number='2']").attr("number", data.content[1][0]);
    $("div[list_number='3']").attr("number", data.content[2][0]);
    $("div[list_number='4']").attr("number", data.content[3][0]);
    $("div[list_number='5']").attr("number", data.content[4][0]);

}

function add_function() {
    // $("div[name=view_more]").attr("onclick", "view_more_record(checked_record_id.length)");
    // $("div[name=edit_more]").attr("onclick", "edit_more_record()");
    // $("div[name=expt_more]").attr("onclick", "delt_more_record()");
    // $("div[name=delt_more]").attr("onclick", "expt_more_record()");

    $("div[name=view]").attr("onclick", "view_record(this, 1)");
    $("div[name=edit]").attr("onclick", "edit_record(this)");
    // $("div[name=expt]").attr("onclick", "expt_record(this, currPage)");
    $("div[name=delt]").attr("onclick", "delt_record(this)");
}

// function expt_record(which_record, page){
//     // var delID
//     // delID = (page - 1) * 5 + parseInt($(which_record).attr('number'));
//     $.ajax({
//         type: 'POST',
//         url: 'http://api.kernelylz.cn/export',
//         // dataType: 'JSON',
//         success: function (res) {
//             console.log(JSON.stringify(res))
//         }
//     })
// }

//查看某项记录，以表格形式呈现
function view_record(which_record, type) {
    var id = $(which_record).attr('number')||which_record;
    // console.log(id)
    $(".return_icon").attr("onclick", "return_home(" + type + ")");
    $("#text_extraction_wrap")[0].style.display = "none";
    $("#show_wrap")[0].style.display = "none";
    $("#view_wrap")[0].style.display = "flex";
    layui.use('table', function () {
        var table = layui.table;
        // var table_name = [''];
        table.render({
            elem: '#view_table'
            , url: 'http://api.kernelylz.cn/get/' + id
            , cellMinWidth: 80
            , cols: [[
                {field: 'ID', title: 'ID'}
                , {field: 'attr', title: '属性'}
                , {field: 'result', title: '诊断结果'}
            ]]
            // , data: record[id - 1]
            , limit: 14
        });
    });
}

//跳转页面，编辑表格中的数据
function edit_record(which_record) {
    // console.log($(which_record).attr('number'));
    // id = (page - 1) * 5 + parseInt($(which_record).attr('number'));
    id = $(which_record).attr('number')
    var url = "http://api.kernelylz.cn/get/" + id;
    $("#edit_table").attr("lay-data", "{url:'" + url + "', id:'edit_table'}");
    $("#text_extraction_wrap")[0].style.display = "none";
    $("#edit_wrap")[0].style.display = "flex";
    layui.use('table', function () {
        var table = layui.table;
        table.reload('edit_table', {
            url: url
            , where: {} //设定异步数据接口的额外参数
            //,height: 300
        });
        table.on('edit(edit_table)', function (obj) {
            var value = obj.value //得到修改后的值
                , data = obj.data //得到所在行所有键值
                , field = obj.field //得到字段

            layer.msg('[ID: ' + data.ID + '] ' + field + ' 字段更改为：' + value);
        });
        // console.log(table)
    });
}

//编辑记录后更新数据
function update_data() {
    var table_data = layui.table.cache.edit_table;
    var final_data = [];
    var temp = {};
    for (var i = 0; i < 14; i++) {

        final_data.push(table_data[i].result);

    }

    $.ajax({
        type: 'POST',
        url: 'http://api.kernelylz.cn/update/' + id,
        dataType: 'JSON',
        // params:{"contentType": "application/json;charset=utf-8"},
        data: {
            "标本": final_data[0],
            "病理": final_data[1],
            "上切端是否累及": final_data[2],
            "下切端是否累及": final_data[3],
            "基底切端是否累及": final_data[4],
            "病理等级": final_data[5],
            "肿瘤大小": final_data[6],
            "分化等级": final_data[7],
            "浸润": final_data[8],
            "淋巴结是否转移": final_data[9],
            "转移比例": final_data[10],
            "脉管侵犯": final_data[11],
            "神经侵犯": final_data[12],
            "性状": final_data[13]
        },
        success: function (res) {
            alert("修改成功!!!");
            window.location.reload();
        },
        error: function (res) {
            alert(JSON.stringify(res))

        }
    })
    // console.log(final_data)
}

//删除记录
function delt_record(which_record) {
    // id = (page - 1) * 5 + parseInt($(which_record).attr('number'));
    id = $(which_record).attr('number');
    layer.confirm('确定删除记录？', {
        btn: ['确定', '取消'] //按钮
    }, function () {//向服务器发送请求加上相应记录id
        $.ajax({
            type: 'POST',
            url: 'http://api.kernelylz.cn/delete/' + id,
            dataType: 'JSON',
            success: function (res) {
                layer.msg('删除成功');
                window.location.reload();
            },
            error: function (res) {
                layer.msg('删除失败，请重试');
            }
        })
    }, function () {
        // layer.msg('删除失败，请重试');
    });

}

//点击添加记录按钮跳转到添加记录页面
function to_add_record() {
    $('#add_record').click();
}

function to_export_record() {
    $('#export').click();

}

function show(){
    layui.use(['laypage', 'layer'], function () {
        var laypage = layui.laypage
            , layer = layui.layer;

        console.log(total)
        //自定义样式
        laypage.render({
            elem: 'btn'
            , count: total
            , theme: '#1E9FFF'
            , limit: 5
            , jump: function (obj, first) {
                //obj包含了当前分页的所有参数，比如：
                // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                // console.log(obj.limit); //得到每页显示的条数
                currPage = obj.curr;
                // console.log(currPage);
                //首次不执行
                    $.ajax({
                        type: 'POST',
                        url: 'http://api.kernelylz.cn/get?page=' + obj.curr,
                        dataType: 'JSON',
                        success: function (data) {
                            init_card_html(data.content)
                        }
                    })
            }
        });
    });
}

function init_card_html(data){
    $(".card_wrap").html("")
    for (var i = 0; i < data.length; i++){

        $(".card_wrap").append("<div class='card_" + (i+1) + " id='card_" + (i+1) + "' onclick='view_record(" + data[i][0] + "," + "2)'>\
            <div class='card_title'>\
            记录" + data[i][0] + "\
            </div>\
            <div class='card_content'>\
            " + data[i][1]+ "<br>\
            " + data[i][2]+ "<br>\
            " + data[i][3]+ "<br>\
            " + data[i][4]+ "<br>\
            " + data[i][5]+ "<br>\
            " + data[i][6]+ "<br>\
            " + data[i][7]+ "<br>\
            " + data[i][8]+ "<br>\
            " + data[i][9]+ "<br>\
            " + data[i][10]+ "<br>\
            " + data[i][11]+ "<br>\
            " + data[i][12]+ "<br>\
            " + data[i][13]+ "<br>\
            " + data[i][14]+ "\
            <div style='border-top: solid #b2b5b8 1px;'>\
            " + data[i][15]+ "\
            </div>\
            </div>\
            </div>")
    }
}

//菜单选中不同的选项进行css设定
function changePage(id) {
    if (id === "text_extraction") {
        document.getElementById("text_extraction").className = "choosed";
        document.getElementById("add_record").className = "unChoosed";
        document.getElementById("export").className = "unChoosed";
        document.getElementById("show").className = "unChoosed";
        document.getElementById("text_extraction_wrap").style.display = "flex";
        document.getElementById("add_record_wrap").style.display = "none";
        document.getElementById("export_wrap").style.display = "none";
        document.getElementById("show_wrap").style.display = "none";
        $("#view_wrap").css('display', 'none');
        $("#edit_wrap").css('display', 'none');
        $("#add_record_wrap").css('display', 'none');
        $("#show_wrap").css('display', 'none');
    } else if (id === "add_record") {
        document.getElementById("text_extraction").className = "unChoosed";
        document.getElementById("add_record").className = "choosed";
        document.getElementById("export").className = "unChoosed";
        document.getElementById("show").className = "unChoosed";
        document.getElementById("text_extraction_wrap").style.display = "none";
        document.getElementById("add_record_wrap").style.display = "flex";
        document.getElementById("export_wrap").style.display = "none";
        document.getElementById("show_wrap").style.display = "none";
        $("#view_wrap").css('display', 'none');
        $("#edit_wrap").css('display', 'none');
        $("#show_wrap").css('display', 'none');
    } else if (id === "export") {
        document.getElementById("text_extraction").className = "unChoosed";
        document.getElementById("add_record").className = "unChoosed";
        document.getElementById("export").className = "choosed";
        document.getElementById("show").className = "unChoosed";
        document.getElementById("text_extraction_wrap").style.display = "none";
        document.getElementById("add_record_wrap").style.display = "none";
        document.getElementById("export_wrap").style.display = "flex";
        document.getElementById("show_wrap").style.display = "none";
        $("#view_wrap").css('display', 'none');
        $("#edit_wrap").css('display', 'none');
        $("#add_record_wrap").css('display', 'none');
        $("#show_wrap").css('display', 'none');
    }else if (id === "show") {
        document.getElementById("text_extraction").className = "unChoosed";
        document.getElementById("add_record").className = "unChoosed";
        document.getElementById("export").className = "unChoosed";
        document.getElementById("show").className = "choosed";
        document.getElementById("text_extraction_wrap").style.display = "none";
        document.getElementById("add_record_wrap").style.display = "none";
        document.getElementById("export_wrap").style.display = "none";
        document.getElementById("show_wrap").style.display = "block";
        $("#view_wrap").css('display', 'none');
        $("#edit_wrap").css('display', 'none');
        $("#add_record_wrap").css('display', 'none');
    }

}

