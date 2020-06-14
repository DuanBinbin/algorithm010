
/*
 * @lc app=leetcode.cn id=42 lang=java
 *
 * [42] 接雨水
 */

// @lc code=start
class Solution {
    // public int trap(int[] height) {
    //     int sum = 0;
    //     int max_left = 0;
    //     int max_right = 0;
    //     int left = 1;
    //     int right = height.length - 2; // 加右指针进去
    //     for (int i = 1; i < height.length - 1; i++) {
    //         //从左到右更
    //         if (height[left - 1] < height[right + 1]) {
    //             max_left = Math.max(max_left, height[left - 1]);
    //             int min = max_left;
    //             if (min > height[left]) {
    //                 sum = sum + (min - height[left]);
    //             }
    //             left++;
    //         //从右到左更
    //         } else {
    //             max_right = Math.max(max_right, height[right + 1]);
    //             int min = max_right;
    //             if (min > height[right]) {
    //                 sum = sum + (min - height[right]);
    //             }
    //             right--;
    //         }
    //     }
    //     return sum;
    // }

    public int trap(int[] height){
        int left = 0, right = height.length - 1;
        int ans = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= left_max)
                    left_max = height[left];
                else
                    ans += (left_max - height[left]);
                ++left;
            } else {
                if (height[right] >= right_max)
                    right_max = height[right];
                else
                    ans += (right_max - height[right]);
                --right;
            }
        }
        return ans;
    }
}

// @lc code=end

