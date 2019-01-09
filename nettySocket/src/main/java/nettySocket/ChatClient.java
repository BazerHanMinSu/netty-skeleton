package nettySocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;



public class ChatClient { 
	
	// IP 주소 및 Port 설정
	public static void main(String[] args) throws Exception {
		new ChatClient("localhost", 8080).run();
	}
	
	private final String host;
	private final int port;
	
	public ChatClient(String host, int port) {
		
		this.host = host;
		this.port = port;
		
	}
	
	public void run() throws Exception {
		
		// I/O 동작을 다루는 멀티스레드 이벤트 루프이다. 
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			
			// Bootstrap은 어플리케이션 동작 및 설정을 지정해주는 클래스
			Bootstrap bootstrap = new Bootstrap()
					.group(group)
					.channel(NioSocketChannel.class)
					.handler(new ChatClientInitializer());
			
			// Channel은 읽기, 쓰기, 연결, 바인드(bind) 등의 I/O 작업을 할 수 있는 클래스이다.
			Channel channel = bootstrap.connect(host, port).sync().channel();
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				
				// Console 창에 작성한 코드 내용이 전달되는 부분
//				channel.writeAndFlush("ReadLine코드 : "+ in.readLine() + "\r\n");
				channel.writeAndFlush(in.readLine() + "\r\n");
				
			}
		}
		finally {
			
			group.shutdownGracefully();
		}
	}		
}
 