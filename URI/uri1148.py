infinito = 999999999

while True:
	tokens = input().split()

	n_cidades = int(tokens[0])+1
	n_links = int(tokens[1])

	if n_cidades == 1 and n_links == 0:
		break

	grafo = {}
	for i in range(n_cidades):
		for j in range(n_cidades):
			grafo[i,j] = infinito

	for i in range(n_links):
		tokens = input().split()
		
		origem = int(tokens[0])
		destino = int(tokens[1])
		distancia = int(tokens[2])
		
		grafo[origem,destino] = distancia

	#Cidades no mesmo país
	for i in range(n_cidades):
		for j in range(n_cidades):
			if grafo[i,j] != infinito and grafo[j,i] != infinito:
				grafo[i,j] = 0
				grafo[j,i] = 0

	#Floyd Warshall
	for k in range(1,n_cidades):
		for i in range(1,n_cidades):
			for j in range(1,n_cidades):
				if grafo[i,j] > grafo[i,k] + grafo[k,j]:
					grafo[i,j] = grafo[i,k] + grafo[k,j]

	n = int(input())

	for i in range(n):
		tokens = input().split()
		
		origem = int(tokens[0])
		destino = int(tokens[1])
		
		res = grafo[origem,destino]
		
		if res >= infinito:
			print("Nao e possivel entregar a carta")
		else:
			print(res)
	
	print()
		
