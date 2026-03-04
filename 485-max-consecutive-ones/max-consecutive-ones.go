func findMaxConsecutiveOnes(nums []int) int {
    n:= len(nums)
    var coun = 0
    var max=0
    for i:= 0;i<n;i++{
        if nums[i]!=1{
            coun = 0
        }else{
            coun=coun+1
            if max<coun{
            max=coun
        }
        }
        
    }
    return max

}