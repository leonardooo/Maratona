valor = int(float(input())*100)
#print(valor)
print("NOTAS:")

cem = valor // 10000
print("%d nota(s) de R$ 100.00" % cem)

valor -= 10000*cem

cinquenta = valor // 5000
print("%d nota(s) de R$ 50.00" % cinquenta)

valor -= 5000*cinquenta

vinte = valor // 2000
print("%d nota(s) de R$ 20.00" % vinte)

valor -= 2000*vinte

dez = valor // 1000
print("%d nota(s) de R$ 10.00" % dez)

valor -= 1000*dez

cinco = valor // 500
print("%d nota(s) de R$ 5.00" % cinco)

valor -= 500*cinco

dois = valor // 200
print("%d nota(s) de R$ 2.00" % dois)

valor -= 200*dois

print("MOEDAS:")

cem = valor // 100
print("%d moeda(s) de R$ 1.00" % cem)

valor -= 100*cem

cinquenta = valor // 50
print("%d moeda(s) de R$ 0.50" % cinquenta)

valor -= 50*cinquenta

vinte = valor // 25
print("%d moeda(s) de R$ 0.25" % vinte)

valor -= 25*vinte

dez = valor // 10
print("%d moeda(s) de R$ 0.10" % dez)

valor -= 10*dez

cinco = valor // 5
print("%d moeda(s) de R$ 0.05" % cinco)

valor -= 5*cinco

um = valor // 1
print("%d moeda(s) de R$ 0.01" % um)
