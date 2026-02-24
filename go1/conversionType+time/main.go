package main

import (
	
	"fmt"
	
	"strconv"
	"strings"
	"time"
)


func main(){
fmt.Println("Hi Enter Value")
//reader:= bufio.NewReader(os.Stdin)
input,_:="aaaa",1
fmt.Println("Read",input)
numVale,err :=strconv.ParseInt(strings.TrimSpace(input),2,64)
if err!=nil{
	fmt.Println(err)
}
numVale1,err :=strconv.ParseFloat(strings.TrimSpace(input),64)
if err!=nil{
	fmt.Println(err)
}
fmt.Println(numVale)
fmt.Println(numVale1)

var t1 time.Time =time.Now()
t2:=time.Now()
fmt.Println(t1)
fmt.Println(t2.Local().Format("02-Jan-06 Mon, 15:04:05"))
t3:=time.Date(2027,time.February,time.Now().Day(),12,12,12,12,time.Local)
fmt.Println(t3.Format("02-Jan-06 Mon, 15:04:05"))

//to create executables ...must have run go mod init before -> go build        
// \ GOOS="windows" go build



}