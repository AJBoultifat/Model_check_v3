package factoryManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import net.sf.javabdd.BDDFactory;

public class BDDFactoryManager {
    
    private static Logger log = Logger.getLogger(BDDFactoryManager.class.getName());

    private static BDDFactory factory = null;
    private static int nodenum   = 1000000;
    private static int cachesize = 100000;
    private static String factoryName; 
    public static final String[] factoryNames = {
        "net.sf.javabdd.BuDDyFactory",
        "net.sf.javabdd.JFactory"//,
    };
    public static BDDFactory initFactory() {
        if (factory != null) {
            factory.done();
            factory = null;
        }
        for (String fname : factoryNames) {
            try {
                Class<?> c = Class.forName(fname);
                Method m = c.getMethod("init", new Class[] { int.class, int.class });
                factory = (BDDFactory) m.invoke(null, new Object[] { new Integer(nodenum), new Integer(cachesize) });
                if (factory != null) {
                    factoryName = fname;
                    break;
                }
            }
            catch (Throwable _) {
                if (_ instanceof InvocationTargetException)
                    _ = ((InvocationTargetException)_).getTargetException();
            }
        }
        return factory;
    }
    public static BDDFactory getFactory() {
        return factory;
    }
    public static String getFactoryName() {
        return factoryName;
    }
}
