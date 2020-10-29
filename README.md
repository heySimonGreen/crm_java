# crm开发文档  

## 1.使用到的技术  

一.前段  
1.vue 2.0    
2.eliment UI  

二.后端  
1.springboot 2.3.4  
2.mybatis 2.1.3  
3.mysql 5.7.29  

## 2.主要功能

1.添加客户  
2.编辑客户信息  
3.删除客户信息  
4.查找客户  
5.删除某个客户的联系人联系地址  
6.超级管理员查看所有客户信息，普通管理员查看自己添加的客户信息  

## 3数据库设计

1.客户表  

| 名 | 类型 | 长度 | 描述 |
| :---:| :---: | :---: | :---:|
|guid| char | 32 | 客户id，java后台使用uuid生成 |
|username| varchar | 16 | 客户名称 |
|notes| varchar | 16 | 备注 |
|role| tinyint | 1 | 客户角色，企业还是个人客户 |
|adminid| int | 11 | 客户管理员id |
|isdelet| tinyint | 1 | 客户是否被删除 |
|createtime| timestamp | 0 | 客户创建时间 |

2.联系人表  

| 名 | 类型 | 长度 | 描述 |
| :---:| :---: | :---: | :---:|
|id| int | 8 | 联系人id，自增 |
|name| varchar | 16 | 联系人姓名 |
|gender| char | 1 | 联系人性别 |
|phonenumber| char | 11 | 联系人电话号码 |
|homephonenumber| char | 12 | 联系人座机号码 |
|wechat| varchar | 16 | 联系人微信 |
|email| varchar | 16 | 联系人email |
|identity| varchar | 16 | 联系人职务/身份 |
|cid| char | 32 | 外键，关联客户表的guid |

3.联系地址表  

| 名 | 类型 | 长度 | 描述 |
| :---:| :---: | :---: | :---:|
|id| int | 8 | 联系地址id，自增 |
|title| varchar | 16 | 联系地址标题 |
|stampnumber| int | 6 | 联系地址邮编 |
|country| varchar | 32 | 联系地址国家 |
|province| char | 6 | 联系地址省份 |
|city| char | 6 | 城市 |
|district| char | 6 | 联地区 |
|detaileara| varchar | 32 |详细地址 |
|cid| char | 32 | 外键，关联客户表的guid |


4.管理员表  

| 名 | 类型 | 长度 | 描述 |
| :---:| :---: | :---: | :---:|
|id| int | 3 | |  |
|username| varchar | 16 | |
|passwd| char | 32 | |
|phonenumber| char | 11 | |
|type| tinyint | 1 | |

## 4.数据加密验证
 ### 对请求添加数据签名signature，签名生成方法是md5(path+time+guid+param+cryptToken)，其中cryptToken使用uuid进过以下过程加密创建
 如0cc175b9c0f1b6a831c399e269772661
 
 1.uuid固定是32位，取它的第2位，第5位，第8位（从0开始），得到c5c
 
 2.再把他当成16进制转换为数字c5c > 3164，再对3164对8求余得到4
 
 3.再对应hash表格规则，如0 :[0,5,9,15,22,28]1 :[2,8,19,25,30,31] 2 :[20,25,31,3,4,8] 3 :
 [25,31,0,9,13,17] 4 :[29,2,11,17,21,26] 5 :[10,15,18,29,2,3] 6 :
[5,10,15,17,18,22] 7 :[8,20,22,27,19,21]，

4.得到对应的数组[29,2,11,17,21,26]，取uuid的对应数组上的位置的数字得到6c1197,即为crypToken

### 前段传过去的signature,和后端自己计算出来的signature进行对比，不一样的话返回签证错误，

### 前段传过去的时间和后台服务器的时间对比，超过2秒提示请求超时,超过10分钟提示请求严重超时，存在攻击行为

### 前段传的guid后端拿来在数据库查找，没有此guid提示guid不存在
