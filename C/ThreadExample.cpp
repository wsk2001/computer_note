#include <stdio.h>
#include <stdlib.h>
#include <memory.h>

#include <iostream>
#include <map>
#include <iostream>
#include <string>
#include <thread>
#include <mutex>

#ifdef _WIN64
#include <windows.h>
#else
#include <unistd.h>
#endif // WIN32

using namespace std;
std::mutex mtx_lock;

int gIntCount = 0;

void SleepMillisecond(unsigned int ms)
{
#ifdef _WIN64
	::Sleep(ms);
#else
	usleep(1000*ms);
#endif
}

void Worker(int count)
{
    int i = 0;

    for( i = 0; i < count; i++ )
    {
        mtx_lock.lock();
        gIntCount++;
        mtx_lock.unlock();

        printf( "i = %d\n", i );
        fflush(stdout);

        SleepMillisecond( 500 );
    }
}

int main(int argc, char** argv)
{
    thread th_arr[16];
    int    i = 0;

    gIntCount = 0;

    for( i = 0; i < 5; i++ )
        th_arr[i] = std::thread(Worker, (i+1) * 10 );

    for( i = 0; i < 5; i++ )
        th_arr[i].join();

    printf( "\n\n");
    printf( "gIntCount = %d\n", gIntCount );
    printf( "\n");

}

/*
 Visual c/c++ 2010 이후 version 으로 컴파일 : cl ThreadExample.cpp
 Linux g++ 을 이용한 컴파일 : g++ --std=c++11 -pthread -o ThreadExample ThreadExample.cpp
*/
