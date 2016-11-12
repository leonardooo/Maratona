segundos = int(input())

horas = (segundos // 60) // 60

segundos -= horas * 60 * 60

minutos = segundos // 60

segundos -= minutos * 60

print("%d:%d:%d" % (horas, minutos, segundos))
