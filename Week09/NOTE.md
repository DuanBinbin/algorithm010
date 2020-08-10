学习笔记



# DP顺推模板

```pytho
function DP():
	dp = [][] #二维情况
    
    for i = 0 .. M {
        for j = 0 .. N {
            dp[i][j] = _Function(dp[i'][j'] ...)
        }
    }

	return dp[M][N];
```



# Rabin-Karp 算法

思想：

1. 假设子串的长度为M(Pat)，目标字符串的长度为N（txt)
2. 计算子串的hash值 hash_pat
3. 计算目标字符串txt中每个长度为M的子串的hash值（共需要计算 N-M+1次）
4. 比较hash值：如果hash值不同，字符串必然不匹配；如果hash值相同，还需要使用朴素算法再次判断



# KMP算法

思想：

设法利用已知信息，不要把“搜索位置”移回已经比较过的位置，继续把它向后移，以此来提高效率。