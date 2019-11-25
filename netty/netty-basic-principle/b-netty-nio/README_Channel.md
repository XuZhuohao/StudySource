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
FileChannel 主要用来对本地文件进行 IO 操作，常见的方法有  
- public int read(ByteBuffer dst), 从通道读取数据并放到缓冲区中  
- public int write(ByteBuffer src), 把缓冲区的数据写到通道中  
- public long transferFrom(ReadableByteChannel src, long position, long count), 从目标通道中复制数据到当前通道  
- public long transferTo(long position, long count, WritableByteChannel target), 把数据从当前通道复制给目标通道

  