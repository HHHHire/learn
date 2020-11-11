//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 4570 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 还可以用 s.indexOf(char,index) 从index开始查询字符串s中的第一个char字符，返回位置。
 * 在doc 3.md中的例子
 */
class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int start = 0;
        int end = start;
        int len = 0;
        int tmp = 0;
        Map<Character, Integer> map = new HashMap<>(26);
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) != null) {
                tmp = map.get(chars[i]) + 1;
                start = start > tmp ? start : tmp;
            }
            map.put(chars[i], i);
            end = i + 1;
            len = len > (end - start) ? len : (end - start);
        }
        return len;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
