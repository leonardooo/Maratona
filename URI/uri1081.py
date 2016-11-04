def inicializa(matriz, nodos):
	for i in range(nodos):
		for j in range(nodos):
			matriz[i,j] = False

def inicializaVisitados(visitados, nodos):
	for i in range(nodos):
		visitados.append(False)

def pathR(matriz, visitados, nodos, nodo, nivel):
	visitados[nodo] = True
	tem_vizinho = False
	for i in range(nodos):
		if matriz[nodo, i]:
			tem_vizinho = True
			print(" "*(nivel*2), end="")
			if not visitados[i]:
				print("%d-%d pathR(G,%d)" % (nodo, i, i))
				pathR(matriz, visitados, nodos, i, nivel+1)
			else:
				print("%d-%d" % (nodo, i))
	
	if tem_vizinho and nivel == 1:
		print()

def dfs(matriz, visitados, nodos):
	for i in range(nodos):
		nivel = 1
		if not visitados[i]:
			pathR(matriz, visitados, nodos, i, nivel)
			#print()

ncasos = int(input())

for i in range(ncasos):
	
	print("Caso %d:" % (i+1))
	
	matriz = {}
	visitados = []
	
	linha = input().split()
	nodos = int(linha[0])
	arestas = int(linha[1])
	
	inicializa(matriz, nodos)
	inicializaVisitados(visitados, nodos)
	
	for j in range(arestas):
		linha = input().split()
		fonte = int(linha[0])
		destino = int(linha[1])
		
		matriz[fonte,destino] = True
	
	dfs(matriz, visitados, nodos)
