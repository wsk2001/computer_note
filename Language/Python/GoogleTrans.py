###########################
# pip install googletrans
###########################

from tkinter import *
import tkinter.filedialog as filedialog
import tkinter.messagebox as messagebox
from googletrans import Translator

root = Tk()
root.title('Google Translator')
root.geometry('{}x{}'.format(600, 400))

translator = Translator()

def exit():
   if messagebox.askokcancel("Quit", "종료 하시겠습니까?"):
      root.destroy()

def about():
   label = messagebox.showinfo("About", "파이썬 으로 만든 구글 번역기")

def clear_source_callback():
    TextAreaSource.delete("1.0", END)

def clear_destination_callback():
    TextAreaDestination.delete("1.0", END)

def translate_callback():
    input = TextAreaSource.get("1.0", END)
    translated = translator.translate(input, src='ko', dest='en')
    TextAreaDestination.insert(CURRENT, translated.text)

def append_clipboard():
    input = TextAreaDestination.get("1.0", END)
    root.clipboard_clear()
    root.clipboard_append(input)

menu = Menu(root)
filemenu = Menu(menu, tearoff=0)
filemenu.add_command(label="Exit", command=exit)
menu.add_cascade(label="File", menu=filemenu)

helpmenu = Menu(menu, tearoff=0)
helpmenu.add_command(label="About", command=about)
menu.add_cascade(label="Help", menu=helpmenu)
root.config(menu=menu)

kor_frame  = Frame(root, width=590, height=180, pady=3)
center     = Frame(root, width=590, height=40,  pady=3)
eng_frame  = Frame(root, width=590, height=180, pady=3)

root.grid_rowconfigure(1, weight=1)
root.grid_columnconfigure(0, weight=1)

kor_frame.grid(row=1, sticky="ew")
center.grid(row=2, sticky="ew")
eng_frame.grid(row=3, sticky="ew")

TextAreaSource = Text(kor_frame, width=100, height=13)
TextAreaSource.grid(row=1, column=0)

clear_source_button = Button(center, text='원문 지우기', command=clear_source_callback)
clear_source_button.grid(row=1, column=0)
clear_source_button.place(x=4, y=4)

label1 = Label(center, text='KOR -> ENG')
label1.grid(row=1, column=1)
label1.place(x=100, y=6)

trans_button = Button(center, text='Translate', command=translate_callback)
trans_button.grid(row=1, column=2)
trans_button.place(x=190, y=4)

clip_board_button = Button(center, text='번역문을 클립보드로 복사', command=append_clipboard)
clip_board_button.grid(row=1, column=4)
clip_board_button.place(x=270, y=4)

clear_destination_button = Button(center, text='번역문 지우기', command=clear_destination_callback)
clear_destination_button.grid(row=1, column=4)
clear_destination_button.place(x=450, y=4)

TextAreaDestination = Text(eng_frame, width=100, height=13)
TextAreaDestination.grid(row=1, column=0)

root.mainloop()