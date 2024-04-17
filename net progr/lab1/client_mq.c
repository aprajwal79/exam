#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <string.h>

#define SHARED_MEM_SIZE 1024
#define SHARED_MEM_NAME "MySharedMemory"

int main() {
    HANDLE hMapFile;
    LPVOID pBuf;

    hMapFile = OpenFileMapping(
        FILE_MAP_ALL_ACCESS,    // Read/write access
        FALSE,                  // Do not inherit the name
        SHARED_MEM_NAME);       // Name of mapping object

    if (hMapFile == NULL) {
        printf("Could not open file mapping object (%d).\n", GetLastError());
        return 1;
    }

    pBuf = MapViewOfFile(
        hMapFile,               // Handle to map object
        FILE_MAP_WRITE,         // Write permission
        0,
        0,
        SHARED_MEM_SIZE);

    if (pBuf == NULL) {
        printf("Could not map view of file (%d).\n", GetLastError());
        CloseHandle(hMapFile);
        return 1;
    }

    while (1) {
        printf("Enter data to send to the server (type 'exit' to quit): ");
        char inputData[SHARED_MEM_SIZE];
        scanf("%s", inputData);

        if (strcmp(inputData, "exit") == 0) {
            break;
        }

        strncpy((char*)pBuf, inputData, SHARED_MEM_SIZE - 1);
        // Signal server that data is ready
        ((char*)pBuf)[SHARED_MEM_SIZE - 1] = '1';
    }

    UnmapViewOfFile(pBuf);
    CloseHandle(hMapFile);

    return 0;
}
