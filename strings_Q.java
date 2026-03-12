import java.util.*;

public class strings_Q {

    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        int n1 = words1.length;
        int n2 = words2.length;
        //System.out.println("n1="+n1 +",n2="+n2);
        int count = 0;

        for (int i = 0; i < n1; i++) {
            map1.put(words1[i], (map1.getOrDefault(words1[i], 0)) + 1);
        }
        for (int i = 0; i < n2; i++) {
            map2.put(words2[i], (map2.getOrDefault(words2[i], 0)) + 1);
        }


       // System.out.println("map1="+map1.keySet().size() +",val2="+map2.keySet().size());

        if (map1.keySet().size() < map2.keySet().size()) {
            for (String key : map1.keySet()) {
                int val1 = map1.getOrDefault(key, 0);
                int val2 = map2.getOrDefault(key, 0);
                if (val1 ==1 && val2== 1)

                {
                  
                  count++;
                } else {
                    
                    continue;
                }
            }

        } else {
           
            if (map1.keySet().size() >= map2.keySet().size())
               {  
                for (String key : map2.keySet()) 
                    {
                    int val1 = map1.getOrDefault(key, 0);
                    int val2 = map2.getOrDefault(key, 0);
                    //System.out.println("key="+key+",val1="+val1+",val2="+val2);
                    if (val1 ==1 && val2== 1)
                        {  
                          count++;
                        } else {
                            
                            continue;
                        }
                }}
        }
        return count;

    }

    public static void main(String args[]) {
        // String[] w1 = { "leetcode", "is", "amazing", "as", "is" };
        // String[] w2 = { "amazing", "leetcode", "is" };
        strings_Q obj = new strings_Q();
        // int n = obj.countWords(w1, w2);
        // System.out.println(n);
        int[] height = new int[]{2,1,0,1,3,2};   
        //int []leftmaxcal =obj.righmaxcal(height, height.length);
        int n =height.length;
    int [] leftmax= obj.leftmaxcal(height,n);
    int [] rightmax= obj.righmaxcal(height,n);
    int count =0;
    for (int i=0;i<n;i++)
    {
        if(Math.min(leftmax[i],rightmax[i])>height[i])
        {
            int water= Math.min(leftmax[i],rightmax[i])-height[i];
            count =count+water;
        }
    }
        System.out.println(count);
        //obj.printArr(leftmax);

    //     List<List<Integer>> l =new ArrayList<>();
    //     int[] nums = new int[]{0,0,1};
    //     if(nums.length==3)
    //         {
    //             if( (nums[0]+nums[1]+nums[2])==0)
    //             {   
    //              List<Integer> l1 = new ArrayList<>(Arrays.asList(nums[0],nums[1],nums[2]));

    //                 l.add(l1);
    //             }
    //             else
    //             {
    //                System.out.println("here");
    //                 }
    //         }

    //     System.out.println(l);
            
    //     // String[] nums = {"01","11"};
    //     // char[] ar= new char[nums.length];
    //     // int i=0;
    //     // for (String num: nums )
    //     // {
    //     //     char ch = num.charAt(i);
        
    //     //     ar[i]= (ch=='0')?'1':'0';
    //     //     System.out.println(ar[i]);
    //     //     i++;
    //     // }

    //     // String ar1= new String (ar);
    //     // System.out.println(ar1);
        
       
    //     // int n= nums.length;
    //     // System.out.println(1&3);
    // }}
}
public void printArr(int [] arr)
{
    for (int a : arr)
    {
        System.out.print(a+ ", ");
    }
    System.out.println();
}

    public void frequency() {
        String str = "Hello world world!";
        Map<String, Integer> count = new HashMap<>();
        String bufStr = "";
        str = str.toLowerCase();
        int n = str.length();
        // System.out.println(str.toLowerCase().toCharArray());
        for (int i = 0; i <= n; i++) {
            try {
                char s = str.charAt(i);
                if (s >= 'a' && s <= 'z') // [a-z]
                {

                    bufStr = bufStr + s;
                    // System.out.println(bufStr);

                } else {
                    if (bufStr.length() > 1) {
                        // System.out.println("adding here");
                        count.put(bufStr, count.getOrDefault(bufStr, 0) + 1);
                        bufStr = "";
                    }

                }
            } catch (Exception e) {
                if (bufStr.length() > 1) {
                    System.out.println("here");
                    count.put(bufStr, count.getOrDefault(bufStr, 0) + 1);
                    bufStr = "";
                }
            }

        }
        for (Map.Entry<String, Integer> e : count.entrySet()) {
            System.out.println(e.getKey() + "," + e.getValue());
        }
    }

    public String findDifferentBinaryString() {
  
        String[] nums = {"01","11"};
        int n= nums.length;
        System.out.println(nums[0]);
       return ""; 
    }


    public int[] leftmaxcal(int [] arr, int n)
    {
        int [] leftmax= new int[n];
        int max= arr[0];
        leftmax[0]= max;
        for(int i=1;i<n;i++)
        {
        max= Math.max(max,arr[i]);
        leftmax[i]= max;
        }
        return leftmax;

    }
    public int[] righmaxcal(int [] arr, int n)
    {
        int [] rightmax= new int[n];
        int max= arr[n-1];
        rightmax[n-1]= max;
        for(int i=n-2;i>=0;i--)
        {
        max= Math.max(max,arr[i]);
        rightmax[i]= max;
        System.out.println("here"+rightmax[i]);
        }
        return rightmax;

    }

    public static String timeConversion(String s) {
       
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

