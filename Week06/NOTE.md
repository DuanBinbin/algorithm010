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



### 步骤

- 重复性（分治）
- 定义状态数组
- DP方程



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



### 经典案例：Count the Path的伪代码

```
// 状态转移方程
核心代码：opt[i, j] = opt[i + 1, j] + opt[i, j+ 1]
完整逻辑：
if a[i,j] = '空地'：
	opt[i,j] = opt[i + 1, j] + opt[i, j + 1]
else:
	opt[i, j] = 0
```



### 经典案例：如何在背包中装下最多的重量



```java
class solution{
    //回溯算法。注意：把输入的变量都定义成了成员变量
    private int maxW = Integer.MIN_VALUE;
    private int maxV = Integer.MIN_VALUE;
    
    private int[] weight = {2, 3, 4, 6, 3}; //物品重量
    private int[] value = {3, 4, 8, 9, 6}; //物品价值
    
    private int n = 5; //物品个数
    private int w = 9; //背包承受的最大重量
    
    private boolean[][] mem = new boolenn[5][10]; //备忘录，默认值false
    
    public void f(int i, int cw){ //调用f(0, 0)
        if (cw == w || i == n){ // cw == w表示装满了，i == n表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        
        if (mem[i][cw]) return; //重复状态
        mem[i][cw] = true; //记录(i, cw)这个状态
        f(i + 1, cw); //选择不装第i个物品
        
        if (cw + weight[i] <= w){
            f(i + 1, cw + weight[i]);// 选择装第i个物品
        }
    }
    
    public void fv(int i, int cw, int cv){ //调用fv(0, 0, 0)
        if (cw == w || i == n){ // cw == w表示装满了， i == n表示物品都考察完了
            if (cv > maxV) maxV = cv;
            return;
        }
        
        f(i + 1, cw, v); //选择不装第i个物品
        if (cw + weight[i] <= w){
            f(i + 1, cw + weight[i], cv + value[i]); // 选择装第i个物品
        }
    }
    
    //动态规划算法 - 二维数据
    public int knapsack(int[] weight, int n, int w){
        boolean[][] states = new boolean[n][w + 1]; //默认值false
        
        states[0][0] = true; //第一行的数据要特殊处理，可以利用哨兵优化
        if (weight[0] <= w){
            states[0][weight[0]] = true;
        }
        
        for (int i = 1; i < n; ++i){ // 动态规划状态转移
            for (int j = 0; j <= w; ++j){// 不把第i个物品放入背包
                if (states[i -1][j] == true){
                    state[i][j] = states[i -1][j];
                }
            }
            
            for (int j = 0; j <= w - weight[i]; ++j){ //把第i个物品放入背包
                if (states[i - 1][j] == true){
                    states[i][j + weight[i]] == true;
                }
            }
        }
        
        for (int i = w; i >= 0; --i){ //输出结果
            if (states[n - 1][i] == true){
                return i;
            }
        }
        return 0；
    }
    
    public int knapsack2(int[] items, int n, int w){
        boolean[] states = new boolean[w + 1]; //默认值false
        states[0] = true; //第一行的数据要特殊处理，可以利用哨兵优化
        
        if (items[0] <= w){
            states[items[0]] = true;
        }
        
        for (int i = 1; i < n; ++i){ //动态规划
            for (int j = w - items[i]; j >= 0; --j){//把第i个物品放入背包
                if (states[j] == true){
                    states[j + items[i]] == true;
                }
            }
        }
        
        for (int i = w; i >= 0; --j){ //输出结果
            if (states[i] == true){
                return i;
            }
        }
        
        return 0;
    }
}
```

