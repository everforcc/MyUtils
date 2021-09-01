<font face="SimSun" size="3">

[TOC]

## 精度

- FDUtils 里面的问题
- 小数点后几位

## 字节

- 由于内存中是二进制的关系
- 数据类型转换截取的问题


## 面试题

~~~
short s = 1;
s = s + 1;//编译错误 右边的s会转换为int类型所以报错
s+ = 1;//正确 s=(short)(s+1)
~~~

</font>