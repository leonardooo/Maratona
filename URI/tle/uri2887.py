def caminho(cam, ori, des):
    res = []
    res.insert(0, cam[ori,des])

    while(res[0] != ori):
        res.insert(0, cam[ori,res[0]])

    return res

infinito = 9999999

linha = input().split()
n = int(linha[0])
q = int(linha[1])

grafo = {}
pred = {}
for i in range(n+1):
    for j in range(n+1):
        grafo[i,j] = infinito

for i in range(n-1):
    linha = input().split()
    s1 = int(linha[0])
    s2 = int(linha[1])
    grafo[s1,s2] = 1
    grafo[s2,s1] = 1

for i in range(1,n+1):
    for j in range(1,n+1):
        if(grafo[i,j] < infinito):
            pred[i,j] = i

#Floyd Warshall
for k in range(1,n+1):
    for i in range(1,n+1):
        for j in range(1,n+1):
            if grafo[i,j] > grafo[i,k] + grafo[k,j]:
                grafo[i,j] = grafo[i,k] + grafo[k,j]
                pred[i,j] = pred[k,j]

for i in range(q):
    linha = input().split()
    o1 = int(linha[0])
    d1 = int(linha[1])
    o2 = int(linha[2])
    d2 = int(linha[3])

    cam1 = caminho(pred, o1, d1)
    cam2 = caminho(pred, o2, d2)

    res = set(cam1).intersection(set(cam2))

    print(len(res))
