package com.demo.agent;

import javassist.CtMethod;
import javassist.ClassPool;
import javassist.CtClass;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class TestTransformer implements ClassFileTransformer {
    public final String ClassName = "com.example.demo.DemoApplication";
    public final String methodName = "hello";
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replace("/", ".");
        if(className.equals(ClassName)){//上面是利用javasgent获取每个方法的premain函数  下面是利用字节码增强技术修改指定方法的字节码
            //CtClass ctclass;
            //ctclass =
            try{
                CtClass ctClass = ClassPool.getDefault().get(className);//利用类名从类池子获取Ctclass
                CtMethod ctmethod = ctClass.getDeclaredMethod(methodName);//获取指定方法
                ctmethod.insertBefore("System.out.println(\"字节码输出\")");
                return ctClass.toBytecode();//返回修改后的字节码
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new byte[0];
    }
}
