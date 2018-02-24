package test;

import java.util.Arrays;

/**
 * Created by wangshuai on 2018/2/5.
 */
public class KuaiPaiSort {
    public static void main(String...args){
     while(true) System.out.println("333");
    }

    public static void  qucikSort(int[] a,int low,int high){
        if(low>high) return;
        int left = low;
        int right = high;
        int mark = a[left];
        while(left!=right){

            //从右边
            while(left<right&&a[right]>mark){
                right--;
            }

            while(left<right&&a[left]<=mark){
                left++;
            }

            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;
        }

        a[low] = a[left];
        a[left] = mark;
        System.out.println(Arrays.toString(a));
        qucikSort(a, low, left - 1);
        qucikSort(a,left+1,high);
    }
}
