package biz.itehnika;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class SerialUtils {

    public static String serialize(Object obj, String pathFile) throws IllegalAccessException, IOException {
        Class<?> cls = obj.getClass();
        StringBuilder stringBuilder = new StringBuilder();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields){
            if (field.isAnnotationPresent(Save.class)){
                if (Modifier.isPrivate(field.getModifiers())){
                    field.setAccessible(true);
                }
                stringBuilder.append(field.getName()).append("=").append(field.get(obj)).append(";");
            }
        }
        String result = stringBuilder.append(System.lineSeparator()).toString();
        FileWriter writer = new FileWriter(pathFile,true);
        writer.write(result);
        writer.close();
        return result;
    }

    public static ArrayList<Object> deserialize(String pathFile, Class<?> cls) throws InstantiationException, IllegalAccessException, IOException, NoSuchFieldException {
        ArrayList<Object> arrList = new ArrayList<>();
        String str;

        try(BufferedReader reader = new BufferedReader(new FileReader(pathFile))){
            while ((str = reader.readLine()) != null){
                Object resultObject = cls.newInstance();

                String[] items = str.split(";");
                for (String item : items){
                    String[] nameAndValue = item.split("=");
                    if (nameAndValue.length != 2){
                        throw new InvalidParameterException("Wrong element for parsing!");
                    }
                    String name = nameAndValue[0];
                    String value = nameAndValue[1];
                    Field field = cls.getDeclaredField(name);
                    if (Modifier.isPrivate(field.getModifiers())){
                        field.setAccessible(true);
                    }
                    if (field.isAnnotationPresent(Save.class)){
                        if (field.getType() == int.class){
                            field.setInt(resultObject, Integer.parseInt(value));
                        } else if (field.getType() == String.class) {
                            field.set(resultObject, value);
                        }
                    }
                }
                 arrList.add(resultObject);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return arrList;
    }
}
