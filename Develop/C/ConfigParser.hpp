#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <vector>
#include <map>
#include <algorithm>
#include <cctype>
#include <locale>
#include <cstdlib>
#include <string.h>

using namespace std;

class ConfigParser
{
    public:
        ConfigParser (void)
        {
            loadFlag = false;
            strFileName = "";
        }

        /**
         * @author  wonsool(wsk2001@gmail.com)
         * @brief   설정 파일(.ini, .properties, .conf) 을 읽어서 memory 에 load 한다.
         *
         * @param   fileName    [in]    설정 파일명
         * @param   delimiter   [in]    key 와 value 사이의 구분자.
         * @param   useSection  [in]    section 을 사용 하는지 선택
         * @return  정상 load 시 true, 실패시 false
         * 
         * @remark  첫 번째 위치에 '#', ';' 오면 해당 line 을 skip 한다.
         *          해당 line 의 중간에 '#' 이 오면 이후는 삭제 한다. (주석 으로 인식)
         *          해당 line 의 중간에 '#' 이 오더라도 이전 문자가 space 또는 tab 이 아니면
         *          주석으로 인식 하지 않는다.
         *          .ini 는 section, key, value 의 구조를 가지고 있으며, .properties, .conf 는
         *          key, value 만 가지고 있는 구조임, 이 함수 에서는 useSection 을 이용하여 파일을 읽어서
         *          메모리에 올리는 방법이 결정 됨.
         */
        bool load (const char* fileName, const char* delimiter, bool useSection)
        {
            FILE*  fr      = (FILE*) NULL;
            char   Buff[ 256 ];
            char*  p       = NULL;
            string section = "";
            string key     = "";
            string value   = "";

            strFileName = string(fileName);

            fr = fopen( fileName, "r");
            _useSection = useSection;
            
            if( fr == (FILE*) NULL )
                return false;

            
            for( ; !feof(fr)  ; )
            {
                memset( Buff, 0x00, sizeof(Buff) );
                fgets( Buff, sizeof( Buff )-1, fr);

                // Skip line
                if( Buff[0] == ';' || Buff[0] == '#')
                    continue;

                ltrim(Buff);
                rtrim(Buff);
                if( strlen(Buff) <= 0)
                    continue;

                mtrim( Buff );

                // get section
                if( useSection == true)
                {
                    p = strchr(Buff, '[');
                    if( p != NULL )
                    {
                        p = strrchr( Buff, ']' );
                        if( p != NULL )
                        {
                            *p = (char) NULL;
                             p = &Buff[1];
                             section = string(p);
                        }
                        continue;
                    }
                }

                // check comment
                p = strrchr( Buff, ';' );
                if( p == NULL )
                    p = strrchr( Buff, '#' );
                
                // remove comment
                if( p != NULL )
                {
                    // space or tab
                    if( *(p-1) == 0x20 || *(p-1) == 0x09 )
                        *p = (char) NULL;
                }

                string s = string(Buff);
                size_t pos = 0;
                string token;

                key   = "";
                value = "";

                for( int i = 0 ; pos != string::npos ; i++ ) 
                {
                    pos = s.find(delimiter);

                    token = s.substr(0, pos);
                    if( i == 0 )
                        key = token;
                    else if ( i == 1)
                        value = token;

                    s.erase(0, pos + 1);
                }

                if( useSection == true )
                {
                    string newKey = section + "#" + key;
                    map_cfg.insert(pair<string,string> (newKey,value));
                }
                else
                    map_cfg.insert(pair<string,string> (key,value));

                memset( Buff, 0x00, sizeof(Buff) );
            }
            
            fclose( fr );
            loadFlag = true;

            return true;
        }

