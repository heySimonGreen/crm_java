(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-330560e7"],{1:function(e,o){},10:function(e,o){},11:function(e,o){},12:function(e,o){},13:function(e,o){},14:function(e,o){},15:function(e,o){},2:function(e,o){},2017:function(e,o,n){"use strict";var s=n("cafe"),t=n.n(s);t.a},3:function(e,o){},4:function(e,o){},5:function(e,o){},6:function(e,o){},7:function(e,o){},8:function(e,o){},9:function(e,o){},"9ed6":function(e,o,n){"use strict";n.r(o);var s=function(){var e=this,o=e.$createElement,n=e._self._c||o;return n("div",{staticClass:"login-container"},[n("el-form",{ref:"loginForm",staticClass:"login-form",attrs:{model:e.loginForm,rules:e.loginRules,"auto-complete":"on","label-position":"left"}},[n("div",{staticClass:"title-container"},[n("h3",{staticClass:"title"},[e._v("Login Form")])]),n("el-form-item",{attrs:{prop:"username"}},[n("span",{staticClass:"svg-container"},[n("svg-icon",{attrs:{"icon-class":"user"}})],1),n("el-input",{ref:"username",attrs:{placeholder:"Username",name:"username",type:"text",tabindex:"1","auto-complete":"on"},model:{value:e.loginForm.username,callback:function(o){e.$set(e.loginForm,"username",o)},expression:"loginForm.username"}})],1),n("el-form-item",{attrs:{prop:"password"}},[n("span",{staticClass:"svg-container"},[n("svg-icon",{attrs:{"icon-class":"password"}})],1),n("el-input",{key:e.passwordType,ref:"password",attrs:{type:e.passwordType,placeholder:"Password",name:"password",tabindex:"2","auto-complete":"on"},nativeOn:{keyup:function(o){return!o.type.indexOf("key")&&e._k(o.keyCode,"enter",13,o.key,"Enter")?null:e.handleLogin(o)}},model:{value:e.loginForm.password,callback:function(o){e.$set(e.loginForm,"password",o)},expression:"loginForm.password"}}),n("span",{staticClass:"show-pwd",on:{click:e.showPwd}},[n("svg-icon",{attrs:{"icon-class":"password"===e.passwordType?"eye":"eye-open"}})],1)],1),n("el-button",{staticStyle:{width:"100%","margin-bottom":"30px"},attrs:{loading:e.loading,type:"primary"},nativeOn:{click:function(o){return o.preventDefault(),e.handleLogin(o)}}},[e._v("Login")])],1)],1)},t=[],r=n("bc3a"),a=n.n(r),i=n("1c46"),l=n.n(i),c={name:"Login",data:function(){var e=function(e,o,n){o.length<6?n(new Error("The password can not be less than 6 digits")):n()};return{list:null,loginForm:{username:"dhl",password:"666666"},loginForm2:{username:"dhl",password:"666666"},loginRules:{username:[{required:!0,trigger:"blur",message:"type is required"}],password:[{required:!0,trigger:"blur",validator:e}]},loading:!1,passwordType:"password",redirect:void 0}},watch:{$route:{handler:function(e){this.redirect=e.query&&e.query.redirect},immediate:!0}},created:function(){var e=this;this.base_api="/prod-api",console.log("this.base_api:................"),console.log(this.base_api),a.a.get("/customer/selectAll").then((function(o){return e.list=o})),console.log(this.list)},methods:{showPwd:function(){var e=this;"password"===this.passwordType?this.passwordType="":this.passwordType="password",this.$nextTick((function(){e.$refs.password.focus()}))},handleLogin:function(){var e=this;this.$refs.loginForm.validate((function(o){if(!o)return console.log("error submit!!"),!1;var n={};e.loading=!0;var s=l.a.createHash("md5");n.username=e.loginForm.username,n.password=e.loginForm.password,console.log("loginForm3.username"),console.log(n.username),console.log("loginForm3.password"),console.log(n.password),s.update(n.password),n.password=s.digest("hex"),console.log(n.password),e.$store.dispatch("user/loginMyself",n).then((function(){e.$router.push({path:e.redirect||"/"}),e.loading=!1})).catch((function(){e.loading=!1}))}))}}},u=c,d=(n("2017"),n("b8f0"),n("2877")),p=Object(d["a"])(u,s,t,!1,null,"d06f454e",null);o["default"]=p.exports},b8f0:function(e,o,n){"use strict";var s=n("fde8"),t=n.n(s);t.a},cafe:function(e,o,n){},fde8:function(e,o,n){}}]);