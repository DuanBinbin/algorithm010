学习笔记



```java
//递归代码模板
public void recur(int level, int param){
    //terminator
    if (level > MAX_LEVEL){
        //process result
        return;
    }
    
    // process current logic
    process(level, param);
    
    // drill down
    recur(level:level + 1,newParam);
    
    //restore current status 恢复当前层
}
```



```python
#分治代码模板
def divide_conquer(problem, param1, param2, ...):
    # recursion terminator
    if problem is None:
        print_result
        return
    
    # prepare data
    data = prepare_data(problem)
    subproblems = split_problem(problem, data)
    
    # conquer subproblems
    subresult1 = self.divide_conquer(subproblems[0], p1, ...)
    subresult2 = self.divide_conquer(subproblems[1], p1, ...)
    subresult3 = self.divide_conquer(subproblems[2], p1, ...)
    # ...
    
    # process and generate the final result
    result = process_result(subresut1, subresult2, subresult3, ...)
    
    #revert the current level states
    
```



## 动态规划

1. 动态规划和递归或者分治没有根本上的区别（关键是看有无最优的子结构）
2. 共性：找到重复子问题
3. 差异性：最优子结构、中途可以淘汰次最优



感触

1.人肉递归低效、很累

2.找到最近最简方法，将其拆解成可重复解决的问题

3.数学归纳思维（抵制人肉递归的诱惑）



**本质：寻找重复性 -> 计算机指令集**



### **关键点**

最优子结构 opt[n] = best_of(opt[n - 1], opt[n - 2], ...)

储存中间状态：opt[i]

递推公式（美其名曰：状态转移方程或者DP方程）

> Fib: opt[i] = opt[n - 1] + opt[n - 2]
>
> 二维路径：opt[i, j] = opt[i + i] [j] + opt[i] [j + 1] (且判断a[i, j]是否空地)

### 小结

- 打破思维习惯，形成机器思维
- 理解复杂逻辑的关键
- 也是职业进阶的要点要领（我下属的下属不是我的下属）

### 步骤

- 重复性（分治）
- 定义状态数组
- DP方程



### 经典案例：斐波那切数列

```java
//斐波那切数列
// 递归法 - 时间复杂度是指数级的2^n
public int fib(int n){
    if (n <= 0){
        return 0;
    } else if (n == 1){
        return 1;
    } else {
        return fib(n -1) + fib(n - 2)
    }
}

//递归 + 记忆化搜索(缓存)，将已经计算过的缓存起来, 时间复杂度为0(n)
public int fib(int n, int[] memo){
    // terminator
    if (n <= 1){
        return n;
    } 
    
    if (memo[n] == 0){
        memo[n] = fib(n - 1) + fib(n - 2);
    }
    return memo[n];
}

// 自底向下 *** 
public int fibButtomUp(int n){
    if (n <= 1){
        return n;
    }
	
    a[0] = 0, a[1] = 1;
    for (int i = 2; i <= n; ++i){
        a[i] = a[i - 1] + a[i - 2];
    }
    
    return a[n];
}
```



经典案例：Count the Path的伪代码

```
// 状态转移方程
核心代码：opt[i, j] = opt[i + 1, j] + opt[i, j+ 1]
完整逻辑：
if a[i,j] = '空地'：
	opt[i,j] = opt[i + 1, j] + opt[i, j + 1]
else:
	opt[i, j] = 0
```

