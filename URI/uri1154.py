acumulador = 0
contador = 0
while True:
    idade = int(input())
    if idade < 0:
        break

    acumulador += idade
    contador += 1

media = acumulador / contador
print("%.2f" % media)
