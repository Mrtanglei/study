import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.Date;

public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    private final static String WEB_SOCKET_URL = "ws://localhost:8888/websocket";

    //服务端处理客户端wesocket请求的核心方法
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        // 传统的HTTP接入
        //第一次握手请求消息由HTTP协议承载，所以它是一个HTTP消息，执行handleHttpRequest方法来处理WebSocket握手请求。
        if (msg instanceof FullHttpRequest) {//处理客户端向服务端发起http握手请求
            handHttpRequest(channelHandlerContext, (FullHttpRequest) msg);
        }
        // WebSocket接入
        // 客户端通过文本框提交请求消息给服务端，WebSocketServerHandler接收到的是已经解码后的WebSocketFrame消息。
        else if (msg instanceof WebSocketFrame) {//处理websocket连接业务
            handWebsocketFrame(channelHandlerContext, (WebSocketFrame) msg);
        }
    }

    /**
     * 处理客户端与服务器之间的websocket业务
     *
     * @param ctx
     * @param socketFrame
     */
    private void handWebsocketFrame(ChannelHandlerContext ctx, WebSocketFrame socketFrame) {
        //判断是否是关闭websocket指令
        if (socketFrame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) socketFrame);
        }
        //判断是是ping消息
        if (socketFrame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(socketFrame.content().retain()));
            return;
        }
        //判断是否是二进制消息，如果是则抛出异常
        if (!(socketFrame instanceof TextWebSocketFrame)) {
            System.out.println("暂不支持二进制消息");
            throw new RuntimeException(this.getClass().getName() + "不支持此消息");
        }
        //返回应答消息
        //获取客户端向服务端发送的消息
        String request = ((TextWebSocketFrame) socketFrame).text();
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + "====>>>" + request);
        //群发，服务器想每个连接上了的客户端群发消息
        NettyConfig.group.write(textWebSocketFrame);
    }

    /**
     * 处理客户端向服务端发起http握手请求业务
     *
     * @param ctx
     * @param req
     */
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        if (!req.decoderResult().isSuccess() || !("websocket".equals(req.headers().get("Uprade")))) {//请求不成功或不是websokect握手请求
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, null, false);
        handshaker = factory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            // 通过它构造握手响应消息返回给客户端，
            // 同时将WebSocket相关的编码和解码类动态添加到ChannelPipeline中，用于WebSocket消息的编解码，
            // 添加WebSocketEncoder和WebSocketDecoder之后，服务端就可以自动对WebSocket消息进行编解码了
            handshaker.handshake(ctx.channel(), req);
        }
    }

    /**
     * 服务端向客户相应消息
     *
     * @param ctx
     * @param req
     * @param resp
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse resp) {
        if (resp.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(resp.status().toString(), CharsetUtil.UTF_8);
            resp.content().writeBytes(buf);
            buf.release();
        }
        //如果是非Keep-Alive，关闭连接
        ChannelFuture future = ctx.channel().writeAndFlush(resp);
        if (resp.status().code() != 200) {
            //关闭连接
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    //客户端与服务端创建连接时调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        NettyConfig.group.add(ctx.channel());
        System.out.println("客户端与服务端建立连接。。。。");
    }

    //客户端与服务端断开连接时调用
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        NettyConfig.group.remove(ctx.channel());
        System.out.println("客户端与服务端断开连接。。。。");
    }

    //服务端接收客户端发送过来的数据结束后调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
        System.out.println("服务端接收客户端发送过来的数据结束。。。。");
    }

    //出现异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
