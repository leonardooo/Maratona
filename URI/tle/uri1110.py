class StringBuilder:
	def __init__(self):
		self.lista = []
	
	def append(self,token):
		self.lista.append(token)
		
	def to_string(self):
		return "".join(self.lista)

class Fila:
	def __init__(self):
		self.fila = []
		
	def enfileira(self,elem):
		self.fila.append(elem)
		
	def desenfileira(self):
		return self.fila.pop(0)
		
	def tamanho(self):
		return len(self.fila)

def inicializa(fila, n):
	for i in range(1,n+1):
		fila.enfileira(i)
		
sb = StringBuilder()
while True:
	n = int(input())
	
	if n == 0:
		break
		
	fila = Fila()
	inicializa(fila, n)
	descartados = Fila()
	
	while fila.tamanho() > 1:
		temp = fila.desenfileira()
		descartados.enfileira(temp)
		temp = fila.desenfileira()
		fila.enfileira(temp)
		
	#print("Discarded cards:", end="")
	sb.append("Discarded cards:")
	while descartados.tamanho() > 1:
		#print(" %d," % descartados.desenfileira(), end="")
		sb.append(" %d," % descartados.desenfileira())
	#print(" %d" % descartados.desenfileira())
	sb.append(" %d\n" % descartados.desenfileira())
	
	#print("Remaining card: %d" % fila.desenfileira())
	sb.append("Remaining card: %d\n" % fila.desenfileira())

print(sb.to_string())
