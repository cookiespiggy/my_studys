package cn.itcast;

import java.util.List;
import java.util.Map;

public interface DataMapperLoader extends Plugin {

    List<DataMapper> createMappers(String table, Map<String, Object> settings);

    DataMapper createMapper(String mapperName, Map<String, Object> settings);

    List<String> getTables();

    List<String> getAnalysers();

}
