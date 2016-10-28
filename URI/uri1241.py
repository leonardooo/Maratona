n = int(input())

for i in range(n):
    linha = input().split()
    num1 = linha[0]
    num2 = linha[1]

    if len(num2) > len(num1):
        print("nao encaixa")
    else:

        encaixa = True

        indice = len(num1) - len(num2)
        k = 0
        for j in range(indice, len(num1)):
            if num1[j] != num2[k]:
                encaixa = False
                break
            k += 1

        if encaixa:
            print("encaixa")
        else:
            print("nao encaixa")

