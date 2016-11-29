package com.shl.netty;

/**
 * Created by jackson on 16/4/13.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelHandlerAdapter {

    private static AtomicInteger counter = new AtomicInteger(0);

    private static final Logger logger = Logger
            .getLogger(TimeClientHandler.class.getName());

    private BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
    byte[] req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        //与服务端建立连接后
        System.out.println("client channelActive..");

        new Thread() {
            @Override
            public void run() {
                try {
                    while (sin.readLine() != null){

                        for (int i = 0 ;i<100;i++){

                            ByteBuf firstMessage = Unpooled.buffer(req.length);
                            firstMessage.writeBytes(req);
                            ctx.writeAndFlush(firstMessage);
                            System.out.println(" counter:"+counter.incrementAndGet());
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }.start();



    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("client channelRead..");
//        //服务端返回消息后
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
        System.out.println("Now is :" + (String) msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println("client exceptionCaught..");
        // 释放资源
        logger.warning("Unexpected exception from downstream:"
                + cause.getMessage());
        ctx.close();
    }

}