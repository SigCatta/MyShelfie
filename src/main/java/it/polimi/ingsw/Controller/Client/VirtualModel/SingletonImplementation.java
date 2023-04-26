package it.polimi.ingsw.Controller.Client.VirtualModel;

import java.util.HashMap;
import java.util.Map;

/**
 * every class that extends this class will be singleton
 */
public abstract class SingletonImplementation {
    /**
     * the class instances are stored in this map
     * with key the type of the class and value the implementation of the class
     */
    private static final Map<Class<? extends SingletonImplementation>, SingletonImplementation> INSTANCES = new HashMap<>();

    protected SingletonImplementation() {}

    /**
     * Get the instance of the class from the map
     */
    public static <T extends SingletonImplementation> T getInstance(Class<T> clazz) {
        if (!INSTANCES.containsKey(clazz)) {
            try {
                INSTANCES.put(clazz, clazz.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException("Error creating instance of " + clazz, e);
            }
        }
        return clazz.cast(INSTANCES.get(clazz));
    }
}