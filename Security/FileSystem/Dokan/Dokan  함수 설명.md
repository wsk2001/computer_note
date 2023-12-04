# Dokan  함수 설명

Dokan 에서 지원하는 함수는 구조체 _DOKAN_OPERATIONS 에 정의되어 있다.

다음은 .. 이다

``` c
/**
 * 구조체 DOKAN_OPERATIONS
 * 설명: Dokan API 콜백 인터페이스
 *
 * DOKAN_OPERATIONS는 Windows가 파일 시스템에 액세스할 때 호출되는 모든 Dokan API 작업을 설명하는 콜백 구조체입니다.
 *
 * 오류가 발생하면 NTSTATUS를 반환합니다. (https://support.microsoft.com/en-us/kb/113996).
 * Win32 오류는 DokanNtStatusFromWin32를 사용하여 NTSTATUS로 변환될 수 있습니다.
 *
 * 모든 콜백은 NULL로 설정하거나 제대로 지원되지 않게 작성된 함수를 지정 한 경우 STATUS_NOT_IMPLEMENTED를 반환할 수 있습니다. 
 * DOKAN_OPERATIONS.ZwCreateFile, DOKAN_OPERATIONS.ReadFile, ...과 같은 중요한 콜백이 이러한 값을 반환하면 
 * 파일 시스템이 작동하지 않거나 불안정해질 수 있습니다.
 */
typedef struct _DOKAN_OPERATIONS {
  /**
  * CreateFile 도칸 API 콜백
  *
  * CreateFile은 파일 시스템 개체에 대한 요청이 이루어질 때마다 호출됩니다.
  *
  * OPEN_ALWAYS 및 CREATE_ALWAYS가 기존 파일을 성공적으로 여는 경우 STATUS_SUCCESS 대신 STATUS_OBJECT_NAME_COLLISION이 반환되어야 합니다.
  * 그러면 요청 중에 파일이 열렸으며 생성되지 않았음을 Dokan에게 알립니다.
  *
  * 파일이 디렉터리이면 CreateFile도 호출됩니다.
  * 이 경우 CreateFile은 해당 디렉터리를 열 수 있고 DOKAN_FILE_INFO.IsDirectory를 TRUE로 설정해야 할 때 STATUS_SUCCESS를 반환해야 합니다.
  * 반면, DOKAN_FILE_INFO.IsDirectory가 TRUE로 설정되어 있지만 경로가 파일을 대상으로 하는 경우 STATUS_NOT_A_DIRECTORY가 반환되어야 합니다.
  *
  * DOKAN_FILE_INFO.Context는 컨텍스트와 관련된 다른 모든 요청에서 검색할 수 있는 데이터(예: HANDLE)를 저장하는 데 사용할 수 있습니다.
  * 메모리 누수를 방지하려면 DOKAN_OPERATIONS.Clean에서 컨텍스트를 해제해야 합니다.
  *
  * @param FileName FileSystem의 커널이 요청한 파일 경로입니다.
  * @param SecurityContext SecurityContext 는 다음을 참조 하세요 
  *        https://msdn.microsoft.com/en-us/library/windows/hardware/ff550613(v=vs.85).aspx
  * @param DesiredAccess 객체에 대한 접근 값을 지정 합니다.
  * @param ShareAccess 0 또는 FILE_SHARE_* 플래그의 조합으로 지정되는 공유 액세스 유형입니다.
  * @param CreateDisposition 파일이 존재하거나 존재하지 않는 경우 수행할 작업을 지정합니다.
  * @param CreateOptions 드라이버가 파일을 만들거나 열 때 적용할 옵션을 지정합니다.
  * @param DokanFileInfo 파일이나 디렉터리에 대한 정보입니다.
  * @return 성공 시 STATUS_SUCCESS 또는 요청 결과에 적합한 NTSTATUS입니다.
  * @see <a href="https://msdn.microsoft.com/en-us/library/windows/hardware/ff566424(v=vs.85).aspx">
  *      이 콜백의 매개 변수에 대한 자세한 내용은 ZwCreateFile(MSDN)을 참조하세요.</a>
  * @see DokanMapKernelToUserCreateFileFlags
  */
  NTSTATUS(DOKAN_CALLBACK *ZwCreateFile)(LPCWSTR FileName,
      PDOKAN_IO_SECURITY_CONTEXT SecurityContext,
      ACCESS_MASK DesiredAccess,
      ULONG FileAttributes,
      ULONG ShareAccess,
      ULONG CreateDisposition,
      ULONG CreateOptions,
      PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * @brief Dokan API 콜백 정리
  *
  *  CloseFile이 호출되기 전 정리 요청입니다.
  *
  * DOKAN_FILE_INFO.DeleteOnClose가 TRUE이면 정리에 있는 파일을 삭제해야 합니다.
  * 함수는 실패할 수 없으므로 파일 시스템은 정리 중에 삭제가 안전하게 발생할 수 있는지 미리 확인해야 합니다.
  * 설명은 DeleteFile 설명서를 참조하세요.
  *
  * @param FileName FileSystem의 커널이 요청한 파일 경로입니다.
  * @param DokanFileInfo 파일이나 디렉터리에 대한 정보입니다.
  * @see DeleteFile
  * @see DeleteDirectory
  */
  void(DOKAN_CALLBACK *Cleanup)(LPCWSTR FileName,
                                PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * @brief CloseFile Dokan API 콜백
  *
  * 남은 컨텍스트 정리
  *
  * CloseFile은 컨텍스트 수명이 끝나면 호출됩니다.
  * DOKAN_FILE_INFO.Context에 남아 있는 모든 항목은 반환하기 전에 지워야 합니다.
  *
  * @param FileName FileSystem의 커널이 요청한 파일 경로입니다.
  * @param DokanFileInfo 파일이나 디렉터리에 대한 정보입니다.
  */
  void(DOKAN_CALLBACK *CloseFile)(LPCWSTR FileName,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * @brief ReadFile Dokan API 콜백
  *
  * 이전에 DOKAN_OPERATIONS.ZwCreateFile에서 열린 파일에 대한 ReadFile 콜백입니다.
  * 동시에 다른 스레드에서 호출할 수 있으므로 읽기/컨텍스트는 스레드로부터 안전해야 합니다.
  * 
  * 앱이 메모리 매핑된 파일을 사용하는 경우 I/O 작업을 완료하기 위해 DOKAN_OPERATIONS.Cleanup 후에 DOKAN_OPERATIONS.WriteFile 
  * 또는 DOKAN_OPERATIONS.ReadFile 함수가 호출될 수 있습니다.
  * 이 경우 파일 시스템 애플리케이션도 제대로 작동해야 합니다.
  *
  * @param FileName FileSystem의 커널이 요청한 파일 경로입니다.
  * @param Buffer 읽기 결과로 채워져야 하는 읽기 버퍼입니다.
  * @param BufferLength 계속할 버퍼 길이와 읽기 크기입니다.
  * @param ReadLength 읽은 총 데이터 크기입니다.
  * @param Offset 읽기를 계속해야 하는 위치의 오프셋입니다.
  * @param DokanFileInfo 파일이나 디렉터리에 대한 정보입니다.
  * @return 성공 시 STATUS_SUCCESS 또는 요청 결과에 적합한 NTSTATUS입니다.
  * @see WriteFile
  */
  NTSTATUS(DOKAN_CALLBACK *ReadFile)(LPCWSTR FileName,
    LPVOID Buffer,
    DWORD BufferLength,
    LPDWORD ReadLength,
    LONGLONG Offset,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * @brief WriteFile Dokan API 콜백
  *
  * 이전에 DOKAN_OPERATIONS.ZwCreateFile에서 열린 파일에 대한 WriteFile 콜백
  * 동시에 다른 스레드에서 호출할 수 있으며, 쓰기/컨텍스트는 스레드로부터 안전해야 합니다.
  *
  * 앱이 메모리 매핑된 파일( DOKAN_FILE_INFO.PagingIo ), DOKAN_OPERATIONS.WriteFile 또는 DOKAN_OPERATIONS.ReadFile을 사용하는 경우
  * I/O 작업을 완료하기 위해 DOKAN_OPERATIONS.Cleanup 후에 함수를 호출할 수 있습니다.
  * 이 경우 파일 시스템 애플리케이션도 제대로 작동해야 합니다.
  * 이러한 유형의 요청은 현재 파일 크기를 확장하지 않는 것과 같은 Windows 규칙을 따라야 합니다.
  * 
  * \param FileName FileSystem의 커널이 요청한 파일 경로입니다.
  * \param Buffer 기록할 data.
  * \param NumberOfBytesToWrite 기록할 data 길이.
  * \param NumberOfBytesWritten 기록된 data 길이.
  * \param Offset Data 기록 시작 옵셋
  * \param DokanFileInfo 파일이나 디렉터리에 대한 정보입니다.
  * \return 성공 시 STATUS_SUCCESS 또는 요청 결과에 적합한 NTSTATUS입니다.
  * \see ReadFile
  */
  NTSTATUS(DOKAN_CALLBACK *WriteFile)(LPCWSTR FileName,
    LPCVOID Buffer,
    DWORD NumberOfBytesToWrite,
    LPDWORD NumberOfBytesWritten,
    LONGLONG Offset,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief FlushFileBuffers Dokan API callback
  *
  * Clears buffers for this context and causes any buffered data to be written to the file.
  *
  * \param FileName File path requested by the Kernel on the FileSystem.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  */
  NTSTATUS(DOKAN_CALLBACK *FlushFileBuffers)(LPCWSTR FileName,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief GetFileInformation Dokan API callback
  *
  * Get specific information on a file.
  *
  * \param FileName File path requested by the Kernel on the FileSystem.
  * \param Buffer BY_HANDLE_FILE_INFORMATION struct to fill.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  */
  NTSTATUS(DOKAN_CALLBACK *GetFileInformation)(LPCWSTR FileName,
    LPBY_HANDLE_FILE_INFORMATION Buffer,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief FindFiles Dokan API callback
  *
  * List all files in the requested path.
  * \ref DOKAN_OPERATIONS.FindFilesWithPattern is checked first. If it is not implemented or
  * returns \c STATUS_NOT_IMPLEMENTED, then FindFiles is called, if assigned.
  * It is recommended to have this implemented for performance reason.
  *
  * \param FileName File path requested by the Kernel on the FileSystem.
  * \param FillFindData Callback that has to be called with PWIN32_FIND_DATAW that contain file information.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  * \see FindFilesWithPattern
  */
  NTSTATUS(DOKAN_CALLBACK *FindFiles)(LPCWSTR FileName,
    PFillFindData FillFindData,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief FindFilesWithPattern Dokan API callback
  *
  * Same as \ref DOKAN_OPERATIONS.FindFiles but with a search pattern.\n
  * The search pattern is a Windows MS-DOS-style expression.
  * It can contain wild cards and extended characters or none of them. See \ref DokanIsNameInExpression.
  *
  * If the function is not implemented, \ref DOKAN_OPERATIONS.FindFiles
  * will be called instead and the result will be filtered internally by the library.
  * It is recommended to have this implemented for performance reason.
  *
  * \param PathName Path requested by the Kernel on the FileSystem.
  * \param SearchPattern Search pattern.
  * \param FillFindData Callback that has to be called with PWIN32_FIND_DATAW that contains file information.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  * \see FindFiles
  * \see DokanIsNameInExpression
  */
  NTSTATUS(DOKAN_CALLBACK *FindFilesWithPattern)(LPCWSTR PathName,
    LPCWSTR SearchPattern,
    PFillFindData FillFindData,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief SetFileAttributes Dokan API callback
  *
  * Set file attributes on a specific file
  *
  * \param FileName File path requested by the Kernel on the FileSystem.
  * \param FileAttributes FileAttributes to set on file.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  */
  NTSTATUS(DOKAN_CALLBACK *SetFileAttributes)(LPCWSTR FileName,
    DWORD FileAttributes,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief SetFileTime Dokan API callback
  *
  * Set file attributes on a specific file
  *
  * \param FileName File path requested by the Kernel on the FileSystem.
  * \param CreationTime Creation FILETIME.
  * \param LastAccessTime LastAccess FILETIME.
  * \param LastWriteTime LastWrite FILETIME.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  */
  NTSTATUS(DOKAN_CALLBACK *SetFileTime)(LPCWSTR FileName,
    CONST FILETIME *CreationTime,
    CONST FILETIME *LastAccessTime,
    CONST FILETIME *LastWriteTime,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief DeleteFile Dokan API callback
  *
  * Check if it is possible to delete a file.
  *
  * DeleteFile will also be called with DOKAN_FILE_INFO.DeleteOnClose set to \c FALSE
  * to notify the driver when the file is no longer requested to be deleted.
  *
  * The file in DeleteFile should not be deleted, but instead the file
  * must be checked as to whether or not it can be deleted,
  * and \c STATUS_SUCCESS should be returned (when it can be deleted) or
  * appropriate error codes, such as \c STATUS_ACCESS_DENIED or
  * \c STATUS_OBJECT_NAME_NOT_FOUND, should be returned.
  *
  * When \c STATUS_SUCCESS is returned, a Cleanup call is received afterwards with
  * DOKAN_FILE_INFO.DeleteOnClose set to \c TRUE. Only then must the closing file
  * be deleted.
  *
  * \param FileName File path requested by the Kernel on the FileSystem.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  * \see DeleteDirectory
  * \see Cleanup
  */
  NTSTATUS(DOKAN_CALLBACK *DeleteFile)(LPCWSTR FileName,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief DeleteDirectory Dokan API callback
  *
  * Check if it is possible to delete a directory.
  *
  * DeleteDirectory will also be called with DOKAN_FILE_INFO.DeleteOnClose set to \c FALSE
  * to notify the driver when the file is no longer requested to be deleted.
  *
  * The Directory in DeleteDirectory should not be deleted, but instead
  * must be checked as to whether or not it can be deleted,
  * and \c STATUS_SUCCESS should be returned (when it can be deleted) or
  * appropriate error codes, such as \c STATUS_ACCESS_DENIED,
  * \c STATUS_OBJECT_PATH_NOT_FOUND, or \c STATUS_DIRECTORY_NOT_EMPTY, should
  * be returned.
  *
  * When \c STATUS_SUCCESS is returned, a Cleanup call is received afterwards with
  * DOKAN_FILE_INFO.DeleteOnClose set to \c TRUE. Only then must the closing file
  * be deleted.
  *
  * \param FileName File path requested by the Kernel on the FileSystem.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or \c NTSTATUS appropriate to the request result.
  * \ref DeleteFile
  * \ref Cleanup
  */
  NTSTATUS(DOKAN_CALLBACK *DeleteDirectory)(LPCWSTR FileName,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief MoveFile Dokan API callback
  *
  * Move a file or directory to a new destination
  *
  * \param FileName Path for the file to be moved.
  * \param NewFileName Path for the new location of the file.
  * \param ReplaceIfExisting If destination already exists, can it be replaced?
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  */
  NTSTATUS(DOKAN_CALLBACK *MoveFile)(LPCWSTR FileName,
    LPCWSTR NewFileName,
    BOOL ReplaceIfExisting,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief SetEndOfFile Dokan API callback
  *
  * SetEndOfFile is used to truncate or extend a file (physical file size).
  *
  * \param FileName File path requested by the Kernel on the FileSystem.
  * \param ByteOffset File length to set.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  */
  NTSTATUS(DOKAN_CALLBACK *SetEndOfFile)(LPCWSTR FileName,
    LONGLONG ByteOffset,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief SetAllocationSize Dokan API callback
  *
  * SetAllocationSize is used to truncate or extend a file.
  *
  * \param FileName File path requested by the Kernel on the FileSystem.
  * \param AllocSize File length to set.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  */
  NTSTATUS(DOKAN_CALLBACK *SetAllocationSize)(LPCWSTR FileName,
    LONGLONG AllocSize,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief LockFile Dokan API callback
  *
  * Lock file at a specific offset and data length.
  * This is only used if \ref DOKAN_OPTION_FILELOCK_USER_MODE is enabled.
  *
  * \param FileName File path requested by the Kernel on the FileSystem.
  * \param ByteOffset Offset from where the lock has to be continued.
  * \param Length Data length to lock.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  * \see UnlockFile
  */
  NTSTATUS(DOKAN_CALLBACK *LockFile)(LPCWSTR FileName,
    LONGLONG ByteOffset,
    LONGLONG Length,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief UnlockFile Dokan API callback
  *
  * Unlock file at a specific offset and data length.
  * This is only used if \ref DOKAN_OPTION_FILELOCK_USER_MODE is enabled.
  *
  * \param FileName File path requested by the Kernel on the FileSystem.
  * \param ByteOffset Offset from where the lock has to be continued.
  * \param Length Data length to lock.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  * \see LockFile
  */
  NTSTATUS(DOKAN_CALLBACK *UnlockFile)(LPCWSTR FileName,
    LONGLONG ByteOffset,
    LONGLONG Length,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief GetDiskFreeSpace Dokan API callback
  *
  * Retrieves information about the amount of space that is available on a disk volume.
  * It consits of the total amount of space, the total amount of free space, and
  * the total amount of free space available to the user that is associated with the calling thread.
  *
  * Neither GetDiskFreeSpace nor \ref GetVolumeInformation
  * save the  DOKAN_FILE_INFO.Context.
  * Before these methods are called, \ref ZwCreateFile may not be called.
  * (ditto \ref CloseFile and \ref Cleanup)
  *
  * \param FreeBytesAvailable Amount of available space.
  * \param TotalNumberOfBytes Total size of storage space
  * \param TotalNumberOfFreeBytes Amount of free space
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or \c NTSTATUS appropriate to the request result.
  * \see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa364937(v=vs.85).aspx"> GetDiskFreeSpaceEx function (MSDN)</a>
  * \see GetVolumeInformation
  */
  NTSTATUS(DOKAN_CALLBACK *GetDiskFreeSpace)(PULONGLONG FreeBytesAvailable,
    PULONGLONG TotalNumberOfBytes,
    PULONGLONG TotalNumberOfFreeBytes,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief GetVolumeInformation Dokan API callback
  *
  * Retrieves information about the file system and volume associated with the specified root directory.
  *
  * Neither GetVolumeInformation nor GetDiskFreeSpace
  * save the \ref DOKAN_FILE_INFO#Context.
  * Before these methods are called, \ref ZwCreateFile may not be called.
  * (ditto \ref CloseFile and \ref Cleanup)
  *
  * VolumeName length can be anything that fit in the provided buffer.
  * But some Windows component expect it to be no longer than 32 characters
  * that why it is recommended to set a value under this limit.
  *
  * FileSystemName could be anything up to 10 characters.
  * But Windows check few feature availability based on file system name.
  * For this, it is recommended to set NTFS or FAT here.
  *
  * \c FILE_READ_ONLY_VOLUME is automatically added to the
  * FileSystemFlags if \ref DOKAN_OPTION_WRITE_PROTECT was
  * specified in DOKAN_OPTIONS when the volume was mounted.
  *
  * \param VolumeNameBuffer A pointer to a buffer that receives the name of a specified volume.
  * \param VolumeNameSize The length of a volume name buffer.
  * \param VolumeSerialNumber A pointer to a variable that receives the volume serial number.
  * \param MaximumComponentLength A pointer to a variable that receives the maximum length.
  * \param FileSystemFlags A pointer to a variable that receives flags associated with the specified file system.
  * \param FileSystemNameBuffer A pointer to a buffer that receives the name of the file system.
  * \param FileSystemNameSize The length of the file system name buffer.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  * \see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa364993(v=vs.85).aspx"> GetVolumeInformation function (MSDN)</a>
  * \see GetDiskFreeSpace
  */
  NTSTATUS(DOKAN_CALLBACK *GetVolumeInformation)(LPWSTR VolumeNameBuffer,
    DWORD VolumeNameSize,
    LPDWORD VolumeSerialNumber,
    LPDWORD MaximumComponentLength,
    LPDWORD FileSystemFlags,
    LPWSTR FileSystemNameBuffer,
    DWORD FileSystemNameSize,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief Mounted Dokan API callback
  *
  * Called when Dokan successfully mounts the volume.
  *
  * If \ref DOKAN_OPTION_MOUNT_MANAGER is enabled and the drive letter requested is busy,
  * the MountPoint can contain a different drive letter that the mount manager assigned us.
  *
  * \param MountPoint The mount point assign to the instance.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  * \see Unmounted
  */
  NTSTATUS(DOKAN_CALLBACK *Mounted)(LPCWSTR MountPoint, PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief Unmounted Dokan API callback
  *
  * Called when Dokan is unmounting the volume.
  *
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or \c NTSTATUS appropriate to the request result.
  * \see Mounted
  */
  NTSTATUS(DOKAN_CALLBACK *Unmounted)(PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief GetFileSecurity Dokan API callback
  *
  * Get specified information about the security of a file or directory.
  *
  * Return \c STATUS_NOT_IMPLEMENTED to let dokan library build a sddl of the current process user with authenticate user rights for context menu.
  * Return \c STATUS_BUFFER_OVERFLOW if buffer size is too small.
  *
  * \since Supported since version 0.6.0. The version must be specified in \ref DOKAN_OPTIONS.Version.
  * \param FileName File path requested by the Kernel on the FileSystem.
  * \param SecurityInformation A SECURITY_INFORMATION value that identifies the security information being requested.
  * \param SecurityDescriptor A pointer to a buffer that receives a copy of the security descriptor of the requested file.
  * \param BufferLength Specifies the size, in bytes, of the buffer.
  * \param LengthNeeded A pointer to the variable that receives the number of bytes necessary to store the complete security descriptor.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  * \see SetFileSecurity
  * \see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa446639(v=vs.85).aspx">GetFileSecurity function (MSDN)</a>
  */
  NTSTATUS(DOKAN_CALLBACK *GetFileSecurity)(LPCWSTR FileName,
    PSECURITY_INFORMATION SecurityInformation,
    PSECURITY_DESCRIPTOR SecurityDescriptor,
    ULONG BufferLength,
    PULONG LengthNeeded,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief SetFileSecurity Dokan API callback
  *
  * Sets the security of a file or directory object.
  *
  * \since Supported since version 0.6.0. The version must be specified in \ref DOKAN_OPTIONS.Version.
  * \param FileName File path requested by the Kernel on the FileSystem.
  * \param SecurityInformation Structure that identifies the contents of the security descriptor pointed by \a SecurityDescriptor param.
  * \param SecurityDescriptor A pointer to a SECURITY_DESCRIPTOR structure.
  * \param BufferLength Specifies the size, in bytes, of the buffer.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  * \see GetFileSecurity
  * \see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa379577(v=vs.85).aspx">SetFileSecurity function (MSDN)</a>
  */
  NTSTATUS(DOKAN_CALLBACK *SetFileSecurity)(LPCWSTR FileName,
    PSECURITY_INFORMATION SecurityInformation,
    PSECURITY_DESCRIPTOR SecurityDescriptor,
    ULONG BufferLength,
    PDOKAN_FILE_INFO DokanFileInfo);

  /**
  * \brief FindStreams Dokan API callback
  *
  * Retrieve all NTFS Streams informations on the file.
  * This is only called if \ref DOKAN_OPTION_ALT_STREAM is enabled.
  *
  * \since Supported since version 0.8.0. The version must be specified in \ref DOKAN_OPTIONS.Version.
  * \param FileName File path requested by the Kernel on the FileSystem.
  * \param FillFindStreamData Callback that has to be called with PWIN32_FIND_STREAM_DATA that contain stream information.
  * \param FindStreamContext Context for the event to pass to the callback FillFindStreamData.
  * \param DokanFileInfo Information about the file or directory.
  * \return \c STATUS_SUCCESS on success or NTSTATUS appropriate to the request result.
  */
  NTSTATUS(DOKAN_CALLBACK *FindStreams)(LPCWSTR FileName,
    PFillFindStreamData FillFindStreamData,
    PVOID FindStreamContext,
    PDOKAN_FILE_INFO DokanFileInfo);

} DOKAN_OPERATIONS, *PDOKAN_OPERATIONS;
```



