#include <stdio.h>
#include <string.h>

// Function to find the One's complement
// of the given binary string
void Ones_complement(char *data, int length)
{
    for (int i = 0; i < length; i++)
    {
        if (data[i] == '0')
            data[i] = '1';
        else
            data[i] = '0';
    }
}

// Function to return the checksum value of
// the given string when divided into K size blocks
void checkSum(char *data, int block_size, char *result)
{
    // Check data size is divisible by block_size
    // Otherwise add '0' front of the data
    int n = strlen(data);
    if (n % block_size != 0)
    {
        int pad_size = block_size - (n % block_size);
        for (int i = 0; i < pad_size; i++)
        {
            memmove(data + 1, data, strlen(data) + 1);
            data[0] = '0';
        }
    }

    // Binary addition of all blocks with carry
    // First block of data stored in result variable
    for (int i = 0; i < block_size; i++)
    {
        result[i] = data[i];
    }

    // Loop to calculate the block-wise addition of data
    for (int i = block_size; i < n; i += block_size)
    {
        // Stores the data of the next block
        char next_block[block_size + 1];
        next_block[block_size] = '\0';

        for (int j = i; j < i + block_size; j++)
        {
            next_block[j - i] = data[j];
        }

        // Stores the binary addition of two blocks
        char additions[block_size + 1];
        additions[block_size] = '\0';
        int sum = 0, carry = 0;

        // Loop to calculate the binary addition of
        // the current two blocks of k size
        for (int k = block_size - 1; k >= 0; k--)
        {
            sum += (next_block[k] - '0') + (result[k] - '0');
            carry = sum / 2;

            if (sum == 0)
            {
                additions[k] = '0';
                sum = carry;
            }
            else if (sum == 1)
            {
                additions[k] = '1';
                sum = carry;
            }
            else if (sum == 2)
            {
                additions[k] = '0';
                sum = carry;
            }
            else
            {
                additions[k] = '1';
                sum = carry;
            }
        }

        // After binary add of two blocks with carry,
        // if carry is 1 then apply binary addition
        char final[block_size + 1];
        final[block_size] = '\0';

        if (carry == 1)
        {
            for (int l = block_size - 1; l >= 0; l--)
            {
                if (carry == 0)
                {
                    final[l] = additions[l];
                }
                else if (((additions[l] - '0') + carry) % 2 == 0)
                {
                    final[l] = '0';
                    carry = 1;
                }
                else
                {
                    final[l] = '1';
                    carry = 0;
                }
            }

            memmove(result, final, block_size);
        }
        else
        {
            memmove(result, additions, block_size);
        }
    }

    // Return One's complement of result value
    // which represents the required checksum value
    Ones_complement(result, block_size);
}

// Function to check if the received message
// is the same as the sender's message
// Function to check if the received message
// is the same as the sender's message
int checker(char *sent_message, char *rec_message, int block_size)
{
    // Checksum Value of the sender's message
    char sender_checksum[block_size + 1];
    sender_checksum[block_size] = '\0';
    checkSum(sent_message, block_size, sender_checksum);

    // Checksum value for the receiver's message
    char receiver_checksum[block_size + 1];
    receiver_checksum[block_size] = '\0';
    checkSum(strcat(rec_message, sender_checksum), block_size, receiver_checksum);

    // If receiver's checksum value is all '0'
    int error = 0;
    for (int i = 0; i < block_size; i++)
    {
        if (receiver_checksum[i] != '0')
        {
            error = 1;
            break;
        }
    }

    return !error; // true if no error, false if error
}


// Driver Code
int main()
{
    char sent_message[] = "10000101011000111001010011101101";
    char recv_message[] = "10000101011000111001010011101101";
    int block_size = 8;

    if (checker(sent_message, recv_message, block_size))
    {
        printf("No Error\n");
    }
    else
    {
        printf("Error\n");
    }

    return 0;
}
