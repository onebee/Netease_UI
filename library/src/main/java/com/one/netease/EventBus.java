package com.one.netease;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.one.netease.library.annotation.Subscribe;
import com.one.netease.library.core.MethodManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author diaokaibin@gmail.com on 2020-02-03.
 */
public class EventBus {

    // volatile 修饰的变量不允许线程内部缓存和重排序,即直接修改内存
    private static volatile EventBus instance;

    private Map<Object, List<MethodManager>> cacheMap;


    private Handler handler;

    private ExecutorService executorService;

    public EventBus() {
        cacheMap = new HashMap<>();

        // handler 高级用法,将handler 放在主线程使用
        handler = new Handler(Looper.getMainLooper());

        // 创建一个子线程(缓存线程池)
        executorService = Executors.newCachedThreadPool();

    }

    public static EventBus getDefault() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    // 找到MainActivity 中所有带符合注解的方法
    public void register(Object getter) {
        // 获取MainActivity 所有的方法
        List<MethodManager> methodManagerList = cacheMap.get(getter);
        if (methodManagerList == null) {// 不为空表示以前注册完成
            methodManagerList = findAnnotationMethod(getter);
            cacheMap.put(getter, methodManagerList);
        }

    }

    // 获取MainActivity 中所有注解的方法
    private List<MethodManager> findAnnotationMethod(Object getter) {
        List<MethodManager> methodList = new ArrayList<>();
        // 获取类
        Class<?> clazz = getter.getClass();

        // 获取所有方法
        Method[] methods = clazz.getMethods();



        //性能优化. N个类父类不可能有自定义注解. 排除后再反射
        while (clazz != null) {

            // 找出系统类,直接跳出,不添加cacheMap (因为不是订阅者)
            String clazzName = clazz.getName();
            Log.i("-----", "clazzName = : " +clazzName);
            if (clazzName.startsWith("java.") || clazzName.startsWith("javax")
                    || clazzName.startsWith("android.")
                    || clazzName.startsWith("androidx.")

            ) {
                break;
            }


            for (Method method : methods) {

                Log.i("-----", "methodName = : " +method.getName());
                // 获取方法的注解
                Subscribe subscribe = method.getAnnotation(Subscribe.class);
                // 判断注解不为空,切记不能抛异常
                if (subscribe == null) {
                    continue;
                }
                // 严格控制方法格式和规范  方法必须是返回void (一次匹配)
                Type returnType = method.getGenericReturnType();
                if (!"void".equalsIgnoreCase(returnType.toString())) {
                    throw new RuntimeException(method.getName() + " 方法返回必须是 void");
                }

                // 方法参数必须有值(二次匹配)
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    throw new RuntimeException(method.getName() + " 方法有且只有一个参数");
                }

                // 完全符合要求,规范的方法,保存到方法对象中MethodManager (3个重要成员:方法,参数,线程)
                MethodManager manager = new MethodManager(parameterTypes[0], subscribe.threadMode(), method);
                methodList.add(manager);


            }
            // 不断循环找出父类含有订阅者(注解方法)的类,直到为空,比如AppCompatActivity 没有
            clazz = clazz.getSuperclass();
                 Log.i("-----","父类: " + clazz);

        }

        return methodList;
    }


    // SecondActivity 发送消息
    public void post(final Object setter) {
        // 订阅者已经登记,从登记中找出
        Set<Object> set = cacheMap.keySet();
        // 比如获取MainActivity 对象
        for (final Object getter : set) {
            // 获取MainActivity 中注解的方法
            List<MethodManager> methodList = cacheMap.get(getter);
            if (methodList != null) {
                for (final MethodManager method : methodList) {
                    // 有可能多个方法参数一样,从而都同时收到发送的消息
                    // 通过EventBean 来判断是否匹配上
                    if (method.getType().isAssignableFrom(setter.getClass())) {
                        // 通过方法的类型匹配,从SecondActivity 发送的EventBean 对象(参数)
                        // 匹配MainActivity 中所有注解的方法 符合要求的,都发送消息
                        // class1.isAssignableFrom(class2) 判定此Class 对象所表示的类或者接口
                        // 与指定的Class 参数所表示的类或者接口是否相同,获取是否是其超类或者接口

                        // 线程调度
                        switch (method.getThreadMode()) {

                            case POSTING:
                                invoke(method, getter, setter);
                                break;

                            case MAIN:

                                // 先判断发送方是否在主线程
                                if (Looper.myLooper() == Looper.getMainLooper()) {
                                    invoke(method, getter, setter);
                                } else {
                                    // 子线程-主线程 , 切换线程 用 handler
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            invoke(method, getter, setter);
                                        }
                                    });
                                }


                                break;

                            case BACKGOUND:
                                // 先判断发送方是否在主线程
                                if (Looper.myLooper() == Looper.getMainLooper()) {

                                    // 主线程 - 子线程,创建一个子线程(缓存线程池)
                                    executorService.execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            invoke(method, getter, setter);
                                        }
                                    });
                                } else {
                                    // 子线程 到子线程 不用切换
                                    invoke(method, getter, setter);
                                }


                                break;


                        }


                    }
                }


            }
        }

    }

    /**
     * 找到匹配方法后,通过反射调用MainActivity 中所有符合要求的方法
     *
     * @param method
     * @param getter
     * @param setter
     */
    private void invoke(MethodManager method, Object getter, Object setter) {
        Method execute = method.getMethod();
        try {
            execute.invoke(getter, setter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
