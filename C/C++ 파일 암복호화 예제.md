# 파일 암/복호화 예제

출처: https://docs.microsoft.com/en-us/windows/win32/seccrypto/example-c-program-encrypting-a-file

다음 예제는 데이터 파일을 암호화합니다. 이 예에서는 암호화 할 일반 텍스트가 포함 된 파일 이름과 암호화 된 데이터를 쓸 파일 이름을 대화식으로 요청합니다.

이 예에서는 입력 파일과 출력 파일의 이름을 입력하라는 메시지를 표시합니다. 또한 암호를 사용하여 암호화 세션 키를 만들지 여부를 묻습니다. 데이터 암호화에 암호를 사용하려면 파일 암호를 해독하는 프로그램에 동일한 암호를 사용해야합니다. 자세한 정보는 예제 C 프로그램 : 파일 암호 해독을 참조하십시오.

내보내기 제어 제한이 변경되어 운영 체제 릴리스마다 기본 CSP (암호화 서비스 공급자)와 기본 키 길이가 변경 될 수 있습니다. 암호화와 암호 해독 모두 동일한 CSP를 사용해야하며 다른 운영 체제 플랫폼에서 상호 운용성을 보장하기 위해 키 길이를 명시 적으로 설정해야합니다.

이 예제는 MyHandleError 함수를 사용합니다. 이 기능에 대한 코드는 샘플에 포함되어 있습니다. 이 기능 및 기타 보조 기능에 대한 코드도 일반 기능 아래에 나와 있습니다.

-- 암호화 예제 --

