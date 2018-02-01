package test;

/**
 * Created by wangshuai on 2018/2/1.
 */
public class Test01 {

    public int sum(int... numbers ){
      int sum=0;
        for(int num:numbers){
            sum+=num;
        }
        return sum;
    }

}
