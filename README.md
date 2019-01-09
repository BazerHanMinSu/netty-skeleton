# netty-skeleton

# Netty란?

- 네트워크 어플리케이션을 빠르고 쉽게 개발하는 것을 가은하게 해주는 NIO 클라이언트 서버 프레임워크이다.
- 참고 : [http://sshinbdev.tistory.com/6](http://sshinbdev.tistory.com/6)



# Netty 소켓 사용 예제

- Netty 설치 및 채팅 구현 : [https://www.youtube.com/watch?v=tsz-assb1X8](https://www.youtube.com/watch?v=tsz-assb1X8)
    - 영상 : [https://www.youtube.com/watch?v=tsz-assb1X8](https://www.youtube.com/watch?v=tsz-assb1X8)

        - 일부 코드 수정필요

          1) ChatServerHandler 클래스
              - new DefaultChannelGroup(); --> 부분을 아래와 같이 수정한다.
              - private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

          2) ChatClientHandler, ChatServerHandler 클래스
              - SimpleChannelInboundHandler 사용할 것. 
              - ChannelInboundMessageHandlerAdapter는 사용할 수 없다.

          3) 모든 클래스 
              - 메세지 전송 시, write(...) 함수 대신에 writeAndFlush(...)를 사용할 것

    - 코드 : [https://github.com/netty/netty/tree/4.1/example/src/main/java/io/netty/example/securechat](https://github.com/netty/netty/tree/4.1/example/src/main/java/io/netty/example/securechat)
