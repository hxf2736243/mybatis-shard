## mybatis-shard 是什么 ##
mybatis-shard是一个基于mybatis spring 的分库分表框架，它可用来做下面的事情：<br>
<ul><li>数据库<b>分表</b>
</li><li>数据库<b>分库</b>
</li><li>数据库<b>读写分离</b>
</li><li>数据库<b>多DB路由</b></li></ul>

目前互联网DB部署大致如图：<br>
<img src='http://img01.taobaocdn.com/bao/uploaded/i1/14839030662043263/T17qIQFaxdXXXXXXXX_!!723064839-2-pix.png_570x10000.jpg' />
<br>
如果你的项目数据的分割大致类似上图，那么本框架很荣幸对你有点参考价值。<br>
<br>
<br>
<h2>参与开发</h2>
<blockquote><h3>浏览源码</h3>
<blockquote>mybatis-shard的源码被托管在google code。你可以从浏览器中直接查看mybatis-shard的全部源代码：<br>
<code>http://code.google.com/p/mybatis-shard/source/browse/</code>
</blockquote><h3>取得源码</h3>
你需要安装<a href='http://tortoisesvn.net/downloads.html'>SVN</a>。然后用下面的命令取得所有可编译的源代码：<br>
<code>svn checkout http://mybatis-shard.googlecode.com/svn/trunk</code>
<h3>编译源码</h3>
你需要安装<a href='http://maven.apache.org/'>Maven</a>和<a href='http://www.oracle.com/technetwork/java/javase/downloads/index.html'>JDK1.6</a>。然后用下面的命令编译所有的源代码：<br>
<pre><code>cd mybatis-shard<br>
mvn clean install<br>
</code></pre></blockquote>

获取更多，请查看<a href='UserGuide.md'>UserGuide</a>