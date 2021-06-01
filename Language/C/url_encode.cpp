#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#define IS_ALNUM(ch) \
         ( ch >= 'a' && ch <= 'z' ) || \
         ( ch >= 'A' && ch <= 'Z' ) || \
         ( ch >= '0' && ch <= '9' ) || \
         ( ch >= '-' && ch <= '.' ) 

using namespace std;

//char* url_decode( const char* str ){
string url_decode( const char* str ){

    int i, j = 0, len;
    char* tmp;
    char hex[3];

    len = strlen( str );
    hex[2] = 0;
    
    tmp = (char*)malloc( sizeof(char) * (len+1) );
    
    for( i = 0 ; i < len ; i++, j++ ){

        if( str[i] != '%' )
            tmp[j] = str[i];

        else{

            if( IS_ALNUM(str[i+1]) && IS_ALNUM(str[i+2]) && i < (len-2) ){

                hex[0] = str[i+1];
                hex[1] = str[i+2];
                tmp[j] = strtol( hex, NULL, 16 );

                i += 2;
                
            }
            else
                tmp[j] = '%';
            
        }
        
    }
    tmp[j] = 0;
    
    //return tmp;
    return string(tmp);
    
}

//char* url_encode( const char* str ){
string url_encode( const char* str ){

    int i, j = 0, len;
    
    char* tmp;
    
    len = strlen( str );
    tmp = (char*) malloc( (sizeof(char) * 3 * len) +1 );

    for( i = 0 ; i < len ; i++ ){

        if( IS_ALNUM( str[i] ) )
            tmp[j] = str[i];

        else{
        
            snprintf( &tmp[j], 4, "%%%02X\n", (unsigned char)str[i] );
            j += 2;

        }
        j++;
        
    }
    tmp[j] = 0;
    
	//return tmp;
	return string(tmp);
}
