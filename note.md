### 编程笔记
#### customer表的ID自增，在添加联系人和联系地址的时候需要Cid，这个cid可以通过myslq中查找最大值函数获取从而找到需要插入的cid
#### 添加备注字段 + 添加联系人接口编写
#### 添加联系人接口编写:每个字段都需要传入值，不传入值Java后台会报错，测试连接地址如下
#### http://localhost:8080/customer/addCustomerInfo?customerUsername=asdfas&contactpersonName=d&contactpersonGender=d&contactpersonPhonenumber=d&contactpersonHomephonenumber=d&contactpersonWechat=d&contactpersonEmail=d&contactpersonIdentity=d&contactaddressTitle=d&contactaddressStampnumber=d&contactaddressCountry=d&contactaddressProvince=d&contactaddressCity=d&contactaddressDistrict=d
#### INSERT INTO `crm`.`contactaddress`(`id`, `cid`, `title`, `stampnumber`, `country`, `province`, `city`, `district`) VALUES (1, 1, 'fs', 11, 'fas', 'asfd', 'asdf', 'asf');
#### springboot新增数据为什么返回实例对象
#### 在添加客户的时候会添加多个联系人和联系地址，先提交一个客户的联系人和联系地址，点击添加可以再添加联系人和联系地址，这时调用单独的添加联系人和联系方式方法

####编写接口时遇到的问题，在接口编写可以单独写好，前段再结合，或者后端接口写一个前段不用合起来，比如客户信息，一个客户有多个联系人和联系地址，那么这些信息感觉后端直接写好比较方便些吧

###springboot mybatis多表查询
创建一个新的实体类，再写新的xml

 [ { "guid": 1, "username": "gsdg" }, { "guid": 3, "username": "asdgasdgfsdafgasd" }, { "guid": 4, "username": "陈伟" }, { "guid": 5, "username": "陈伟" }, { "guid": 8, "username": "写不V字形大风车" }, { "guid": 9, "username": "asdfas" }, { "guid": 10, "username": "asdfas" }, { "guid": 11, "username": "asdfas" } ]
 
 数据显示时，最后一行的操作，删除是删除这个客户，联系地址和联系人后面需要添加删除和编辑操作吗？感觉可以添加这个操作