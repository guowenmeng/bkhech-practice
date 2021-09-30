# CompletableFuture
> Java8新增的CompletableFuture 提供对异步计算的支持，可以通过回调的方式处理计算结果.
>CompletableFuture 类实现了CompletionStage和Future接口，所以还可以像之前使用Future那样使用CompletableFuture ，尽管已不再推荐这样用了。

1. CompletableFuture中join()和get()方法的区别
相同点： 
   join()和get()方法都是用来获取CompletableFuture异步之后的返回值 
区别：
   i. join()方法抛出的是uncheck异常（即未经检查的异常),不会强制开发者抛出，
  会将异常包装成CompletionException异常 /CancellationException异常，
  但是本质原因还是代码内存在的真正的异常.
   ii. get()方法抛出的是经过检查的异常，ExecutionException, InterruptedException 
   需要用户手动处理（抛出或者 try catch）