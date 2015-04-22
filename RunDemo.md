# 编译运行 #
## 一、下载源码 ##
  * 从 Google Code SVN 中检出源代码到你本地磁盘
> > 项目的checkout URL为：http://mybatis-shard.googlecode.com/svn/trunk/
  * 配置Maven本地仓库配置settings.xml，我配置的如下：
```
<?xml version="1.0" encoding="UTF-8"?>
<settings>
	  <localRepository>D:/JAVA_TOOLS/repository</localRepository>
      <mirrors>  
       <mirror>  
         <id>oss.sonatype.org</id>  
         <name>oss.sonatype.org</name>  
         <url>http://oss.sonatype.org/content/repositories/releases</url>  
         <mirrorOf>central</mirrorOf>  
       </mirror>  
      </mirrors>  
</settings>
```
此步到此为止。

## 二、导入eclispe ##
开发基本步骤：
  * （1）编译,执行 mvn clean install -Dmaven.test.skip=true 安装到本地maven 仓库
  * （2）eclipse工程化,执行mvn eclipse:clean eclipse:eclipse使MAVEN工程转换成eclipse标准工程

注意如果工程导入eclipse飘红了，说明eclipse配置出现问题，设置下m2eclipse的配置。
在路径windows-->Preferences-->Maven---> User Setting中将配置指向你系统Maven的setting.xml
成功之后，工程的结构如图：<br>
<img src='http://img01.taobaocdn.com/bao/uploaded/i1/14839030631177145/T1GkMYFkFbXXXXXXXX_!!723064839-0-pix.jpg_570x10000.jpg' />

<h2>三、运行工程</h2>
在没本地MySql的情况只能运行如下demo：<br>
<img src='http://img02.taobaocdn.com/bao/uploaded/i2/14839030690566754/T1TgU3Fc4XXXXXXXXX_!!723064839-0-pix.jpg_570x10000.jpg' />
<br>
<ul><li>ModRouteTest 和 ModRouteTest02 测试ModRoute<br>
</li><li>MapRouteTest 测试 MapRoute<br>
</li><li>DBRouteUtilsTest 测试DBRouteUtils<br>
</li><li>ModMonthRouteTest 测试ModMonthRouteTest</li></ul>

其它的复杂Demo请前往查看<br>
<ul><li><a href='Tutorial03.md'>Tutorial03 </a>
</li><li><a href='Tutorial04.md'>Tutorial04</a>