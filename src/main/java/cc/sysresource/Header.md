<font face="SimSun" size=3>

- 可以替换:成 = 之后放到properties里，方便读取
- 告诉服务器编码方式，为了知道文件大小
- conn.setRequestProperty("Accept-Encoding", "identity");
- 这个请求头,会压缩文件
- Accept-Encoding= gzip, deflate 但是这个传输速度比较快，相比本地解压来说，本地可以忽略吗？测试试试
- 并不需要所有header，配置文件 灰色的可以删除

</font>