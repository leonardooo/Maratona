def alloff(lamps):
    for i in range(len(lamps)):
        if(lamps[i]):
            return False
    return True

def click(all_lamps, switch_lamps):
    for i in range(len(switch_lamps)):
        all_lamps[switch_lamps[i]] = not all_lamps[switch_lamps[i]]

def compare(initial, current):
    for i in range(len(initial)):
        if(initial[i] != current[i]):
            return False
    return True

linha = input().split()
s = int(linha[0])
l = int(linha[1])

lamps = []
lamps.append(False)
for i in range(l):
    lamps.append(False)

linha = input().split()
for i in range(1, int(linha[0])+1):
    lamps[int(linha[i])] = True

initial_lamps = []
for i in range(len(lamps)):
    initial_lamps.append(lamps[i])

switchs = []
switchs.append([])
for i in range(1,s+1):
    linha = input().split()
    switchs.append([])
    for j in range(1, int(linha[0])+1):
        switchs[i].append(int(linha[j]))

#print(lamps)
#print(switchs)

res = 0
curr_switch = 1
while(True):
    if(alloff(lamps)):
        break
    
    #condicao de parada escrota pra evitar o loop infinito

    click(lamps, switchs[curr_switch])
    res += 1
    curr_switch += 1
    if(curr_switch > s):
        curr_switch = 1
        if(compare(initial_lamps, lamps)):
            res = -1
            break

print(res)