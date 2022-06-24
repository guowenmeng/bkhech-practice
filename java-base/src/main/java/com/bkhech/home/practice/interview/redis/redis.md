1. Redis 是什么？都有哪些使用场景？
Redis 是一个使用 C 语言开发的高速缓存数据库。
Redis 使用场景：
· 记录帖子点赞数、点击数、评论数；
· 缓存近期热帖；
· 缓存文章详情信息；
· 记录用户会话信息。

2. Redis 有哪些功能？
· 数据缓存功能
· 分布式锁的功能
· 支持数据持久化
· 支持事务
· 支持消息队列

3. Redis 和 memcache 有什么区别？
· 存储方式不同：memcache 把数据全部存在内存之中，断电后会挂掉，数据不能超过内存大小；Redis 有部份存在硬盘上，这样能保证数据的持久性。
· 数据支持类型：memcache 对数据类型支持相对简单；Redis 有复杂的数据类型。
· 使用底层模型不同：它们之间底层实现方式，以及与客户端之间通信的应用协议不一样，Redis 自己构建了 vm 机制，因为一般的系统调用系统函数的话，会浪费一定的时间去移动和请求。
· value 值大小不同：Redis 最大可以达到 512mb；memcache 只有 1mb。

4. Redis 为什么是单线程的？
因为 cpu 不是 Redis 的瓶颈，Redis 的瓶颈最有可能是机器内存或者网络带宽。既然单线程容易实现，而且 cpu 又不会成为瓶颈，那就顺理成章地采用单线程的方案了。
关于 Redis 的性能，官方网站也有，普通笔记本轻松处理每秒几十万的请求。
而且单线程并不代表就慢 nginx 和 nodejs 也都是高性能单线程的代表。

5. 什么是缓存穿透？怎么解决？
缓存穿透：指查询一个一定不存在的数据，由于缓存是不命中时需要从数据库查询，查不到数据则不写入缓存，这将导致这个不存在的数据每次请求都要到数据库去查询，造成缓存穿透。
解决方案：最简单粗暴的方法如果一个查询返回的数据为空（不管是数据不存在，还是系统故障），我们就把这个空结果进行缓存，但它的过期时间会很短，最长不超过五分钟。

6. Redis 支持的数据类型有哪些？
Redis 支持的数据类型：string（字符串）、list（列表）、hash（字典）、set（集合）、zset（有序集合）。

7. Redis 支持的 Java 客户端都有哪些？
支持的 Java 客户端有 Redisson、jedis、lettuce 等。

8. jedis 和 Redisson 有哪些区别？
· jedis：提供了比较全面的 Redis 命令的支持。
· Redisson：实现了分布式和可扩展的 Java 数据结构，与 jedis 相比 Redisson 的功能相对简单，不支持排序、事务、管道、分区等 Redis 特性。

9. 怎么保证缓存和数据库数据的一致性？
· 合理设置缓存的过期时间。
· 新增、更改、删除数据库操作时同步更新 Redis，可以使用事物机制来保证数据的一致性。

10. Redis 持久化有几种方式？
> 宕机了，Redis数据丢了怎么办？ https://mp.weixin.qq.com/s/s5p1qdoxntpaBkFj-xuxhA 
> Redis4.0开始使用AOF日志记录两次快照之间执行的命令（AOF和RDB混合使用）https://blog.csdn.net/weixin_33810006/article/details/90394921

Redis 的持久化有两种方式，或者说有两种策略：
· RDB（Redis Database）：指定的时间间隔能对你的数据进行快照存储。
· AOF（Append Only File）：每一个收到的写命令且执行成功的命令都通过write函数追加到文件中。

11. Redis用过是吧？那你讲讲Redis都有哪些监控指标？

    > https://mp.weixin.qq.com/s/CESc14pVBTfhjLhMYEtTkg

    - 性能指标：Performance

      | Name                      | Description              |
      | :------------------------ | :----------------------- |
      | latency                   | Redis响应一个请求的时间  |
      | instantaneous_ops_per_sec | 平均每秒处理请求总数     |
      | hi rate(calculated)       | 缓存命中率（计算出来的） |

    - 内存指标: Memory

      | Name                    | Description                                   |
      | :---------------------- | :-------------------------------------------- |
      | used_memory             | 已使用内存                                    |
      | mem_fragmentation_ratio | 内存碎片率                                    |
      | evicted_keys            | 由于最大内存限制被移除的key的数量             |
      | blocked_clients         | 由于BLPOP,BRPOP,or BRPOPLPUSH而备阻塞的客户端 |

    - 基本活动指标：Basic activity

      | Name                       | Description                |
      | :------------------------- | :------------------------- |
      | connected_clients          | 客户端连接数               |
      | conected_laves             | slave数量                  |
      | master_last_io_seconds_ago | 最近一次主从交互之后的秒数 |
      | keyspace                   | 数据库中的key值总数        |

    - 持久性指标: Persistence

      | Name                       | Description                        |
      | :------------------------- | :--------------------------------- |
      | rdb_last_save_time         | 最后一次持久化保存磁盘的时间戳     |
      | rdb_changes_sice_last_save | 自最后一次持久化以来数据库的更改数 |

    - 错误指标：Error

      | Name                           | Description                           |
      | :----------------------------- | :------------------------------------ |
      | rejected_connections           | 由于达到maxclient限制而被拒绝的连接数 |
      | keyspace_misses                | key值查找失败(没有命中)次数           |
      | master_link_down_since_seconds | 主从断开的持续时间（以秒为单位)       |

    #### 监控方式

    - redis-benchmark(性能测试)

    - redis-stat(需要安装此监控程序)

    - redis-cli

      - monitor(实时监控redis指令)

      - info（可以一次性获取所有的信息，也可以按块获取信息）

        - 1）server:服务器运行的环境参数
        - 2）clients:客户端相关信息
        - 3）memory：服务器运行内存统计数据
        - 4）persistence：持久化信息
        - 5）stats：通用统计数据
        - 6）Replication：主从复制相关信息
        - 7）CPU：CPU使用情况
        - 8）cluster：集群信息
        - 9）Keypass：键值对统计数量信息

      - slowlog(慢日志) 

        > 语法： slowlog get 2

        - 1）get：获取慢查询日志
        - 2）len：获取慢查询日志条目数
        - 3）reset：重置慢查询日志

      相关配置：

    - ```shell
      slowlog-log-slower-than 1000 # 设置慢查询的时间下线，单位：微秒
      slowlog-max-len 100 # 设置慢查询命令对应的日志显示长度，单位：命令数
      ```

    - 

12. 
