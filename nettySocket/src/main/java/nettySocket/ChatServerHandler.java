package nettySocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

// 서버에 이벤트 발생에 따라 행동을 결정해주기 위한 클래스
//  - 클라이언트 연결이 추가되었을 경우 : handlerAdded 메서드
//  - 클라이언트 연결이 끊어졌을 경우 : handlerRemoved
//	- 클라이언트가 메세지를 보냈을 경우 : channelRead0

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

	private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	Runnable loading = new Runnable() {
		public void run() {
			try {
				System.out.println("시간 지연");
				for(int i = 0; i < 5; i++) {
					Thread.sleep(1 * 1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub

		// 서버와 연결된 모든 Client에게 메세지를 전송해주기 위해 작성된 코
//		Channel incoming = ctx.channel();
//		for(Channel channel : channels) {
//			channel.writeAndFlush("[SERVER] - "+ incoming.remoteAddress() + "  연결됨 has joined!\n");
//		}
//		channels.add(ctx.channel());
		
		Channel incoming = ctx.channel();
		incoming.writeAndFlush("[Client] - "+ incoming.remoteAddress() + " 서버와 연결됨 \n");
		System.out.println("[SERVER] - "+ incoming.remoteAddress() + "  클라이언트와 연결됨 \n");
		channels.add(ctx.channel());
		
		
	}
	
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

		// 서버와 연결된 모든 Client에게 메세지를 전송해주기 위한 코드
//		Channel incoming = ctx.channel();
//		for(Channel channel : channels) {
//			channel.writeAndFlush("[SERVER] - "+incoming.remoteAddress() + "  연결 끊어짐 has left!!!!!!\n");
//		}
//		channels.remove(ctx.channel());
		
		Channel incoming = ctx.channel();
		incoming.writeAndFlush("[Client] - "+incoming.remoteAddress() + "  서버와 연결 끊어짐 !!!!!!\n");
		System.out.println("[SERVER] - "+ incoming.remoteAddress() + "  클라이언트와 연결 끊어짐 !!!!!!\n");
		channels.remove(ctx.channel());
		
	}
	
	

	@Override
	protected void channelRead0(ChannelHandlerContext arg0, String message) throws Exception {
		
		
		
		Channel incoming = arg0.channel();
		
//		for(Channel channel : channels) {
//			if(channel != incoming) {
//				channel.writeAndFlush("[" + incoming.remoteAddress() + "] 서버에서 전송된 내용 : " + message + "\n");				
//			}
//		}
		
		
		// 시간 지연을 위한 코드
		if(message.equals("1")) {
			
			System.out.println("[SERVER-Loading]-[" + incoming.remoteAddress() + "] : " + message + "\n");
			Thread load1 = new Thread(loading);
			load1.start();
			
			try {
				load1.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			incoming.writeAndFlush("[Client-Loading]-[" + incoming.remoteAddress() + "] : " + message + "\n");
			
			
		} else {
			incoming.writeAndFlush("[Client]-[" + incoming.remoteAddress() + "] : " + message + "\n");
			System.out.println("[SERVER]-[" + incoming.remoteAddress() + "] : " + message + "\n");
			
		}
		
		
		

	}

}
