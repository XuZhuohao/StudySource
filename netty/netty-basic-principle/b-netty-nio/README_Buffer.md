# 缓冲区(Buffer)
## 1. 基本介绍
本质是一个可以读写数据的内存块  
可以理解为一个**容器对象(含数组)**  
Channel 提供从文件、网络读取数据的渠道，但是读取或写入的数据都必须经由 Buffer  

## 2. Buffer 类以及子类

- Buffer (java.nio)  
    - IntBuffer (java.nio)
    - FloatBuffer (java.nio)
    - CharBuffer (java.nio)
    - DoubleBuffer (java.nio)
    - ShortBuffer (java.nio)
    - LongBuffer (java.nio)
    - **ByteBuffer (java.nio)**
## 3. Buffer 的四个属性
```
    private int mark = -1;
    private int position = 0;
    private int limit;
    private int capacity;
```

| 属性| 描述 |  
| --- | --- |  
| Capacity | 容量，即可以容纳的最大数据量；在缓冲区创建时被设定并且不能改变 |  
| Limit | 表示缓冲区的当前终点，不能对缓冲区超过极限的位置进行读写操作。且极限是可以修改的 |  
| Position | 位置，下一个要被读或写的元素的索引，每次读写缓冲区数据时者都会改变改值，为下次读写作准备 |
| Mark | 标记 |

