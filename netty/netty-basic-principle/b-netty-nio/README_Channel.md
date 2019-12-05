# 通道 (channel)
## 1.基本介绍
### 1.1 与流的区别
NIO 的通道类似于流，但有些区别如下：
- 通道可以同时进行读写，而流只能读或者只能写  
- 通道可以实现异步读写数据  
- 通道可以从缓冲读数据，也可以写数据到缓冲  

### 1.2 Channel
- Channel 在 NIO 中式一个接口  
- 常用的类有： FileChannel, DatagramChannel, ServerSocketChannel 和 SocketChannel  
- FileChannel 用于文件的数据读写， DatagramChannel 用于 UDP 的数据读写， ServerSocketChannel 和 SocketChannel 用于 TCP 的数据读写  



## 2.子类
### 2.1 FileChannel
- FileChannel 主要用来对本地文件进行 IO 操作，常见的方法有  
- 相关方法  
    - public int read(ByteBuffer dst), 从通道读取数据并放到缓冲区中  
    - public int write(ByteBuffer src), 把缓冲区的数据写到通道中  
    - public long transferFrom(ReadableByteChannel src, long position, long count), 从目标通道中复制数据到当前通道  
    - public long transferTo(long position, long count, WritableByteChannel target), 把数据从当前通道复制给目标通道

### 2.2 ServerSocketChannel
- 在客户端监听新的客户端 Socket 连接  
- 相关方法
    - public static ServerSocketChannel open(); // 得到一个 ServerSocketChannel 通道  
    - public final ServerSocketChannel bind(SocketAddress local); // 设置服务器端口号  
    - public final SelectableChannel configureBlocking(boolean block); // 设置阻塞或者非阻塞模式， 取值 false 表示非阻塞  
    - public abstract SocketChannel accept(); // 接受一个连接，返回代表这个连接的通道对象  
    - public final SelectionKey register(Selector sel, int ops, Object att); // 注册一个选择器并设置监听事件  

### 2.3 SocketChannel
- 具体负责进行读写操作。nio 把缓冲区的数据写入通道，或者把通道里的数据读到缓冲区。
- 相关方法 
    - public static SocketChannel open(); // 得到一个 ServerSocketChannel 通道  
    - public final SocketChannel configureBlocking(boolean block); // 设置阻塞或者非阻塞模式， 取值 false 表示非阻塞  
    - public abstract boolean connect(SocketAddress remote); // 连接服务器  
    - public abstract boolean finishConnect(); // 如果上面的连接失败，接下来就要通过该方法完成连接操作  
    - public abstract int write(ByteBuffer src); // 往通道里面写数据  
    - public abstract int read(ByteBuffer dst); // 从通道里面读数据  
    - public final SelectionKey register(Selector sel, int ops, Object att); // 注册一个选择器并设置监听事件，最后一个参数可以设置共享数据  
    - public final void close(); // 关闭通道        