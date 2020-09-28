package net.crisps.cloud.common.exception;


import net.crisps.framework.tac.base.constant.ResultPool;
import net.crisps.framework.tac.base.enums.ResultCode;
import net.dgg.utils.string.DggStringUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

/**
 * @Description: 断言
 * @Author Created by yan.x on 2019-10-18 .
 **/
public final class Asserts {

    /**
     * 构造异常信息
     *
     * @param errorMsg
     * @param <T>
     */
    public final static <T extends RuntimeException> boolean build(String errorMsg) {
        return build(CloudException.class, errorMsg);
    }

    /**
     * 构造异常信息
     *
     * @param errorMsg
     * @param <T>
     */
    public final static <T extends RuntimeException> boolean build(Class<T> exceptionClass, String errorMsg) {
        return getResult(false, exceptionClass, errorMsg == null ? "操作失败" : errorMsg);
    }

    public final static void isOk(Integer status, ResultCode resultCode) throws CloudException {
        isOk(status, resultCode.getCode(), resultCode.getMessage());
    }

    public final static void isOk(Integer code, String message) throws CloudException {
        if (null == code) {
            throw new CloudException(ResultPool.UNKNOW_ERROR_CODE, message);
        }
        if (0 != code && 200 != code) {
            throw new CloudException(1 == code ? ResultPool.UNKNOW_ERROR_CODE : code, message);
        }
    }

    public final static void isOk(Integer status, Integer code, String message) throws CloudException {
        if (null == status) {
            throw new CloudException(ResultPool.UNKNOW_ERROR_CODE, message);
        }
        if (0 != status && 200 != status) {
            throw new CloudException(1 == status ? code : status, message);
        }
    }

    public final static void equals(int status, int code, String message) throws CloudException {
        if (status != code) {
            throw new CloudException(code, message);
        }
    }

    public final static void notEquals(Object param1, Object param2, ResultCode resultCode) throws CloudException {
        if (param1.equals(param2)) {
            throw new CloudException(resultCode);
        }
    }

    public final static void equals(Object param1, Object param2, ResultCode resultCode) throws CloudException {
        if (!param1.equals(param2)) {
            throw new CloudException(resultCode);
        }
    }

    public final static void isTrue(boolean expression, ResultCode resultCode) throws CloudException {
        if (false == expression) {
            throw new CloudException(resultCode.getCode(), resultCode.getMessage());
        }
    }

    public final static void isTrue(boolean expression) throws CloudException {
        if (false == expression) {
            throw new CloudException();
        }
    }

    public final static void isFalse(boolean expression, Integer code, String message) throws CloudException {
        if (true == expression) {
            throw new CloudException(code, message);
        }
    }

    public final static void isFalse(boolean expression, ResultCode resultCode) throws CloudException {
        if (true == expression) {
            throw new CloudException(resultCode.getCode(), resultCode.getMessage());
        }
    }

    public final static void isFalse(boolean expression) throws CloudException {
        if (true == expression) {
            throw new CloudException();
        }
    }

    public final static void isNull(Object object, ResultCode resultCode) throws CloudException {
        if (object != null) {
            throw new CloudException(resultCode.getCode(), resultCode.getMessage());
        }
    }

    public final static void isNull(Object object) throws CloudException {
        if (object != null) {
            throw new CloudException();
        }
    }

    public final static <T> T isNotNull(T object, String message) throws CloudException {
        if (object == null) {
            throw new CloudException(message);
        }
        return object;
    }

    public final static <T> T isNotNull(T object, ResultCode resultCode) throws CloudException {
        if (object == null) {
            throw new CloudException(resultCode.getCode(), resultCode.getMessage());
        }
        return object;
    }

    public final static <T> T isNotNull(T object) throws CloudException {
        if (object == null) {
            throw new CloudException();
        }
        return object;
    }

