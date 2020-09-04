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

    * 伪静态变量分配内存、赋初值
    * 实例变量是在创建对象的时候完成赋值的，没有赋初值阶段
    * 准备阶段final类型修饰的变量直接赋值，没有赋初值阶段

    <img src="C:\Users\sqm\AppData\Roaming\Typora\typora-user-images\image-20200904165541334.png" alt="image-20200904165541334" style="zoom:80%;" />

  * 初始化

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

* 自定义类加载器

* SPI机制

## 运行时数据区

### 内存池与JVM内存模型

## 执行引擎

### JIT

### 逃逸分析

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