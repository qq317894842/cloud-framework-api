package net.crisps.cloud;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import net.crisps.framework.tac.starter.mybatis.plus.generator.GeneratorToolkit;
import net.crisps.framework.tac.starter.mybatis.plus.generator.model.GeneratorModel;

import java.io.IOException;

/**
 * @Description: 代码生成测试
 * @Author Created by HOT SUN on 2020/1/29 .
 **/
public class GeneratorTest {

    public static void main(String[] args) throws IOException {
        GeneratorModel generatorModel = GeneratorModel.build()
                .setAddress("192.168.254.18:6666")     // 数据库地址(默认D环境)
                .setStock("db_iboss_orf")                 // 库名
                .setUsername("root")                   // 帐号(默认D环境)
                .setPassword("mypass")                   // 密码(默认D环境)
                .setAuthor("Administrator")             // 作者
                .setParent("net.crisps.cloud")             // 包名
                .setModuleName("orf")                  // 模块名称
                .setSuperEntityClass(Model.class.getCanonicalName())   // 自定义继承的Entity类全称，带包名,默认Model
                .setTablePrefix("orf_")         // 忽略表前缀
                .setLikeTable("orf_contract_template");
//                .setLikeTable("sys_");                  // 模糊匹配匹配 需要生成包含的表名(与include二选一配置)
//                .setInclude("sys_announce_manage");   // 需要生成的表(不设置默认生成所有)(与likeTable二选一配置)
        GeneratorToolkit.execute(generatorModel);
    }
}
