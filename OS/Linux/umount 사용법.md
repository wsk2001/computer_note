# umount 사용법

mount된 디렉토리를 umount 하고자 할 때, 만약 특정 사용자 & 프로세스가 해당 디렉토리를 사용하고 있으면 "device is busy" 메세지가 발생하며 umount 가 되지 않습니다.
이 경우 umount를 위해 해당 프로세스를 강제로 kill 하는 방법은 아래와 같습니다.

```bash
# fuser -ck 마운트디렉토리
ex) fuser -ck /home/mount_dir
```

위 명령으로 프로세스를 kill 하고 umount를 재시도하면 정상적으로 mount가 해제 됩니다.

* 해당 디렉토리를 사용하는 사용자가 누구인지 확인하고 싶을 때
``` bash
# fuser -cu /home/hostway
```



## umount options

### NAME
​       umount - unmount file systems

### SYNOPSIS
​       umount [-hV]

       umount -a [-dflnrv] [-t vfstype] [-O options]
       umount [-dflnrv] {dir|device}...

### DESCRIPTION
​       The  umount  command  detaches the file system(s) mentioned from the file hierarchy.  A file system is specified by giving the directory where it has been mounted.  Giving the special device on
​       which the file system lives may also work, but is obsolete, mainly because it will fail in case this device was mounted on more than one directory.

       Note that a file system cannot be unmounted when it is 'busy' - for example, when there are open files on it, or when some process has its working directory there, or when a swap file on it  is
       in use.  The offending process could even be umount itself - it opens libc, and libc in its turn may open for example locale files.  A lazy unmount avoids this problem.

### OPTIONS
​       -a, --all
​              All of the file systems described in /etc/mtab are unmounted.  (With umount version 2.7 and later: the proc filesystem is not unmounted.)

       -A, --all-targets
              Unmount  all  mountpoints  in the current namespace for the specified filesystem.  The filesystem could be specified by one of the mountpoints or device name (or UUID, etc.). This option
              could be used together with --recursive then all nested mounts within the filesystem are recursively unmounted.
    
       -c, --no-canonicalize
              Do not canonicalize paths.  For more details about this option see the mount(8) man page.
    
       -d, --detach-loop
              In case the unmounted device was a loop device, also free this loop device.
    
       --fake Causes everything to be done except for the actual system call; this 'fakes' unmounting the filesystem.  It can be used to remove entries from /etc/mtab that were unmounted earlier  with
              the -n option.
    
       -f, --force
              Force unmount (in case of an unreachable NFS system).  (Requires kernel 2.1.116 or later.)
    
       -i, --internal-only
              Do not call the /sbin/umount.<filesystem> helper even if it exists.  By default /sbin/umount.<filesystem> helper is called if one exists.
    
       -n, --no-mtab
              Unmount without writing in /etc/mtab.
    
       -l, --lazy
              Lazy  unmount.   Detach  the  filesystem  from  the  filesystem hierarchy now, and cleanup all references to the filesystem as soon as it is not busy anymore.  (Requires kernel 2.4.11 or later.)
    
       -O, --test-opts options,list
              Indicate that the actions should only be taken on file systems with the specified options in /etc/fstab.  More than one option type may be specified in  a  comma  separated  list.   Each
              option can be prefixed with no to specify options for which no action should be taken.
    
       -R, --recursive
              Recursively  unmount  each directory specified. Recursion for each directory will stop if any unmount operation in the chain fails for any reason. The relationship between mountpoints is
              determined by /proc/self/mountinfo entries. The filesystem must be specified by mountpoint path, recursive unmount by device name (or UUID) is unsupported.
    
       -r, --read-only
              In case unmounting fails, try to remount read-only.
    
       -t, --types vfstype,ext2,ext3
              Indicate that the actions should only be taken on file systems of the specified type.  More than one type may be specified in a comma separated list.  The list of file system  types  can
              be prefixed with no to specify the file system types on which no action should be taken.
    
       -v, --verbose
              Verbose mode.
    
       -h, --help
              Print help message and exit.
    
       -V, --version
              Print version and exit.

### THE LOOP DEVICE
​       The  umount command will free the loop device (if any) associated with the mount, in case it finds the option 'loop=...' in /etc/mtab, or when the -d option was given.  Any pending loop devices
​       can be freed using 'losetup -d', see losetup(8).

### NOTES
​       The syntax of external umount helpers is:

       /sbin/umount.<suffix> {dir|device} [-nlfvr] [-t type.subtype]
    
       where the <suffix> is filesystem type or a value from "uhelper=" or "helper=" mtab option.  The -t option is used  for  filesystems  with  subtypes  support  (for  example  /sbin/mount.fuse  -t
       fuse.sshfs).
    
       The uhelper= (unprivileged umount helper) is possible to use when non-root user wants to umount a mountpoint which is not defined in the /etc/fstab file (e.g. devices mounted by udisk).
    
       The helper= mount option redirects all umount requests to the /sbin/umount.<helper> independently on UID.

### FILES
​       /etc/mtab table of mounted file systems

### ENVIRONMENT
​       LIBMOUNT_FSTAB=<path>
​              overrides the default location of the fstab file

       LIBMOUNT_MTAB=<path>
              overrides the default location of the mtab file
    
       LIBMOUNT_DEBUG=0xffff
              enables debug output

### SEE ALSO
​       umount(2), mount(8), losetup(8)

### HISTORY
​       A umount command appeared in Version 6 AT&T UNIX.

### AVAILABILITY
​       The umount command is part of the util-linux package and is available from Linux Kernel Archive ⟨ftp://ftp.kernel.org/pub/linux/utils/util-linux/⟩.