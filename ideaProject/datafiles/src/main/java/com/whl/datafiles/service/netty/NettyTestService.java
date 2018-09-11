package com.whl.datafiles.service.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyTestService {


    public NettyTestService(int port){
        NioEventLoopGroup boss = new NioEventLoopGroup();//boss线程池
        NioEventLoopGroup work = new NioEventLoopGroup();//work线程池

        ServerBootstrap serverBootstrap = new ServerBootstrap();//服务启动器
        serverBootstrap.group(boss,work);//指定线程
        serverBootstrap.channel(NioServerSocketChannel.class);//设置服务通道
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                nioSocketChannel.pipeline().addLast("split",new DelimiterBasedFrameDecoder(1000,Delimiters.lineDelimiter()));
                nioSocketChannel.pipeline().addLast("decoder",new StringDecoder());
                nioSocketChannel.pipeline().addLast("encoder",new StringEncoder());
                nioSocketChannel.pipeline().addLast("handler",new FirstHandlerDiy());
            }
        });//设置处理器

        try {
            ChannelFuture future = serverBootstrap.bind(port).sync();//绑定端口
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    private class FirstHandlerDiy extends SimpleChannelInboundHandler<String> {
        /**
         * 接受消息后执行这个方法
         * @param channelHandlerContext
         * @param s
         * @throws Exception
         */
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
            System.out.println("既收到了"+s);
        }

        /**
         * 通道被客户端激活会执行这个方法
         * @param ctx
         * @throws Exception
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("server active");
            super.channelActive(ctx);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.out.println("收完了");
            ctx.writeAndFlush("fdfdfd");
        }
    }

    public static void main(String[] args) {
        NettyTestService nettyTestService=new NettyTestService(12345);
    }
}
