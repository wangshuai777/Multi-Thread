package test;

/**
 * Created by wangshuai on 2018/2/12.
 */
public class Test05 {

    public static ListNode reverse(ListNode head){
        if(head==null){
            return head;
        }
        ListNode pre=head;
        ListNode cur=head.getNext();
        ListNode temp;
        while(cur!=null){
            temp=cur.getNext();
            cur.setNext(pre);
            pre=cur;
            head=temp;
        }
        head.setNext(null);
        return pre;
    }

}

class ListNode{
    int val;
    ListNode next;
    public ListNode(int val){
        this.val=val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
