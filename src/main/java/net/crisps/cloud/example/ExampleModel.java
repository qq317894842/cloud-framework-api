package net.crisps.cloud.example;

import lombok.Data;
import net.crisps.framework.tac.starter.basis.verification.NonEmpty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description: TODO
 * @Author Created by HOT SUN on 2020/2/2 .
 **/
@Data
public class ExampleModel {
    private String id;
    @NonEmpty("name")
    private String name;
    private long createTime;
    private Long updateTime;
    private Date date;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
}