```c++
// Encrypting_a_File.cpp : Defines the entry point for the console 
// application.
//

#include <tchar.h>
#include <stdio.h>
#include <windows.h>
#include <wincrypt.h>
#include <conio.h>

// Link with the Advapi32.lib file.
#pragma comment (lib, "advapi32")

#define KEYLENGTH  0x00800000
#define ENCRYPT_ALGORITHM CALG_RC4 
#define ENCRYPT_BLOCK_SIZE 8 

bool MyEncryptFile(
    LPTSTR szSource, 
    LPTSTR szDestination, 
    LPTSTR szPassword);

void MyHandleError(
    LPTSTR psz, 
    int nErrorNumber);

int _tmain(int argc, _TCHAR* argv[])
{
    if(argc < 3)
    {
        _tprintf(TEXT("Usage: <example.exe> <source file> ")
            TEXT("<destination file> | <password>\n"));
        _tprintf(TEXT("<password> is optional.\n"));
        _tprintf(TEXT("Press any key to exit."));
        _gettch();
        return 1;
    }

    LPTSTR pszSource = argv[1]; 
    LPTSTR pszDestination = argv[2]; 
    LPTSTR pszPassword = NULL;

    if(argc >= 4)
    {
        pszPassword = argv[3];
    }

    //---------------------------------------------------------------
    // Call EncryptFile to do the actual encryption.
    if(MyEncryptFile(pszSource, pszDestination, pszPassword))
    {
        _tprintf(
            TEXT("Encryption of the file %s was successful. \n"), 
            pszSource);
        _tprintf(
            TEXT("The encrypted data is in file %s.\n"), 
            pszDestination);
    }
    else
    {
        MyHandleError(
            TEXT("Error encrypting file!\n"), 
            GetLastError()); 
    }

    return 0;
}

//-------------------------------------------------------------------
// Code for the function MyEncryptFile called by main.
//-------------------------------------------------------------------
// Parameters passed are:
//  pszSource, the name of the input, a plaintext file.
//  pszDestination, the name of the output, an encrypted file to be 
//   created.
//  pszPassword, either NULL if a password is not to be used or the 
//   string that is the password.
bool MyEncryptFile(
    LPTSTR pszSourceFile, 
    LPTSTR pszDestinationFile, 
    LPTSTR pszPassword)
{ 
    //---------------------------------------------------------------
    // Declare and initialize local variables.
    bool fReturn = false;
    HANDLE hSourceFile = INVALID_HANDLE_VALUE;
    HANDLE hDestinationFile = INVALID_HANDLE_VALUE; 

    HCRYPTPROV hCryptProv = NULL; 
    HCRYPTKEY hKey = NULL; 
    HCRYPTKEY hXchgKey = NULL; 
    HCRYPTHASH hHash = NULL; 

    PBYTE pbKeyBlob = NULL; 
    DWORD dwKeyBlobLen; 

    PBYTE pbBuffer = NULL; 
    DWORD dwBlockLen; 
    DWORD dwBufferLen; 
    DWORD dwCount; 
     
    //---------------------------------------------------------------
    // Open the source file. 
    hSourceFile = CreateFile(
        pszSourceFile, 
        FILE_READ_DATA,
        FILE_SHARE_READ,
        NULL,
        OPEN_EXISTING,
        FILE_ATTRIBUTE_NORMAL,
        NULL);
    if(INVALID_HANDLE_VALUE != hSourceFile)
    {
        _tprintf(
            TEXT("The source plaintext file, %s, is open. \n"), 
            pszSourceFile);
    }
    else
    { 
        MyHandleError(
            TEXT("Error opening source plaintext file!\n"), 
            GetLastError());
        goto Exit_MyEncryptFile;
    } 

    //---------------------------------------------------------------
    // Open the destination file. 
    hDestinationFile = CreateFile(
        pszDestinationFile, 
        FILE_WRITE_DATA,
        FILE_SHARE_READ,
        NULL,
        OPEN_ALWAYS,
        FILE_ATTRIBUTE_NORMAL,
        NULL);
    if(INVALID_HANDLE_VALUE != hDestinationFile)
    {
         _tprintf(
             TEXT("The destination file, %s, is open. \n"), 
             pszDestinationFile);
    }
    else
    {
        MyHandleError(
            TEXT("Error opening destination file!\n"), 
            GetLastError()); 
        goto Exit_MyEncryptFile;
    }

    //---------------------------------------------------------------
    // Get the handle to the default provider. 
    if(CryptAcquireContext(
        &hCryptProv, 
        NULL, 
        MS_ENHANCED_PROV, 
        PROV_RSA_FULL, 
        0))
    {
        _tprintf(
            TEXT("A cryptographic provider has been acquired. \n"));
    }
    else
    {
        MyHandleError(
            TEXT("Error during CryptAcquireContext!\n"), 
            GetLastError());
        goto Exit_MyEncryptFile;
    }

    //---------------------------------------------------------------
    // Create the session key.
    if(!pszPassword || !pszPassword[0]) 
    { 
        //-----------------------------------------------------------
        // No password was passed.
        // Encrypt the file with a random session key, and write the 
        // key to a file. 

        //-----------------------------------------------------------
        // Create a random session key. 
        if(CryptGenKey(
            hCryptProv, 
            ENCRYPT_ALGORITHM, 
            KEYLENGTH | CRYPT_EXPORTABLE, 
            &hKey))
        {
            _tprintf(TEXT("A session key has been created. \n"));
        } 
        else
        {
            MyHandleError(
                TEXT("Error during CryptGenKey. \n"), 
                GetLastError()); 
            goto Exit_MyEncryptFile;
        }

        //-----------------------------------------------------------
        // Get the handle to the exchange public key. 
        if(CryptGetUserKey(
            hCryptProv, 
            AT_KEYEXCHANGE, 
            &hXchgKey))
        {
            _tprintf(
                TEXT("The user public key has been retrieved. \n"));
        }
        else
        { 
            if(NTE_NO_KEY == GetLastError())
            {
                // No exchange key exists. Try to create one.
                if(!CryptGenKey(
                    hCryptProv, 
                    AT_KEYEXCHANGE, 
                    CRYPT_EXPORTABLE, 
                    &hXchgKey))
                {
                    MyHandleError(
                        TEXT("Could not create "
                            "a user public key.\n"), 
                        GetLastError()); 
                    goto Exit_MyEncryptFile;
                }
            }
            else
            {
                MyHandleError(
                    TEXT("User public key is not available and may ")
                        TEXT("not exist.\n"), 
                    GetLastError()); 
                goto Exit_MyEncryptFile;
            }
        }

        //-----------------------------------------------------------
        // Determine size of the key BLOB, and allocate memory. 
        if(CryptExportKey(
            hKey, 
            hXchgKey, 
            SIMPLEBLOB, 
            0, 
            NULL, 
            &dwKeyBlobLen))
        {
            _tprintf(
                TEXT("The key BLOB is %d bytes long. \n"), 
                dwKeyBlobLen);
        }
        else
        {  
            MyHandleError(
                TEXT("Error computing BLOB length! \n"), 
                GetLastError());
            goto Exit_MyEncryptFile;
        }

        if(pbKeyBlob = (BYTE *)malloc(dwKeyBlobLen))
        { 
            _tprintf(
                TEXT("Memory is allocated for the key BLOB. \n"));
        }
        else
        { 
            MyHandleError(TEXT("Out of memory. \n"), E_OUTOFMEMORY); 
            goto Exit_MyEncryptFile;
        }

        //-----------------------------------------------------------
        // Encrypt and export the session key into a simple key 
        // BLOB. 
        if(CryptExportKey(
            hKey, 
            hXchgKey, 
            SIMPLEBLOB, 
            0, 
            pbKeyBlob, 
            &dwKeyBlobLen))
        {
            _tprintf(TEXT("The key has been exported. \n"));
        } 
        else
        {
            MyHandleError(
                TEXT("Error during CryptExportKey!\n"), 
                GetLastError());
            goto Exit_MyEncryptFile;
        } 
         
        //-----------------------------------------------------------
        // Release the key exchange key handle. 
        if(hXchgKey)
        {
            if(!(CryptDestroyKey(hXchgKey)))
            {
                MyHandleError(
                    TEXT("Error during CryptDestroyKey.\n"), 
                    GetLastError()); 
                goto Exit_MyEncryptFile;
            }
      
            hXchgKey = 0;
        }
     
        //-----------------------------------------------------------
        // Write the size of the key BLOB to the destination file. 
        if(!WriteFile(
            hDestinationFile, 
            &dwKeyBlobLen, 
            sizeof(DWORD),
            &dwCount,
            NULL))
        { 
            MyHandleError(
                TEXT("Error writing header.\n"), 
                GetLastError());
            goto Exit_MyEncryptFile;
        }
        else
        {
            _tprintf(TEXT("A file header has been written. \n"));
        }

        //-----------------------------------------------------------
        // Write the key BLOB to the destination file. 
        if(!WriteFile(
            hDestinationFile, 
            pbKeyBlob, 
            dwKeyBlobLen,
            &dwCount,
            NULL))
        { 
            MyHandleError(
                TEXT("Error writing header.\n"), 
                GetLastError());
            goto Exit_MyEncryptFile;
        }
        else
        {
            _tprintf(
                TEXT("The key BLOB has been written to the ")
                    TEXT("file. \n"));
        }

        // Free memory.
        free(pbKeyBlob);
    } 
    else 
    { 

        //-----------------------------------------------------------
        // The file will be encrypted with a session key derived 
        // from a password.
        // The session key will be recreated when the file is 
        // decrypted only if the password used to create the key is 
        // available. 

        //-----------------------------------------------------------
        // Create a hash object. 
        if(CryptCreateHash(
            hCryptProv, 
            CALG_MD5, 
            0, 
            0, 
            &hHash))
        {
            _tprintf(TEXT("A hash object has been created. \n"));
        }
        else
        { 
            MyHandleError(
                TEXT("Error during CryptCreateHash!\n"), 
                GetLastError());
            goto Exit_MyEncryptFile;
        }  

        //-----------------------------------------------------------
        // Hash the password. 
        if(CryptHashData(
            hHash, 
            (BYTE *)pszPassword, 
            lstrlen(pszPassword), 
            0))
        {
            _tprintf(
                TEXT("The password has been added to the hash. \n"));
        }
        else
        {
            MyHandleError(
                TEXT("Error during CryptHashData. \n"), 
                GetLastError()); 
            goto Exit_MyEncryptFile;
        }

        //-----------------------------------------------------------
        // Derive a session key from the hash object. 
        if(CryptDeriveKey(
            hCryptProv, 
            ENCRYPT_ALGORITHM, 
            hHash, 
            KEYLENGTH, 
            &hKey))
        {
            _tprintf(
                TEXT("An encryption key is derived from the ")
                    TEXT("password hash. \n")); 
        }
        else
        {
            MyHandleError(
                TEXT("Error during CryptDeriveKey!\n"), 
                GetLastError()); 
            goto Exit_MyEncryptFile;
        }
    } 

    //---------------------------------------------------------------
    // The session key is now ready. If it is not a key derived from 
    // a  password, the session key encrypted with the private key 
    // has been written to the destination file.
     
    //---------------------------------------------------------------
    // Determine the number of bytes to encrypt at a time. 
    // This must be a multiple of ENCRYPT_BLOCK_SIZE.
    // ENCRYPT_BLOCK_SIZE is set by a #define statement.
    dwBlockLen = 1000 - 1000 % ENCRYPT_BLOCK_SIZE; 

    //---------------------------------------------------------------
    // Determine the block size. If a block cipher is used, 
    // it must have room for an extra block. 
    if(ENCRYPT_BLOCK_SIZE > 1) 
    {
        dwBufferLen = dwBlockLen + ENCRYPT_BLOCK_SIZE; 
    }
    else 
    {
        dwBufferLen = dwBlockLen; 
    }
        
    //---------------------------------------------------------------
    // Allocate memory. 
    if(pbBuffer = (BYTE *)malloc(dwBufferLen))
    {
        _tprintf(
            TEXT("Memory has been allocated for the buffer. \n"));
    }
    else
    { 
        MyHandleError(TEXT("Out of memory. \n"), E_OUTOFMEMORY); 
        goto Exit_MyEncryptFile;
    }

    //---------------------------------------------------------------
    // In a do loop, encrypt the source file, 
    // and write to the source file. 
    bool fEOF = FALSE;
    do 
    { 
        //-----------------------------------------------------------
        // Read up to dwBlockLen bytes from the source file. 
        if(!ReadFile(
            hSourceFile, 
            pbBuffer, 
            dwBlockLen, 
            &dwCount, 
            NULL))
        {
            MyHandleError(
                TEXT("Error reading plaintext!\n"), 
                GetLastError());
            goto Exit_MyEncryptFile;
        }

        if(dwCount < dwBlockLen)
        {
            fEOF = TRUE;
        }

        //-----------------------------------------------------------
        // Encrypt data. 
        if(!CryptEncrypt(
            hKey, 
            NULL, 
            fEOF,
            0, 
            pbBuffer, 
            &dwCount, 
            dwBufferLen))
        { 
            MyHandleError(
                TEXT("Error during CryptEncrypt. \n"), 
                GetLastError()); 
            goto Exit_MyEncryptFile;
        } 

        //-----------------------------------------------------------
        // Write the encrypted data to the destination file. 
        if(!WriteFile(
            hDestinationFile, 
            pbBuffer, 
            dwCount,
            &dwCount,
            NULL))
        { 
            MyHandleError(
                TEXT("Error writing ciphertext.\n"), 
                GetLastError());
            goto Exit_MyEncryptFile;
        }

        //-----------------------------------------------------------
        // End the do loop when the last block of the source file 
        // has been read, encrypted, and written to the destination 
        // file.
    } while(!fEOF);

    fReturn = true;

Exit_MyEncryptFile:
    //---------------------------------------------------------------
    // Close files.
    if(hSourceFile)
    {
        CloseHandle(hSourceFile);
    }

    if(hDestinationFile)
    {
        CloseHandle(hDestinationFile);
    }

    //---------------------------------------------------------------
    // Free memory. 
    if(pbBuffer) 
    {
        free(pbBuffer); 
    }
     

    //-----------------------------------------------------------
    // Release the hash object. 
    if(hHash) 
    {
        if(!(CryptDestroyHash(hHash)))
        {
            MyHandleError(
                TEXT("Error during CryptDestroyHash.\n"), 
                GetLastError()); 
        }

        hHash = NULL;
    }

    //---------------------------------------------------------------
    // Release the session key. 
    if(hKey)
    {
        if(!(CryptDestroyKey(hKey)))
        {
            MyHandleError(
                TEXT("Error during CryptDestroyKey!\n"), 
                GetLastError());
        }
    }

    //---------------------------------------------------------------
    // Release the provider handle. 
    if(hCryptProv)
    {
        if(!(CryptReleaseContext(hCryptProv, 0)))
        {
            MyHandleError(
                TEXT("Error during CryptReleaseContext!\n"), 
                GetLastError());
        }
    }
    
    return fReturn; 
} // End Encryptfile.


//-------------------------------------------------------------------
//  This example uses the function MyHandleError, a simple error
//  handling function, to print an error message to the  
//  standard error (stderr) file and exit the program. 
//  For most applications, replace this function with one 
//  that does more extensive error reporting.

void MyHandleError(LPTSTR psz, int nErrorNumber)
{
    _ftprintf(stderr, TEXT("An error occurred in the program. \n"));
    _ftprintf(stderr, TEXT("%s\n"), psz);
    _ftprintf(stderr, TEXT("Error number %x.\n"), nErrorNumber);
}
```



