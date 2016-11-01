from math import sqrt, pow

p1 = input().split()
p2 = input().split()

p1x = float(p1[0])
p1y = float(p1[1])

p2x = float(p2[0])
p2y = float(p2[1])

res = sqrt(pow(p2x - p1x, 2) + pow(p2y - p1y, 2))

print("%.4f" % res)
