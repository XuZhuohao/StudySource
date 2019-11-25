## 1.NIO 基本介绍
1 java NIO (java non-blocking IO), begin from JDK1.4， 同步非阻塞   
2 package java.nio  
3 分为： Channel(通道), Buffer(缓冲区)， Selector(选择器)  
4 NIO 是面向**缓冲区，或者面向块**编程的。数据读取到一个它稍后处理的缓冲区，需要时可在缓冲区中前后移动，这就增加了处理过程中的灵活性，使用它可以提供非阻塞的高伸缩性网络  
5 NIO 的非阻塞模式，使一个线程从某通道发送请求或者读取数据，但是它仅能得到目前为止可用的数据，没有数据时不会保持线程阻塞，该线程继续其他处理  
6 HTTP2.0 使用了多路复用的技术，能做到一个连接并发处理多个请求，而且并发请求的数量比 HTTP1.1 大了好几个数量级  

## 2.NIO 与 BIO 的比较
1 BIO 以流的方式处理数据，而 NIO 以块的方式处理数据，块 I/O 的效率比流 I/O 高很多  
2 BIO 是阻塞的， NIO 则是非阻塞的  
3 BIO 基于字节流和字符流进行操作的，而 NIO 基于 Channel 和 Buffer 进行操作的，数据总是**从通道读取到缓冲区中**，或者**从缓冲区写入到通道中**。Selector(选择器)用于监听多个通道的事件(比如：连接请求，数据到达等)，因此使用**单个线程可以监听多个客户端通道**  

## 3.NIO 三大核心原理关系
1 每个channel都会对应一个Buffer  
2 Selector 对对应一个线程，一个线程对应多个channel（连接）  
3 程序程序切换到哪个channel是由事件决定的，Event 就是一个重要的概念  
4 Selector 会根据不同的事件，在各个通道上切换  
5 buffer 就是一个内存块，底层是有一个数组  
6 数据的读取写入是通过 Buffer，这个和 BIO，BIO 中要么是输入流，或者是输出流，不能双向，但是NIO的Buffer 是可以读也可以写，需要fip方法切换  
7 channel是双向的，可以返回底层操作系统的情况，比如Linux，底层的操作系统通道就是双向的

## 4.代码
### 4.1 应用实例1-本地文件写数据
实例要求：
1）使用前面学习后的ByteBuffer（缓冲）和FileChannel（通道），将“hello world”写入到file01.txt中  
2）文件不存在就创建  
3）{@link com.yui.study.netty.nio.NioFileChannel.case1}  

### 4.2 应用实例2-本地文件读数据
实例要求：
1）使用前面学习后的ByteBuffer（缓冲）和FileChannel（通道），将 file01.txt 中的数据读入到程序，并显示在控制台屏幕  
2）假定文件已经存在  
3）{@link com.yui.study.netty.nio.NioFileChannel.case2}  

### 4.3 应用实例3-使用一个Buffer完成文件读取实例要求：+
1）使用FileChannel（通道）和方法read，write，完成文件的拷贝
2）拷贝一个文本文件1.txt，放在项目下即可  
3）{@link com.yui.study.netty.nio.NioFileChannel.case3}  