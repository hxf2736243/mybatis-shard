# 环境搭建 #
## 一、 版本 ##
  * 1、MySQL 5.5
  * 2、JDK 1.6
  * 3、Maven 2.0.11
## 二、 安装JDK ##
> 从Oracle中拷贝下[JDK1.6](http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase6-419409.html)，然后按照。
> 安装目录：D:\JAVA\_TOOLS\JDK\_6
> 安装完成后需配置环境变量。
    * （1）JAVA\_HOME为 D:\JAVA\_TOOLS\JDK\_6
    * （2）Path新增 %JAVA\_HOME%\bin;
## 三、 安装maven ##
> > 从Apache官网下载下[maven](http://maven.apache.org/download.cgi) 2.0版本，然后解压拷贝到目录：D:\JAVA\_TOOLS中，
> > 设置MAVEN环境变量：
      * （1）MAVEN\_HOME ：D:\JAVA\_TOOLS\apache-maven-2.0.11
      * （2）Path新增 %MAVEN\_HOME%\bin;
## 四、安装SVN ##
> > > 从tortoisesvn官网下[SVN](http://tortoisesvn.net/downloads.html)安装。本人安装的是1.6.7版本。
> > > 执行安装即可
## 五、 安装eclipse ##

> > 从Eclipse官网下载[eclipse](http://www.eclipse.org/downloads/),下载完成之后打开eclipse。
有以下几件事情需完成：
      * （1）工程的默认编码方式设置
      * （2）eclipse的SVN插件subclipse安装
      * （3）eclipse的MAVEN插件m2eclipse安装
### 5.1：默认编码方式设置 ###

> 在eclipse的windows-->Preferences-->Workspace中设置为UTF-8。防止工程乱码
### 5.2：安装SVN插件 ###
> > subclipse：http://subclipse.tigris.org/update_1.6.
> > 在Help-->Install New Software 安装
### 5.3：安装m2eclipse插件 ###
> > m2eclipse：http://m2eclipse.sonatype.org/sites/m2e
> > 在Help-->Install New Software 安装

## 六、 安装MySQL ##

> 从MySQL官网下载[Mysql](http://www.mysql.com/downloads/)5.6版本，安装即可。