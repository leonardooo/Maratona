linha = input()
lista_linha = linha.split()

cod = int(lista_linha[0])
qnt = int(lista_linha[1])

res = 0
if cod == 1:
    res = 4.0 * qnt
elif cod == 2:
    res = 4.5 * qnt
elif cod == 3:
    res = 5.0 * qnt
elif cod == 4:
    res = 2.0 * qnt
elif cod == 5:
    res = 1.5 * qnt

print("Total: R$ %.2f" % res)
