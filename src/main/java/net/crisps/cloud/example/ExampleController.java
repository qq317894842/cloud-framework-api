package net.crisps.cloud.example;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import net.crisps.cloud.common.utils.LoginUtils;
import net.crisps.framework.tac.base.controller.AbstractCrispsController;
import net.crisps.framework.tac.base.model.CrispsResponse;
import net.crisps.framework.tac.base.utils.MapBuilder;
import net.crisps.framework.tac.base.utils.ResultTool;
import net.crisps.framework.tac.common.context.CrispsContextAware;
import net.crisps.framework.tac.starter.strategy.request.LoadBalancedClient;
import net.dgg.utils.file.DggIoUtil;
import net.dgg.utils.request.DggSslUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @Description: TODO
 * @Author Created by yan.x on 2020-02-02 .
 **/
@Validated
@RestController
@RequestMapping("/api/example")
public class ExampleController extends AbstractCrispsController {

    private String gateWayPath = "https://microuag.dgg188.cn/authplatform";
    private String basePath = "http://tac-authplatform-server";
    private String path = "/api/manager/operate/v2/storage/select/platform_channel_config.do";
    private Map<String, Object> args = MapBuilder.create(String.class, Object.class)
            .push("platformCode", "qds_888")
            .push("channel", "YeDun");

    private Map<String, String> heards = MapBuilder.create(String.class, String.class)
            .push("sysCode", "qds_888")
            .push("X-Req-Client", "ANDROID")
            .push("X-Device-Type", "deviceType")
            .push("X-Device-Code", "deviceCode")
            .push("n-d-env", "dev")
            .push("nonce", "4jadkjakljdakldjakljdakljdkl")
            .push("X-User-Agent", "4b43c3f3-d817-4576-95b1-ad8519a2f14e")
            .push("X-Req-UserId", "7902841307248123906")
            .push("X-Auth-Token", "7961774579524435968");

    @Autowired
    private LoadBalancedClient loadBalancedClient;

    @Autowired
    private Environment environment;

    @ApiOperation(value = "方法描述信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "param1", value = "参数1", required = false, dataType = "String"),
            @ApiImplicitParam(name = "param2", value = "参数2", required = true, dataType = "String")
    })
    @GetMapping("/swagger.do")
    public CrispsResponse swagger(String param1, String param2) {
        SwaggerVO vo = new SwaggerVO();
        return ResultTool.success(vo);
    }


    public class SwaggerVO {
        @ApiModelProperty(value = "属性1描述", example = "0")
        private Integer attr1;
        @ApiModelProperty(value = "属性2描述", example = "test")
        private String attr2;
    }

    @GetMapping("/get.do")
    public CrispsResponse get(@RequestParam String param) {
        System.err.println(LoginUtils.getUserId());
        System.err.println(param);
        ExampleModel exampleModel = new ExampleModel();
        exampleModel.setDate(new Date());
        exampleModel.setLocalDate(LocalDate.now());
        exampleModel.setLocalDateTime(LocalDateTime.now());
        return ResultTool.success(exampleModel);
    }

    @PostMapping("/save.do")
    public CrispsResponse save(@Validated @RequestBody ExampleModel exampleModel) {
        exampleModel.setDate(new Date());
        exampleModel.setLocalDate(LocalDate.now());
        exampleModel.setLocalDateTime(LocalDateTime.now());
        return ResultTool.success(exampleModel);
    }

    @GetMapping("/load_balanced/test.do")
    public CrispsResponse loadBalancedTest() {
        Object obj = loadBalancedClient.doGet(basePath, path, args, heards);
        return ResultTool.success(obj);
    }


    @PostMapping("/test.do")
    public CrispsResponse test(String node) {
        String property = environment.getProperty(node);
        return this.success(CrispsContextAware.getEnvironment().getProperty(node));
    }

    @javax.annotation.Resource
    private ResourceLoader resourceLoader;

    @GetMapping("/download.do")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        InputStream bis = null;
        OutputStream bos = null;
        URLConnection con = null;
        try {
            Resource resource = resourceLoader.getResource("classpath:static/电子合同模板文件配置规范.docx");
            DggSslUtil.ignoreSsl();
            bis = resource.getInputStream();
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-msdownload;");
            response.setHeader("content-disposition", "attachment;filename=\"" + new String("电子合同模板文件配置规范.docx".getBytes("UTF-8"), "ISO8859-1") + "\"");
            bos = response.getOutputStream();
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DggIoUtil.close(bos);
        }
    }

}