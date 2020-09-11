package com.itcast;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

public class FileStream {
    public static void main(String[] args) throws Exception {

        //build the spark sesssion
        SparkSession spark = SparkSession.builder().appName("spark streaming").config("spark.master", "local")
                // hdfs目录
                .config("spark.sql.warehouse.dir", "file:///app/").getOrCreate();

        //set the log level only to log errors
        spark.sparkContext().setLogLevel("ERROR");

        //define schema type of file data source
        // 创建 json schema-指定表结构的方式创建DataSet 预定义json的格式并解析kafka的数据内容
        StructType schema = new StructType().add("empId", DataTypes.StringType).add("empName", DataTypes.StringType)
                .add("department", DataTypes.StringType);

        //build the streaming data reader from the file source, specifying csv file format
        Dataset<Row> rawData = spark.readStream().option("header", true).format("csv").schema(schema)
                .csv("D:/streamingfiles/*.csv");

        rawData.createOrReplaceTempView("empData");

        //count of employees grouping by department
        Dataset<Row> result = spark.sql("select count(*), department from  empData group by department");

        //write stream to output console with update mode as data is being aggregated
        StreamingQuery query = result.writeStream().outputMode(OutputMode.Complete()).format("console").start();
        query.awaitTermination();

    }
}
