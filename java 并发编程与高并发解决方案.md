# java 并发编程与高并发解决方案

## 1.3 并发与高并发解决方案

## 基本概念

> 并发：同时拥有两个或者多个线程  如果程序在单核处理器上运行,多个线程将交替的换入或者换出内存 这些现实是同时存在的 每个线程都处于执行过程的某个状态 如果运行在多核处理器上 此时 程序中的每个线程都将分配到一个处理器核上,因此 可以同时运行
>
> 高并发：高并发(High Concurrentcy)是互联网分布式系统架构设计中必须考虑的因素之一 他通常是指  通过设计包装系统能够**同时并行处理** 很多请求

​		并发：多个线程操作相同的资源 保证线程安全 合理使用资源

​		高并发：服务器能同时处理很多请求 提高程序性能

## 2.1 CPU多级缓存-缓存一致性

​		为什么需要CPU cache :CPU平率太快了 快到主存跟不上 这样在处理器的时钟周期内 CPU常常需要等待主存 浪费资源  所以cache的出现 是为了缓解CPU和内存之间的速度不匹配问题(结构:CPU->cache->memory)

	### CPU cache有什么意义

​		时间局部性：如果某个数据被访问，那么在不久的将来他很可能被再次访问

​		空间局部性：如果某个数据被访问,那么与他相邻的数据很快也可能被访问;

	### 缓存一致性(MESI)

​	用于保证多个CPU cache之间缓存共享数据的一致

	### 乱序执行优化

CPU为了提高运算速度而作为违背代码原有顺序的优化

### Java内存模型（Java Memory Model JMM）

![5](G:\BaiduNetdiskDownload\32 Java并发编程与高并发解决方案(完整)\[www.zxit8.com]  Java并发课程资料\课程资料\相关图例\5.jpg)

![6](G:\BaiduNetdiskDownload\32 Java并发编程与高并发解决方案(完整)\[www.zxit8.com]  Java并发课程资料\课程资料\相关图例\6.jpg)

CPU  寄存器  高速缓存  主存

![8](G:\BaiduNetdiskDownload\32 Java并发编程与高并发解决方案(完整)\[www.zxit8.com]  Java并发课程资料\课程资料\相关图例\8.jpg)

###同步八种操作

lock(锁定):作用于主内存的变量 把一个变量标识为一个线程独占状态

unlock(解锁):作用于主内存的变量,把一个处于锁定状态的变量释放出来,释放后的变量才可以被其他线程锁定

read(读取):作用于主内存的变量,把一个变量从主内存传送到线程的工作内存中去 以便随后的 load 动作使用

load(载入):作用于工作内存的变量 他把read操作从主内存中得到的变量值放入到工作内存的变量副本中

use(使用):作用于工作内存的变量 把工作内存中的一个变量值传递给执行引擎

assign(赋值):作用于工作内存的变量 他把一个从执行引起接收到的赋值给工作内存的变量

store(存储);作用于工作内存的变量把工作内存中的一个变量的值传送到主内存中 以便随后的write操作

write(写入):作用于组内存的变量,他把store操作从工作内存中的一个变量的值传送到主内存的变量中

### 同步规则

如果要把一个变量从主内存中复制到工作内存，就需要按照顺序的执行read和load操作 如果把变量从工作内存同步回主内存中，就要按顺序的执行store和write操作 。但java内存模型只是要求上述操作必须顺序执行 而没有保证必须是连续执行

不允许read和load ，store和write操作之一单独出现

不允许一个线程丢弃他最近assign的操作 即变量在工作内存中改变了之后必须同步到主内存中

不允许一个线程无原因的(没有发生过任何assign操作)把数据从工作内存同步回主内存中

一个新的变量只能在主内存中诞生 不允许在工作内存中直接受用一个未被初始化(load和assign)的变量 即就是对一个变量实时use和store操作之前 必须执行过了assign和load操作

一个变量在同一时刻只允许一个线程对其进行lock操作 但是lock操作可以被同一个线程重新执行多次 多次执行lock后 只有执行相同次数的unlock操作 变量才会被解锁 lock与unlock必须成对出现

如果对一个变量执行lock操作 将会清空工作内存中此变量的值，在执行引擎使用这个变量钱需要重新执行load或assign操作初始化变量的值

如果一个变量事先没有被lock操作锁定 则不允许对他执行unlock操作 也不允许去unlock一个被其他线程锁定的变量

对一个变量执行unlock操作之前 必须把此变量同步到主内存中（执行store和write操作）

![9](G:\BaiduNetdiskDownload\32 Java并发编程与高并发解决方案(完整)\[www.zxit8.com]  Java并发课程资料\课程资料\相关图例\9.jpg)



