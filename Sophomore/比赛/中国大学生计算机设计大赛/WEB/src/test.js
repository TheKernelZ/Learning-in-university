function test() {
    $.ajax({
        type: 'POST',
        url: 'http://47.102.196.224/update/1',
        dataType: 'JSON',
        // params:{"contentType": "application/json;charset=utf-8"},
        data:{

                "标本": "直肠癌根治标本",
                "病理": "腺癌",
                "上切端是否累及": "否",
                "下切端是否累及": "否",
                "基底切端是否累及": "否",
                "病理等级": "（空）",
                "肿瘤大小": "3.5×3.5×2.0cm",
                "分化等级": "x分化",
                "浸润": "肠壁肌层",
                "淋巴结是否转移": "无",
                "转移比例": "",
                "脉管侵犯": "无",
                "神经侵犯": "无",
                "性状": "隆起型"
        },
        success: function (res) {
            alert(JSON.stringify(res))
        },
        error: function (res) {
            alert(JSON.stringify(res))

        }
    })
}