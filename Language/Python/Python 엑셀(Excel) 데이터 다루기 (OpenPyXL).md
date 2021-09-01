# Python 엑셀(Excel) 데이터 다루기 (OpenPyXL)

출처: https://needjarvis.tistory.com/640

### **차례**

- [테스트에 사용한 엑셀](https://needjarvis.tistory.com/640#h)
- [모든 데이터 읽어오기](https://needjarvis.tistory.com/640#h_1)
- [특정 데이터 읽기](https://needjarvis.tistory.com/640#h_2)
- [엑셀 쓰기](https://needjarvis.tistory.com/640#h_3)

엑셀을 쉽게 읽는 방법으로 판다스(Pandas)를 활용하는 방법도 있지만, OpenPyXL을 사용하여 엑셀을 디테일하게 컨트롤 할 수 있다. 

 



![img](./images/img.jpg)엑셀 데이터 읽기/쓰기 (OpenPyXL)



 

## **테스트에 사용한 엑셀**



![img](C:\home\rnd\python\airdrop\doc\images\img_02.png)테스트에 사용한 엑셀

[faq.xlsx0.01MB](https://blog.kakaocdn.net/dn/bogy8i/btqS6YO3C6M/AX6HKbQL6ezG4OupoPTyqk/faq.xlsx?attach=1&knm=tfile.xlsx)



키움증권과 카카오에 있는 FAQ 내용을 몇개 가져와서 2개의 sheet를 만든 구조이며 컬럼은 질문과 답변으로 되어 있는 구조이다.

 

## **모든 데이터 읽어오기**

``` python
import openpyxl as xl

wb = xl.load_workbook('C:/Project/steel/faq.xlsx')

for sheet_nm in wb.sheetnames:
    print('*' * 100)
    print('시트명:', sheet_nm)
    sheet = wb[sheet_nm]

    for row_data in sheet.iter_rows(min_row=1):	# min_row는 시작 행을 지정
        for cell in row_data:
            print('[', cell.value, ']')
        print('=' * 100)

wb.close()
```

sheetnames를 사용하면, sheet 명을 가져올 수 있고, 이를 통해 모든 데이터를 읽어올 수 있다. 워크북(workbook)에 시트명을 입력하면 해당 시트 데이터를 불러올 수 있고, iter_rows 함수를 사용하여 로우 데이터를 가져온다. min_row를 1로 할 경우 처음부터 가져오며, 헤더 이후 값을 가지고 오고 싶을 경우, min_row=2를 지정한다.

 

row_data의 값은 셀(cell)값들로 이루어져 있으며, 셀의 값을 가져오려면 cell.value를 사용해야 한다. 모든 작업이 끝날 경우, 워크북을 close하여 엑셀 access를 닫는다.

 

## **특정 데이터 읽기**

```python
import openpyxl as xl

wb = xl.load_workbook('C:/Project/steel/faq.xlsx')
sheet = wb['키움']

# A1 데이터를 가져오는 방법
print('[', sheet.cell(row=1, column=1).value, ']')
print('[', sheet['A1'].value, ']')

print('=' * 50)
# A1 부터, B1 데이터까지 가져오기
for data in sheet['A1':'B1']:
    for cell in data:
        print('[', cell.value, ']')

print('=' * 50)
# A1 부터, B2 데이터까지 가져오기
for data in sheet['A1':'B2']:
    for cell in data:
        print('[', cell.value, ']')

wb.close()
```

sheet.cell 함수에서 row와 column을 입력하면, 지정한 value를 가져올 수 있고, Cell(ex: A1)을 직접 입력하는 방식으로도 value를 가져올 수 있다.

 

sheet에 슬라이싱을 사용하는 방식으로 데이터를 가져올 수도 있다. A1 ~ B1까지 로우값을 가져올 수도 있지만 A1 ~ B2와 같이 셀범위를 2차원 형태로 지정을 할수도 있다.

 

## **엑셀 쓰기**

```python
import openpyxl as xl

wb = xl.Workbook()
sheet = wb.active
sheet.title = '테스트'

# 컬럼명 지정(헤더)
col_names = ['질문', '답변']
for seq, name in enumerate(col_names):
    sheet.cell(row=1, column=seq+1, value=name)

# 시트 저장
faq_data = [('비밀번호 변경', '비밀번호 변경은 어떻게 해야 합니다.'),
            ('테스트 하는 방법', '테스트는 잘 해야 합니다.')]

row_no = 2
for n, rows in enumerate(faq_data):
    for seq, value in enumerate(rows):
        sheet.cell(row=row_no+n, column=seq+1, value=value)

wb.save("c:/project/steel/faq2.xlsx")
wb.close()
```

엑셀을 쓰는 방법에는 컬럼을 지정하여 쓰는 방법도 있지만, enumerate를 사용하여 데이터를 저장할 수 있다. 

 



![img](./images/img_03.png)저장한 엑셀 화면



 