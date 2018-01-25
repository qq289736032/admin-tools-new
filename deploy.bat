@echo off
echo 输入选项dev intranet test  zhanshen
set /p var=请输入: 
echo 您输入的为%var%   
cmd /k mvn  clean package -Dmaven.test.skip=true -P%var%
pause