one = "one"
two = "two"
three = "three"

n = int(input())
for i in range(n):
    palavra = input()
    cont = 0
    if len(palavra) == 3:

        for j in range(3):
            if palavra[j] == one[j]:
                cont += 1

        if cont >= 2:
            print("1")
        else:
            print("2")

    else:
        print("3")