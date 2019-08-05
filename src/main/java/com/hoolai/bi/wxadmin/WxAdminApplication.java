package com.hoolai.bi.wxadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableTransactionManagement
@ServletComponentScan
@SpringBootApplication
@EnableSwagger2
//@MapperScan("com.hoolai.bi.wxadmin.mapper")
public class WxAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxAdminApplication.class, args);
	}

}
