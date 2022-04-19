package com.automatski.ocenjivac.studenata.boot.internal;

import groovy.lang.GroovyClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;

public class TestCodeParser {
    /**
     Unit test for class loader with groovy
     */
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException, IOException, ClassNotFoundException {
        //GroovyShell shell = new GroovyShell();
        //upplier<String> supplier = Reflect.compile("", getCode()).create().call("");
        GroovyClassLoader gcl = new GroovyClassLoader();
        Class clazz = gcl.parseClass(getCode());
        Object invocable = clazz.getDeclaredConstructor().newInstance(); //the new instance of the given class
        HashMap<String, Class<?>[]> resultMap = parseCodeTemplate(getCode());
        //Method nadjiBroj = clazz.getDeclaredMethod("nadjiNajveciBroj", resultMap.get("nadjiNajveciBroj"));
        Field methods = clazz.getField("methods");
        Object x = methods.get(invocable);
        String[] arr = (String[]) x;
        System.out.println(x);
        Method[] tt = clazz.getMethods();
        for (String m : arr) {
            //get args
            Field field_in;
            Field field_out_expected;
            for (int i = 1; i < 2; i++) {
                for (Method m_l : clazz.getDeclaredMethods()) {
                    if (m_l.getName().equals(m)) {
                        field_in = clazz.getField(m + "In" + i);
                        field_out_expected = clazz.getField(m + "Out" + i);
                        Type t = field_in.getGenericType();
                        System.out.println(t.getTypeName());
                        System.out.println("exected" + field_out_expected.get(invocable));
                        Object l_Result_actual = m_l.invoke(invocable, field_in.get(invocable));
                        System.out.println("actual" + l_Result_actual);
                        if (l_Result_actual == field_out_expected.get(invocable)) {
                            System.out.println("Test case passed!");
                        }
                    }
                }
            }

        }
//        Field f = clazz.getField("in1_1");
//        Object localArgs = f.get(invocable);
//        System.out.println(localArgs);
//        Object result = nadjiBroj.invoke(invocable, localArgs);
//        System.out.println(result);
//        System.out.println(result instanceof Integer);


    }
    public static String getCode() {
        return "package com.itbootcamp.test;\n" +
                "\n" +
                "import java.util.Arrays;\n" +
                "public class MarkoStankovic {\n" +
                "public static String[] methods = new String[]{\"nadjiNajveciBroj\"};" +
                "public static int[] arg1 = new int[]{1, 2, -1000, 1000}; \n" +
                "    public int[] nadjiNajveciBrojIn1 = new int[]{1, 2, -1000, 1000};\n" +
                "    public int nadjiNajveciBrojOut1 = 1000;" +
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

    public static String[] getMethods() {
        return new String[]{"sss"};
    }
}
