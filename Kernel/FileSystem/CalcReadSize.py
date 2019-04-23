#!/usr/bin/python
#-*- coding: utf-8 -*-
#-*- coding: cp949 -*

import sys
import os

FILE_BLOCK_SIZE = 4096

def calc_read_param(offset, size):
    first_offset = 0
    first_data_size = 0
    last_offset = 0
    last_data_size = 0
    diff_offset = 0
    blockCount = 0
    partialSize = 0

    if (offset % FILE_BLOCK_SIZE) == 0:
        first_offset    = offset
        diff_offset     = 0
        first_data_size = size if size <= FILE_BLOCK_SIZE else FILE_BLOCK_SIZE
        blockCount      = (int)(size /  FILE_BLOCK_SIZE)
        partialSize     = size %  FILE_BLOCK_SIZE
        last_offset     = 0
        last_data_size  = 0
        if 0 < partialSize:
            last_offset = offset + blockCount * FILE_BLOCK_SIZE
            if first_offset != last_offset:
                last_data_size  = partialSize
            blockCount = blockCount + 1
    else:
        first_offset = (int) (offset / FILE_BLOCK_SIZE) * FILE_BLOCK_SIZE
        diff_offset  = offset - first_offset
        first_data_size = size if (size < FILE_BLOCK_SIZE - diff_offset) else FILE_BLOCK_SIZE - diff_offset 
        blockCount      = (int)((size-first_data_size) /  FILE_BLOCK_SIZE)
        partialSize     = (size-first_data_size) %  FILE_BLOCK_SIZE

        if 0 < first_data_size:
            blockCount = blockCount + 1

        if 0 < partialSize:
            last_offset = first_offset +  blockCount * FILE_BLOCK_SIZE
            last_data_size  = partialSize
            blockCount = blockCount + 1

    print ("offset          = " + str(offset) )
    print ("size            = " + str(size) )
    print ("first_offset    = " + str(first_offset) )
    print ("first_data_size = " + str(first_data_size) )
    print ("last_offset     = " + str(last_offset) )
    print ("last_data_size  = " + str(last_data_size) )
    print ("diff_offset     = " + str(diff_offset) )
    print ("blockCount      = " + str(blockCount) )
    print ("partialSize     = " + str(partialSize) )

def main():
    if 2 <= len(sys.argv):
        calc_read_param( int(sys.argv[1]), int(sys.argv[2]))

if __name__ == "__main__":
    main()