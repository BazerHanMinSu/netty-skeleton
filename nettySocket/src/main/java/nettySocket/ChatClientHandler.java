package nettySocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

// 입력받은 내용을 출력하기 위한 클래스

public class ChatClientHandler extends SimpleChannelInboundHandler<String> {
	
	@Override
	protected void channelRead0(ChannelHandlerContext arg0, String arg1) throws Exception {
		System.out.println(arg1);
	}
	
}
