c = int(input())
for i in range(c):
    n = int(input())

    idioma = input()
    ingles = False

    for k in range(n-1):
        if idioma != input():
            ingles = True

    if ingles:
        print("ingles")
    else:
        print(idioma)