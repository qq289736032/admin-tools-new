@echo off
echo ����ѡ��dev intranet test  zhanshen
set /p var=������: 
echo �������Ϊ%var%   
cmd /k mvn  clean package -Dmaven.test.skip=true -P%var%
pause