package cn.itcast;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.spark.sql.SparkSession;

import java.util.HashMap;
import java.util.Map;
// https://github.com/xtreamdarsan/casem/blob/412010e84b6742d07ea38d4499a223a6c0436f3f/src/main/java/javasparkes/test/TestApp.java

// https://github.com/agoclover/bigdata-technology/blob/806ce40f9cf9431f5f87b068eb5053e4198da7fc/6_Kafka/codes/Kafka0317/src/main/java/com/atguigu/kafka/consumer/MyConsumer.java
/*

StructType masterSchema = new StructType()
				.add("caseNumber","String")
				.add("masterName","String")
				.add("masterAddress","String");

				.selectExpr("CAST(value AS STRING) as masterDetails")
		            .select(functions.from_json(functions.col("masterDetails"),masterSchema).as("json"))
		            .select("json.*")


当Kafka中没有初始偏移量
What to do when there is no initial offset in Kafka
or 服务器上不再存在当前偏移量时
if the current offset does not exist any more on the server(e.g. because that data has been deleted):
<ul><li>earliest: automatically reset the offset to the earliest offset<li>
latest: automatically reset the offset to the latest offset</li><li>none: throw exception to the consumer if no previous offset is found for the consumer's group</li><li>anything else: throw exception to the consumer.</li></ul>
 */
public class AppDemo1 {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();

        map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        map.put(ConsumerConfig.AUTO_OFFSET_RESET_DOC, "");


        SparkSession session = null;

        // KafkaSourceProvider
        session.readStream().format("kafka").option("auto.offset.reset","smallest");
    }
}
