@echo off
PATH=D:\cygwin;D:\cygwin\bin;C:\WINDOWS\system32
cygpath %~dp0 >zbuild.ini
set  /P TARGET=<zbuild.ini
del zbuild.ini
D:
cd \android-ndk-r6b
bash ndk-build -C %1 %2  %TARGET%  -B
@echo on
pause