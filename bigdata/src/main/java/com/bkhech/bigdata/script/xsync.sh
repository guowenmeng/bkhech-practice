#!/bin/bash
# 集群内部服务器之间文件同步
#1.判断参数的个数
if [ $# -lt 1 ]; then
    echo Not enough arguements!
    exit
fi

#2.遍历集群的所有机器
for host in hadoop01 hadoop02 hadoop03 ; do
    echo ========= $host ===========
    #3.遍历所有目录，挨个发送
    for file in $@ ; do
      #4.判断文件是否存在
      if [ -e $file ]; then
          #5.获取父目录
          pdir=$(cd -P $(dirname $file); pwd)
          #6.获取当前文件名
          fname=$(basename $file)

          ssh $host "mkdir -p $pdir"
          rsync -avz $pdir/$fname $USER@$host:$pdir
      else
          echo $file does not exists!
      fi
    done
done