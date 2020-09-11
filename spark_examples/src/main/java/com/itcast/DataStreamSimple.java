package com.itcast;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.DataTypes;

public class DataStreamSimple {
    public static void main(String[] args) throws StreamingQueryException {
        SparkSession session = SparkSession.builder()
                .master("local[*]")
                .appName("structuredViewingReport")
                .getOrCreate();
        session.sparkContext().setLogLevel("ERROR");

        // 数据来了才会报错，启动不会报错
        session.udf().register("fn1", new UDF1<String, Long>() {
            @Override
            public Long call(String messageValue) throws Exception {
                String[] strArr = messageValue.split(",");
                return Long.parseLong(strArr[0]);
            }
        }, DataTypes.LongType);

        session.udf().register("fn2", new UDF1<String, String>() {
            @Override
            public String call(String messageValue) throws Exception {
                String[] strArr = messageValue.split(",");
                return strArr[1];
            }
        }, DataTypes.StringType);

        Dataset<Row> df = session.readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "localhost:9092")
                .option("subscribe", "test")
                .load();
        df.createOrReplaceTempView("spark_demo");

        Dataset<Row> preres = session.sql("select fn1(cast (value as string)) as f1 ,fn2(cast (value as string)) as f2,timestamp from spark_demo");

        preres.createOrReplaceTempView("spark_data");

        Dataset<Row> results = session.sql("select window,sum(f1) as fn3 ,f2 from spark_data group by window(timestamp,'2 minutes'),f2");

        StreamingQuery query = results
                .writeStream()
                .format("console")
                .outputMode(OutputMode.Update())
                // 否截断输出  只是为了在控制台输出时，不进行列宽度自动缩小。
                .option("truncate", false)
                // 如何在具有特定大小/记录块的控制台上写入流数据？
                .option("numRows", 50)
                .start();

        query.awaitTermination();
    }
}
