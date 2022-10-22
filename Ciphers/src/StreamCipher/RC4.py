# Function for encryption
from StreamCipher.RC4Interface import RC4Interface


class RC4():


def encryption():

    global key, plain_text, n

    # Given text and key
    plain_text = "111110011111"
    key = "101000000000"

    n = 3

    # The initial state vector array
    S = [i for i in range(0, 2**n)]
    print("S : ", S)

    key_list = [key[i:i + n] for i in range(0, len(key), n)]

    # Convert to key_stream to decimal
    for i in range(len(key_list)):
        key_list[i] = int(key_list[i], 2)

    # Convert to plain_text to decimal
    global pt

    pt = [plain_text[i:i + n] for i in range(0, len(plain_text), n)]

    for i in range(len(pt)):
        pt[i] = int(pt[i], 2)

    print("Plain text ( in array form ): ", pt)

    # Making key_stream equal
    # to length of state vector
    diff = int(len(S)-len(key_list))

    if diff != 0:
        for i in range(0, diff):
            key_list.append(key_list[i])

    print("Key list : ", key_list)
    print(" ")

    # Perform the KSA algorithm
    def KSA():
        j = 0
        N = len(S)

        # Iterate over the range [0, N]
        for i in range(0, N):

            # Find the key
            j = (j + S[i]+key_list[i]) % N

            # Update S[i] and S[j]
            S[i], S[j] = S[j], S[i]
            print(i, " ", end ="")

            # Print S
            print(S)


    KSA()


    # Perform PGRA algorithm
    def PGRA():

        N = len(S)
        i = j = 0
        global key_stream
        key_stream = []

        # Iterate over [0, length of pt]
        for k in range(0, len(pt)):
            i = (i + 1) % N
            j = (j + S[i]) % N

            # Update S[i] and S[j]
            S[i], S[j] = S[j], S[i]
            print(k, " ", end ="")
            print(S)
            t = (S[i]+S[j]) % N
            key_stream.append(S[t])


    PGRA()

    # Performing XOR between generated
    # key stream and plain text
    def XOR():
        global cipher_text
        cipher_text = []
        for i in range(len(pt)):
            c = key_stream[i] ^ pt[i]
            cipher_text.append(c)

    XOR()

    # Convert the encrypted text to
    # bits form
    encrypted_to_bits = ""
    for i in cipher_text:
        encrypted_to_bits += '0'*(n-len(bin(i)[2:]))+bin(i)[2:]

    print(" ")
    print("Cipher text : ", encrypted_to_bits)


encryption()


# Function for decryption of data
def decryption():

    # The initial state vector array
    S = [i for i in range(0, 2**n)]

    key_list = [key[i:i + n] for i in range(0, len(key), n)]

    # Convert to key_stream to decimal
    for i in range(len(key_list)):
        key_list[i] = int(key_list[i], 2)

    # Convert to plain_text to decimal
    global pt

    pt = [plain_text[i:i + n] for i in range(0, len(plain_text), n)]

    for i in range(len(pt)):
        pt[i] = int(pt[i], 2)

    # making key_stream equal
    # to length of state vector
    diff = int(len(S)-len(key_list))

    if diff != 0:
        for i in range(0, diff):
            key_list.append(key_list[i])

    print(" ")

    # KSA algorithm
    def KSA():
        j = 0
        N = len(S)

        # Iterate over the range [0, N]
        for i in range(0, N):
            j = (j + S[i]+key_list[i]) % N

            # Update S[i] and S[j]
            S[i], S[j] = S[j], S[i]
            print(i, " ", end ="")
            print(S)

        initial_permutation_array = S
        print(" ")
        print("The initial permutation array is : ",
              initial_permutation_array)

    KSA()

    # Perform PRGA algorithm
    def do_PGRA():

        N = len(S)
        i = j = 0
        global key_stream
        key_stream = []

        # Iterate over the range
        for k in range(0, len(pt)):
            i = (i + 1) % N
            j = (j + S[i]) % N

            # Update S[i] and S[j]
            S[i], S[j] = S[j], S[i]
            print(k, " ", end ="")
            print(S)
            t = (S[i]+S[j]) % N
            key_stream.append(S[t])

    do_PGRA()

    # Perform XOR between generated
    # key stream and cipher text
    def do_XOR():
        global original_text
        original_text = []
        for i in range(len(cipher_text)):
            p = key_stream[i] ^ cipher_text[i]
            original_text.append(p)

    do_XOR()

    # convert the decrypted text to
    # the bits form
    decrypted_to_bits = ""
    for i in original_text:
        decrypted_to_bits += '0'*(n-len(bin(i)[2:]))+bin(i)[2:]

    print(" ")
    print("Decrypted text : ",
          decrypted_to_bits)

# Driver Code
decryption()
