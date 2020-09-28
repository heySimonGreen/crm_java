### 编程笔记
#### customer表的ID自增，在添加联系人和联系地址的时候需要Cid，这个cid可以通过myslq中查找最大值函数获取从而找到需要插入的cid
#### 添加备注字段 + 添加联系人接口编写
#### 添加联系人接口编写:每个字段都需要传入值，不传入值Java后台会报错，测试连接地址如下
#### http://localhost:8080/customer/addCustomerInfo?customerUsername=asdfas&contactpersonName=d&contactpersonGender=d&contactpersonPhonenumber=d&contactpersonHomephonenumber=d&contactpersonWechat=d&contactpersonEmail=d&contactpersonIdentity=d&contactaddressTitle=d&contactaddressStampnumber=d&contactaddressCountry=d&contactaddressProvince=d&contactaddressCity=d&contactaddressDistrict=d
#### INSERT INTO `crm`.`contactaddress`(`id`, `cid`, `title`, `stampnumber`, `country`, `province`, `city`, `district`) VALUES (1, 1, 'fs', 11, 'fas', 'asfd', 'asdf', 'asf');
#### springboot新增数据为什么返回实例对象
#### 在添加客户的时候会添加多个联系人和联系地址，先提交一个客户的联系人和联系地址，点击添加可以再添加联系人和联系地址，这时调用单独的添加联系人和联系方式方法

