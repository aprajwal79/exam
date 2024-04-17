#include <windows.h>
#include <stdio.h>
#include <string.h>

#define SHM_SIZE 1024
#define SHM_NAME "MySharedMemory"

int main() {
    HANDLE hMapFile;
    LPCTSTR pBuf;

    hMapFile = CreateFileMapping(
        INVALID_HANDLE_VALUE,
        NULL,
        PAGE_READWRITE,
        0,
        SHM_SIZE,
        SHM_NAME);

    if (hMapFile == NULL) {
        printf("Could not create file mapping object (%d).\n", GetLastError());
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

    printf("Server is waiting for data from the client...\n");

    while (1) {
        printf("Server: Enter data (type 'exit' to quit): ");
        fgets((char*)pBuf, SHM_SIZE, stdin);

        if (strncmp((char*)pBuf, "exit", 4) == 0) {
            break;
        }

        Sleep(1000); // Wait for the client to read the data
        while (pBuf[0] != '\0') {
            Sleep(1000); // Wait until the client reads the data
        }
    }

    UnmapViewOfFile(pBuf);
    CloseHandle(hMapFile);

    return 0;
}
