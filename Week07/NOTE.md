学习笔记

# 字典树

## 基本结构

字典树，即Trie树，又称单次查找树或键树，是一种树形结构。典型应用是用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。

它的优点是：最大限度地减少无谓的字符串比较，查询效率比哈希表高。

## 基本性质

1. 结点本身不存完整单次；
2. 从根节点到某一结点，路径上经过的字符连接起来，为该节点对应的字符串；
3. 每个节点的所有子节点路径代表的字符串都不相同。

## 核心思想

- Trie树的核心思想是空间换时间。
- 利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的。

## 代码模板

### Python

```python
class Trie(object):
    def __init__(self):
        self.root = {}
        self.end_of_word = "#"
        
	def insert(self, word):
        node = self.root
        for char in word:
            node = node.setdefault(char, {})
        node[self.end_of_word] = self.end_of_word
        
	def search(self, word):
        node = self.root
        for char in word:
            if char not in node:
                return false
            node = node[char]
         return self.end_of_word in node
    
    def startsWith(self, prefix):
        node = self.root
        for char in prefix:
            if char not in node:
                return false
            node = node[char]
            
		return True            
        
```

### Java

```java
class Trie{
    private boolean isEnd;
    private Trie[] next;
    
    public Trie(){
        isEnd = false;
       	next = new Trie[26];
    }
    
    public void insert(String word){
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.lenght; i++){
            int n = words[i] - 'a';
            if (curr.next[n] == null) curr.next[n] = new Trie();
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }
    
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }
    
    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i++){
            node = node.next[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }
}
```

# 并查集

[傻子都能看懂的并查集入门](https://segmentfault.com/a/1190000004023326)



### Java

```java
public class UnionFind{
    private int count = 0;
    private int[] parent;
    
    public UnionFind(int n){
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++){
            parent[i] = i;
        }
    }
    
    public int find(int p){
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
 
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }
}
```

### python

```python
def init(p):
    # for i = 0 .. n: p[i] = i;
    p = [i for i in range(n)]
    
def union(self, p, i, j):
    p1 = self.parent(p, i)
    p2 = self.parent(p, j)
    p[p1] = p2
    
def parent(self, p, i):
    root = i
    while p[root] != root:
        root = p[root]
	while p[i] != i： # 路径压缩？
    	x = i; i = p[i]; p[x] = root
	return root        
```

### C/C++

```
class UnionFind{
public:
	UnionFind(vector<vector<char>>& grid){
		count = 0;
		int m = grid.size();
		int n = grid[0].size();
		for (int i = 0; i < m; ++i){
			for (int j = 0; j < n; ++j){
				if (grid[i][j] == '1'){
					parent.push_back(i * n + j);
					++count;
				} else {
					parent.push_back(-1);
				}
				rank,push_back(0);
			}
		}
	}

//递归
	int find(int i) {
		if (parent[i] != i) {
			parent[i] = find(parent[i]);
		}
		return parent[i];
	}

	void unite(int x, int y) {
		int rootx = find(x);
		int rooty = find(y);
		if (rootx != rooty) {
			if (rank[rootx] < rank[rooty]) {
				swap(rootx, rooty);
			}
			parent[rooty] = rootx;
			if (rank[rootx] == rank[rooty]) rank[rootx] += 1;
			--count;
		}
	}
	
	int getCount() const {
		return count;
	}
	
private:
	vector<int> parent;
	vector<int> rank;
	int count;
}
```



# 高级搜索



# AVL树

1. AVL树是一种平衡二叉搜索树
2. 每个结点村balance factor = {-1，0, 1}
3. 四种旋转操作

不足：结点需要存储额外信息、且调整次数频繁



# 红黑树

红黑树是一种`近似平衡`的二叉搜索树(Binary Search Tree)，它能够确保任何一个结点的左右子树的`高度差小于两倍`。具体来说，红黑树是满足如下条件的二叉搜索树：

1. 每个节点要么是红色，要么是黑色。
2. 根节点永远是黑色。
3. 所有的叶子节点都是空节点(即null)，并且是黑色的。
4. 每个红色节点的两个子节点都是黑色(从每个叶子到根的路径上不会有两个连续的红色节点)。
5. 从任一节点到其子树中每个叶子节点的路径都包含相同数量了的黑色节点。



**对比**

* AVL tress provide faster lookups than Red Black Trees because they are more strictly balanced.
* Red Black Trees provide faster insertion and removal operations than AVL trees as fewer rotations are done due to relatively relaxed balancing.
* AVL trees store balance factors or heights with each node, thus requires storage for and integer per node whereas Red Black Tree requires only 1 bit of informations per node.
* Red Black Trees are uesed in most of the language libraries like map、multimap、multisetin C++ whereas AVL trees are used in databases where faster retrievals are required. 



[我画了近百张图来理解红黑树](https://www.jianshu.com/p/cc34ca0e219c)





