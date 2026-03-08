func reverseString(s []byte)  {
    n:= len(s)
    i:=0
    j:=n-1
    for ; i<j;{
        swap(s,i,j)
        i=i+1
        j=j-1

    }
}
func swap (s []byte,i int,j int){ //check if references is passed or not
temp:= s[i]
s[i]= s[j]
s[j]=temp
}