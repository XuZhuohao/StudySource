# NIO 与零拷贝
## 1. 基本介绍
- 零拷贝是网络编程的关键，很多性能优化都离不开
- Java 中，常用的零拷贝有，mmap(内存映射) 和 sendFile
- 零拷贝是指**没有 CPU 拷贝**



## 2. 零拷贝

### 2.1 传统 IO

```java
File file = new File("test.txt");
RandomAccessFile raf = new RandomAccessFile(file, "rw");

byte[] arr = new byte[(int)file.length()];
raf.read(arr);

Socket socket = new ServerSocket(8080).accept();
socket.getOutputStream().write(arr);
```



#### 2.1.1 文件拷贝和用户态、内核态切换

-  **Hard drive** --- DMA copy --->  **kernel buffer** --- CPU copy ---> **user buffer** --- CPU copy ---> **socket buffer** --- DMA copy ---> **protocol engine**
- DMA: direct memory access，直接内存拷贝（不使用 CPU）



### 2.2 mmap 优化

- mmap 通过内存映射，将文件映射到内核缓冲区，同时，**用户空间可以共享内核空间的数据**。这样，在进行网络传输时，可以减少内核空间到用户空间的拷贝次数
- 少了一次拷贝



### 2.3 sendFile 优化

- Linu2.1 中， SendFile ：数据根本不经过用户态，直接从内核缓冲区进入到 SocketBuffer，同时，由于和用户态完全无关，就减少了一次上下文切换，少一次拷贝，少一次切换。
- Linu2.4 中，只从 kernel buffer 拷贝少量数据到 socketBuffer 中，可以忽略不计。减去所有CPU拷贝

