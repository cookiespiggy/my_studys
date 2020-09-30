package cn.itcast.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/*
https://github.com/ZAKLLL/Study_notes/blob/9ef99c18a78220b38546488a67760db4e28aef97/%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0/Java%E7%9B%B8%E5%85%B3/%E7%BD%91%E7%BB%9C%E7%BC%96%E7%A8%8B/Netty%E5%BF%83%E8%B7%B3%E6%A3%80%E6%B5%8B.md
 */
public class MyServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline channelPipeline = ch.pipeline();
                            channelPipeline.addLast(new HeartBeatHandler(3, 0, 0));
                            channelPipeline.addLast(new MyServerHandler());

                            channelPipeline.addLast(new ChannelInboundHandlerAdapter(){

                                @Override
                                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                    System.out.println("last Trigger触发");
                                    super.userEventTriggered(ctx, evt);
                                }
                            });
                        }
                    });


            ChannelFuture channelFuture = serverBootstrap.bind(10005).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}