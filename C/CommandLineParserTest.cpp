#include <stdio.h>
#include <stdlib.h>

#include "CommandLineParser.hpp"

void setOption(CmdLineParser * parser)
{
    parser->addOption("-h","help");
    parser->addOption("-conf","Load config file");
    parser->addOption("-sleep","Sleep milliseconds, default value is '0'");
    parser->addOption("-diskLimit","Free disk space, limit(MBytes, ex: 500)");

    parser->addOption("-ll","Write log level, default values is 'WARNING'\n\t\t\t"
                     "Available options are 'TRACE', 'DEBUG', 'INFO', 'WARNING', 'ERROR', and 'FATAL'.");

}

int main(int argc, char **argv)
{ 
    CmdLineParser cmdparams;

    cmdparams.load(argc, argv);
    setOption( &cmdparams );

    cmdparams.setUsage(argv[0],"-conf sc_enc.conf -sleep 10 -ll ERROR");

    if( cmdparams.validationCheck() == false )
    {
        cmdparams.printOptions();
        exit( 0 );
    }
      
    if( cmdparams.cmdOptionExists("-h")  || 
        cmdparams.cmdOptionExists("--h") || 
        cmdparams.cmdOptionExists("/h")  ||
        cmdparams.cmdOptionExists("/?") )
    {
        cmdparams.printOptions();
        exit( 0 );
    }
    
    if( argc < 2 )
    {
        cmdparams.printOptions();
        exit( 0 );
    }

    std::string log_level = cmdparams.getCmdOption("-ll");
    if (!log_level.empty()){
        printf( "log level = %s\n", log_level.c_str() );
    }
    
    return 0;
}

/*
 compile: g++ -o CommandLineParserTest CommandLineParserTest.cpp
 run test: CommandLineParserTest -ll DEBUG
*/
