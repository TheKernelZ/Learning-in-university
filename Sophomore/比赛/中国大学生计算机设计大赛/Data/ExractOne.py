import jieba
import re
import json


# jieba.load_userdict("dict/user_dict")


# result = {
#     "标本": "（空）",
#     "病理": "（空）",
#     "上切端是否累及": "（空）",
#     "下切端是否累及": "（空）",
#     "基底切端是否累及": "（空）",
#     "病理等级": "（空）",
#     "肿瘤大小": "（空）",
#     "分化等级": "（空）",
#     "浸润": "（空）",
#     "淋巴结是否转移": "（空）",
#     "转移比例": "（空）",
#     "脉管侵犯": "（空）",
#     "神经侵犯": "（空）",
#     "性状": "（空）"
# }

out = open("result.csv", "a", encoding="gbk")

with open("1000records.csv") as f:
    splited = []
    for line in f.readlines():
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
        # line = "右半结肠切除标本：1.回盲部阑尾开口处中低分化腺癌（浸润至型），累及结肠和阑尾壁，浸润至浆膜外；回肠切端、结肠切端，未见癌组织累及，肠系膜淋巴结2/10枚见癌转移，另见癌结节3枚。 "
        line = line.strip()
        splited = re.split("[,，。“”‘’:；：()（）、]", line)
        # print(splited)
        for content in splited:
            if "标本" in content:
                if result["标本"] == "（空）":
                    result["标本"] = content
                else:
                    result["标本"] += "-" + content
            if "分化" in content:
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
                    x = re.findall(r"\d/[1-9]\d*", content)
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
                index = content.find("化")
                index2 = content.find("腺癌")
                if index == -1:
                    result["病理"] = content[: index2+2]
                else:
                    result["病理"] = content[index+1: index2+2]
                
                index3 = content.find("级")
                if index3 != -1:
                    result["病理等级"] = content[index2+2: index3+1]
            
        out.write(json.dumps(result, ensure_ascii=False))
        out.write("\n")
# print(result)


out.close()