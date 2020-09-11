package com.itcast.redis;


import redis.clients.jedis.Jedis;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisTest {
    public static void main(String[] args) throws IOException, InterruptedException {
//        Jedis jedis = new Jedis("47.93.221.206", 6379);
//        jedis.connect();
//        if (jedis.isConnected()) {
////            FileInputStream inputStream = new FileInputStream("D:\\FFOutput\\1.wav");
////            AudioStream as = new AudioStream(inputStream);
//            URL url = new URL("http://s.aigei.com/src/aud/wav/24/24951271c59240388fb8b22cc5da1d17.wav?download/%E5%92%9A1_%E7%88%B1%E7%BB%99%E7%BD%91_aigei_com.wav&e=1599672720&token=P7S2Xpzfz11vAkASLTkfHN7Fw-oOZBecqeJaxypL:s1Dv4CNPklQvFg6XgOt_uPumhaY=");
//            AudioStream as = new AudioStream(url.openStream());
//            AudioPlayer.player.start(as);
//            System.out.println("已连接");
//            TimeUnit.SECONDS.sleep(9);
//        }
//        Set<String> keys = jedis.keys("*");
//        for (String key : keys) {
//            System.out.println(key);
//        }
//        jedis.disconnect();
//        if (!jedis.isConnected()) {
//            System.out.println("已断开");
//        }
        URL url = new URL("http://s.aigei.com/src/aud/wav/24/24951271c59240388fb8b22cc5da1d17.wav?download/%E5%92%9A1_%E7%88%B1%E7%BB%99%E7%BD%91_aigei_com.wav&e=1599672720&token=P7S2Xpzfz11vAkASLTkfHN7Fw-oOZBecqeJaxypL:s1Dv4CNPklQvFg6XgOt_uPumhaY=");
        AudioStream as = new AudioStream(url.openStream());
        AudioPlayer.player.start(as);

        AudioPlayer.player.stop(as);

        AudioData data = as.getData();
        ContinuousAudioDataStream continuousAudioDataStream = new ContinuousAudioDataStream(data);
        AudioPlayer.player.start(continuousAudioDataStream);
        Thread.sleep(10_000);
    }
}