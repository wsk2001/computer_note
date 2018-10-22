#!/bin/bash 

usage(){
	echo "Usage: $0 pid"
	exit 1
}

if [ $# -ne 1 ] ; then
    usage
fi

while : 
do 
	cat /proc/$1/status | grep VmSize
	date
	
	sleep 5 
done

