学习笔记

## 递归

### 思维要点

- 不要人肉进行递归（最大误区）
- 找到最近最简方法，将其拆解成可重复解决的问题（重复子问题）
- 数学归纳思维

### 代码模板

```java
//Java代码模板
public void recur(int level, int param){
    //terminator
    if(level > MAX_LEVEL){
        //process result
        return;
    }
    
    // process current logic
    process(level,params);        
    
    // drill down 
    recur(level:level + 1, newParam);
    
    // restore current status   
}
```



```python
#Python代码模板
def recursion(level, param1, parsm2,....):
    # recursion terminator
    if level > MAX_LEVEL:
        process_result
        return
    
    # process logic in current level
    process(level, data ...)
    
    # drill down
    self.recursion(level + 1, p1, ...)
    
    # reverse the current level status if needed
```



## 分治和回溯



```python
def divide_conquer(problem, param1, param2, ...):
    # recursion terminator
    if problem is None:
        print_result
        return
    
    # prepare data
    data = prepare_data(problem)
    subproblems = split_problem(problem, data)
    
    #conquer subproblems
    subresult1 = self.divide_conquer(subproblem[0], p1, ...)
    
    subresult2 = self.divide_conquer(subproblem[1], p1, ...)
        
   	subresult3 = self.divide_conquer(subproblem[2], p1, ...)
    
    ...
    
    # process and generate the final result
    result = process_result(subresult1, subresult2,subresult3, ...)
    
    # revert the current level stats
```

