valor = int(input())
print(valor)

cem = valor // 100
print("%d nota(s) de R$ 100,00" % cem)

valor -= 100*cem

cinquenta = valor // 50
print("%d nota(s) de R$ 50,00" % cinquenta)

valor -= 50*cinquenta

vinte = valor // 20
print("%d nota(s) de R$ 20,00" % vinte)

valor -= 20*vinte

dez = valor // 10
print("%d nota(s) de R$ 10,00" % dez)

valor -= 10*dez

cinco = valor // 5
print("%d nota(s) de R$ 5,00" % cinco)

valor -= 5*cinco

dois = valor // 2
print("%d nota(s) de R$ 2,00" % dois)

valor -= 2*dois

um = valor // 1
print("%d nota(s) de R$ 1,00" % um)
