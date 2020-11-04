package com.example.demo.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: chenwei
 * @data: 2020/10/28 22:02
 */
@Configuration
public class mvcCofig implements WebMvcConfigurer {

    @Bean
    public MyInterceptor interceptor(){
        return new MyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor()).addPathPatterns("/**")
                //配置不拦截的路径
                .excludePathPatterns(
//                        "/customer/selectAllTest",
//                        "/customer/deleteAllById",
//                        "/contactperson/selectByCid",
//                        "/contactaddress/selectByCid",
//                        "/customer/addCustomer3",
//                        "/customer/searchInputButton",
//                        "/favicon.ico",
//                        "/customer/batchDeletAllCustomerByGuid",
//                        "/index.html",
//                        "/contactperson/deleteById",
//                        "/contactaddress/deleteById",
//                        "/contactaddress/updateAddressItem",
//                        "/contactperson/updateContactItem",
//                        "/contactaddress/addContactAddress",
//                        "/contactperson/addContactPerson",
                        "/admin/login","/","/login","/css/**","/js/**","/fonts/**","images/**");
//        super.addInterceptors(registry);

    }
}
