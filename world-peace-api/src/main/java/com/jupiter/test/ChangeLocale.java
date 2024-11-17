package com.jupiter.test;

import java.util.Locale;

/**
 * @desc: on-line hot change locale
 * @author: Jupiter.Lin
 * @date: 2024-11-16
 *
java8=/Users/jupiter/11.java/jdk-1.8.0_422/Contents/Home/bin/java
$java8 com.jupiter.test.DateTest_1_8 #startup test

$java8 -jar /Users/jupiter/11.java/arthas-packaging-3.6.7-bin/arthas-boot.jar #start arthas --help
classloader --classLoaderClass sun.misc.Launcher$AppClassLoader --load com.jupiter.test.ChangeLocale #load a new class
vmtool --action getInstances --className com.jupiter.test.ChangeLocale #get instance

 *   #retransform /Users/jupiter/14.idea-workspace/world-peace/world-peace-api/target/classes/com/jupiter/test/ChangeLocale.class
 *   #jad com.jupiter.test.ChangeLocale
 */
public class ChangeLocale {
    static {
        Locale.setDefault(Locale.UK);
        System.out.println("Default Locale updated to United Kingdom");
    }

    public static void main(String[] args) {}
}
