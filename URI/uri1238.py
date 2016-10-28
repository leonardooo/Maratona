n = int(input())
for i in range(n):
    linha = input()
    lista = linha.split()
    
    p1 = lista[0]
    p2 = lista[1]
    
    lista = []
    tam = 0
    
    if len(p1) > len(p2):
        tam = len(p1)
    else:
        tam = len(p2)
        
    for j in range(tam):
        if j < len(p1):
            lista.append( p1[j] )
        if j < len(p2):
            lista.append( p2[j] )

    res = "".join(lista)
    print(res)
