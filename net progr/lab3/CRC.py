def xor(a, b):
    result = []
    for i in range(1, len(b)):
        if a[i] == b[i]:
            result.append('0')
        else:
            result.append('1')
    return ''.join(result)

def mod2div(dividend, divisor):
    pick = len(divisor)
    tmp = dividend[0: pick]
    while pick < len(dividend):
        if tmp[0] == '1':
            tmp = xor(divisor, tmp) + dividend[pick]
        else:
            tmp = xor('0'*pick, tmp) + dividend[pick]
        pick += 1
    if tmp[0] == '1':
        tmp = xor(divisor, tmp)
    else:
        tmp = xor('0'*pick, tmp)
    checkword = tmp
    return checkword

def encodeData(data, key):
    l_key = len(key)
    appended_data = data + '0'*(l_key-1)
    remainder = mod2div(appended_data, key)
    codeword = data + remainder
    print("Encoded Data (Data + Remainder): ", codeword)
    return codeword

def decodeData(received_data, key):
    l_key = len(key)
    remainder = mod2div(received_data, key)
    print("Remainder : ", remainder)
    if int(remainder) == 0:
        print("No error detected. Data received without error.")
    else:
        print("Error detected in received data.")

def main():
    data = str(input("Enter the data to be transmitted: "))
    key = str(input("Enter the key: "))
    print("\nSender Side:")
    encoded_data = encodeData(data, key)

    print("\nReceiver Side:")
    received_data = encoded_data
    decodeData(received_data, key)

if __name__ == "__main__":
    main()
