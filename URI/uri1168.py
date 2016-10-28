n = int(input())
for i in range(n):
    linha = input()
    cont = 0
    for j in range(len(linha)):

        if linha[j] == "0" or linha[j] == "6" or linha[j] == "9":
            cont += 6
        if linha[j] == "1":
            cont += 2
        if linha[j] == "2" or linha[j] == "3" or linha[j] == "5":
            cont += 5
        if linha[j] == "4":
            cont += 4
        if linha[j] == "7":
            cont += 3
        if linha[j] == "8":
            cont += 7

    print("%d leds" % cont)
