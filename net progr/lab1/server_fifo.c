#include <windows.h>
#include <stdio.h>

int main() {
    HANDLE hPipe;
    char buffer[1024];
    DWORD dwRead;

    hPipe = CreateNamedPipe("\\\\.\\pipe\\MyNamedPipe",
                            PIPE_ACCESS_INBOUND,
                            PIPE_TYPE_BYTE | PIPE_READMODE_BYTE | PIPE_WAIT,
                            1,
                            0,
                            0,
                            NMPWAIT_USE_DEFAULT_WAIT,
                            NULL);

    ConnectNamedPipe(hPipe, NULL);

    ReadFile(hPipe, buffer, sizeof(buffer) - 1, &dwRead, NULL);
    buffer[dwRead] = '\0';
    printf("Received: %s\n", buffer);

    CloseHandle(hPipe);

    return 0;
}
