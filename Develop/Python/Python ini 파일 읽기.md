### Python 에서 ini 파일 읽기

---

어디서 가져 왔는데 출처가 기억이 나지 않는다.

---

##### ReadIni.py

	import ConfigParser
	import sys
	
	section = sys.argv[1]
	conf_file = sys.argv[2]
	
	config = ConfigParser.ConfigParser()
	config.read(conf_file)
	
	sns_id = config.get(section, 'id'
	sns_passwd = config.get(section, 'passwd')



##### INI 파일 형식

	[SECTION]
	option = value



##### INI 파일 작성 예제

	[MAIL_INFO]
	id = mail
	passwd = mail
	email = mail@info
	
	[SNS_INFO]
	id = sns
	passwd = sns
	email = sns@info
	
	[MESSENGER_INFO]
	id = messenger
	passwd = messenger
	email = messenger@info

