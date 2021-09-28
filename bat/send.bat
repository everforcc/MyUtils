@echo off
echo "copy file"
rem 拷贝的文件名称
set name=dir
rem 拷贝文件的目录
set folder=C:\test\
rem 当前目录
set current=C:\test\
rem if exist %current%%name% del %current%%name%

if exist %current%%name% echo "find file start copy"

rem xcopy %folder%%name% %current%

rem pscp -r 51Shanjian!@# %current%%name% root@42.192.18.126:/test/
rem 从前者copy到后者
pscp  -pw 51Shanjian!@# -r %current%%name% root@42.192.18.126:/test/

@cmd.exe
exist