        /**
         * @author  wonsool(wsk2001@gmail.com)
         * @brief   설정 파일에 등록된 key 를 이용하여 value 를 조회 한다
         *
         * @param   key             [in]    설정 key
         * @param   defaultValue    [in]    key 및 value 가 등록 되지 않았을 경우 되돌려줄 기준값
         * @return  정상 조회시 value, 등록되지 않은 key 일 경우 defaultValue
         * 
         * @remark  .conf, .properties 를 load 한 경우 이 함수를 호출 하여 등록 값을 조회 한다.
         */ 
        string getValueStr(string key, string defaultValue)
        {
            string value = map_cfg.find(key)->second;

            if( value.length() <= 0)
                return defaultValue;
            else
                return value;
        }

        /**
         * @author  wonsool(wsk2001@gmail.com)
         * @brief   설정 파일에 등록된 key 를 이용하여 value 를 조회 한다
         *
         * @param   section         [in]    section 
         * @param   key             [in]    설정 key
         * @param   defaultValue    [in]    key 및 value 가 등록 되지 않았을 경우 되돌려줄 기준값
         * @return  정상 조회시 value, 등록되지 않은 key 일 경우 defaultValue
         * 
         * @remark  .ini 를 load 한 경우 이 함수를 호출 하여 등록 값을 조회 한다.
         */ 
        string getValueStr(string section, string key, string defaultValue)
        {
            string newKey = section + "#" + key;
            string value = map_cfg.find(newKey)->second;

            if( value.length() <= 0)
                return defaultValue;
            else
                return value;
        }

        /**
         * @author  wonsool(wsk2001@gmail.com)
         * @brief   설정 파일에 등록된 key 를 이용하여 value 를 int 형 으로 조회 한다
         *
         * @param   key             [in]    설정 key
         * @param   defaultValue    [in]    key 및 value 가 등록 되지 않았을 경우 되돌려줄 기준값
         * @return  정상 조회시 value, 등록되지 않은 key 일 경우 defaultValue
         * 
         * @remark  .conf, .properties 를 load 한 경우 이 함수를 호출 하여 등록 값을 조회 한다.
         */ 
        int getValueInt(string key, int defaultValue)
        {
            string value = map_cfg.find(key)->second;

            if( value.length() <= 0)
                return defaultValue;
            else
                return atoi(value.c_str());
        }

        /**
         * @author  wonsool(wsk2001@gmail.com)
         * @brief   설정 파일에 등록된 key 를 이용하여 value 를 int 형 으로 조회 한다
         *
         * @param   section         [in]    section 
         * @param   key             [in]    설정 key
         * @param   defaultValue    [in]    key 및 value 가 등록 되지 않았을 경우 되돌려줄 기준값
         * @return  정상 조회시 value, 등록되지 않은 key 일 경우 defaultValue
         * 
         * @remark  .ini 를 load 한 경우 이 함수를 호출 하여 등록 값을 조회 한다.
         */ 
        int getValueInt(string section, string key, int defaultValue)
        {
            string newKey = section + "#" + key;
            string value = map_cfg.find(newKey)->second;

            if( value.length() <= 0)
                return defaultValue;
            else
                return atoi(value.c_str());
        }

        /**
         * @author  wonsool(wsk2001@gmail.com)
         * @brief   설정 파일에 등록된 [section], key, value 를 console 화면에 표시 한다. 
         *
         * @param   없음 
         * @return  없음
         */ 
        void printConfig(void)
        {
            printf("\n");
            printf("%s config list\n", strFileName.c_str() );
            printf( "\n");
            int i = 0;

            for ( std::map<string,string>::iterator it = map_cfg.begin(); 
                  it != map_cfg.end(); ++it)
            {
                if( _useSection )
                {
                    string s = it->first.c_str();
                    string token;
                    size_t pos;

                    pos = s.find('#');
                    if( pos != string::npos )
                    {
                       printf( "%s,%s,%s\n", s.substr(0, pos).c_str()
                                                , s.substr(pos+1, pos+s.length()).c_str()
                                                , it->second.c_str() );
                    } 
                }
                else
                {
                    printf( "%s,%s\n", it->first.c_str(), it->second.c_str());
                }
            }
        }
    private:
        /**
         * @author  wonsool(wsk2001@gmail.com)
         * @brief   string 형 data 의 앞쪽 공백을 제거 한다. 
         *
         * @param   s       [in]     공백을 제거할 string 변수
         * @return  없음
         * @remark  Lambda 함수 사용함.
         */ 
        static inline void ltrim(std::string &s) {
            s.erase(s.begin(), std::find_if(s.begin(), s.end(), [](int ch) {
                return !std::isspace(ch);
            }));
        }

