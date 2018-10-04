encr = input()
decr = input()
res = 0
for i in range(len(encr)-len(decr)+1):
    iguais = False
    for j in range(len(decr)):
        if( encr[i+j] == decr[j] ):
            iguais = True
            break
    if not iguais:
        res += 1

print(res)
