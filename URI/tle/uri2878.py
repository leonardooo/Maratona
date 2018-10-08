class Corte:
    def __init__(self, ini, fim):
        self.ini = ini
        self.fim = fim

    def intersect(self, outro_corte):
        if( self.ini < outro_corte.ini and self.fim > outro_corte.fim ) or ( self.ini > outro_corte.ini and self.fim < outro_corte.fim ):
            return True
        return False


linha = input().split()
x = int(linha[0])
y = int(linha[1])

linha = input().split()
h = int(linha[0])
v = int(linha[1])

horizontais = []
for i in range(h):
    linha = input().split()
    horizontais.append( Corte(int(linha[0]), int(linha[1])) )

verticais = []
for i in range(v):
    linha = input().split()
    verticais.append( Corte(int(linha[0]), int(linha[1])) )

horizontais.sort(key=lambda corte: corte.ini)
verticais.sort(key=lambda corte: corte.ini)

res = (h+1)*(v+1)

for i in range(h-1):
    for j in range(i+1, h):
        if( horizontais[i].intersect(horizontais[j]) ):
            res += 1

for i in range(v-1):
    for j in range(i+1, v):
        if( verticais[i].intersect(verticais[j]) ):
            res += 1

print(res)
