这里是zzu大三生产实习大数据项目
刑事文书搜索系统
分为四个营，java、python数据分析、大数据和人工智能
这里没抢到java和人工智能选了大数据

不想自己写了网上找了找，没找到
怒从心头起，恶向胆边生
遂自己一个人摸了，发出来，万一后面有人用到了是吧

由于整个组只有我一个人有开发的经验，其他人都没用过SpringBoot，因此前端我一概不知，后端我自己写的，项目环境我部署的。
电脑端访问www.dawnlight.top就能访问到页面，访问不到，是因为我懒得再去腾讯云备案了，或者我服务器到期懒得续了，又或者，我把服务器做别的项目了。

### 1. 数据库表设计
   组里其他人也是现学的爬虫，最后才爬出来数据，但当时我已经~~懒得~~没时间改那一堆了，因此数据库表中有大量冗余字段，在实际应用时可以根据你们爬虫的数据进行修改。
   实际用到的只有案件号、案件名、全文和法院。爬虫数据，自学爬虫吧你们。。。
### 2. 配置
   配置的话，修改application.yml文件即可。我觉得能来扒出这个项目的，大概率你也是不怎么会开发的，问一下大模型都是啥，配置成自己的就好。
   然后把demoApplication当中的bean的地址注册为你的自己的部署的elasticSearch地址
### 3. 代码
   我都有注释，你应该能看懂是啥。具体的索引库需要你自己建立。我们没在java代码当中跑mysql，因为出bug了我没调好。~~谁去给他调啊七月了刚拒了大厂实习准备考研呢，糊弄过去得了~~
   如果你爬虫的数据量不多，你可以直接手动输入到数据库中。索引库为：
```dsl
PUT /law
{
  "mappings": {
    "properties": {
      "caseId": {
        "type": "keyword"
      },
      "plaintiff": {
        "type": "keyword"
      },
      "defendant": {
        "type": "keyword"
      },
      "plaintiffLawyer": {
        "type": "keyword"
      },
      "defendantLawyer": {
        "type": "keyword"
      },
      "caseName": {
        "type": "text",
        "analyzer": "ik_smart"
      },
      "court": {
        "type": "keyword"
      },
      "judge": {
        "type": "keyword"
      },
      "causeOfAction": {
        "type": "text",
        "analyzer": "ik_smart"
      },
      "judgmentResult": {
        "type": "keyword"
      },
      "hiberarchy": {
        "type": "keyword"
      },
      "location": {
        "type": "keyword"
      },
      "text": {
        "type": "text",
        "analyzer": "ik_smart"
      }
    }
  }
}

插入语句示例：
POST /law/_doc/1
{
  "caseId": "京1 101",
  "plaintiff": "zzer",
  "caseName": "你犯事了吗",
  "text": "造孽啊，为什么我们要生产实习，还要做一堆抽象项目，还只给十天，还他妈要加班，还tmd要验收，还分两次验收，草了"
}
示例2：
POST /law/_doc/118
{"caseId": "(2023)最高法刑申89号", "plaintiff": null, "defendant": null, "plaintiffLawyer": null, "defendantLawyer": null, "caseName": "邹某故意杀人罪、强奸罪死刑复核刑事裁定书", "court": "山东省临沂市中级人民法院", "judge": null, "caseofAction": "强奸", "judgementResult": null, "hierarchy": null, "location": null, "text": "被告人邹某甲，男，汉族，1970年出生于山东省沂水县，小学文化，务工，户籍地沂水县××镇×××村，住所地陕西省靖边县××村。1996年5月13日因犯盗窃罪被判处有期徒刑七年，2000年1月15日被假释，假释考验期自2000年1月15日起至2002年10月1日止。2019年7月24日被逮捕。现在押。\r\n山东省临沂市中级人民法院审理临沂市人民检察院指控被告人邹某甲犯故意杀人罪、强奸罪一案，于2020年12月18日以（2020）鲁13刑初29号刑事附带民事判决，认定被告人邹某甲犯故意杀人罪，判处死刑，剥夺政治权利终身；犯强奸罪，判处有期徒刑六年，与原判没有执行的二年八个月零十六天并罚，决定执行死刑，剥夺政治权利终身。宣判后，邹某甲提出上诉。山东省高级人民法院经依法开庭审理，于2021年12月16日以（2021）鲁刑终378号刑事裁定，驳回上诉，维持原判，并依法报请本院核准。本院复核期间，根据被告人邹某甲申请，通知司法部法律援助中心指派北京市百瑞律师事务所律师周珍为其提供辩护。本院依法组成合议庭，对本案进行了复核，依法讯问了被告人，听取了辩护律师意见。现已复核终结。\r\n经复核确认：2001年1月19日晚上，被告人邹某甲酒后路过山东省沂水县××镇×××村同村村民武某某（被害人，女，殁年84岁）家时，发现门未关便推门进入家中。邹某甲见武某某正在床上睡觉，遂产生强奸念头，强行将武某某奸污。武某某用手抓伤邹某甲的脸部并推搡反抗，邹某甲用手掐住武某某的颈部致武某某窒息死亡。\r\n上述事实，有第一审、第二审开庭审理中经质证确认的被告人邹某甲前罪刑事判决书和假释裁定书等书证，证人邹某乙、武某甲、邹某丙、邹某丁等的证言，尸体鉴定意见、证实在被害人武某某阴道擦拭物上检出邹某甲精斑的DNA鉴定意见，现场勘验、检查笔录，指认笔录等证据证实。被告人邹某甲亦供认。足以认定。\r\n本院认为，被告人邹某甲违背妇女意志，强行与被害人发生性关系，其行为已构成强奸罪；强奸后又采取扼颈手段致被害人死亡，其行为已构成故意杀人罪，应依法数罪并罚。邹某甲深夜入室强奸同村八十余岁老人，又将被害人杀死，犯罪性质恶劣，犯罪情节、后果特别严重，罪行极其严重，且邹某甲系在前罪被假释期间再次犯罪，主观恶性深、社会危害性大，应依法惩处。对于辩护律师所提现有证据不足以证明被害人的死亡系邹某甲手掐颈部导致，无法排除被害人因其它原因死亡合理怀疑等意见，与查明的事实不符，不予采纳；所提被告人主动交代犯罪事实，构成自首，可以从轻处罚的意见，同意原判不予采纳的意见。第一审判决、第二审裁定认定的事实清楚，证据确实、充分，定罪准确，量刑适当。审判程序合法。依照《中华人民共和国刑事诉讼法》第二百四十六条、第二百五十条和《最高人民法院关于适用〈中华人民共和国刑事诉讼法〉的解释》第四百二十九条第(一)项的规定，裁定如下：\r\n核准山东省高级人民法院（2021）鲁刑终378号维持第一审对被告人邹某甲以故意杀人罪判处死刑，剥夺政治权利终身；以强奸罪判处有期徒刑六年，与原判没有执行的二年八个月零十六天并罚，决定执行死刑，剥夺政治权利终身的刑事裁定。\r\n本裁定自宣告之日起发生法律效力。\r\n审判长　　杨志华\r\n审判员　　周　刚\r\n审判员　　朱晶晶\r\n二〇二二年十一月五日\r\n书记员　　冀瑞燕\r\n\r\n"}
```
### 4. 前端页面
   记得发起的请求发送到你们自己的服务器当中。前端不是我写的，在resources的static下。


就这吧，部署工作其实不用做，因为备案很麻烦的。你们在自己虚拟机上运行老师也不会说什么。

问的不难，主要看是不是你做的。


   

   
