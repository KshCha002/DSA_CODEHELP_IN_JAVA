package main

import (
	"bufio"
	"fmt"
	"os"
)

func main(){
	// fmt.Println("Variable")
	var a int  = 9
	//var b bool= false
	// var s string ="hii"
	// var smalF float32 =3.4
	var x float32 = 5.00
	fmt.Printf("Value of a is :%d and Type is %T\n",a,a)

	//fmt.Printf("Value of b is :%g and Type is %T",b,b)
	fmt.Printf("Value of x is :%g and Type is %T \n",x,x)
	y:=6
	fmt.Printf("Value of y is :%d and Type is %T\n",y,y)
	y = 60


	//user input
	reader:= bufio.NewReader(os.Stdin) //uses packages bufio and os
	fmt.Println("Hi enter stuff")
	input,_:=reader.ReadString('\n')
	fmt.Println("here is stuff",input)
	fmt.Printf("here is stuff Type is %T",input)

}