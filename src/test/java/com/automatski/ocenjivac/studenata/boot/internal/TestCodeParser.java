package com.automatski.ocenjivac.studenata.boot.internal;

import groovy.lang.GroovyClassLoader;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;

public class TestCodeParser {

    String testProgram1 = "program1.txt";

    /**
     Unit test for class loader with groovy
     */
    @Test
    public void myInternalTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException, IOException, ClassNotFoundException, URISyntaxException {
        //TOLE: trudi se da imas samo jednu main metodu u aplikaciji (IDE - Intellij moze da protumaci pogresno). Nije greska, samo good practice.
        //Koristi @Test anotaciju umesto toga, pokreces ih ili kao prave testove ili desno dugme iz Idea i run konkretne metode

        //GroovyShell shell = new GroovyShell();
        //upplier<String> supplier = Reflect.compile("", getCode()).create().call("");
        GroovyClassLoader gcl = new GroovyClassLoader();

        String programCode = getStringFromFile(testProgram1);

        Class clazz = gcl.parseClass(programCode);

        Object invocable = clazz.getDeclaredConstructor().newInstance(); //the new instance of the given class
//        HashMap<String, Class<?>[]> resultMap = parseCodeTemplate(getCode()); //unused
        //Method nadjiBroj = clazz.getDeclaredMethod("nadjiNajveciBroj", resultMap.get("nadjiNajveciBroj"));
        Field methods = clazz.getField("methods");
        Object x = methods.get(invocable);
        String[] arr = (String[]) x;
        System.out.println(Arrays.toString(arr));
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

    private String getStringFromFile(String filename) throws IOException {
        return IOUtils.toString(this.getClass().getClassLoader().getResource(filename),"UTF-8");
    }

    /**
     * This method parses java code template provided by teacher.
     * @param codeTemplate the code as a string
     * @return key value pairs that can be consumed
     */
    private HashMap<String, Class<?>[]> parseCodeTemplate(String codeTemplate) throws IOException {
        HashMap<String, Class<?>[]> map = new HashMap<>();
        GroovyClassLoader gcl = new GroovyClassLoader();
        Class clazz = gcl.parseClass(getStringFromFile(testProgram1));
        for (Method m : clazz.getMethods()) {
            map.put(m.getName(), m.getParameterTypes());
        }
        return map;
    }

    public static String[] getMethods() {
        return new String[]{"sss"};
    }
}
