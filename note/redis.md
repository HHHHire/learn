# redis

> 数据类型：string(字符串)、hash(哈希)、list(列表)、set(集合)、zset(有序集合)

* del key 

* exists key 检查给定 key 是否存在。

* expire key seconds 为给定 key 设置过期时间（以秒计）。

* ttl key 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)

* keys pattern 查找所有符合给定模式( pattern)的 key 。 

  keys 通配符     获取所有与pattern匹配的key,返回所有与该匹配 

    通配符： 

  ​         \*  代表所有 

  ​         ？ 表示代表一个字符 

* move key db

* type key



### String

**赋值语法：**

* SET KEY_NAME   VALUE

Redis SET 命令用于设置给定 key 的值。如果 key 已经存储值， SET 就覆写旧值，且无视类型 



* SETNX key value  //解决分布式锁 方案之一

只有在 key 不存在时设置 key 的值。Setnx（**SET** if **N**ot e**X**ists） 命令在指定的 key 不存在时，为 key 设置指定的值



* MSET key value [key value ...]

同时设置一个或多个 key-value 对



**取值语法：** 

* GET KEY_NAME

Redis GET命令用于获取指定 key 的值。如果 key 不存在，返回 nil 。如果key 储存的值不是字符串类型，返回一个错误。 



* GETRANGE key start end 

用于获取存储在指定 key 中字符串的子字符串。字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)



* GETBIT key offset

对 key 所储存的字符串值，获取指定偏移量上的位(bit)



* MGET key1 [key2..]

获取所有(一个或多个)给定 key 的值



* GETSET语法：  GETSET  KEY_NAME  VALUE

 Getset 命令用于设置指定 key 的值，并返回 key 的旧值,当 key 不存在时，返回 nil 



* STRLEN key

返回 key 所储存的字符串值的长度



**删除语法：**

* DEL KEY_Name 

删除指定的KEY，如果存在，返回值数字类型。 



**自增/自减：**

* INCR KEY_Name

Incr 命令将 key 中储存的数字值增1。如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作 

**自增：INCRBY KEY_Name 增量值** 

 Incrby 命令将 key 中储存的数字加上指定的增量值 

**自减：DECR KEY_NAME       或    DECYBY KEY_NAME 减值** 

 decR 命令将 key 中储存的数字减1



字符串拼接：APPEND  KEY_NAME VALUE 

 Append 命令用于为指定的 key 追加至未尾，如果不存在，为其赋值 





***********

### hash

**赋值语法：** 

  HSET KEY  FIELD  VALUE    //为指定的KEY，设定FILD/VALUE  

 **HMSET  KEY  FIELD VALUE [FIELD1,VALUE1]****…… 同时将多个 field-value (域-值)对设置到哈希表 key 中。** 

