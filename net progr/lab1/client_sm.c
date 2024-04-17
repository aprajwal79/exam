#include <windows.h>
#include <stdio.h>
#include <string.h>

#define SHM_SIZE 1024
#define SHM_NAME "MySharedMemory"

int main() {
    HANDLE hMapFile;
    LPCTSTR pBuf;

    hMapFile = OpenFileMapping(
        FILE_MAP_ALL_ACCESS,
        FALSE,
        SHM_NAME);

    if (hMapFile == NULL) {
        printf("Could not open file mapping object (%d).\n", GetLastError());
        return 1;
    }

    pBuf = (LPTSTR)MapViewOfFile(
        hMapFile,
        FILE_MAP_ALL_ACCESS,
        0,
        0,
        SHM_SIZE);

    if (pBuf == NULL) {
        printf("Could not map view of file (%d).\n", GetLastError());
        CloseHandle(hMapFile);
        return 1;
    }

    while (1) {
        while (pBuf[0] == '\0') {
            Sleep(1000); // Wait for the server to write data
        }

        printf("Client received: %s", pBuf);

        if (strncmp((char*)pBuf, "exit", 4) == 0) {
            break;
        }

        printf("Client: Enter data (type 'exit' to quit): ");
        fgets((char*)pBuf, SHM_SIZE, stdin);

        if (strncmp((char*)pBuf, "exit", 4) == 0) {
            break;
        }

        Sleep(1000); // Wait for the server to read the data
        while (pBuf[0] != '\0') {
            Sleep(1000); // Wait until the server reads the data
        }
    }

    UnmapViewOfFile(pBuf);
    CloseHandle(hMapFile);

    return 0;
}