---

출처: https://docs.microsoft.com/en-us/windows/win32/seccrypto/example-c-program-decrypting-a-file

다음 예제는 파일의 암호 해독을 보여줍니다. 이 예에서는 사용자에게 암호화 된 파일 이름과 해독 된 데이터가 기록 될 파일 이름을 묻습니다. 암호화 된 데이터가있는 파일이 존재해야합니다. 이 예제는 출력 파일을 작성하거나 덮어 씁니다.

이 예는 또한 암호로 사용되는 문자열을 요청합니다. 암호를 사용하여 암호화 세션 키를 만든 경우 암호 해독 세션 키를 만들려면 동일한 암호를 입력해야합니다. 자세한 내용은 예제 C 프로그램 : 파일 암호화를 참조하십시오.

내보내기 제어 제한이 변경되어 운영 체제 릴리스마다 기본 CSP (암호화 서비스 공급자)와 기본 키 길이가 변경 될 수 있습니다. 암호화와 암호 해독 모두 동일한 CSP를 사용해야하며 다른 운영 체제 플랫폼에서 상호 운용성을 보장하기 위해 키 길이를 명시 적으로 설정해야합니다.

이 예제는 MyHandleError 함수를 사용합니다. 이 기능에 대한 코드는 샘플에 포함되어 있습니다. 이 기능 및 기타 보조 기능에 대한 코드도 일반 기능 아래에 나와 있습니다.

