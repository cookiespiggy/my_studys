package cn.itcast;

import java.util.List;
import java.util.Map;

public class Loader implements DataMapperLoader{

    @Override
    public List<DataMapper> createMappers(String table, Map<String, Object> settings) {
        return null;
    }

    @Override
    public DataMapper createMapper(String mapperName, Map<String, Object> settings) {
        return null;
    }

    @Override
    public List<String> getTables() {
        return null;
    }

    @Override
    public List<String> getAnalysers() {
        return null;
    }

    @Override
    public boolean init() {
        return false;
    }
}
