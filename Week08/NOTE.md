学习笔记

## 位运算

### 位运算符

> 左移：<<
>
> 右移：>>
>
> 按位或：|
>
> 按位与：&
>
> 按位取反：~
>
> 按位异或（相同为零不同为一）：^
>
> ​	x^0 = x
>
> ​	x^1s = ~x //注意 1s = ~0
>
> ​	x^(~x)=1s
>
> ​	x^x=0
>
> ​	c = a^b => a^c = b, b^c = a  //交换两个数
>
> ​	a^b^c = a^(b^c) = (a^b)^c //associative

### 算数移位与逻辑移位

1. 将x最右边的n位清零：x&(~0 << n)
2. 获取x的第n位值（0或者1）：(x >> n) & 1
3. 获取x的第n位的幂值：x&(1 << n)
4. 仅将第n位置为1： x|(1 <<n)
5. 仅将第n位置为0：x&(~(1<<n))
6. 将x最高位至第n为（含）清零：x&((1 << n) - 1)
7. 将第n为至第0为（含）清零：x&(~((1<<(n+1)) - 1))

### 位运算的应用

- 判断奇偶：

x % 2 == 1 -> (x&1) == 1

x % 2 == 1 -> (x&1) == 0

- x >> 1 -> x / 2，即 x = x / 2；-> x == x >> 1

mid = (left + right) / 2 -> mid = (left + right) >> 1

- x = x & (x -1) 清零最低为的1
- x&-x => 得到最低为的1
- x&~x => 0





```python
def solveNQueens(self, n):
    def DFS(queens, xy_dif, xy_sum):
        p = len(queens)
        if p == n:
            result.appen(queens)
            return None
        for q in range(n):
            if q not in queens and p-q not in xy_dif and p+q not in xy_sum:
                DFS(queens+[q], xy_dif+[p-q], xy_sum[p+q])

	result = []                
    DFS([],[],[])
    return [["." * i + "Q" + "." * (n - i - 1) for i in sol] for sol in result]
```



```python
//N皇后的位运算解决
def totalNQueens(self, n):
    if n < 1: return []
    self.count = 0
    self.DFS(n, 0, 0, 0, 0)
    return self.count

def DFS(self, n , row, cols, pie, na):
    #recursion terminator
    if row >= n:
        self.count += 1
        return
    
    bits = (~(cols | pie | na)) & ((1 << n) - 1) #得到当前所有的空位
    
    while bits:
        p = bits & -bits #取到最低为的1
        bits = bits & (bits - 1) #表示在p位置放入皇后
        self.DFS(n, row + 1, cols | p， （pie | p) << 1, (na | p) >> 1)
        # 不需要revert cols, pie , na 的状态
```



```java
class Solution{
    private int size;
    private int count;
    
    private void solve(int row, int ld, int rd) {
        if (row == size) {
            count++;
            return;
        }
        int pos = size & (~(row | ld |ro));
        while (pos != 0) {
            int p = pos & (~pos);
            pos -= p;
            solve(row | p, (ld | p) << 1, (rd | p) >> 1);
        }
    }
    
    public int totalNQueens(int n){
        count = 0;
        size = (1 << n) - 1;
        solve(0, 0, 0);
        return count;
    }
}
```



```C++
public vector<int> countBits (int num) {
    vector<int> bits (num + 1, 0);
    for (int i = 1; i <= num; i++) {
        bits[i] += bits[i & (i -1)] + 1;
    }
}
```



## 布隆过滤器



## LRU缓存

```python
class LURCache(object):
    def __init__(self, capacity):
        self.dic = collections.OrderedDict()
        self.remain = capacity
        
	def get(self, key):
        if key not in self.dic:
            return -1
        v = self.dic.pop(key)
        self.dic[key] = v #key as the newest one
        return v
    
    def put(self, key, value):
        if key in self.dic:
            self.dic.pop(key)
		else:
            if self.remain > 0:
                self.remain -= 1
             else: # self.dic is full
                self.dic.popitem(last = False)
		self.dic[key] = value                
```



## 排序算法

| 比较类排序                          | 非比较类排序 |
| ----------------------------------- | ------------ |
| 交换排序：冒泡排序/快速排序         | 计数排序     |
| 插入排序：简单插入排序/希尔排序     | 桶排序       |
| 选择排序：简单选择排序/堆排序       | 基数排序     |
| 归并排序：二路归并排序/多路归并排序 |              |



### 快速排序

```java
public static void quickSort(int[] array, int begin, int end) {
	if (end <= begin) return;
    int pivot = partition(array, begin, end);
    quickSort(array, begin, pivot - 1);
    quickSort(array, pivot + 1, end);
}

public static void partition(int[] a, int begin, int end) {    
    int pivot = end, counter = begin;
   	for (int i = begin; i < end; i++){
		if (a[i] < a[pivot]) {
            int temp = a[counter]; a[counter] = a[i]; a[i] = temp;
            counter++;
        }       
    }
    int temp = a[pivot]; a[pivot] = a[counter]; a[counter] = temp;
    return counter;
}
```



### 归并排序

```java
public static void mergeSort(int[] array, int left, int right) {
    if (right <= left) return;
    int mid = (left + right) >> 1;
    
    mergeSort(array, left, mid);
    mergeSort(array, mid + 1， right);
    merge(array, left, mid, right);
}

public static void merge(int[] arr, int left, int mid, int right) {
    int[] temp = new int[right - left + 1]; //中间数组
    int i = left, j = mid + 1, k = 0;
    
    while (i <= mid && j <= right) {
        temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++]; // 很经典的一段代码
    }
    
    while (i <= mid)	temp[k++] = arr[i++];
    while (j <= right)	temp[k++] = arr[j++];
    
    for (int p = 0; p < temp.length; p++) {
        arr[left + p] = temp[p];
    }
}
```

总结：

> 归并和快排具有相似性，但步骤顺序相反
>
> 归并：先排序左右子数组，然后合并两个有序子数组
>
> 快排：先调配出左右子数组，然后对于左右子数组进行排序

### 堆排序

```c++
void heap_sort(int a[], int len) {
    priority_queue<int, vector<int>, greater<int>> q;
    
    for (int i = 0; i < len; i++) {
        q.push(a[i]);
    }
    
    for (int i = 0; i < len; i++) {
        a[i] = q.pop();
    }
}
```

