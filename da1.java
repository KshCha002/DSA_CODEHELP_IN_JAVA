import java.util.Scanner;
class Main {
    public static void main(String[] args) {

    int [] ar2= new int [10];
            ar2=new int [] {1,2,43,-4,2,5,6,7,8,90};
            
    Main a =new Main();

           // a.reverseArr(ar2,0);
            //a.printAr(ar2);
            int x= a.fib(2);
            System.out.printf("x= %d ",x);

}
    void printAr(int []ar)
    {
        int n =ar.length;
        System.out.println("\n Array elements are: ");
        for (int i=0;i<n;i++)
        {
            System.out.println(ar[i]);
        }
    }
    void reverseArr(int []ar,int i)
    {
        int n=ar.length;
        if (i>(n/2))
            return ;
        Swap(ar,i,(n-1-i));
        reverseArr(ar, i+1);
        return;
        
    }

    void Swap(int []ar ,int i,int j)
    {
        int temp=ar[i];
        ar[i]=ar[j];
        ar[j]=temp;
    }
    int fib(int n)
    {int sum =0;
    if (n<=1)
        return n;
    int left =fib(n-1);
    int right = fib(n-2);
     sum=left +right;
     return sum ;
    }

}