        /**
         * @author  wonsool(wsk2001@gmail.com)
         * @brief   string 형 data 의 뒤쪽 공백을 제거 한다. 
         *
         * @param   s       [in]     공백을 제거할 string 변수
         * @return  없음
         * @remark  Lambda 함수 사용함.
         */ 
        static inline void rtrim(std::string &s) {
            s.erase(std::find_if(s.rbegin(), s.rend(), [](int ch) {
                return !std::isspace(ch);
            }).base(), s.end());
        }

        /**
         * @author  wonsool(wsk2001@gmail.com)
         * @brief   char* (array) 형 data 의 앞쪽 공백을 제거 한다. 
         *
         * @param   TrimStr       [in]     공백을 제거할 string 변수
         * @return  없음
         * 
         */ 
        static inline void ltrim( char *TrimStr )
        {
            int  len = 0;
            char Buff[ 128 ];
            char *p = NULL;

            memset( Buff, 0x00, sizeof( Buff ));
            p = TrimStr;

            for( ; *p != (char) NULL; p++)
            {
                if( *p == 0x20 )
                    continue;
    
                memcpy( Buff, p, strlen(p));
                break;
            }

            len = strlen(Buff);
            memset( TrimStr, 0x00, strlen( TrimStr ));
            memcpy( TrimStr, Buff, len);
        }

        /**
         * @author  wonsool(wsk2001@gmail.com)
         * @brief   char* (array) 형 data 의 뒤쪽 공백을 제거 한다. 
         *
         * @param   TrimStr       [in]     공백을 제거할 string 변수
         * @return  없음
         * @remark  공백 및 cr, lf 도 제거 한다.
         */ 
        static inline void rtrim( char *TrimStr )
        {
            int len = strlen( TrimStr );

            len--;
            TrimStr += len;

            while( 0 <= len )
            {
                if( *TrimStr == 0x20 || *TrimStr == 0x00 ||
                    *TrimStr == 0x0d || *TrimStr == 0x0a )
                    *TrimStr-- = 0x00;
                else 
                    break;
                len--;
            }
        }

        /**
         * @author  wonsool(wsk2001@gmail.com)
         * @brief   char* (array) 형 data 의 중간 에 있는 공백과 TAB 을 제거 한다. 
         *
         * @param   s       [in]     공백을 제거 할 변수
         * @return  없음
         */ 
        static inline void mtrim( char *s )
        {
            int   len = strlen( s );
            char  Buff[ 4096 ];
            char* p = s;
            bool  findFlag = false;
            int   pos = 0;
            int   i;

            memset( Buff, 0x00, sizeof( Buff) );

            for( i = 0, pos = 0; i < len; i++, p++ )
            {
                // space, tab
                if( *p == (char) 0x20 || *p == 0x09)
                {
                    findFlag = true;
                    continue;    
                }
                
                if( findFlag == true)
                {
                    Buff[pos++] = (char) 0x20;
                    Buff[pos++] = *p;
                    findFlag = false;
                }
                else
                {
                    Buff[pos++] = *p;
                }
                    
            }

            sprintf(s, "%s", Buff );
        }

    private:
        std::map <string,string> map_cfg; 
        bool _useSection;
        bool loadFlag;
        string strFileName;
};
