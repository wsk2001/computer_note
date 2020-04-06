# Linux backup script

``` bash
#!/bin/sh

ISSUE_DATE=`date +%Y%m%d`
ISSUE_KERNEL=`uname -r`
ARCHIVE_NAME=백업파일명_${ISSUE_KERNEL}_${ISSUE_DATE}.tar.gz

tar -cvzpf "${ARCHIVE_NAME}" "." \
--exclude=./_test_files \
--exclude=./_utilitys \
--exclude=./linux_kerner_internal \
--exclude=./src_backup.sh \
--exclude=./"${ARCHIVE_NAME}"

```

