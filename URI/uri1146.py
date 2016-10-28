while True:
    n = int(input())
    if n == 0:
        break
    i = 1
    while i <= n:
        if i == n:
            print(i)
        else:
            print(i, end = " ")
        i += 1
