# JVM

## 类加载器子系统

### oop-Klass模型

oop：对象

klass：类元信息

MetaspaceObj

* Metadata
  * Klass：Java类在JVM中的表现形式C++代码
    * InstanceKlass：Java类的元信息（非数组）（方法区）
      * InstanceMirrorKlass：镜像类、Class对象（堆区）
      * InstanceRefKlass：引用
      * InstanceClassLoaderKlass：类加载
    * ArrayKlass 存储数组类的元信息
      * TypeArrayKlass：基本类型数组在JVM中存在形式 newarray，创建一个原始类型的数组
      * ObjArrayKlass：引用类型数组在JVM中存在形式 anewarray，创建一个引用类型的数组

> 类加载器将.class文件加载进系统，将.class文件解析，会生成类元信息，类元信息会存储在InstanceKlass中

> 对象的内存结构：markword、klass pointer、填充信息
>
> Java中的数组是动态数据类型
>
> ​	静态数据类型  JVM内置的，八种数据类型
>
> ​	动态数据类型（运行时生成）

### 类加载过程

* 加载
  * 步骤
    * 通过类的全限定名获取存储该类的class文件
    * 解析成运行时数据，即instanceKlass实例，存放在方法区
    * 在堆区生成该类的class对象非，即instanceMirrorKlass实例
  * 何时加载
    * new、
    * 反射
    * 初始化一个类的子类会去加载其父类
    * 启动类（main函数入口）
  * 从哪加载
    * jar、war
    * 网络
    * 动态生成（cglib）
    * 其他文件：JSP
    * 从数据库读取
    * 加密文件中读取

* 链接
  * 验证

    * 文件格式验证
    * 元数据验证
    * 字节码验证
    * 符号引用验证

  * 准备

    * 为静态变量分配内存、赋初值
    * 实例变量是在创建对象的时候完成赋值的，没有赋初值阶段
    * 准备阶段final类型修饰的变量直接赋值，没有赋初值阶段

  * 解析

    * 将常量池中的符号引用转为直接引用
  * 间接引用：指向运行时常量池的引用
    * 直接引用：内存地址
      * 常量池
        * 运行时常量池
        * class文件的常量池（静态）
        * 字符串常量池：StringTable
  
* 初始化
  * 执行静态代码块，完成静态变量的赋值
  * 静态字段、静态代码块，JVM也会生成一个clinit
    * 顺序：静态代码块 -> 静态字段

* 使用

* 销毁

### JVM加载类是懒加载模式（lazy loading）

* 类使用的时候才会加载
* 子类实例化、父类也会实例化
* 数组是JVM运行期间创建的
* 静态属性存储在InstanceMirrorKlass中

不同的类加载器在方法区有不同的空间

### 类加载器子系统与SPI

### 类加载器

