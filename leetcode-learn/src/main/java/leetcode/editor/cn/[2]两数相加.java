//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。 
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学 
// 👍 5230 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
package leetcode.editor.cn;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 最开始节点
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        // 进位
        int c = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            // 两数之和，还要加上进位
            int sum = x + y + c;
            // 算出进位
            c = sum / 10;
            // 算出真正放在节点上的数
            sum = sum % 10;

            // 把数放到下一个节点上
            cur.next = new ListNode(sum);
            cur = cur.next;

            // 如果还存在下一个节点，继续
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 循环完成后还有进位的话，在另外加一个节点存放
        if (c == 1) {
            cur.next = new ListNode(1);
        }
        // 返回最开始的节点
        return pre.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
