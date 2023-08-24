package biz.itehnika;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Volume {
    @MyAnnotation(x = 3, y = 4, z = 5)
    public static int volume(int x, int y, int z){
        return x * y * z;
    }

    public static int testRefl(Class<?> cls) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = cls.getDeclaredMethods();
        int result = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation annot = method.getAnnotation(MyAnnotation.class);
                    result = (int) method.invoke(null, annot.x(), annot.y(), annot.z());
            }
        }
        return result;
    }
}
