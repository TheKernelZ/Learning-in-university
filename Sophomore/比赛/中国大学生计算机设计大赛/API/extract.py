import jieba
import re
import json


def extract_one(data):
    splited = []
    result = {
        "标本": "（空）",
        "病理": "（空）",
        "上切端是否累及": "（空）",
        "下切端是否累及": "（空）",
        "基底切端是否累及": "（空）",
        "病理等级": "（空）",
        "肿瘤大小": "（空）",
        "分化等级": "（空）",
        "浸润": "（空）",
        "淋巴结是否转移": "（空）",
        "转移比例": "（空）",
        "脉管侵犯": "（空）",
        "神经侵犯": "（空）",
        "性状": "（空）"
    }

    data = data.strip()
    splited = re.split("[,，。“”‘’:；：()（）、]", data)

    for content in splited:
        if "标本" in content:
            if result["标本"] == "（空）":
                result["标本"] = content
            else:
                result["标本"] += "-" + content
        if "分化" in content:
            if r"." in content:
                xindex = content.find(".")
                content = content[xindex+1: ]
            if result["分化等级"] == "（空）":
                index = content.find("分化")
                result["分化等级"] = content[:index+2]
            else:
                index = content.find("分化")
                result["分化等级"] += "-" + content[:index+2]
            
        if "浸润" in content:
            result["浸润"] = content[3:]
        if "大小" in content:
            index = content.find("大小")
            result["肿瘤大小"] = content[index+2:]
        if "脉管" in content:
            if "未" in content:
                result["脉管侵犯"] = "无"
            else:
                result["脉管侵犯"] = "有"
        if "转移" in content:
            if "未" in content:
                result["淋巴结是否转移"] = "无"
                result["转移比例"] = ""
            else:
                result["淋巴结是否转移"] = "是"
                x = re.findall(r"\d{1,2}/[0-9]\d*", content)
                if x != []:
                    result["转移比例"] = x[0]
        if "上" in content and "切端" in content:
            if "未" in content:
                result["上切端是否累及"] = "否"
            else:
                result["上切端是否累及"] = "是"
        if "下" in content and "切端" in content:
            if "未" in content:
                result["下切端是否累及"] = "否"
            else:
                result["下切端是否累及"] = "是"
        if "两切端" in content:
            if "未" in content:
                result["上切端是否累及"] = "否"
                result["下切端是否累及"] = "否"
            else:
                result["上切端是否累及"] = "是"
                result["下切端是否累及"] = "是"
        if "基底" in content:
            if "未" in content:
                result["基底切端是否累及"] = "否"
            else:
                result["基底切端是否累及"] = "是"
        if "切端" in content and "上" not in content and "下" not in content:
            result["上切端是否累及"] = "否"
            result["下切端是否累及"] = "否"
            result["基底切端是否累及"] = "否"
        if "神经" in content:
            if "未" in content:
                result["神经侵犯"] = "无"
            else:
                result["神经侵犯"] = "有"
        if "型" in content:
            result["性状"] = content
        if "腺癌" in content:
            if r"." in content:
                xindex = content.find(".")
                content = content[xindex+1: ]
            index = content.find("化")
            index2 = content.find("腺癌")
            if index == -1:
                result["病理"] = content[: index2+2]
            else:
                result["病理"] = content[index+1: index2+2]
            
            index3 = content.find("级")
            if index3 != -1:
                result["病理等级"] = content[index2+2: index3+1]

    return result


def create_insert_sql(result, now):
    sql = "INSERT INTO bowelCancerInformation(`biaoben`, `bingli`, `shangqieduan`, `xiaqieduan`, `jidiqieduan`, `bingli_level`, `zhongliudaxiao`, `fenhua_level`, `jinrun`, `linbajie`, `bili`, `maiguan`, `shenjing`, `xingzhuang`, `update_time`) VALUES('{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}', '{}')"
    biaoben = result["标本"]
    bingli = result["病理"]
    shangqieduan = result["上切端是否累及"]
    xiaqieduan = result["下切端是否累及"]
    jidiqieduan = result["基底切端是否累及"]
    bingli_level = result["病理等级"]
    zhongliudaxiao = result["肿瘤大小"]
    fenhua_level = result["分化等级"]
    jinrun = result["浸润"]
    linbajie = result["淋巴结是否转移"]
    bili = result["转移比例"]
    maiguan = result["脉管侵犯"] 
    shenjing = result["神经侵犯"] 
    xingzhuang = result["性状"]
    return sql.format(biaoben, bingli, shangqieduan, xiaqieduan, jidiqieduan, bingli_level, zhongliudaxiao, fenhua_level, jinrun, linbajie, bili, maiguan, shenjing, xingzhuang, now)


def create_update_sql(result, zid, now):
    sql = "UPDATE bowelCancerInformation SET `biaoben`='{}', `bingli`='{}', `shangqieduan`='{}', `xiaqieduan`='{}', `jidiqieduan`='{}', `bingli_level`='{}', `zhongliudaxiao`='{}', `fenhua_level`='{}', `jinrun`='{}', `linbajie`='{}', `bili`='{}', `maiguan`='{}', `shenjing`='{}', `xingzhuang`='{}', `update_time`='{}' WHERE `id`={}"
    biaoben = result["标本"]
    bingli = result["病理"]
    shangqieduan = result["上切端是否累及"]
    xiaqieduan = result["下切端是否累及"]
    jidiqieduan = result["基底切端是否累及"]
    bingli_level = result["病理等级"]
    zhongliudaxiao = result["肿瘤大小"]
    fenhua_level = result["分化等级"]
    jinrun = result["浸润"]
    linbajie = result["淋巴结是否转移"]
    bili = result["转移比例"]
    maiguan = result["脉管侵犯"] 
    shenjing = result["神经侵犯"] 
    xingzhuang = result["性状"]
    return sql.format(biaoben, bingli, shangqieduan, xiaqieduan, jidiqieduan, bingli_level, zhongliudaxiao, fenhua_level, jinrun, linbajie, bili, maiguan, shenjing, xingzhuang, now, zid)

def create_delete_sql(zid):
    sql = "DELETE FROM bowelCancerInformation WHERE `id`={}"
    return sql.format(zid)

def create_search_sql(keyword):
    keyword = "%"+keyword+"%"
    sql = "SELECT * FROM bowelCancerInformation WHERE `biaoben` LIKE '{}' OR `bingli` LIKE '{}' OR `shangqieduan` LIKE '{}' OR `xiaqieduan` LIKE '{}' OR `jidiqieduan` LIKE '{}' OR `bingli_level` LIKE '{}' OR `zhongliudaxiao` LIKE '{}' OR `fenhua_level` LIKE '{}' OR `jinrun` LIKE '{}' OR `linbajie` LIKE '{}' OR `bili` LIKE '{}' OR `maiguan` LIKE '{}' OR `shenjing` LIKE '{}' OR `xingzhuang` LIKE '{}'"
    return sql.format(keyword, keyword, keyword, keyword, keyword, keyword, keyword, keyword, keyword, keyword, keyword, keyword, keyword, keyword)
