#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <unistd.h>

#define TIMEOUT 3 

void sender();
void receiver();

int main() {
    sender();
    return 0;
}

void sender() {
    bool ack_received = false;
    int message = 10;
    int ack;

    while (!ack_received) {
        printf("Sender: Sending message %d\n", message);
        sleep(1); 
        if (rand() % 4 != 0) {
            printf("Sender: Message %d lost\n", message);
        } else {
            receiver(&ack);
            if (ack == message + 1) {
                printf("Sender: Acknowledgment received for message %d\n", message);
                ack_received = true;
                message++;
            } else {
                printf("Sender: Incorrect acknowledgment received. Retransmitting message %d\n", message);
            }
        }
    }
}

void receiver(int *ack) {
    sleep(1);
    *ack = rand() % 2 == 0 ? 0 : 1; // Generate random acknowledgment
    printf("Receiver: Acknowledgment %d sent\n", *ack);
}
