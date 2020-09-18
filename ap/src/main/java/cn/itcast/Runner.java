package cn.itcast;

import java.util.Map;

public class Runner {

    private Class<?> engineCreator;
    // Runner里持有这个引用的意义：
    private DataMapperLoader dataMapperLoader;

    private Map<String, Object> settings = null;

    public void init(Map<String, Object> settings) {
        this.settings = settings;
    }

    private Class<?> loadClass(String clzName) {
        try {
            return Class.forName(clzName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean loadDataMapper() {
        Class<?> loader = loadClass("cn.itcast.Loader");
        if (loader == null) return false;

        dataMapperLoader = createDataMapperLoader(loader);
        if (dataMapperLoader == null) {
            return false;
        }

        if (!dataMapperLoader.init()) {
            return false;
        }

        return true;
    }

    private DataMapperLoader createDataMapperLoader(Class<?> dataMapperCreator) {
        try {
            return (DataMapperLoader) dataMapperCreator.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
