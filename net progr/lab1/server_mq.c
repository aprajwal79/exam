#include <stdio.h>
#include <stdlib.h>
#include <windows.h>

#define SHARED_MEM_SIZE 1024
#define SHARED_MEM_NAME "MySharedMemory"

int main() {
    HANDLE hMapFile;
    LPVOID pBuf;

    hMapFile = CreateFileMapping(
        INVALID_HANDLE_VALUE,    // Use paging file
        NULL,                    // Default security
        PAGE_READWRITE,          // Read/write access
        0,                       // Maximum size (high-order DWORD)
        SHARED_MEM_SIZE,         // Maximum size (low-order DWORD)
        SHARED_MEM_NAME);        // Name of mapping object

    if (hMapFile == NULL) {
        printf("Could not create file mapping object (%d).\n", GetLastError());
        return 1;
    }

    pBuf = MapViewOfFile(
        hMapFile,               // Handle to map object
        FILE_MAP_WRITE,         // Read/write permission
        0,
        0,
        SHARED_MEM_SIZE);

    if (pBuf == NULL) {
        printf("Could not map view of file (%d).\n", GetLastError());
        CloseHandle(hMapFile);
        return 1;
    }

    printf("Server is waiting for data from the client...\n");

    while (1) {
        while (((char*)pBuf)[SHARED_MEM_SIZE - 1] != '1') {
            Sleep(1000); // Wait for data signal from client
        }

        printf("Received data from client: %s\n", (char*)pBuf);

        // Reset signal flag
        ((char*)pBuf)[SHARED_MEM_SIZE - 1] = '\0';
    }

    UnmapViewOfFile(pBuf);
    CloseHandle(hMapFile);

    return 0;
}