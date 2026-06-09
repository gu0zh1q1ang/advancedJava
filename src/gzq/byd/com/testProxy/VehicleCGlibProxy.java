package gzq.byd.com.testProxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class VehicleCGlibProxy implements MethodInterceptor {

    private Object initObj = null;

    public VehicleCGlibProxy(Object oo) {
        initObj = oo;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName()+" proxied!");

        A4CGLib annotation = method.getAnnotation(A4CGLib.class);
        if(annotation!=null) {
            System.out.println(method.getName() + "executed! and there's an annotation on it with title " + annotation.title());
            return methodProxy.invokeSuper(o, objects);
        }else{
            return methodProxy.invoke(initObj,objects);
        }

    }
}
