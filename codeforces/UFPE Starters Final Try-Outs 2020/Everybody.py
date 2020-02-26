def isPerfect(num):
    sum = 1
    for i in range(2, 2000001):
        if( i*i > num):
            break
        if(num % i == 0):
            if(i*i != num):
                sum += i + num/i
            else:
                sum += i
    
    return (sum == num) and (num != 1)
        
#for i in range(1, 2000001):
#    if (isPerfect(i)):
#        print(i)

cases = int(input())
for i in range(0, cases):
    num = int(input())
    if num < 6:
        print(-1)
    elif num < 28:
        print(6)
    elif num < 496:
        print(28)
    elif num < 8128:
        print(496)
    else:
        print(8128)