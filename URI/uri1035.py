linha = input()
lista_linha = linha.split()

a = int(lista_linha[0])
b = int(lista_linha[1])
c = int(lista_linha[2])
d = int(lista_linha[3])

if b > c and d > a and (c+d) > (a+b) and c >= 0 and d >= 0 and (a % 2) == 0:
    print("Valores aceitos")
else:
    print("Valores nao aceitos")
    
