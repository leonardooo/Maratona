num = input()
cont = len(num) - 1
res = ""
while cont >= 0:
    res += num[cont]
    cont -= 1
print(res)
