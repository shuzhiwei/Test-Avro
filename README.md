# Test-Avro
利用avsc文件生成对象的实现步骤：
1. 创建maven工程
2. 引入avro依赖的jar包
3. 根据pom.xml文件的配置，在工程下建立src\main\avro 源目录
4. 定义User类模式文件,user.avsc，（avro的所有schema文件都放在src\main\avro目录下）便于管理
5. 利用maven的阶段指令（phase）generate-sources，avro的插件会扫描src\main\avro目录下的所有schema文件，此时user.avsc生成对应的User对象
6. 利用AVRO的API实现对象的序列化和反序列化
