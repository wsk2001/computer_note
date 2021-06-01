# cannot open file MSVCRT.lib 해결 방법

## Scenario

**1. Windows 10** with **Visual Studio 2017** (FRESH installation).

**2. 'C' project** (LINK : fatal **error LNK1104**: cannot open file '**MSVCRTD.lib**').



## **Resolve**

1. Run '**Visual Studio Installer**'.

2. Click button 'Modify'.

3. Select '**Desktop development with C++**'.

4. From "Installation details"(usually on the right-sidebar) select:

   4.1. **VC++ 2015.3 v14.00(v140) toolset for desktop**.

   - Version of 'toolset' in 4.1. is just for example.

5. Click button 'Modify', to apply changes.

6. Right-click 'SomeProject' -> 'Properties' -> '**Linker**' -> '**General**' -> '**Additional Library Directories**': **$(VCToolsInstallDir)**\lib\x86

   (!!! for **x64** project: 'Additional Library Directories': $(VCToolsInstallDir)\lib\x64 !!!)



출처: <https://stackoverflow.com/questions/6228112/link-fatal-error-lnk1104-cannot-open-file-msvcrtd-lib>     

