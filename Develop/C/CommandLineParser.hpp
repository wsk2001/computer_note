#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

std::string upperString( std::string str )
{
    std::string v = str;

    std::transform(v.begin(), v.end(),v.begin(), ::toupper);

    return v;
}

class CmdLineParser
{
    public:
        CmdLineParser (void)
        {
        }

        void load (int &argc, char **argv)
        {
            Program = argv[0];

            for (int i = 1; i < argc; ++i) {
                std::string key = argv[i];

                if( key.at(0) == '-' || key.at(0) == '/')
                    this->tokens.push_back( upperString(key) ); 
                else
                    this->tokens.push_back(std::string(argv[i]));
            }
        }
        
        const std::string& getCmdOption(const std::string &option) const
        {
            std::vector<std::string>::const_iterator itr;
            
            itr =  std::find(this->tokens.begin(), this->tokens.end(), upperString(option));
            
            // return to next paramater
            if (itr != this->tokens.end() && ++itr != this->tokens.end())
            {
                return *itr;
            }
            
            static const std::string empty_string("");
            
            return empty_string;
        }
        
        bool cmdOptionExists(const std::string &option) const
        {
            return std::find(this->tokens.begin(), this->tokens.end(), upperString(option))
                   != this->tokens.end();
        }

        void addOption( std::string key, std::string value )
        {
            map_options.insert(pair<string,string> (key,value));
            this->options.push_back( upperString(key) ); 
        }
        
        std::string getOption(std::string key)
        {
            std::string value = map_options.find(key)->second;
            
            return value;
        }
        
        void printOptions(void)
        {
            printf("\n");
            printf("Usage: %s [OPTION]...\n", Program.c_str());
            printf( "\n");

            for ( std::map<std::string,std::string>::iterator it = map_options.begin(); 
                  it != map_options.end(); ++it)
            {
                if( 8 <= it->first.length())
                    printf( "%s\t\t%s\n", it->first.c_str(), it->second.c_str());
                else
                   printf( "%s\t\t\t%s\n", it->first.c_str(), it->second.c_str());
            }

            if( 0 < UsageMsg.length() )
            {
                printf( "\n" );
                printf( "example: %s\n", UsageMsg.c_str() );
                printf( "\n" );
            }
        }

        void setUsage(std::string prog, std::string msg)
        {
            UsageMsg =  prog + " " + msg;
        }

        bool validationCheck(void)
        {
            bool bResult = true;

            for ( std::vector<std::string>::iterator it = tokens.begin(); 
                  it != tokens.end(); ++it)
            {
                if( it->at(0) == '-' || it->at(0) == '/' )
                {
                    if ( std::find(options.begin(), options.end(), it->c_str()) == options.end() )
                    {
                        bResult = false;
                        break;
                    }
                }
            }
            return bResult;
        }   

    private:
        std::vector <std::string> tokens;
        std::map <std::string,std::string> map_options; 
        std::string Program;
        std::string UsageMsg;
        std::vector <std::string> options;
};