## 4.1线程安全性

	### 线程安全性

​	定义:当多个线程访问某个类时 不管运行时环境采用何种调度方式或者这个进行将如何交替执行 不切子主调代码中不需要额外的同步或者协调 这个类都能表现出正确的行为 那么就称这个类时线程安全的

原子性：提供了互斥访问 同一个时刻只能有一个线程来对他进行操作

可见性:一个线程对主内存的修改可以及时的被其他线程观察到

有序性:一个线程观察其他线程中的执行执行顺序 由于指令重排序的存在 该结果一般杂乱无序

### 原子性 Atomic包

AtomicXXX: CAS ,Unsafe.compareAndSwapInt 

```java
public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

        return var5;
    }
```



AtomicLong 

LongAdder :热点数据分离





## 5.1 发布对象

### 发布对象

​	使一个对象能够被当前范围之外的代码所使用

### 对象逸出

​	一种错误的发布。当一个对象还没有构造完成时 就是他被其他线程所见



### 安全发布对象

​	在静态初始化函数中初始化一个对象引用

​	将对象的引用保存到volatile类型域或者AtomicReference对象中

​	将对象的引用保存到某个正确构造对象的final类型域中

​	将对象的引用保存到一个由锁保护的域中

```java
public class SingletonExample6 {
    private SingletonExample6() {
    }

    static {
        instance = new SingletonExample6();
    }

    private static SingletonExample6 instance = null;

    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(instance);
        System.out.println(instance);
    }
}
//这样打印的值为null
因为先执行静态代码块 后 执执行静态域的初始化

public class SingletonExample6 {
    private SingletonExample6() {
    }

 private static SingletonExample6 instance = null;
 
    static {
        instance = new SingletonExample6();
    }

   

    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(instance);
        System.out.println(instance);
    }
}

```





## 6.1 不可变对象

	### 不可变对象需要满足的条件

​		对象创建以后其状态就是不能修改

​		对象所有域都是final类型

​		对象是正确创建的(在对象创建期间,this引用没有逸出)

**final关键字:类 方法 变量**

​	修饰类：不能被继承(其所有方法都隐式的指定为final方法)

​	修饰方法：1 锁定方法不被继承类修改 2 效率(一个类的private 方法被隐式的指定为final方法)

​	修饰变量：基本数据类型变量 引用类型变量



### 不可变对象

Collections.unmodifiablexxx   Collection，list  Set  Map 



Guava:ImmutableXXX: Collection ，List ， Set，Map

## 



### 线程封闭

​	Ad-hoc线程封闭：程序控制实现 最糟糕 忽略

​	堆栈封闭：局部变量 无并发问题

​	ThreadLocal线程封闭：特别好的封闭方法



### 线程不安全的类与写法

​	StringBuilder -->StringBuffer

​	SimpleDateFormate->jodaTime

​	ArrayList，HashSet，HashMap等Collection 





### 线程安全--同步容器

ArrayList->Vector,Stack

HashMap->HashTable(key,value不能为null)

Collections.synchronizedXXX(List,Set,Map)

java.util.ConcurrentModificationException 



### 线程安全-并发容器JUC

ArrayList->CopyOnWriteArrayList

HashSet,TreeSet->CopyOnWriteArraySet ConcurrentSkipListSet

HashMap,TreeMap->ConcurrentHashMap ConcurrentSkipListMap(排序)

![-2](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\-2.png)

### 安全共享对象的策略

​	线程限制:一个呗线程限制的对象 由线程独占 并且只能被占有她的线程修改

​	共享只读：一个共享只读的对象，在没有额外同步的情况下 可以被多个线程并发访问 但是任何线程都不能修改他

​	线程安全对象:一个线程安全的对象或者容器 在内部通过同步机制来保证线程安全 所以其他线程无需额外的同步就可以通过公共接口随意访问他

​	被守护对象：被守护对象只能通过获取特定的锁来访问





### CountDownLatch

### Semaphore

### CyclicBarrier

> cyclicbarrier 计数器可以使用多次
>
> ​		多个线程之间相互等待
>
> CountDownLatch计时器只能使用一次
>
> ​	一个或多个线程等待其他线程
>
> 

### ReentrantLock 与锁

ReentrantLock(可重入锁)和synchronized的区别

​	可重入性

​	锁的实现

​	性能的区别

​	功能区别

​	

reentrantLock独有的功能

​	可以指定是公平锁还是非公平锁

​	提供了一个Condition类 可以分组唤醒需要唤醒的线程

​	提供能够中的等待锁的线程的机制 lock.lockInterruptibly();

ReentrantReadWriteLock