1. bootstrap ClassLoader jre/lib/rt.jar
2. Extension ClassLoader jre/lib/ext/*.jar
3. Application ClassLoader classpath下
4. 自定义加载器  指定目录下

### 打破双亲委派机制

* 自定义类加载器（不委派）
* SPI机制

## 字节码文件

init：默认构造方法

clinit：如果有静态属性，或者静态代码块编译时会生成这个方法，只会生成一个

main

## 运行时数据区

### 内存池与JVM内存模型

## 执行引擎

JVM运行java程序的一套子系统



#### 两种解释器

字节码解释器：解释执行 java字节码交给c++执行再编译成硬编码（热点代码）

* 解释执行
* 跟即时编译器无关

模板解释器：编译执行 将java字节码文件编译成硬编码（热点代码）

* 执行的硬编码就是即时编译器编译好的硬编码



#### 三种运行模式

-Xint 纯字节码解释器

-Xcomp 纯模板解释器

-Xmixed 字节码解释器和模板解释器



如何理解java是半编译半解释语言

1、javac编译、java运行

2、字节码解释器解释执行、模板解释器编译执行

### JIT

just in time 即时编译器

#### C1编译器

-client模式启动，特点：

* 需要收集的数据较少，即达到触发编译的条件比较宽松
* 自带的编译优化的优化点较少
* 编译器教C2，没那么耗CPU，带来的结果是编译后生成的代码较C2编译器生成的代码效率低

#### C2编译器

-server模式启动（64位jvm只有server模式），特点：

* 需要收集的数据较多
* 编译时耗CPU
* 编译优化的点较多
* 编译后的代码较C1编译器生成的代码运行效率更高

#### 混合编译器

目前的-server模式启动，已经不再是纯粹的C2编译器。程序运行初期因为产生的数据较少，这时候执行C1编译器，运行一段时间后，收集到足够的数据，开始执行C2编译器



MAC 无法使用JIT，因为mac无法申请到一块可执行的内存

#### 热点代码缓冲区

热点代码保存在方法区，用于保存即时编译器编译后的热点代码（硬编码）

server 编译器模式下代码缓存大小则起始于 2496KB

client 编译器模式下代码缓存大小起始于 160KB

java -XX:+PrintFlagsFinal -version | grep InitialCodeCacheSize



#### 即时编译器的触发条件

* java -client -XX:+PrintFlagsFinal -version | grep CompileThreshold
* 即时编译的最小单位不是一个函数，而是代码块（for、while）
*  Client 编译器模式下，N 默认的值 1500
* Server 编译器模式下，N 默认的值则是 10000

#### 热度衰减

#### 阿里的热机切冷机故障

* 冷机：程序刚启动
* 热机：程序运行一段时间后
* 程序刚启动是C1编译器，运行一段时间后收集到足够多的信息后切换C2编译器，C2编译器会消耗大量的CPU资源，这个过程中流量瞬间变大，导致机器抗不住，热机状态，因为热点代码缓冲区的存在，运行效率更高，并发性个更好

#### 即时编译器是如何运行的

1、将即时编译任务放入队列（queue）

2、VM_THREAD从这个队列中获取任务，并运行



即时编译器的线程及调优

```
java -client -XX:+PrintFlagsFinal -version | grep CICompilerCount
-XX:CICompilerCount=N 
```

### 逃逸分析

#### 逃逸

逃逸是一种现象

##### 什么叫逃逸

* 共享变量
* 返回值
* 参数
* 这个对象是不是局部的

##### 逃到哪里去了

* 方法外
* 线程外

##### 什么叫不逃逸

* 对象的作用域是局部作用变量



#### 分析

一种技术手段，为什么要对逃逸进行分析，jvm对逃逸对象是没办法做优化的，只能对未逃逸对象做优化

##### 栈上分配

对象在堆区分配

对象在虚拟机栈上分配



-XX:+/-DoEscapeAnalysis

##### 标量替换

* 标量：不可再分，java中的基本类型就是标量
* 聚合量：可再分，对象

##### 锁消除

```
public void noEscape1(){
    synchronized (new Object()){ // 局部变量，不会逃逸，锁可消除
        System.out.println("hello");
    }
}
```

## JVM调优

### 字节基础

* 位(bit)

也叫比特位，计算最小的存储单位，简写为小写字母'b'

二进制的一位，每个0或1是一个bit

* 字节(Byte)

Byte是字节，也可写成byte，简写为大写字母'B'

1Byte = 8bit  1字节为8比特

既然叫字节，肯定和字符有关系，1个英文字符=1字节，一个汉字=2字节

因为1字节等于8比特（1B=8bit），比特有0和1两种

所以1个字节=2^8-1=255

即0x00~0xff，相当于可以用2个16进制字符表示一个字节



1byte=8bit 1bit=1b 1B=8b 1Kb = 1024B



根据1字节=8比特的换算方法，得出下载速度理论上应该是宽带的八分之一。

2M宽带的理论下载速度是 256KB

2Mb=2048kb  2048kb/8=256KB

10M宽带理论下载速度是 1280 KB

10*1024/8=1280



### 对象内存布局

* 对象头Mark Word
* 类型指针
* 数组对象
* 实例数据
* 对齐填充

### 指针压缩

64位机器一个指针占8byte，开启了指针压缩后，将一个oop压缩位4B

```
boolean 1B
byte   	1B
char  	2B
short   2B
int     4B
float   8B  ---> 4B 压缩后
double  8B
long    8B
```



指针压缩的实现原理

1、存储的时候，后三位0抹掉 （java中所有的对象都是8字节对齐的，8字节对齐的规律就是，后三位永远是0）

2、使用的时候，后三位填充0



4B = 32bit -> 2^32 = 4G

2^35 = 32G





## 垃圾收集器

### 垃圾回收算法

### 垃圾回收

## 面试题

## 工具

HSDB

Source Insight

jclasslib

## 书籍

周志明：《深入理解Java虚拟机》