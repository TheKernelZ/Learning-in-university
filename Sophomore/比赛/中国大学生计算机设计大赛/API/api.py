from flask import Flask, send_file, request, make_response
import re
import json
import pymysql
import numpy as np
import pandas as pd
from io import BytesIO
from extract import *
import os 
import xlrd
import time
 

app = Flask(__name__)


@app.route('/export', methods=["POST", "GET"])
def export():
    return_dict = {
        "标本": [],
        "病理": [],
        "上切端是否累及": [],
        "下切端是否累及": [],
        "基底切端是否累及": [],
        "病理等级": [],
        "肿瘤大小": [],
        "分化等级": [],
        "浸润": [],
        "淋巴结是否转移": [],
        "转移比例": [],
        "脉管侵犯": [],
        "神经侵犯": [],
        "性状": [],
        "时间": []
    }

    db = pymysql.connect("localhost", "root", "root", "BD")
    cursor = db.cursor()

    sql = "SELECT * FROM bowelCancerInformation"

    cursor.execute(sql)
    datas = cursor.fetchall()

    for _, biaoben, bingli, shangqieduan, xiaqieduan, jidiqieduan, bingli_level, zhongliudaxiao, fenhua_level, jinrun, linbajie, bili, maiguan, shenjing, xingzhuang, now in datas:
        return_dict["标本"].append(biaoben)
        return_dict["病理"].append(bingli)
        return_dict["上切端是否累及"].append(shangqieduan)
        return_dict["下切端是否累及"].append(xiaqieduan)
        return_dict["基底切端是否累及"].append(jidiqieduan)
        return_dict["病理等级"].append(bingli_level)
        return_dict["肿瘤大小"].append(zhongliudaxiao)
        return_dict["分化等级"].append(fenhua_level)
        return_dict["浸润"].append(jinrun)
        return_dict["淋巴结是否转移"].append(linbajie)
        return_dict["转移比例"].append(bili)
        return_dict["脉管侵犯"].append(maiguan)
        return_dict["神经侵犯"].append(shenjing)
        return_dict["性状"].append(xingzhuang)
        return_dict["时间"].append(now)
        

    result = pd.DataFrame(return_dict) 

    output = BytesIO()
    writer = pd.ExcelWriter(output, engine='xlsxwriter')

    result.to_excel(writer, startrow = 0, merge_cells = False, sheet_name = "Result")

    workbook = writer.book
    format = workbook.add_format()
    format.set_bg_color('#eeeeee')

    writer.close()
    output.seek(0)

    return send_file(output, attachment_filename="result.xlsx", as_attachment=True)



@app.route("/import", methods=["POST"])
def create():
    now = time.strftime('%Y-%m-%d %H:%M:%S',time.localtime())

    if request.method == "POST":
        # datas = request.form["data"]
        num = 0
        data = request.form.to_dict()["data[]"]
        content = []
        # print(data)
        # print(datas["data[]"])
        
        if data == [] or data == "" or data == None:
            return json.dumps({"msg": "No Data"}, ensure_ascii=False)

        db = pymysql.connect("localhost", "root", "root", "BD")
        
        cursor = db.cursor()

        #for data in datas:
        result = extract_one(data)
            # print(result)
        sql = create_insert_sql(result, str(now))
        # print(sql) 
            # return json.dumps(sql, ensure_ascii=False)
        try:
            i = cursor.execute(sql)
            db.commit()
            content.append(result)
        except:
            db.rollback()
            i = 0
        num += i

        result_text = json.dumps({"msg": "OK", "affect_row": num, "content": content}, ensure_ascii=False)
        rst = make_response(result_text)
        rst.headers['Access-Control-Allow-Origin'] = '*'

        return rst
        

@app.route("/import_file", methods=["POST"])
def import_as_excel():
    
    if request.method == "POST":
        num = 0
        content = []

        db = pymysql.connect("localhost", "root", "root", "BD")
        
        cursor = db.cursor()

        my_file = request.files['file']
        suffix = my_file.filename.split(".")[-1]
        
        if suffix == "csv":
            content = []

            for data in my_file.readlines():
                try:
                    data = data.decode("gbk")
                except:
                    
                    result_text = json.dumps({"msg": "Error Encode"}, ensure_ascii=False)
                    rst = make_response(result_text)
                    rst.headers['Access-Control-Allow-Origin'] = '*'

                    return rst

                now = time.strftime('%Y-%m-%d %H:%M:%S',time.localtime())
                result = extract_one(data)
                result["修改时间"] = str(now)
                sql = create_insert_sql(result, str(now))

                try:
                    i = cursor.execute(sql)
                    db.commit()
                    
                except:
                    db.rollback()
                    i = 0
                num += i
                content.append(result)
                
        elif suffix == "xlsx" or suffix == "xls":

            f = my_file.read()
            data = xlrd.open_workbook(file_contents=f)
            table = data.sheets()[0]
            names = data.sheet_names()
            status = data.sheet_loaded(names[0]) 
            content = []

            if status == True:
                data = table.col_values(0)
                for line in data:
                    line = line.strip()
                    
                    now = time.strftime('%Y-%m-%d %H:%M:%S',time.localtime())
                    result = extract_one(line)
                    result["修改时间"] = str(now)
                    sql = create_insert_sql(result, str(now))
                    print(sql)

                    try:
                        i = cursor.execute(sql)
                        db.commit()
                        content.append(result)
                    except:
                        db.rollback()
                        i = 0
                    num += i
                    content.append(result)

    result_text = json.dumps({"msg": "OK", "affect_row": num, "content": content}, ensure_ascii=False)
    rst = make_response(result_text)
    rst.headers['Access-Control-Allow-Origin'] = '*'

    return rst


