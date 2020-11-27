package com.yui.study.reference;

import com.yui.study.example.dto.AccountDto;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 四大引用简单使用
 *
 * @author XuZhuohao
 * @date 2020/05/26
 */
public class BaseUseReference {
    public static void main(String[] args) {
//        strongReference();
//        softReference();
//        weakReference();
        phantomReference();
    }

    /**
     * vm 参数： -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails
     * 强引用：
     * java.lang.OutOfMemoryError
     */
    public static void strongReference() {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            list.add(new byte[1024 * 1024]);
        }
    }

    /**
     * 软引用
     * 1.软引用，当GC的时候，如果GC Root可达，如果内存足够，就不会被回收；
     * 如果内存不够用，会被回收。不会 OOM
     * 2.应用于缓存。
     */
    public static void softReference() {
        List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024]);
            list.add(softReference);
            System.out.println("insert data:" + softReference.get());
        }
        // 触发 gc ，之后被回收，剩下未被回收的数据
        list.forEach(temp -> System.out.println("before gc:" + temp.get()));
        // 主动 gc 时，内存足够，数据没有变化
        System.gc();
        list.forEach(temp -> System.out.println("after gc:" + temp.get()));
    }

    /**
     * 弱引用
     * 1.软引用，只要这个对象发生GC，就会被回收。
     * 2.WeakHashMap
     * 3.ThreadLocalMap ThreadLocal
     * 4.Tomcat ConcurrentCache ConcurrentHashMap
     */
    public static void weakReference() {
        List<WeakReference<byte[]>> list = new LinkedList<>();
        for (int i = 0; i < 21; i++) {
            WeakReference<byte[]> weakReference = new WeakReference<>(new byte[1024 * 1024]);
            list.add(weakReference);
            System.out.println("insert data:" + weakReference.get());
        }
        // gc 之后已存在的全部被回收，剩下后边插入未被 gc 的数据
        list.forEach(temp -> System.out.println("before gc:" + temp.get()));
        // 主动 gc 之后全部被回收
        System.gc();
        list.forEach(temp -> System.out.println("after gc:" + temp.get()));
    }

//    public static final ReferenceQueue<AccountDto> queue = new ReferenceQueue<>();
//    public static final List<byte[]> list = new ArrayList<>();

    public static void phantomReference() {
        ReferenceQueue<AccountDto> queue = new ReferenceQueue<>();
        List<byte[]> list = new ArrayList<>();

        String test01 = "测试对象1";
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("测试对象1");
        PhantomReference<AccountDto> reference = new PhantomReference<>(accountDto, queue);
        System.out.println(reference.get());
        new Thread(() -> {
            while (true) {
                Reference<? extends AccountDto> poll = queue.poll();
                if (poll != null) {
                    System.out.println("############对象被回收了： " + poll.get());
                    break;
                }
            }

        }).start();


        new Thread(() -> {
            while (true) {
                list.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                AccountDto s = reference.get();
                System.out.println("正常输出:" + s);
            }
        }).start();
        // 是引用失效，让gc回收实例，触发通知
        accountDto = null;
        System.out.println("test-end");
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
