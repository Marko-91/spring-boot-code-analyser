package com.automatski.ocenjivac.studenata.boot.internal;

import groovy.lang.GroovyClassLoader;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class TestCodeParser {
    /**
     Unit test for class loader with groovy
     */
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        //GroovyShell shell = new GroovyShell();
        GroovyClassLoader gcl = new GroovyClassLoader();
        Class clazz = gcl.parseClass(getCode());
        Object invocable = clazz.getDeclaredConstructor().newInstance(); //the new instance of the given class
        System.out.println("The class firstName is: " + clazz.getName());
        HashMap<String, Class<?>[]> resultMap = parseCodeTemplate(getCode());
        Method nadjiBroj = clazz.getDeclaredMethod("nadjiNajveciBroj", resultMap.get("nadjiNajveciBroj"));
        Field f = clazz.getField("arg1");
        Object localArgs = f.get(invocable);
        Object result = nadjiBroj.invoke(invocable, localArgs);
        System.out.println(result);


    }
    public static String getCode() {
        return "package com.itbootcamp.test;\n" +
                "\n" +
                "import java.util.Arrays;\n" +
                "public class MarkoStankovic {\n" +
                "public static int[] arg1 = new int[]{1, 2, -1000, 1000}; \n" +

                "    public static int nadjiNajveciBroj(int[] array) {\n" +
                "        int max = array[0];\n" +
                "        for (int i = 0; i < array.length; i++) {\n" +
                "            if (array[i] > max) max = array[i];\n" +
                "        }\n" +
                "\n" +
                "        return max;\n" +
                "    }\n" +
                "}\n";
    }

    /**
     * This method parses java code template provided by teacher.
     * @param codeTemplate the code as a string
     * @return key value pairs that can be consumed
     */
    public static HashMap<String, Class<?>[]> parseCodeTemplate(String codeTemplate) {
        HashMap<String, Class<?>[]> map = new HashMap<>();
        GroovyClassLoader gcl = new GroovyClassLoader();
        Class clazz = gcl.parseClass(getCode());
        for (Method m : clazz.getMethods()) {
            map.put(m.getName(), m.getParameterTypes());
        }
        return map;
    }
}
