package com.automatski.ocenjivac.studenata.boot.helpers;

import java.lang.reflect.Method;

public interface CodeParser {

    String[] getFullName();

    Object getFieldByName(String filedName) throws NoSuchFieldException, IllegalAccessException;

    Method getMethodByName(String methodName) throws NoSuchMethodException;
}
