package utils;

import java.lang.reflect.Field;

public class SetFieldNewValueHelper {

    public static void setFieldValue(Object target, String fieldName, Object fieldValue) throws NoSuchFieldException, IllegalAccessException {
        Field nameField = target.getClass().getDeclaredField(fieldName);
        nameField.setAccessible(true);
        nameField.set(target, fieldValue);
    }
}
