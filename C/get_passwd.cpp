#include <iostream>
#include <string>
#include <unistd.h>

using namespace std;

int main(){
   string pass ="";
   char ch;
   char *password = getpass("Password: ");
   
   pass = password;
   
   if(pass == "Passwd"){
      cout << "\nAccess granted :P\n";
   }else{
      cout << "\nAccess aborted...\n";
   }
}
