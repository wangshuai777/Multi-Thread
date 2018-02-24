package test;

import java.util.*;

/**
 * Created by wangshuai on 2018/2/1.
 */
public class Test01 {

    static int count;
    int i;

   /* static{
        System.out.println("Static Initializer...");
        System.out.println("Count when Static Initializer is run is="+count);
    }

    {
        i=6;
        count=count+1;
        System.out.println("Count when Instance Initializer is run is="+count);

    }

    public Test01(){
        System.out.println("test01....");
    }
*/
    public static int sum(int... numbers ){
      int sum=0;
        for(int num:numbers){
            sum+=num;
        }
        return sum;
    }

    private static int computerSimpleInterest(int principal,float interest,int years){
       assert(false);
        return 100;
    }

    public static void method(){
        Calendar calendar =new GregorianCalendar(2000,10,30);
        System.out.println(calendar);
    }

    private static void tokenize(String str,String regex){
           String[] tokens=str.split(regex);
        System.out.println(Arrays.toString(tokens));
    }

    private static void tokenizeUsingScanner(String str,String regex){
       Scanner scanner= new Scanner(str);
        scanner.useDelimiter(regex);
        List<String> matches=new ArrayList<String>();
        while(scanner.hasNext()){
            matches.add(scanner.next());
        }
        System.out.println(matches);
    }

    public static void main(String[] args) {
        System.out.println("begin test ...");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.MONTH, 8);
        System.out.println(calendar.getTime());
        System.out.println("end test ...");
    }

}
