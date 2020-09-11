package com.itcast;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.DataTypes;

public class DataStream {
    public static void main(String[] args) throws StreamingQueryException {
        //set the hadoop home directory for kafka source
        System.setProperty("hadoop.home.dir", "D:\\sparkrun\\winutils-master\\hadoop-2.7.7\\bin");
        //建立连接
        SparkSession session = SparkSession.builder()
                .master("local[*]")
                .appName("structuredViewingReport")
                .getOrCreate();
        session.sparkContext().setLogLevel("ERROR");

        //define UDF to parse kafka message that can be passed to Spark SQL
        // 创建 json schema-指定表结构的方式创建DataSet 预定义json的格式并解析kafka的数据内容 本文中好像沒有用到這種方式
        session.udf().register("sessionDurationFn", new UDF1<String, Long>() {
            @Override
            public Long call(String messageValue) throws Exception {
                String[] strArr = messageValue.split(",");
                //returns the session duration value from Kafka message which is the first value in the coma delimited string value passed fron Kafka broker
                return Long.parseLong(strArr[0]);
            }
        }, DataTypes.LongType);

        session.udf().register("userNameFn", new UDF1<String, String>() {
            @Override
            public String call(String messageValue) throws Exception {
                String[] strArr = messageValue.split(",");
                //returns user name value from Kafka message which is the second value in the coma delimited string value passed fron Kafka broker
                return strArr[1];
            }
        }, DataTypes.StringType);

        //define kafka streaming reader
        // 读取kafka的主题topic sessiondata，并将数据注册为临时表 session_data_init
        Dataset<Row> df = session.readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "localhost:9092")
                .option("subscribe", "test")
                .load();
        // start  dataframe operations
        df.createOrReplaceTempView("session_data_init");

        // key, value, timestamp are the core attributes in the kafka message. Value contains the coma delimited string with sessionduration,userid value format
        // key是偏移量 value是字节数组，执行CAST的作用是将value转换为字符串
        Dataset<Row> preresults = session.sql("select sessionDurationFn(cast (value as string)) as session_duration, userNameFn(cast (value as string)) as userName,timestamp from session_data_init");
        // 从字符串中解析为各个字段并注册成临时表，进而对表进行SQL操作
        preresults.createOrReplaceTempView("session_data");

        //compute sum of session duration grouping on 2 minute window and userName
        Dataset<Row> results =
                session.sql("select window,sum(session_duration) as session_duration,userName  from session_data group by window(timestamp,'2 minutes'),userName");

        //log the results to console
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
