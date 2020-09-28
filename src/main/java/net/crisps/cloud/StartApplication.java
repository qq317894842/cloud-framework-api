package net.crisps.cloud;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @Description: 启动类
 * @Author Created by yan.x on 2019-07-31 .
 **/
@Slf4j
// feign自动生成器
//@DggEnableGenfeign
@EnableFeignClients(basePackages = {"net.crisps.cloud"})
@MapperScan("net.crisps.cloud.**.mapper*")
@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
public class StartApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
        log.info("************************************************************");
        log.info("*                                                          *");
        log.info("*                   服 务 启 动 成 功                      *");
        log.info("*                                                          *");
        log.info("************************************************************");
    }
}
