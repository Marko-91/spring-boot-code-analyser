package com.automatski.ocenjivac.studenata.boot.helpers;

import groovy.lang.GroovyClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaCodeParser implements CodeParser {
    private GroovyClassLoader glc;
    private Class clazz;
    private String sourceCode;
    private Object invocable;

    public JavaCodeParser(GroovyClassLoader glc, String sourceCode) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.glc = glc;
        this.sourceCode = sourceCode;
        this.clazz = glc.parseClass(sourceCode);
        this.invocable = clazz.getDeclaredConstructor().newInstance();
    }

    /**
     * The method will return an array of size 2 with First name and Last name.
     * Class name should be FirstnameLastname format
     * @return array[0]=firstName array[1]=lastname
     */
    @Override
    public String[] getFullName() {
        return this.clazz.getSimpleName().split("(?=[A-Z])");
    }

    @Override
    public Object getFieldByName(String filedName) throws NoSuchFieldException, IllegalAccessException {
        return this.clazz.getField(filedName).get(this.invocable);
    }

    @Override
    public Method getMethodByName(String methodName) throws NoSuchMethodException {
        return clazz.getDeclaredMethod(methodName);
    }
}
