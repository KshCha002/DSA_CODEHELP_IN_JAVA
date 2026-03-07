
func countWords(word1 []string, word2 []string) int {
	var n int =0
var map1 = map[string]int{}
for  s := range word1{
	str := word1[s]
	if val,ok:= map1[str];
	ok{
		map1[str]= val+1
	}else{
		map1[str]= 1
	}
}

for  s := range word2{
	str := word2[s]
	if value,ok :=map1[str]
	ok{
		if(value<=1){
			map1[str]=value-1
		}
	}
}
for _,value:= range map1{
	if(value==0){
		n=n+1
	}
}

	return  n

}
