total = 0.0
linha = input().split()
total = total + int(linha[1]) * float(linha[2])
linha = input().split()
total = total + int(linha[1]) * float(linha[2])
print("VALOR A PAGAR: R$ %.2f" % total)
