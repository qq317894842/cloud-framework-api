package net.crisps.cloud.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.crisps.framework.tac.common.constant.StringPool;
import net.crisps.framework.tac.starter.basis.utils.CrispsRequestUtils;

/**
 * @Description: 获取用户信息工具类
 * @Author Created by yan.x on 2019-09-19 .
 **/
@Slf4j
public final class LoginUtils extends CrispsRequestUtils {

    /**
     * 获取当前用户实体
     *
     * @return 用户
     */
    public final static JSONObject getLoginUser() {
        JSONObject userInfo = CrispsRequestUtils.getUserInfo(JSONObject.class);
        if (null == userInfo) {
            return null;
        }
        return userInfo;
    }

    /**
     * 获取当前用户实体
     *
     * @return 用户
     */
    public final static JSONObject getLoginUser(final String userId) {
        return CrispsRequestUtils.getUserInfo(userId, getClientId(), StringPool.DEFAULT_PATH);
    }
}
