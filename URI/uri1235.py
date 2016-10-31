n = int(input())
for i in range(n):
	linha = input()
	res = []
	#print("tamanho da linha %d" % len(linha))
	for j in range( len(linha)//2-1, -1, -1 ):
		#print(j)
		res.append( linha[j] )
	for j in range( len(linha)-1, len(linha)//2-1, -1 ):
		#print(j)
		res.append( linha[j] )
	texto = "".join(res)
	print(texto)
