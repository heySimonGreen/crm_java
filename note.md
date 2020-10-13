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
 
 数据显示时，最后一行的操作，删除是删除这个客户，联系地址和联系人后面需要添加删除和编辑操作吗？感觉可以添加这个操作\
 
 requestBody 和RequestParam区别，耗时几个小时了都没解决
 
 formData传参数为数组时的写法
 
 url参数中传输对象数组的方案
 
 qs.stringify和JSON.stringify使用和区别 会改变queryString parameter 为request reloading,qs用于get吧，json用于post吧
 
 去掉@RequestBody不报错，但后端也或许不到传过来的任何数据，
 有@RequestBody报错org.springframework.http.converter.HttpMessageNotReadabl
 org.springframework.http.converter.HttpMessageNotReadableException: I/O error while reading input message; nested exception is org.apache.catalina.connector.ClientAbortException: java.io.EOFException: Unexpected EOF read on the socket
 
 request payload
 
 数据库的命名
 表单验证 电话、座机、邮箱、 去前后空格
 弹窗居中
 后端对数据进行验证
 地址的存放 不要放文字
 uuid
 移到element-admim
 页码设置为中文
 提交后自动刷新显示的数据
 显示所有客户信息的时候显示下拉，固定高度，参照华夏平台
 显示所有用户页面，多选可以删除多个客户
 
 const service = axios.create({
   baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
   // withCredentials: true, // send cookies when cross-domain requests
   timeout: 5000 // request timeout
 })
 request.js跨域问题，修改的位置
 要使用vue-admin-template自己封装的axios的话，要在request.js文件里面修改，以前修改过，可以找找以前的代码，
 不修改的话，请求java后台的数据时会报跨域错误，解决办法是自己写axios请求和发送数据，如下
     getCustomerDetailIngoByGuid() {
       this.$axios.post('http://localhost:8080/customer/addCustomer2', { cid: this.guid2 }, { timeout: 3000 })
         .then(res => {
           console.log(res)
           this.$notify({
             title: '成功',
             message: '成功',
             type: 'success',
             duration: 2000
           })
         })
         .catch(err => {
           console.log(err)
         })
     }
     
###这几天最大的问题是以前解决跨域问题时写路由url时都不用前面的http://localhost:8080/,因为以前在requset.js修改过，
###那样就可以把请求封装在api里面，但这次任务开始的时候好像是自己在GitHub上下载的，并没有修改过request.js文件
###刚才试了一下，写上前面的http://localhost:8080/，在请求时Request URL: http://localhost:8080/customer/addCustomer2
###不写是Request URL: http://localhost:9528/customer/addCustomer2，但奇怪的是不写8080时可以正常的用get，而不能用post，
###以至于我在解决问题时，传递参数用get方法，很吃力，在后端可以看到，处理数据很不方便，至于为什么是9528时能正常使用get请求而不能用post请求需要问老师
###刚才在写的时候写了好几个get请求，根据上面的说法，就是get可以用api封装起来，但写post的时候不可以，使用get并且在api中使用的时候要注意修改request中的code,没记错是request.js中
我在后台设置了跨域，就是加了一个@crossing注解，可能是这个注解让9525端口可以访问到，但是当时post的时候不行了，应该是跨域的问题