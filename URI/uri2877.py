def mex(di,dj):
    marked = []
    for i in range(1000):
        marked.append(False)
    
    for i in range(di):
        marked[ stones[i,dj] ] = True

    for j in range(dj):
        marked[ stones[di,j] ] = True

    mini = min(di,dj)

    for i in range(1,mini):
        marked[ stones[di-i,dj-i] ] = True

    return marked.index(False)

def printa(stones):
    for i in range(11):
        for j in range(11):
            print(str(stones[i,j]) + " ", end="")
        print("")


stones = {}
for i in range(101):
    for j in range(101):
        stones[i,j] = 0

for i in range(101):
    stones[i,i] = 999
    stones[0,i] = 999
    stones[i,0] = 999

for i in range(1,101):
    for j in range(1,101):
        if i != j:
            stones[i,j] = mex(i,j)

n = int(input())
res = 0
for i in range(n):
    linha = input().split()
    x = int(linha[0])
    y = int(linha[1])

    if( stones[x,y] == 999 ):
        print("Y")
        res = -1
        break
    
    res ^= stones[x,y]

if( res == 0 ):
    print("N")
elif( res > 0 ):
    print("Y")
