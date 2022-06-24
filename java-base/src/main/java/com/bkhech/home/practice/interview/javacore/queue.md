1. 如果想使用固定大小的队列，有几种队列可以选择，有何不同？
   > https://segmentfault.com/a/1190000011098836
   
   答：可以使用 LinkedBlockingQueue 和 ArrayBlockingQueue 两种队列。
    前者是链表，后者是数组，链表新增时，只要建立起新增数据和链尾数据之间的关联即可，
   数组新增时，需要考虑到索引的位置（takeIndex 和 putIndex 分别记录着下次拿数据、放数据的索引位置），
   如果增加到了数组最后一个位置，下次就要重头开始新增。
