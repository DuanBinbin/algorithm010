学习笔记

# 基础知识

## 1.数组

 数组（Array）是一种`线性表`数据结构，用一组连续的内存空间，来存储一组具有相同类型的数据。 

> 线性表：数组、链表、队列、栈
>
> 非线性表：树、图

时间复杂度：

数组支持随机访问，根据下标随机访问的时间复杂度为O(1)

插入/删除： 最好情况时间复杂度为 O(1)；如果删除开头的数据，则最坏情况时间复杂度为 O(n)；平均情况时间复杂度也为 O(n)。 

## 2.链表和跳表



## 3.栈



## 4.队列/优先队列/双端队列

队列最大的特点就是先进先出，主要的两个操作是入队和出队。

```Java
// 用数组实现的队列
public class ArrayQueue {
  // 数组：items，数组大小：n
  private String[] items;
  private int n = 0;
  // head表示队头下标，tail表示队尾下标
  private int head = 0;
  private int tail = 0;

  // 申请一个大小为capacity的数组
  public ArrayQueue(int capacity) {
    items = new String[capacity];
    n = capacity;
  }


   // 入队操作，将item放入队尾
  public boolean enqueue(String item) {
    // tail == n表示队列末尾没有空间了
    if (tail == n) {
      // tail ==n && head==0，表示整个队列都占满了
      if (head == 0) return false;
      // 数据搬移
      for (int i = head; i < tail; ++i) {
        items[i-head] = items[i];
      }
      // 搬移完之后重新更新head和tail
      tail -= head;
      head = 0;
    }
    
    items[tail] = item;
    ++tail;
    return true;
  }

  // 出队
  public String dequeue() {
    // 如果head == tail 表示队列为空
    if (head == tail) return null;
    // 为了让其他语言的同学看的更加明确，把--操作放到单独一行来写了
    String ret = items[head];
    ++head;
    return ret;
  }
}
```



```java
// 实现循环队列的关键是，确定好对空和队满的判定条件。队列为空的判断条件是head == tail,队列为满的判断条是
// (tail + 1) % n = head
public class CircularQueue {
  // 数组：items，数组大小：n
  private String[] items;
  private int n = 0;
  // head表示队头下标，tail表示队尾下标
  private int head = 0;
  private int tail = 0;

  // 申请一个大小为capacity的数组
  public CircularQueue(int capacity) {
    items = new String[capacity];
    n = capacity;
  }

  // 入队
  public boolean enqueue(String item) {
    // 队列满了
    if ((tail + 1) % n == head) return false;
    items[tail] = item;
    tail = (tail + 1) % n;
    return true;
  }

  // 出队
  public String dequeue() {
    // 如果head == tail 表示队列为空
    if (head == tail) return null;
    String ret = items[head];
    head = (head + 1) % n;
    return ret;
  }
}
```



# 学习思考

## 1.学习算法懵逼的时候怎么办？

暴力求解？ 基本情况？

核心：找 最近 重复子问题

if else

for while, recursion



## 2.万能的双指针

