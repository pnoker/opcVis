@echo off
echo==========mybatis开始生成代码================
java -jar src/main/webapp/WEB-INF/lib/mybatis-generator-core-1.3.2.jar -configfile generatorConfig.xml -overwrite
echo==========mybatis生成代码完毕================
pause
