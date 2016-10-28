entrada = input().split()
a = int(entrada[0])
b = int(entrada[1])
c = int(entrada[2])
d = int(entrada[3])

if c % a != 0:
	print("-1")
elif a == b or c == d:
	print("-1")
else:
	res = a
	achou = False
	while res <= 1000000000:
		if res > c:
			break
		
		if res % a == 0:
			if res % b != 0:
				if c % res == 0:
					if d % res != 0:
						achou = True
						break
		res += a

	if achou:
		print(res)
	else:
		print("-1")
