# DDLConverter

将DDL中的建表字段转换成驼峰式的json格式（可以加入自定义前缀和后缀）

## 原理

正则表达式获取``中的内容再转换，因此input复制的范围有所限制

## 使用例

启动程序后复制如图内容 （第一个字段开始，最后一个字段结束）

- 不包含Create Table(因为表名有``)
- 不包含Key的信息(因为key有``)

![image-20231116111230909](image-20231116111230909.png)

![image-20231116111543433](image-20231116111543433.png)