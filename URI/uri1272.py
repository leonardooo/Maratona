n = int(input())
for i in range(n):
    linha = input()
    lista = linha.split()
    
    res = []
    
    for j in range( len(lista) ):
        palavra = lista[j]
        res.append( palavra[0] )
        
    saida = "".join(res)
    print(saida)
