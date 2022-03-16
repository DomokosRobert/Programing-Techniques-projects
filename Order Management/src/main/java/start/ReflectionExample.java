package start;

import java.lang.reflect.Field;

/**
 * The type Reflection example.
 */
public class ReflectionExample {

	/**
	 * Retrieve properties.
	 *
	 * @param object the object
	 */
	public static void retrieveProperties(Object object) {

		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true); 
			Object value;
			try {
				value = field.get(object);
				System.out.println(field.getName() + "=" + value);

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		}
	}
}



