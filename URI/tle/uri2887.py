class Edge:
    def __init__(self, ori, des):
        self.ori = ori
        self.des = des

def caminho(cam, ori, des):
    res = []
    res.insert(0, cam[des])

    while(res[0] != ori):
        res.insert(0, cam[res[0]])

    return res

def bellmanford(n, origem, distance, edges):
    distance[origem] = 0
    for j in range(n-1):
        for k in range(len(edges)):
            if distance[edges[k].ori] + 1 < distance[edges[k].des]:
                distance[edges[k].des] = distance[edges[k].ori] + 1
                pred[edges[k].des] = edges[k].ori

infinito = 9999999

linha = input().split()
n = int(linha[0])
q = int(linha[1])

edges = []
for i in range(n-1):
    linha = input().split()
    s1 = int(linha[0])
    s2 = int(linha[1])
    edges.append(Edge(s1,s2))
    edges.append(Edge(s2,s1))

for i in range(q):
    linha = input().split()
    o1 = int(linha[0])
    d1 = int(linha[1])
    o2 = int(linha[2])
    d2 = int(linha[3])

    pred = []
    distance = []
    for i in range(n+1):
        pred.append(None)
        distance.append(infinito)

    bellmanford(n, o1, distance, edges)
    cam1 = caminho(pred, o1, d1)

    pred = []
    distance = []
    for i in range(n+1):
        pred.append(None)
        distance.append(infinito)

    bellmanford(n, o2, distance, edges)
    cam2 = caminho(pred, o2, d2)

    res = set(cam1).intersection(set(cam2))

    print(len(res))
