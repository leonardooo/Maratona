num = int(input())
contador = 3
num1 = 0
num2 = 1
if num == 1:
    print("0")
elif num == 2:
    print("0 1")
else:
    print("0 1", end = " ")
    while contador <= num:

        temp = num1 + num2
        num1 = num2
        num2 = temp

        if contador == num:
            print(num2)
        else:
            print(num2, end = " ")

        contador += 1
