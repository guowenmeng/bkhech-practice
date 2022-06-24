1. MyBatis 中 #{}和 ${}的区别是什么？
\#{}是预编译处理，${}是字符替换。 在使用 #{}时，MyBatis 会将 SQL 中的 #{}替换成“?”，配合 PreparedStatement 的 set 方法赋值，这样可以有效的防止 SQL 注入，保证程序的运行安全。

2. MyBatis 有几种分页方式？
分页方式：逻辑分页和物理分页。
逻辑分页： 使用 MyBatis 自带的 RowBounds 进行分页，它是一次性查询很多数据，然后在数据中再进行检索。这样做弊端是需要消耗大量的内存、有内存溢出的风险、对数据库压力较大。
物理分页： 自己手写 SQL 分页或使用分页插件 PageHelper，去数据库查询指定条数的分页数据的形式。弥补了一次性全部查出的所有数据的种种缺点，比如需要大量的内存，对数据库查询压力较大等问题。

3. RowBounds 是一次性查询全部结果吗？为什么？
RowBounds 表面是在“所有”数据中检索数据，其实并非是一次性查询出所有数据，因为 MyBatis 是对 jdbc 的封装，在 jdbc 驱动中有一个 Fetch Size 的配置，它规定了每次最多从数据库查询多少条数据，假如你要查询更多数据，它会在你执行 next()的时候，去查询更多的数据。就好比你去自动取款机取 10000 元，但取款机每次最多能取 2500 元，所以你要取 4 次才能把钱取完。只是对于 jdbc 来说，当你调用 next()的时候会自动帮你完成查询工作。这样做的好处可以有效的防止内存溢出。
Fetch Size 官方相关文档：http://t.cn/EfSE2g3

5. MyBatis 是否支持延迟加载？延迟加载的原理是什么？
MyBatis 支持延迟加载，设置 lazyLoadingEnabled=true 即可。
延迟加载的原理的是调用的时候触发加载，而不是在初始化的时候就加载信息。比如调用 a.getB().getName()，这个时候发现 a.getB() 的值为 null，此时会单独触发事先保存好的关联 B 对象的 SQL，先查询出来 B，然后再调用 a.setB(b)，而这时候再调用 a.getB().getName() 就有值了，这就是延迟加载的基本原理。

6. 说一下 MyBatis 的一级缓存和二级缓存？
· 一级缓存：基于 PerpetualCache 的 HashMap 本地缓存，它的声明周期是和 SQLSession 一致的，有多个 SQLSession 或者分布式的环境中数据库操作，可能会出现脏数据。当 Session flush 或 close 之后，该 Session 中的所有 Cache 就将清空，默认一级缓存是开启的。
· 二级缓存：
    也是基于 PerpetualCache 的 HashMap 本地缓存，不同在于其存储作用域为 Mapper 级别的，如果多个SQLSession之间需要共享缓存，则需要使用到二级缓存，并且二级缓存可自定义存储源，如 Ehcache。
    二级缓存默认开启，除非显示的设置cacheEnabled=FALSE，才会从系统层面关闭二级缓存，但是只是开启二级缓存，不一定使用，需要在mapper.xml文件中使用cache标签指定。
    使用二级缓存属性类需要实现 Serializable 序列化接口(可用来保存对象的状态)。
开启二级缓存数据查询流程：二级缓存 -> 一级缓存 -> 数据库。
缓存更新机制：当某一个作用域(一级缓存 Session/二级缓存 Mapper)进行了C/U/D 操作后，默认该作用域下所有 select 中的缓存将被 clear。

7. MyBatis 和 hibernate 的区别有哪些？
· 灵活性：MyBatis 更加灵活，自己可以写 SQL 语句，使用起来比较方便。
· 可移植性：MyBatis 有很多自己写的 SQL，因为每个数据库的 SQL 可以不相同，所以可移植性比较差。
· 学习和使用门槛：MyBatis 入门比较简单，使用门槛也更低。
· 二级缓存：hibernate 拥有更好的二级缓存，它的二级缓存可以自行更换为第三方的二级缓存。

8. MyBatis 有哪些执行器（Executor）？
MyBatis 有三种基本的Executor执行器：
· SimpleExecutor：每执行一次 update 或 select 就开启一个 Statement 对象，用完立刻关闭 Statement 对象；
· ReuseExecutor：执行 update 或 select，以 SQL 作为 key 查找 Statement 对象，存在就使用，不存在就创建，用完后不关闭 Statement 对象，而是放置于 Map 内供下一次使用。简言之，就是重复使用 Statement 对象；
· BatchExecutor：执行 update（没有 select，jdbc 批处理不支持 select），将所有 SQL 都添加到批处理中（addBatch()），等待统一执行（executeBatch()），它缓存了多个 Statement 对象，每个 Statement 对象都是 addBatch()完毕后，等待逐一执行 executeBatch()批处理，与 jdbc 批处理相同。

9. MyBatis 分页插件的实现原理是什么？
分页插件的基本原理是使用 MyBatis 提供的插件接口，实现自定义插件，在插件的拦截方法内拦截待执行的 SQL，然后重写 SQL，根据 dialect 方言，添加对应的物理分页语句和物理分页参数。

备份回答：
1.MyBatis 中 #{}和 ${}的区别是什么？
首先，构建BoundSql时解析处理方式不同。在进行SqlNode解析时，每一个#{}会被替换为？占位符，内部信息会封装成ParamterMapping对象，用于后续参数值绑定；${}不会进行替换
其次，赋值处理不同。#{}解析为?占位符后，是通过DefaultParameterHandler遍历ParameterMapping集合，通过setNonNullParameter()进行赋值；${}是在构建sql，循环SqlNode结点时，直接通过值进行替换
最后，#{}通过占位符方式，${}容易导致sql注入
2. MyBatis 有几种分页方式？
逻辑分页、物理分页
3. RowBounds 是一次性查询全部结果吗？为什么？
不是。虽然不指定RowBounds中的limit默认为Integer.MAX_VALUE，但是MyBatis底层是对JDBC的封装，JDBC在拉去数据时有fetchSize限制，规定了每次最多从数据库拉去多少数据，如果需要查询更多，是在执行next()时，去查询更多的数据
4. MyBatis 逻辑分页和物理分页的区别是什么？
逻辑分页是先查询数据，然后在内存中完成过滤；物理分页通过插件，修改sql，查出的数据就已经完成了分页
5. MyBatis 是否支持延迟加载？延迟加载的原理是什么？
支持，延迟加载的原理是通过代理机制(默认Javassist)，创建代理对象,当需要加载对象时，通过代理对象触发查询