    public final static String isEmpty(String text, ResultCode resultCode) throws CloudException {
        if (StringUtils.isNotEmpty(text)) {
            throw new CloudException(resultCode.getCode(), resultCode.getMessage());
        }
        return text;
    }

    public final static String isEmpty(String text) throws CloudException {
        if (StringUtils.isNotEmpty(text)) {
            throw new CloudException();
        }
        return text;
    }

    public final static String isBlank(String text, ResultCode resultCode) throws CloudException {
        if (StringUtils.isNotBlank(text)) {
            throw new CloudException(resultCode.getCode(), resultCode.getMessage());
        }
        return text;
    }

    public final static String isBlank(String text) throws CloudException {
        if (StringUtils.isNotBlank(text)) {
            throw new CloudException();
        }
        return text;
    }

    public final static Object[] isEmpty(Object[] array, ResultCode resultCode) throws CloudException {
        if (ArrayUtils.isNotEmpty(array)) {
            throw new CloudException(resultCode.getCode(), resultCode.getMessage());
        }
        return array;
    }

    public final static Object[] isEmpty(Object[] array) throws CloudException {
        if (ArrayUtils.isNotEmpty(array)) {
            throw new CloudException();
        }
        return array;
    }

    public final static <T> Collection<T> isEmpty(Collection<T> collection, ResultCode resultCode) throws CloudException {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new CloudException(resultCode.getCode(), resultCode.getMessage());
        }
        return collection;
    }

    public final static <T> Collection<T> isEmpty(Collection<T> collection) throws CloudException {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new CloudException();
        }
        return collection;
    }

    public final static <K, V> Map<K, V> isEmpty(Map<K, V> map, ResultCode resultCode) throws CloudException {
        if (!CollectionUtils.isEmpty(map)) {
            throw new CloudException(resultCode.getCode(), resultCode.getMessage());
        }
        return map;
    }

    public final static <K, V> Map<K, V> isEmpty(Map<K, V> map) throws CloudException {
        if (!CollectionUtils.isEmpty(map)) {
            throw new CloudException();
        }
        return map;
    }

    public final static String isNotEmpty(String text, ResultCode resultCode) throws CloudException {
        if (StringUtils.isEmpty(text)) {
            throw new CloudException(resultCode.getCode(), resultCode.getMessage());
        }
        return text;
    }

    public final static String isNotEmpty(String text) throws CloudException {
        if (StringUtils.isEmpty(text)) {
            throw new CloudException();
        }
        return text;
    }

    public final static String isNotBlank(String text, String message) throws CloudException {
        if (StringUtils.isBlank(text)) {
            throw new CloudException(message);
        }
        return text;
    }

    public final static String isNotBlank(String text, ResultCode resultCode) throws CloudException {
        if (StringUtils.isBlank(text)) {
            throw new CloudException(resultCode.getCode(), resultCode.getMessage());
        }
        return text;
    }

    public final static String isNotBlank(String text) throws CloudException {
        if (StringUtils.isBlank(text)) {
            throw new CloudException();
        }
        return text;
    }

    public final static Object[] isNotEmpty(Object[] array, ResultCode resultCode) throws CloudException {
        if (ArrayUtils.isEmpty(array)) {
            throw new CloudException(resultCode.getCode(), resultCode.getMessage());
        }
        return array;
    }

    public final static Object[] isNotEmpty(Object[] array) throws CloudException {
        if (ArrayUtils.isEmpty(array)) {
            throw new CloudException();
        }
        return array;
    }

    public final static <T> Collection<T> isNotEmpty(Collection<T> collection, ResultCode resultCode) throws CloudException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new CloudException(resultCode.getCode(), resultCode.getMessage());
        }
        return collection;
    }

    public final static <T> Collection<T> isNotEmpty(Collection<T> collection) throws CloudException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new CloudException();
        }
        return collection;
    }

    public final static <K, V> Map<K, V> isNotEmpty(Map<K, V> map, ResultCode resultCode) throws CloudException {
        if (CollectionUtils.isEmpty(map)) {
            throw new CloudException(resultCode.getCode(), resultCode.getMessage());
        }
        return map;
    }

    public final static <K, V> Map<K, V> isNotEmpty(Map<K, V> map) throws CloudException {
        if (CollectionUtils.isEmpty(map)) {
            throw new CloudException();
        }
        return map;
    }


    /**
     * 检查值是否在指定范围内
     *
     * @param value 值
     * @param min   最小值（包含）
     * @param max   最大值（包含）
     * @return 检查后的长度值
     */
    public final static int checkBetween(int value, int min, int max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(DggStringUtil.format("Length must be between {} and {}.", min, max));
        }
        return value;
    }

    /**
     * 检查值是否在指定范围内
     *
     * @param value 值
     * @param min   最小值（包含）
     * @param max   最大值（包含）
     * @return 检查后的长度值
     */
    public final static long checkBetween(long value, long min, long max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(DggStringUtil.format("Length must be between {} and {}.", min, max));
        }
        return value;
    }

    /**
     * 检查值是否在指定范围内
     *
     * @param value 值
     * @param min   最小值（包含）
     * @param max   最大值（包含）
     * @return 检查后的长度值
     */
    public final static double checkBetween(double value, double min, double max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(DggStringUtil.format("Length must be between {} and {}.", min, max));
        }
        return value;
    }

    /**
     * 检查值是否在指定范围内
     *
     * @param value 值
     * @param min   最小值（包含）
     * @param max   最大值（包含）
     * @return 检查后的长度值
     */
    public final static Number checkBetween(Number value, Number min, Number max) {
        isNotNull(value);
        isNotNull(min);
        isNotNull(max);
        double valueDouble = value.doubleValue();
        double minDouble = min.doubleValue();
        double maxDouble = max.doubleValue();
        if (valueDouble < minDouble || valueDouble > maxDouble) {
            throw new IllegalArgumentException(DggStringUtil.format("Length must be between {} and {}.", min, max));
        }
        return value;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------- Private method start

    /**
     * 错误的下标时显示的消息
     *
     * @param index  下标
     * @param size   长度
     * @param desc   异常时的消息模板
     * @param params 参数列表
     * @return 消息
     */
    private final static String badIndexMsg(int index, int size, String desc, Object... params) {
        if (index < 0) {
            return DggStringUtil.format("{} ({}) must not be negative", DggStringUtil.format(desc, params), index);
        } else if (size < 0) {
            throw new IllegalArgumentException("negative size: " + size);
        } else { // index >= size
            return DggStringUtil.format("{} ({}) must be less than size ({})", DggStringUtil.format(desc, params), index, size);
        }
    }

    /**
     * 处理验证结果的私有方法，减少重复代码量
     *
     * @param result         结果本身
     * @param exceptionClass 需要抛出的异常类实例
     * @param errorMsg       需要返回的消息
     * @param <T>            需要抛出的异常类类型
     * @return 结果
     */
    private final static <T extends RuntimeException> boolean getResult(boolean result, Class<T> exceptionClass, String errorMsg) {
        // 如果结果为false，并且需要抛出异常
        if (!result && exceptionClass != null) {
            throw buildException(exceptionClass, errorMsg);
        }
        return result;
    }

    /**
     * 构造异常
     *
     * @param exceptionClass 需要抛出的异常类实例
     * @param errorMsg       需要返回的消息
     * @param <T>            需要抛出的异常类类型
     * @return
     */
    private final static <T extends RuntimeException> T buildException(Class<T> exceptionClass, String errorMsg) {
        T exception = null;
        Constructor constructor = null;
        try {
            constructor = exceptionClass.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            throw new CloudException("缺少String为参数的构造函数", e);
        }
        try {
            exception = (T) constructor.newInstance(errorMsg);
        } catch (InstantiationException e) {
            throw new CloudException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new CloudException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new CloudException(e.getMessage(), e);
        }
        return exception;
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------- Private method end
}