@app.route("/update/<int:zid>", methods=["POST", "GET"])
def update(zid):
    now = time.strftime('%Y-%m-%d %H:%M:%S',time.localtime())
    datas = request.form
    result = {}
    for key in datas.to_dict():
        result[key] = datas[key]
    print(result)
    if datas == None:
    
        result_text = json.dumps({"msg": "No Data"}, ensure_ascii=False)
        rst = make_response(result_text)
        rst.headers['Access-Control-Allow-Origin'] = '*'

        return rst
    
    db = pymysql.connect("localhost", "root", "root", "BD")

    cursor = db.cursor()
    sql = create_update_sql(result, zid, str(now))

    try:
        i = cursor.execute(sql)
        db.commit()
    except:
        db.rollback()
        i = 0
        
    result_text = json.dumps({"msg": "OK", "affect_row": i}, ensure_ascii=False)
    rst = make_response(result_text)
    rst.headers['Access-Control-Allow-Origin'] = '*'

    return rst


@app.route("/delete/<int:zid>", methods=["GET", "POST"])
def delete(zid):
    db = pymysql.connect("localhost", "root", "root", "BD")

    cursor = db.cursor()
    sql = create_delete_sql(zid)

    try:
        i = cursor.execute(sql)
        db.commit()
    except:
        db.rollback()
        i = 0

    result_text = json.dumps({"msg": "OK", "affect_row": i}, ensure_ascii=False)
    rst = make_response(result_text)
    rst.headers['Access-Control-Allow-Origin'] = '*'

    return rst


@app.route("/get/<int:zid>", methods=["GET", "POST"])
def get_one(zid):
    db = pymysql.connect("localhost", "root", "root", "BD")

    cursor = db.cursor()
    sql = "SELECT * FROM bowelCancerInformation WHERE `id`={} LIMIT 1".format(zid)
    content = []
    result = []
    keys = ["标本", "病理", "上切端是否累及", "下切端是否累及", "基底切端是否累及", "病理等级", "肿瘤大小", "分化等级", "浸润", "淋巴结是否转移", "转移比例", "脉管侵犯", "神经侵犯", "性状"]
    i = 1

    cursor.execute(sql)
    content = cursor.fetchone()[1: ]
    
    for key, value in zip(keys, content):
        result_dict = {}
        result_dict["ID"] = i
        result_dict["attr"] = key
        result_dict["result"] = value
        result.append(result_dict)
        i += 1

    if content == [] or content == ():
        content = "No Data"

    result_text = json.dumps({"msg": "", "code": 0, "data": result}, ensure_ascii=False)
    rst = make_response(result_text)
    rst.headers['Access-Control-Allow-Origin'] = '*'

    return rst


@app.route("/get", methods=["GET", "POST"])
def get():
    num = 5
    page = int(request.args.get("page"))
    page = (page - 1) * 5

    db = pymysql.connect("localhost", "root", "root", "BD")

    cursor = db.cursor()
    sql = "SELECT * FROM bowelCancerInformation"
    
    content = []

    cursor.execute(sql)
    content = cursor.fetchall()[page: page+5] 

    if content == [] or content == ():
        content = "No Data" 

    result_text = json.dumps({"msg": "OK", "content": content}, ensure_ascii=False)
    rst = make_response(result_text)
    rst.headers['Access-Control-Allow-Origin'] = '*'

    return rst


@app.route("/search", methods=["GET", "POST"])
def search():
    
    keyword = request.args.get("keyword")
    page = int(request.args.get("page"))
    page = (page - 1) * 5

    db = pymysql.connect("localhost", "root", "root", "BD")

    cursor = db.cursor()
    sql = create_search_sql(keyword)
    
    content = []

    cursor.execute(sql)
    content = cursor.fetchall()[page: page+5]

    if content == [] or content == ():
        content = "No Data" 

    result_text = json.dumps({"msg": "OK", "content": content}, ensure_ascii=False)
    rst = make_response(result_text)
    rst.headers['Access-Control-Allow-Origin'] = '*'

    return rst


if __name__ == "__main__":
    app.run("0.0.0.0", port=80)
