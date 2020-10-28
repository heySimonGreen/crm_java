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

| 名 | 类型 | 长度 |
| :----:| :---: | :----: |
|guid| char | 32 |
|username| varchar | 16 |
|notes| varchar | 16 |
|role| tinyint | 1 |
|adminid| int | 11 |
|isdelet| tinyint | 1 |
|createtime| timestamp | 0 |

2.联系人表  

| 名 | 类型 | 长度 |
| :----:| :---: | :----: |
|id| int | 8 |
|name| varchar | 16 |
|gender| char | 1 |
|phonenumber| char | 11 |
|homephonenumber| char | 12 |
|wechat| varchar | 16 |
|email| varchar | 16 |
|identity| varchar | 16 |
|cid| char | 32 |

3.联系地址表  

| 名 | 类型 | 长度 |
| :----:| :----: | :----: |
|id| int | 8 |
|title| varchar | 16 |
|stampnumber| int | 6 |
|country| varchar | 32 |
|province| char | 6 |
|city| char | 6 |
|district| char | 6 |
|detaileara| varchar | 32 |
|cid| char | 32 |


4.管理员表  

| 名 | 类型 | 长度 |
| :----:| :---: | :----: |
|id| int | 3 |
|username| varchar | 16 |
|passwd| char | 32 |
|phonenumber| char | 11 |
|type| tinyint | 1 |
