package com.alibou.security.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClassUtil {

    public static List<String> getAllFieldNames(Class<?> _class) {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = _class.getDeclaredFields();

        for (Field field : fields) {
            fieldNames.add(field.getName());
        }

        return fieldNames;
    }
}
