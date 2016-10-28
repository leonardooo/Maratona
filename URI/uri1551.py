n = int(input())

for i in range(n):

    linha = input()
    lista = []
    
    for k in range( len(linha) ):
        letra = linha[k]
        if letra != ' ' and letra != ',':

            achou = False
            for l in range( len(lista) ):
                if letra == lista[l]:
                    achou = True
                    break

            if not achou:
                lista.append(letra)

    if len(lista) == 26:
        print("frase completa")
    elif len(lista) >= 13:
        print("frase quase completa")
    else:
        print("frase mal elaborada")
