import java.util.*;

public class timeConv {

 
    public static void main(String args[]) {
    timeConv oj =new timeConv();
    String arr= "12:05:45AM";
    String ans = oj.timeConversion(arr);
System.out.println(ans);
      
}
public void printArr(int [] arr)
{
    for (int a : arr)
    {
        System.out.print(a+ ", ");
    }
    System.out.println();
}
    public String timeConversion(String s) {
       
        String ampm = "";
        if(s.endsWith("AM") && s.startsWith("12"))
        {
            ampm="00"+s.substring(2,8);
        }
        else if(s.endsWith("PM") && !s.startsWith("12") )
        {
            int n = Integer.parseInt(s.substring(0, 2))+12;
            ampm=Integer.toString(n)+s.substring(2,8);
        }
        else {
            ampm=s.substring(0,8);
        }
        return ampm;
        }
}

