import os
import sys
import pathlib
from urllib.request import urlopen

def makePath(path):
    if not os.path.exists(path):
        print('mkdir: star ' + path)
        pathlib.Path(path).mkdir(parents=True, exist_ok=True)
        print( 'mkdir: complete')

def download(img_url, file_name):
    with urlopen(img_url) as f:
        with open(file_name, 'wb') as h:
            img = f.read()
            h.write(img)


# SrcMarkdownFile = input('검색 Markdown 파일명을 입력 하세요: ')
# BaseURL = input('검색 기준 문자열을 입력 하세요: ')
# SaveBasePath = input('저장 기준 경로를 입력 하세요: ')

SrcMarkdownFile = '플라스크 메가 튜토리얼.md'
BaseURL = 'https://blog.miguelgrinberg.com/static/images'

SaveBasePath = './images'

try:
    file = open(SrcMarkdownFile, 'rt',  encoding='utf-8')
    while True:
        line: str = file.readline()
        if line == '' :
            break
        line = line.rstrip('\n')

        if BaseURL in line:
            FullUrl = line.replace('](','|').replace(')','').split('|')[1]
            SaveUrl = SaveBasePath + FullUrl.replace(BaseURL,'')
            makePath(os.path.dirname(SaveUrl))
            download(FullUrl,SaveUrl)
            print('download : ' + FullUrl)

    file.close()

except IOError:
    print
    'Cannot read file : ' + SrcMarkdownFile
    sys.exit(0)
