n = int(input())
linha = input().split()

achou = False
for i in range(1, len(linha)):
    if int(linha[i]) < int(linha[i-1]):
        print(i+1)
        achou = True
        break

if not achou:
    print(0)
