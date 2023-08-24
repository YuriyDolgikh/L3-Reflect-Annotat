package biz.itehnika;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IOException, NoSuchFieldException, InstantiationException {
		// Check task 1
		System.out.println(Volume.testRefl(Volume.class));

		// Check task 2
		Class<?> cls = TextContainer.class;
		if(!cls.isAnnotationPresent(SaveTo.class)){
			System.err.println("Class 'SaveTo' is not annotated!");
		}else {
			SaveTo saveTo = cls.getAnnotation(SaveTo.class);
			String path = saveTo.path();
			Method[] methods = cls.getMethods();
			for (Method mtd : methods) {
				if (mtd.isAnnotationPresent(Saver.class)){
					mtd.invoke(new TextContainer(), path);
					break;
				}
			}
			System.out.println("File saved!");
		}

		// Check task 3
		String pathFile = "./task-3.txt";
		SerialUtils.serialize(new User("Tom", "Cruis", 61, "$RFV!ty"), pathFile);
		SerialUtils.serialize(new User("Keanu", "Reeves", 58, "ndhn$%64"), pathFile);
		SerialUtils.serialize(new User("Bill", "Gates", 67, "1qaz2wsx"), pathFile);

		ArrayList<Object> users = SerialUtils.deserialize(pathFile, User.class);
		printArray(users);

	}
	static void printArray(ArrayList<Object> users){
		for (Object user : users){
			System.out.println(user);
		}
	}
}