-- 복호화 예제 --

```c++
// Decrypting_a_File.cpp : Defines the entry point for the console 
// application.
//

#include <tchar.h>
#include <stdio.h>
#include <windows.h>
#include <wincrypt.h>
#include <conio.h>

// Link with the Advapi32.lib file.
#pragma comment (lib, "advapi32")

#define KEYLENGTH  0x00800000
#define ENCRYPT_ALGORITHM CALG_RC4 
#define ENCRYPT_BLOCK_SIZE 8 

bool MyDecryptFile(
    LPTSTR szSource, 
    LPTSTR szDestination, 
    LPTSTR szPassword);

void MyHandleError(
    LPTSTR psz, 
    int nErrorNumber);

int _tmain(int argc, _TCHAR* argv[])
{
    if(argc < 3)
    {
        _tprintf(TEXT("Usage: <example.exe> <source file> ")
            TEXT("<destination file> | <password>\n"));
        _tprintf(TEXT("<password> is optional.\n"));
        _tprintf(TEXT("Press any key to exit."));
        _gettch();
        return 1;
    }

    LPTSTR pszSource = argv[1]; 
    LPTSTR pszDestination = argv[2]; 
    LPTSTR pszPassword = NULL;

    if(argc >= 4)
    {
        pszPassword = argv[3];
    }

    //---------------------------------------------------------------
    // Call EncryptFile to do the actual encryption.
    if(MyDecryptFile(pszSource, pszDestination, pszPassword))
    {
        _tprintf(
            TEXT("Encryption of the file %s was successful. \n"), 
            pszSource);
        _tprintf(
            TEXT("The encrypted data is in file %s.\n"), 
            pszDestination);
    }
    else
    {
        MyHandleError(
            TEXT("Error encrypting file!\n"), 
            GetLastError()); 
    }

    return 0;
}

//-------------------------------------------------------------------
// Code for the function MyDecryptFile called by main.
//-------------------------------------------------------------------
// Parameters passed are:
//  pszSource, the name of the input file, an encrypted file.
//  pszDestination, the name of the output, a plaintext file to be 
//   created.
//  pszPassword, either NULL if a password is not to be used or the 
//   string that is the password.
bool MyDecryptFile(
    LPTSTR pszSourceFile, 
    LPTSTR pszDestinationFile, 
    LPTSTR pszPassword)
{ 
    //---------------------------------------------------------------
    // Declare and initialize local variables.
    bool fReturn = false;
    HANDLE hSourceFile = INVALID_HANDLE_VALUE;
    HANDLE hDestinationFile = INVALID_HANDLE_VALUE; 
    HCRYPTKEY hKey = NULL; 
    HCRYPTHASH hHash = NULL; 

    HCRYPTPROV hCryptProv = NULL; 

    DWORD dwCount;
    PBYTE pbBuffer = NULL; 
    DWORD dwBlockLen; 
    DWORD dwBufferLen; 

    //---------------------------------------------------------------
    // Open the source file. 
    hSourceFile = CreateFile(
        pszSourceFile, 
        FILE_READ_DATA,
        FILE_SHARE_READ,
        NULL,
        OPEN_EXISTING,
        FILE_ATTRIBUTE_NORMAL,
        NULL);
    if(INVALID_HANDLE_VALUE != hSourceFile)
    {
        _tprintf(
            TEXT("The source encrypted file, %s, is open. \n"), 
            pszSourceFile);
    }
    else
    { 
        MyHandleError(
            TEXT("Error opening source plaintext file!\n"), 
            GetLastError());
        goto Exit_MyDecryptFile;
    } 

    //---------------------------------------------------------------
    // Open the destination file. 
    hDestinationFile = CreateFile(
        pszDestinationFile, 
        FILE_WRITE_DATA,
        FILE_SHARE_READ,
        NULL,
        OPEN_ALWAYS,
        FILE_ATTRIBUTE_NORMAL,
        NULL);
    if(INVALID_HANDLE_VALUE != hDestinationFile)
    {
         _tprintf(
             TEXT("The destination file, %s, is open. \n"), 
             pszDestinationFile);
    }
    else
    {
        MyHandleError(
            TEXT("Error opening destination file!\n"), 
            GetLastError()); 
        goto Exit_MyDecryptFile;
    }

    //---------------------------------------------------------------
    // Get the handle to the default provider. 
    if(CryptAcquireContext(
        &hCryptProv, 
        NULL, 
        MS_ENHANCED_PROV, 
        PROV_RSA_FULL, 
        0))
    {
        _tprintf(
            TEXT("A cryptographic provider has been acquired. \n"));
    }
    else
    {
        MyHandleError(
            TEXT("Error during CryptAcquireContext!\n"), 
            GetLastError());
        goto Exit_MyDecryptFile;
    }

    //---------------------------------------------------------------
    // Create the session key.
    if(!pszPassword || !pszPassword[0]) 
    { 
        //-----------------------------------------------------------
        // Decrypt the file with the saved session key. 

        DWORD dwKeyBlobLen;
        PBYTE pbKeyBlob = NULL;

        // Read the key BLOB length from the source file. 
        if(!ReadFile(
            hSourceFile, 
            &dwKeyBlobLen, 
            sizeof(DWORD), 
            &dwCount, 
            NULL))
        {
            MyHandleError(
                TEXT("Error reading key BLOB length!\n"), 
                GetLastError());
            goto Exit_MyDecryptFile;
        }

        // Allocate a buffer for the key BLOB.
        if(!(pbKeyBlob = (PBYTE)malloc(dwKeyBlobLen)))
        {
            MyHandleError(
                TEXT("Memory allocation error.\n"), 
                E_OUTOFMEMORY); 
        }

        //-----------------------------------------------------------
        // Read the key BLOB from the source file. 
        if(!ReadFile(
            hSourceFile, 
            pbKeyBlob, 
            dwKeyBlobLen, 
            &dwCount, 
            NULL))
        {
            MyHandleError(
                TEXT("Error reading key BLOB length!\n"), 
                GetLastError());
            goto Exit_MyDecryptFile;
        }

        //-----------------------------------------------------------
        // Import the key BLOB into the CSP. 
        if(!CryptImportKey(
              hCryptProv, 
              pbKeyBlob, 
              dwKeyBlobLen, 
              0, 
              0, 
              &hKey))
        {
            MyHandleError(
                TEXT("Error during CryptImportKey!/n"), 
                GetLastError()); 
            goto Exit_MyDecryptFile;
        }

        if(pbKeyBlob)
        {
            free(pbKeyBlob);
        }
    }
    else
    {
        //-----------------------------------------------------------
        // Decrypt the file with a session key derived from a 
        // password. 

        //-----------------------------------------------------------
        // Create a hash object. 
        if(!CryptCreateHash(
               hCryptProv, 
               CALG_MD5, 
               0, 
               0, 
               &hHash))
        {
            MyHandleError(
                TEXT("Error during CryptCreateHash!\n"), 
                GetLastError());
            goto Exit_MyDecryptFile;
        }
        
        //-----------------------------------------------------------
        // Hash in the password data. 
        if(!CryptHashData(
               hHash, 
               (BYTE *)pszPassword, 
               lstrlen(pszPassword), 
               0)) 
        {
            MyHandleError(
                TEXT("Error during CryptHashData!\n"), 
                GetLastError()); 
            goto Exit_MyDecryptFile;
        }
    
        //-----------------------------------------------------------
        // Derive a session key from the hash object. 
        if(!CryptDeriveKey(
              hCryptProv, 
              ENCRYPT_ALGORITHM, 
              hHash, 
              KEYLENGTH, 
              &hKey))
        { 
            MyHandleError(
                TEXT("Error during CryptDeriveKey!\n"), 
                GetLastError()) ; 
            goto Exit_MyDecryptFile;
        }
    }

    //---------------------------------------------------------------
    // The decryption key is now available, either having been 
    // imported from a BLOB read in from the source file or having 
    // been created by using the password. This point in the program 
    // is not reached if the decryption key is not available.
     
    //---------------------------------------------------------------
    // Determine the number of bytes to decrypt at a time. 
    // This must be a multiple of ENCRYPT_BLOCK_SIZE. 

    dwBlockLen = 1000 - 1000 % ENCRYPT_BLOCK_SIZE; 
    dwBufferLen = dwBlockLen; 

    //---------------------------------------------------------------
    // Allocate memory for the file read buffer. 
    if(!(pbBuffer = (PBYTE)malloc(dwBufferLen)))
    {
       MyHandleError(TEXT("Out of memory!\n"), E_OUTOFMEMORY); 
       goto Exit_MyDecryptFile;
    }
    
    //---------------------------------------------------------------
    // Decrypt the source file, and write to the destination file. 
    bool fEOF = false;
    do
    {
        //-----------------------------------------------------------
        // Read up to dwBlockLen bytes from the source file. 
        if(!ReadFile(
            hSourceFile, 
            pbBuffer, 
            dwBlockLen, 
            &dwCount, 
            NULL))
        {
            MyHandleError(
                TEXT("Error reading from source file!\n"), 
                GetLastError());
            goto Exit_MyDecryptFile;
        }

        if(dwCount <= dwBlockLen)
        {
            fEOF = TRUE;
        }

        //-----------------------------------------------------------
        // Decrypt the block of data. 
        if(!CryptDecrypt(
              hKey, 
              0, 
              fEOF, 
              0, 
              pbBuffer, 
              &dwCount))
        {
            MyHandleError(
                TEXT("Error during CryptDecrypt!\n"), 
                GetLastError()); 
            goto Exit_MyDecryptFile;
        }

        //-----------------------------------------------------------
        // Write the decrypted data to the destination file. 
        if(!WriteFile(
            hDestinationFile, 
            pbBuffer, 
            dwCount,
            &dwCount,
            NULL))
        { 
            MyHandleError(
                TEXT("Error writing ciphertext.\n"), 
                GetLastError());
            goto Exit_MyDecryptFile;
        }

        //-----------------------------------------------------------
        // End the do loop when the last block of the source file 
        // has been read, encrypted, and written to the destination 
        // file.
    }while(!fEOF);

    fReturn = true;

Exit_MyDecryptFile:

    //---------------------------------------------------------------
    // Free the file read buffer.
    if(pbBuffer)
    {
        free(pbBuffer);
    }

    //---------------------------------------------------------------
    // Close files.
    if(hSourceFile)
    {
        CloseHandle(hSourceFile);
    }

    if(hDestinationFile)
    {
        CloseHandle(hDestinationFile);
    }

    //-----------------------------------------------------------
    // Release the hash object. 
    if(hHash) 
    {
        if(!(CryptDestroyHash(hHash)))
        {
            MyHandleError(
                TEXT("Error during CryptDestroyHash.\n"), 
                GetLastError()); 
        }

        hHash = NULL;
    }

    //---------------------------------------------------------------
    // Release the session key. 
    if(hKey)
    {
        if(!(CryptDestroyKey(hKey)))
        {
            MyHandleError(
                TEXT("Error during CryptDestroyKey!\n"), 
                GetLastError());
        }
    } 

    //---------------------------------------------------------------
    // Release the provider handle. 
    if(hCryptProv)
    {
        if(!(CryptReleaseContext(hCryptProv, 0)))
        {
            MyHandleError(
                TEXT("Error during CryptReleaseContext!\n"), 
                GetLastError());
        }
    } 

    return fReturn;
}


//-------------------------------------------------------------------
//  This example uses the function MyHandleError, a simple error
//  handling function, to print an error message to the  
//  standard error (stderr) file and exit the program. 
//  For most applications, replace this function with one 
//  that does more extensive error reporting.

void MyHandleError(LPTSTR psz, int nErrorNumber)
{
    _ftprintf(stderr, TEXT("An error occurred in the program. \n"));
    _ftprintf(stderr, TEXT("%s\n"), psz);
    _ftprintf(stderr, TEXT("Error number %x.\n"), nErrorNumber);
}
```



