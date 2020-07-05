学习笔记



深度优先搜索/广度优先搜索



贪心算法



## 深度优先搜索/广度优先搜索

广度优先搜索

> 从起始顶点开始,依次往外遍历,需要借助队列来实现,遍历得到的路径就是,起始顶点到终止顶点的最短路径.

深度优先搜索

> 利用回溯思想,使用递归实现,借助栈来实现

## 贪心算法



## 二分查找

### 前提

1. 目标函数单调性（单调递增或者递减）
2. 存在上下界（bounded)
3. 能够通过索引访问（index accessible)

### 代码模板

```python
left, right = 0,len(array) - 1
while left <= right:
    mid = (left + right) / 2
    if array[mid] == target:
        #find the target!!
        break or return result
    elif array[mid] < target:
        left = mid + 1
	else:
        right = mid - 1
```



```java
// 二分查找（循环实现）
public int bsearch(int[] a, int n, int value){
	int low = 0;
	int high = n - 1;
	
	while(low <= high){
		int mid = low + ((high - low) >> 1);
		if (a[mid] == value){
			return mid;
		} else if(a[mid] < value){
			low = mid + 1;
		} else{
			high = mid - 1;
		}
	}
	
	return -1;
}

// 二分查找（递归实现）
public int bsearch(int[] a, int n, int val){
	return bsearchInternally(a, 0, n - 1, val);
}

private int bsearchInternally(int[] a, int low, int high, int value){
    if(low > high) return -1;
    
    int mid = low + ((high - low) >> 1);
    if (a[mid] == value){
        return mid;
    } else if(a[mid] < value){
        return bsearchInternally(a, mid + 1, high, value);
    } else {
        return bsearchInternally(a, low, mid - 1, value);
    }
}
```

### 局限性

- 二分查找依赖的是**顺序表**结构，简单点说就是数组，如果数据使用链表存储，二分查找的时间复杂度就会变得很高。
- 二分查找针对的是有序数据，只能用在插入、删除操作不频繁，一次排序多次查找的场景中。
- 数据量太小不适合二分查找，因为时间复杂度差不多。
- 数据量太大也不适合二分查找，因为底层依赖数据，而数组要求内存空间连续，对内存要求比较苛刻。

### 变形问题

- 变体一：查找第一个值等于给定值的元素

```java
public int bsearch(int[] a, int n, int value){
    int low = 0;
    int high = n - 1;
    while(low <= high){
        int mid = low + ((high - low) >> 1);
        if
    }
}
```



- 变体二：查找最后一个值等于给定值的元素
- 变体三：查找第一个大于等于给定值的元素
- 变体四：查找最后一个小于等于给定值的元素