![img](https://note.youdao.com/yws/public/resource/91be86b8ffcf6144671e0d1bfe3c8952/xmlnote/OFFICE47DA25EADA0F4A5393A9B056C405CFE2/6260)



**取值语法：** 

  HGET KEY FIELD   //获取存储在HASH中的值，根据FIELD得到VALUE

  HMGET key field[field1]      //获取key所有给定字段的值 

  HGETALL key                 //返回HASH表中所有的字段和值 

​	

HKEYS key   //获取所有哈希表中的字段

HLEN key   //获取哈希表中字段的数量



**删除语法：** 

   HDEL KEY field1[field2]    //删除一个或多个HASH表字段 



**其它语法：**

HSETNX key field value 

只有在字段 field 不存在时，设置哈希表字段的值



HINCRBY key field increment 

为哈希表 key 中的指定字段的整数值加上增量 increment 。



HINCRBYFLOAT key field increment  

为哈希表 key 中的指定字段的浮点数值加上增量 increment 。



HEXISTS key field  //查看哈希表 key 中，指定的字段是否存在

****************

### List

**赋值语法：** 



LPUSH key value1 [value2]  //将一个或多个值插入到列表头部(从左侧添加)

RPUSH key value1 [value2]  //在列表中添加一个或多个值(从右侧添加)

LPUSHX key value   //将一个值插入到已存在的列表头部。如果列表不在，操作无效

RPUSHX key value   //一个值插入已存在的列表尾部(最右边)。如果列表不在，操作无效。



**取值语法：** 

LLEN key      //获取列表长度

LINDEX key index   //通过索引获取列表中的元素

LRANGE key start stop  //获取列表指定范围内的元素



描述： 返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。 其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。

**start:  页大小\*(页数-1)**  

**stop : (页大小\*页数)-1**



**删除语法：**

LPOP key  移出并获取列表的第一个元素(从左侧删除)

RPOP key  移除列表的最后一个元素，返回值为移除的元素(从右侧删除)



BLPOP key1 [key2 ] timeout 

移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。

**实例**

redis 127.0.0.1:6379> BLPOP list1 100

在以上实例中，操作会被阻塞，如果指定的列表 key list1 存在数据则会返回第一个元素，否则在等待100秒后会返回 nil 。



BRPOP key1 [key2 ] timeout 

移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。



LTRIM key start stop   对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。





**修改语法：**



LSET key index value  通过索引设置列表元素的值

LINSERT key BEFORE|AFTER world value 在列表的元素前或者后插入元素

描述：将值 value 插入到列表 key 当中，位于值 world 之前或之后。



**高级语法：**



RPOPLPUSH source destination 

移除列表的最后一个元素，并将该元素添加到另一个列表并返回	 **示例描述**：

RPOPLPUSH a1  a2   //a1的最后元素移到a2的左侧

RPOPLPUSH a1  a1  //循环列表，将最后元素移到最左侧



BRPOPLPUSH source destination timeout 

从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。

********

### set

​	**赋值语法：** 

  [SADD key member1 [member2\]](http://www.runoob.com/redis/sets-sadd.html) 向集合添加一个或多个成员 



**取值语法：** 

  [SCARD key](http://www.runoob.com/redis/sets-scard.html) 获取集合的成员数 

  [SMEMBERS key](http://www.runoob.com/redis/sets-smembers.html)  返回集合中的所有成员 

  [SISMEMBER key member](http://www.runoob.com/redis/sets-sismember.html)  判断 member 元素是否是集合 key 的成员(开发中：验证是否存在判断） 

  [SRANDMEMBER key [count\]](http://www.runoob.com/redis/sets-srandmember.html) 返回集合中一个或多个随机数	   

  

**删除语法：**  

SREM key member1 [member2] 移除集合中一个或多个成员

SPOP key [count]  移除并返回集合中的一个随机元素

SMOVE source destination member 

将 member 元素从 source 集合移动到 destination 集合



**差集语法：** 

   [SDIFF key1  [key2\]](http://www.runoob.com/redis/sets-sdiff.html)   返回给定所有集合的差集(左侧） 

SDIFFSTORE destination key1 [key2]  返回给定所有集合的差集并存储在 destination 中

**交集语法：** 

   [SINTER key1 [key2\]](http://www.runoob.com/redis/sets-sinter.html)  返回给定所有集合的交集(共有数据） 

SINTERSTORE destination key1 [key2]  返回给定所有集合的交集并存储在 destination 中

**并集语法：** 

   [SUNION key1 [key2\]](http://www.runoob.com/redis/sets-sunion.html) 返回所有给定集合的并集 

SUNIONSTORE destination key1 [key2]  所有给定集合的并集存储在 destination 集合中

*********

### zset

**赋值语法：** 

ZADD key score1 member1 [score2 member2] 

向有序集合添加一个或多个成员，或者更新已存在成员的分数  



**取值语法：** 

 ZCARD key  获取有序集合的成员数

 ZCOUNT key min max 计算在有序集合中指定区间分数的成员数

ZRANK key member 返回有序集合中指定成员的索引

 ZRANGE key start stop [WITHSCORES] 

通过索引区间返回有序集合成指定区间内的成员(低到高)

ZREVRANGE key start stop [WITHSCORES] 

返回有序集中指定区间内的成员，通过索引，分数从高到底



**删除语法：** 

​     del key   移除集合 

ZREM key member [member ...] 移除有序集合中的一个或多个成员

ZREMRANGEBYRANK key start stop 移除有序集合中给定的排名区间的所有成员(第一名是0)(低到高排序)

ZREMRANGEBYSCORE key min max 移除有序集合中给定的分数区间的所有成员

********

### redisTemplate

redis 序列化默认是走jdk的，可以在配置中更改为String的

redis 序列化：keySerializer、valueSerializer

如果为空的话就走jdk



### redis集群

各个节点之间数据不共享，各个节点间是相互联通的。但要查找某个数据时，回去其他节点查找。重定向。

步骤：

配置各个节点的配置信息：port、ip...

启动各个节点服务

redis-cli开启集群