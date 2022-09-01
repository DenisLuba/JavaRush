package com.javarush.task.task32.task3205;

import java.lang.reflect.Proxy;

public class Solution {
    public static void main(String[] args) {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);

        /* expected output
        stringMethodWithoutArgs in
        inside stringMethodWithoutArgs
        stringMethodWithoutArgs out
        voidMethodWithIntArg in
        inside voidMethodWithIntArg
        inside voidMethodWithoutArgs
        voidMethodWithIntArg out
         */
    }

    public static SomeInterfaceWithMethods getProxy() {
        SomeInterfaceWithMethods object = new SomeInterfaceWithMethodsImpl();
        ClassLoader objectClassLoader = object.getClass().getClassLoader();
        Class[] interfaces = object.getClass().getInterfaces();
        SomeInterfaceWithMethods objectProxy = (SomeInterfaceWithMethods) Proxy.newProxyInstance(objectClassLoader, interfaces, new CustomInvocationHandler(object));

        return objectProxy;
    }
}
