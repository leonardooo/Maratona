class Pilha:
	def __init__(self):
		self.lista = []
	
	def push(self,elem):
		self.lista.append(elem)
		
	def pop(self):
		return self.lista.pop( self.size() - 1 )
		
	def top(self):
		return self.lista[ self.size() - 1 ]
		
	def size(self):
		return len(self.lista)
		
	def diamante(self):
		if self.size() >= 2:
			return self.lista[self.size()-1] == '>' and \
				self.lista[self.size()-2] == '<'
		return False
			
n = int(input())
for i in range(n):
	linha = input()
	
	pilha = Pilha()
	contador = 0
	for k in range(len(linha)):
		if linha[k] == '.':
			continue
		
		pilha.push(linha[k])
		
		if pilha.diamante():
			contador += 1
			pilha.pop()
			pilha.pop()
	
	print("%d" % contador)
