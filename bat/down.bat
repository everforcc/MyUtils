@echo off
echo "copy file"
rem 拷贝的文件名称
set name=dir
rem 拷贝文件的目录
set folder=C:\test\
rem 当前目录
set current=C:\test\down
rem if exist %current%%name% del %current%%name%

if exist %current%%name% echo "find file start"

rem xcopy %folder%%name% %current%

rem pscp -r 51Shanjian!@# %current%%name% root@42.192.18.126:/test/
pscp  -pw 51Shanjian!@# -r root@42.192.18.126:/test/ %current%%name%

@cmd.exe
exist