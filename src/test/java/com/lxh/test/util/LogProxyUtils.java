package com.lxh.test.util;

import com.lxh.test.service.TestProxyService;
import com.lxh.test.service.impl.TestProxyServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 刘晓禾
 * @date 2019/7/15 11:09
 * @company www.tydic.com
 * @description 实现动态代理来实现日志
 */
public class LogProxyUtils implements InvocationHandler {

    // 目标对象
    private Object targetObject;


    //绑定关系，也就是关联到哪个接口（与具体的实现类绑定）的哪些方法将被调用时，执行invoke方法。
    //Proxy.newProxyInstance 创建的代理对象是在jvm运行时动态生成的一个对象，它并不是我们的InvocationHandler类型，
    // 也不是我们定义的那组接口的类型，而是在运行是动态生成的一个对象，并且命名方式都是这样的形式，
    // 以$开头，proxy为中，最后一个数字表示对象的标号。
    public Object newProxyInstance(Object targetObject){
        this.targetObject=targetObject;
        //该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
        //第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
        //第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
        //第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
        //根据传入的目标返回一个代理对象
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),this);
    }


    /**
     *
     * 功能描述: 动态代码必须要实现这个方法来确定代理关系
     *
     * Object proxy:被代理的对象
     * Method method:要调用的方法
     * Object[] args:方法调用时所需要参数
     * @return
     * @throws
     * @auther 刘晓禾
     * @date  2019/7/15
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始动态代理");
        Object result = null;
        try {
            System.out.println(method.toString());
            result = method.invoke(targetObject,args);
            System.out.println("代码成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("动态代理失败");
        }
        return result;
    }
    
    
    public static void main(String[] args){

       LogProxyUtils logProxyUtils = new LogProxyUtils();
       TestProxyService testProxyService = (TestProxyService)logProxyUtils.newProxyInstance(new TestProxyServiceImpl());
       System.out.println(new TestProxyServiceImpl().getClass().getInterfaces()[0].toString());

       testProxyService.addtest("11:43");

    }


}
