package com.demo.agent;

import java.lang.instrument.Instrumentation;

public class TestAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("=======premain1 execute======");
        System.out.println(agentArgs);
        inst.addTransformer(new TestTransformer());
    }

    public static void premain(String agentArgs) {
        System.out.println("====premain2 execute====");
        System.out.println(agentArgs);
    }

}
