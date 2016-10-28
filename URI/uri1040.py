linha = input().split()
n1 = float(linha[0])
n2 = float(linha[1])
n3 = float(linha[2])
n4 = float(linha[3])
media = n1*0.2 + n2*0.3 + n3*0.4 + n4*0.1
print("Media: %.1f" % media)
if media < 5.0:
    print("Aluno reprovado.")
elif media >= 7.0:
    print("Aluno aprovado.")
else:
    print("Aluno em exame.")
    nf = float(input())
    print("Nota do exame: %.1f" % nf)
    mf = (media+nf) / 2
    if mf > 4.9:
        print("Aluno aprovado.")
    else:
        print("Aluno reprovado.")
    print("Media final: %.1f" % mf)
