qntdias = int(input())

anos = qntdias / 365
qntdias = qntdias % 365

meses = qntdias / 30
qntdias = qntdias % 30

dias = qntdias

print("%d ano(s)" % anos)
print("%d mes(es)" % meses)
print("%d dia(s)" % dias)
