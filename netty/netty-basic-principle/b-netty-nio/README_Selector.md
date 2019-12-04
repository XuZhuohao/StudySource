# selector
## 1.基本介绍
1) Java的NIO，用非阻塞的I0方式。可以用一个线程，处理多个的客户端连接，就会使用到Selector（选择器）  
2) Selector 能够检测多个注册的通道上是否有事件发生（注意：多个Channel以事件的方式可以注册到同一个Selector），如果有事件发生，便获取事件然后针对每个事件进行相应的处理。这样就可以只用一个单线程去管理多个通道，也就是管理多个连接和请求。  
3) 只有在连接真正有读写事件发生时，才会进行读写，就大大地减少了系统开销，并且不必为每个连接都创建一个线程，不用去维护多个线程  
4) 避免了多线程之间的上下文切换导致的开销

## 2.特点说明
1) Netty 的IO线程 NioEventLoop 聚合了 Selector（选择器，也叫多路复用器），可以同时并发处理成百上千个客户端连接。  
2) 当线程从某客户端 Socket 通道进行读写数据时，若没有数据可用时，该线程可以进行其他任务。  
3) 线程通常将非阻塞IO的空闲时间用于在其他通道上执行l0操作，所以单独的线程可以管理多个输入和输出通道。  
4) 由于读写操作都是非阻塞的，这就可以充分提升10线程的运行效率，避免由于频繁I/O阻塞导致的线程挂起。  
5) 一个I/O线程可以并发处理N个客户端连接和读写操作，这从根本上解决了传统同步阻塞I/O一连接一线程模型，架构的性能、弹性伸缩能力和可靠性都得到了极大的提升。    

## 3.类相关方法(@link java.nio.channels.Selector)
- {@link java.nio.channels.Selector.open}  
得到一个选择器对象  
- {@link java.nio.channels.Selector.select()} or {@link java.nio.channels.Selector.select(long)}  
监控所有注册的通道，当其中有 IO 操作可以进行时，将对应的 SelectionKey 加入到内部集合中并返回，参数设置超时时间    
- {@link java.nio.channels.Selector.selectedKeys}  
从内部集合中得到所有的 SelectionKey  

## 4.注意事项
1) NIO中的ServerSocketChannel功能类似ServerSocket，SocketChannel功能类似Socket  
2) selector 相关方法说明  
selector.select()//阻塞  
selector.select(1000);//阻塞1000毫秒，在1000毫秒后返回  
selector.wakeup();//唤醒selector selector.selectNow();//不阻塞，立马返还
selector.selectNow(); // 不阻塞，立马返还  
3) 只有设置为非阻塞才能向 selector 注册，否则 java.nio.channels.IllegalBlockingModeException  

## 5.NIO 非阻塞网络编程原理分析图 
1) 当客户端连接时，会通过 ServerSocketChannel 得到 SocketChannel    
2) 将 SocketChannel 注册到 Selector 上 register(Selector sel, int ops, Object att), selector 上可以注册多个 SocketChannel  
3) 注册后返回一个 SelectionKey ，会和该 Selector 关联（集合）  
4) Selector 进行监听 select 方法，返回有事件发生的通道的个数  
5) 进一步得到各个 SelectionKey(有事件发生)  
6) 在通过 SelectionKey 反向获取到 SocketChannel ,方法 channel()  
7) 可以通过得到的 channel，完成业务处理  

## 6.案例
- 案例要求：  
1）编写一个NIO入门案例，实现服务器端和客户端之间的数据简单通讯（非阻塞）  
2）目的：理解NIO非阻塞网络编程机制  
3）{@link }  