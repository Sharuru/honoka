on error resume next
set sysenv=CreateObject("WScript.Shell").Environment("system") 
Path = CreateObject("Scripting.FileSystemObject").GetFolder(".").Path

sysenv("PGHOME")="D:\fangj_20150714\pgsql"
sysenv("PGHOST")="localhost"
sysenv("Path")=sysenv("PGHOME")+"\bin;"+sysenv("Path")
sysenv("PGLIB")=sysenv("PGHOME")+"\lib"
sysenv("PGDATA")=sysenv("PGHOME")+"\data"
 
wscript.echo "PostgreSQL "


initdb.exe -D D:\fangj_20150714\pgsql\data -E UTF-8 --locale=jp -U postgres -W

pg_ctl.exe register -D D:\fangj_20150714\pgsql\data -NPostgreSQL

org.postgresql.Driver.class


java -jar mybatis-generator-core-x.x.x.jar -configfile generatorConfig.xml -overwrite
java -jar mybatis-generator-core-x.x.x.jar -configfile generatorConfig.xml
java -jar mybatis-generator-core-x.x.x.jar -configfile generatorConfig.xml -overwrite
java -cp mybatis-generator-core-x.x.x.jar org.mybatis.generator.api.ShellRunner -configfile generatorConfig.xml
java -cp mybatis-generator-core-1.3.2.jar org.mybatis.generator.api.ShellRunner -configfile generator.xml -overwrite


<a href="resources/html/02.html">02.html</a>
<a href="resources/html/03a.html">03a.html</a>
<a href="resources/html/03b.html">03b.html</a>
<a href="resources/html/03c.html">03c.html</a>
<a href="resources/html/05处理过个人信息.html">05处理过个人信息.html</a>
<a href="resources/html/06.html">06.html</a>
<a href="resources/html/07.html">07.html</a>
<a href="resources/html/07新建小组.html"></a>
<a href="resources/html/08.html"></a>
<a href="resources/html/08小组编辑.htm"></a>
<a href="resources/html/09.html"></a>
<a href="resources/html/09新建岗位.htm
<a href="resources/html/10.html"></a>
<a href="resources/html/11新建项目.htm
<a href="resources/html/12.html"></a>
<a href="resources/html/12项目添加成员.htm
<a href="resources/html/13.html"></a>
<a href="resources/html/14.html"></a>
<a href="resources/html/16.html"></a>
<a href="resources/html/17.html"></a>
<a href="resources/html/17add.html"></a>
<a href="resources/html/18.html"></a>
<a href="resources/html/18add.html"></a>
<a href="resources/html/19.html"></a>
<a href="resources/html/19田娃库——点击详细信息.htm
<a href="resources/html/20.html"></a>
<a href="resources/html/20田娃申请阶段——再申请前端录入（田娃库里已经有信息）.htm"></a>
<a href="resources/html/20田娃申请阶段——再申请前端录入（田娃库里已经有信息）.html"></a>
<a href="resources/html/20田娃申请阶段——新建申请（第一次申请）.html"></a>
<a href="resources/html/21.html"></a>
<a href="resources/html/22a.html"></a>
<a href="resources/html/22田娃审核阶段-面访（前端录入）.html"></a>
<a href="resources/html/23a.html"></a>
<a href="resources/html/23田娃审核阶段-走访（前端录入）.html"></a>
<a href="resources/html/24a.html"></a>
<a href="resources/html/24田娃审核阶段-电话回访（前端录入）.html"></a>
<a href="resources/html/25a.html"></a>
<a href="resources/html/25b.html"></a>
<a href="resources/html/26.html"></a>
<a href="resources/html/contentFrame.html"></a>
<a href="resources/html/leftMune.html"></a>
<a href="resources/html/leftMune2.html"></a>
<a href="resources/html/Login.html"></a>
<a href="resources/html/mainFrame.html"></a>
<a href="resources/html/navigation.html"></a>
<a href="resources/html/welcome.html"></a>