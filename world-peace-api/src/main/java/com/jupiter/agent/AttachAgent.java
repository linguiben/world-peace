package com.jupiter.agent;

/**
 * @desc: TODO
 * @author: Jupiter.Lin
 * @date: 2024/11/24
 */

import com.sun.tools.attach.*;

import java.io.IOException;

/*
Test case: 通过java Agent更改目标JVM的Local，从而影响Calendar的结果。
Test Steps:
#1.build agent jar 使用以下命令打包JAR文件：
  cd /Users/jupiter/14.idea-workspace/world-peace/world-peace-api/target/classes
  jar cvfm myagent.jar META-INF/MANIFEST.MF com/jupiter/agent/*.class com/jupiter/test/*.class
#2.startup target JVM
  $java8 com.jupiter.test.DateTest_1_8
#3.run attach agent
  LIB=/Users/jupiter/11.java/jdk-1.8.0_422/Contents/Home/lib/tools.jar
  AGENT_JAR=/Users/jupiter/14.idea-workspace/world-peace/world-peace-api/target/classes/myagent.jar
  TARGET_PID=`jps -lm|grep DateTest_1_8|cut -d' ' -f 1`
  $java8 -cp .:$LIB com.jupiter.agent.AttachAgent $TARGET_PID $AGENT_JAR
*/

public class AttachAgent {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: AttachAgent <target PID> <agent JAR path>");
            return;
        }

        String targetPid = args[0];
        String agentPath = args[1];

        try {
            // 列出所有正在运行的JVM实例
            for (VirtualMachineDescriptor vmDesc : VirtualMachine.list()) {
                if (vmDesc.id().equals(targetPid)) {
                    System.out.println("Found target JVM: " + vmDesc.displayName() + " (ID: " + vmDesc.id() + ")");

                    // 连接到目标JVM
                    VirtualMachine vm = VirtualMachine.attach(targetPid);

                    // 加载Java Agent
                    vm.loadAgent(agentPath);

                    // 断开连接
                    vm.detach();
                    System.out.println("Agent loaded successfully.");
                    return;
                }
            }

            System.out.println("Target JVM not found.");
        } catch (AttachNotSupportedException | IOException e) {
            e.printStackTrace();
        } catch (AgentLoadException e) {
            throw new RuntimeException(e);
        } catch (AgentInitializationException e) {
            throw new RuntimeException(e);
        }
    }
}
