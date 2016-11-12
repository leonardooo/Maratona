from math import sqrt

linha = input().split()

a = float(linha[0])
b = float(linha[1])
c = float(linha[2])

if a == 0:
	print("Impossivel calcular")
else:
	delta = (b*b) - (4*a*c)
	print(delta)
	if delta < 0:
		print("Impossivel calcular")
	else:
		delta = sqrt(delta)
		print(delta)
		r1 = ((-b) + delta) / 2*a
		r2 = ((-b) - delta) / 2*a
		print(r1)
		print(r2)
		print("R1 = %.5f" % r1)
		print("R2 = %.5f" % r2)
