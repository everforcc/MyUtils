<font face="SimSun" size=3 >

[TOC]

基础数据类型

---

|数据类型|描述|边界|空间|默认值
| ---- | ---- | ---- | ---- | ---- |
|byte|8位，有符号,以二进制补码表示的整数|127,-128|大型数组节约，只有int的1/4| 0 |
|short|16位，有符号,以二进制补码表示的整数|32767,-32768|大型数组节约，只有int的1/2| 0 |
|int|32位，有符号,以二进制补码表示的整数|2^31-1,2147483647,-2147483648(十位数)| 基准| 0 |
|long|64位，有符号,以二进制补码表示的整数|2^63 -1,-2^63 (19位数) | 这种类型主要使用在需要比较大整数的系统上| 0L |
|float|单精度,32位，符合IEEE 754标准的浮点数| 十位数，小数点后8超过变科学计数法| 在储存大型浮点数组的时候可节省内存空间| 0.0f |
|double|双精度、64 位、符合 IEEE 754 标准的浮点数| 19位数，小数点16超过变科学计数法| | 0.0d |
|boolean|一位|true,false| | false |
|char|单一的 16 位 Unicode 字符|\u0000（十进制等效值为 0）,\uffff（即为 65535）| 可以储存任何字符 | 无，需要初始化 |

- 单精度 二进制 1符号位，8位指数，23位小数
- 双精度 二进制 1符号位，11位指数，52位小数

## 码

- [参考地址](https://blog.csdn.net/weixin_37870009/article/details/79775926)
- 二进制首位1负数，0正数

### 原码

- int下的
~~~
5的原码：00000000 00000000 00000000 00000101
-5的原码:10000000 00000000 00000000 00000101
~~~

### 反码

- 正数: 原码即为其反码
~~~
5的原码：00000000 00000000 00000000 00000101
5的反码：00000000 00000000 00000000 00000101
~~~~

- 负数: 需要将原码除符号位以外的位数取反
~~~
-5的原码:10000000 00000000 00000000 00000101
-5的反码：11111111 11111111 11111111 11111010
~~~

### 补码

- 正数: 原码即为其补码
- 负数: 补码是反码加1
~~~
5的原码：00000000 00000000 00000000 00000101
5的补码：00000000 00000000 00000000 00000101
~~~
- 
~~~
-5的原码:10000000 00000000 00000000 00000101
-5的补码：11111111 11111111 11111111 11111011
~~~

<font color="red"> Java中所有基本数据类型均使用该数字的补码进行表示，所以在Java中测试5与-5的输出我们能看到这样的结果：</font>

~~~
System.out.println(Integer.toBinaryString(5));
System.out.println(Integer.toBinaryString(-5));
101 // 前面的0被省略
11111111111111111111111111111011
~~~

## 字符集

- code

</font>