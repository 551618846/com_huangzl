/** 
 * @组件名：eelly_core
 * @包名：com.eelly.core.util
 * @文件名：ReflectionUtil.java
 * @创建时间： 2014年7月15日 上午10:30:57
 * @版权信息：Copyright © 2014 eelly Co.Ltd,衣联网版权所有。
 */

package com.huangzl.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;


/**
 * @类名：ReflectionUtil
 * @描述: 反射工具类
 * @创建人：<a href=mailto: huangweiqi@eelly.net>huangweiqi</a>
 * @修改人：
 * @修改时间：2014年7月15日 上午10:30:57
 * @修改说明：<br/>
 * @版本信息：V1.0.0<br/>
 */
public class ReflectionUtil {

    private static final Logger logger = LogManager.getLogger(ReflectionUtil.class);

    /** 基本类型的ClassMap */
    private static final Map<String, Class<?>> PRIMITIVE_CLASS_MAP = new HashMap<>();
    static {
        PRIMITIVE_CLASS_MAP.put(boolean.class.getName(), boolean.class);
        PRIMITIVE_CLASS_MAP.put(char.class.getName(), char.class);
        PRIMITIVE_CLASS_MAP.put(byte.class.getName(), byte.class);
        PRIMITIVE_CLASS_MAP.put(short.class.getName(), short.class);
        PRIMITIVE_CLASS_MAP.put(int.class.getName(), int.class);
        PRIMITIVE_CLASS_MAP.put(long.class.getName(), long.class);
        PRIMITIVE_CLASS_MAP.put(float.class.getName(), float.class);
        PRIMITIVE_CLASS_MAP.put(double.class.getName(), double.class);
        PRIMITIVE_CLASS_MAP.put(void.class.getName(), void.class);
    }

    /**
     * serialVersionUID字段名
     */
    private static final String SERIALVERSIONUID_FIELD_NAME = "serialVersionUID";

    /**
     * @方法名：getSuperGenericClass
     * @描述：获取父类的泛型Class
     * @创建人：<a href=mailto: huangweiqi@eelly.net>huangweiqi</a>
     * @修改人：
     * @修改时间：2014年7月15日 上午10:32:15
     * @param clz
     *            当前子类的Class
     * @param index
     *            泛型参数索引
     * @return
     * @返回值：Class
     * @异常说明：
     */
    @SuppressWarnings("rawtypes")
    public static Class getSuperGenericClass(Class clz, int index) {
        Type type = clz.getGenericSuperclass();
        if (!(type instanceof ParameterizedType)) {
            logger.error(clz.getSimpleName() + "'s superclass is not ParameterizedType");
            return Object.class;
        }
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            logger.error("params.length: " + params.length + ", index is illegal");
            return Object.class;
        }

        if (!(params[index] instanceof Class)) {
            logger.error(clz.getSimpleName()
                    + "has not set the actual class to superclass generic parameter");
            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * @方法名：getClassByClassName
     * @描述：通过类名获取Class
     * @创建人：<a href=mailto: huangweiqi@eelly.net>huangweiqi</a>
     * @修改人：
     * @修改时间：2014年7月24日 下午7:07:53
     * @param className
     *            类名
     * @return
     * @返回值：Class<?>
     * @异常说明：
     */
    public static Class<?> getClassByClassName(String className) {
        Class<?> clz = PRIMITIVE_CLASS_MAP.get(className);
        if (clz != null) {
            return clz;
        }

        try {
            clz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return clz;
    }

    /**
     * @方法名：getMethod
     * @描述：获取方法对象
     * @创建人：<a href=mailto: huangweiqi@eelly.net>huangweiqi</a>
     * @修改人：
     * @修改时间：2014年6月5日 下午5:50:14
     * @param joinPoint
     *            连接点
     * @return
     * @throws NoSuchMethodException
     * @返回值：Method
     * @异常说明：
     */
    public static Method getMethod(JoinPoint joinPoint) throws NoSuchMethodException {
        final Signature sig = joinPoint.getSignature();
        if (!(sig instanceof MethodSignature)) {
            throw new NoSuchMethodException("This annotation is only valid on a method.");
        }
        final MethodSignature msig = (MethodSignature) sig;
        final Object target = joinPoint.getTarget();
        return target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
    }

    /**
     * @方法名：isNullObjectExceptPrimary
     * @描述：除主键和serialVersionUID之外的值都为null的判断
     * @创建人：<a href=mailto:hehui@eelly.net>何辉</a>
     * @修改人：
     * @修改时间：2014年8月11日 下午1:43:26
     * @param object
     * @return
     * @返回值：boolean
     * @异常说明：
     */
    /*public static boolean isNullObjectExceptPrimary(Primaryable object) {
        if (null == object) {
            return true;
        }
        Class<? extends Primaryable> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (SERIALVERSIONUID_FIELD_NAME.equals(field.getName())
                    || field.getName().equals(object.getPrimaryFieldName())) {
                continue;
            }
            try {
                if (null != PropertyUtils.getProperty(object, field.getName())) {
                    return false;
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }*/
}
