package net.crisps.cloud.common.config;

import feign.Contract;
import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @Author Created by yan.x on 2019-09-26 .
 **/
@Slf4j
@Configuration
public class FeignConfiguration {

    //    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.HEADERS;
    }
}