n = int(input())
for i in range(n):
	linha = input()
	lista = []
	
	for j in range( (len(linha) // 2)-1, -1, -1 ):
		lista.append( linha[j] )
	for j in range( len(linha)-1, (len(linha) // 2)-1, -1 ):
		lista.append( linha[j] )
		
	res = "".join(lista)
	print(res)
