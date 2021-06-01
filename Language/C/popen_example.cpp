#include <windows.h>
#include <stdio.h>
#include <conio.h>
#include <string>
#include <sstream>
#include <iostream>

using namespace std;

int main()
{
   
	FILE *fp = popen("cmd.exe", "r+" );
	char buff[50];
	char command[50];
	
	while (fp != NULL)
	{
		while ( fgets( buff, sizeof buff, fp ) != NULL ) 
		{
		  cout << buff;
		}
		cin.getline(command, sizeof command);
		fputs(command, fp);
	}
	
	pclose( fp );
	getch();  
   
}
