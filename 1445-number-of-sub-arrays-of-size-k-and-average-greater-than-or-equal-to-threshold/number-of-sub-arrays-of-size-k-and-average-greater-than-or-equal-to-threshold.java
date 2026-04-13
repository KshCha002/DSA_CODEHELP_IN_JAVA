class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int i=0;
        int j=0;
        int avg=0;
        int count=0;
        int n=arr.length;
        
        while(j<k && j<n)
        {
            avg=avg+arr[j];
            j++;
           
        }
if(avg/k>=threshold)
{count++;
 System.out.println(count);}
while(j<n){
        avg=avg-arr[i];
        avg=avg+arr[j];
        i++;
        j++;
        if(avg/k>=threshold)
            count++;
    }

    return count;
}}