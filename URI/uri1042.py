linha = input().split()
lista = []

for token in linha:
	lista.append(int(token))

lista.sort()

for i in lista:
	print(i)
	
print()

for i in linha:
	print(i)

