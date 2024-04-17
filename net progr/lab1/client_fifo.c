#include <windows.h>
#include <stdio.h>

int main() {
    HANDLE hPipe;
    DWORD dwWritten;

    hPipe = CreateFile("\\\\.\\pipe\\MyNamedPipe",
                       GENERIC_WRITE,
                       0,
                       NULL,
                       OPEN_EXISTING,
                       0,
                       NULL);

    WriteFile(hPipe, "Hello, i am sending message in fifo", 36, &dwWritten, NULL);

    CloseHandle(hPipe);

    return 0;
}
