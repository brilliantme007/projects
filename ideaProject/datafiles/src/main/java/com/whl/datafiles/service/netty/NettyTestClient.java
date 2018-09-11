package com.whl.datafiles.service.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class NettyTestClient {

    public NettyTestClient(String address,int  port){

        NioEventLoopGroup group=new NioEventLoopGroup();
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                nioSocketChannel.pipeline().addLast("split",new DelimiterBasedFrameDecoder(1000,Delimiters.lineDelimiter()));
                nioSocketChannel.pipeline().addLast("decoder",new StringDecoder());
                nioSocketChannel.pipeline().addLast("encoder",new StringEncoder());
                nioSocketChannel.pipeline().addLast("handler",new ClientHandler());
            }
        });

        try {
            Channel channel = bootstrap.connect(address, port).sync().channel();
            Scanner scanner=new Scanner(System.in);//读取键盘输入
            while(true){
                String line = scanner.nextLine();
                if(line==null||"".equals(line)){
                    continue;
                }
                if("exit".equals(line)){//退出字符串
                    channel.close();
                    break;
                }
                channel.writeAndFlush(line+ "\r\n");//\n为了分隔识别，\r为了格式化输出
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }

    private class ClientHandler extends SimpleChannelInboundHandler<String> {
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, String o) throws Exception {
            System.out.println("--------->"+o);
            }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("client active");
            super.channelActive(ctx);
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("clent close");
            super.channelInactive(ctx);
        }
    }

    public static void main(String[] args) {
        new NettyTestClient("localhost",12345);
    }
}
