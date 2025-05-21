package Fabrics;

import annotations.Singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//usage example
//  Health h1 = SingletonFactory.getInstance(Health.class, new MyBusinessClass());
public class SingletonFactory {
    //NOTE I know that its over engineering, but I like it
    private static final Map<Class<?>, Object> instances = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> clazz, Object... args) {
        if (!clazz.isAnnotationPresent(Singleton.class)) {
            throw new IllegalArgumentException(clazz.getName() + " is not annotated with @Singleton");
        }

        return (T) instances.computeIfAbsent(clazz, cls -> {
            try {
                if (args.length == 0) {
                    return cls.getDeclaredConstructor().newInstance();
                } else {
                    // Найдём нужный конструктор
                    Class<?>[] argTypes = new Class<?>[args.length];
                    for (int i = 0; i < args.length; i++) {
                        argTypes[i] = args[i].getClass();
                    }
                    return cls.getDeclaredConstructor(argTypes).newInstance(args);
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to instantiate singleton: " + clazz.getName(), e);
            }
        });
    }
}
