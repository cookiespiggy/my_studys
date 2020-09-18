package cn.itcast;

import java.util.List;

public interface CalcEngine {

    boolean init();

    boolean execute(List<DataQuery> queries);

    List<DataSource> getResult();

    boolean finish();
}
