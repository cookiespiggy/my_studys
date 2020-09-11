package com.itcast;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.streaming.Trigger;

public class Triggers {

    public static void main(String[] args) throws StreamingQueryException {

        SparkSession session = SparkSession.builder().appName("triggers").master("local").getOrCreate();
        session.sparkContext().setLogLevel("WARN");

        // 弱类型，写字符串  ds为强类型，可以.属性去操作 比如 filter强   where就不支持，只能写字符串
        Dataset<Row> rateDF = session.readStream().format("rate").load();

        Dataset<Row> resDF = rateDF;

        // update 只输出更新的数据  更新和新增的  聚合你想想

        // append: 输出以后永远都不会再变的数据，输出之后，数据就会从内测中销毁掉(从内存中移除)   相当于数据清理的手段
        // 每一批次的數據都展現最新那一批次的數據
        // 反過來講，有聚合了，就不能用append，除非你有watermark，要不然就丢数据了。

        // complete: 有聚合的時候，把全局的聚合結構展示出來 必須要有聚合操作
        // complete展示的是全局的結果，如果你在這種模式還沒有聚合的話，就會出現把所有數據全都放在一個sta..stoge當中，壓力很大
        // 所有spark規定，沒有聚合，不能用complete模式
        resDF.writeStream().format("console").outputMode(OutputMode.Append())
                // 展示的時候，列長度正好適配值，看著比較輸出
                .option("truncate", false)
                // 5秒輸出一批
                //.trigger(Trigger.ProcessingTime("5 seconds"))
                .start().awaitTermination();

    }
}
