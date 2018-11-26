str1 = "ACKNOWLEDGEMENT"
#str2 = "BEZAZZAAEA"
str2 = "AAAZAWAAZGEBAAZ"
counter = 0
for i in range (0, len(str1)):
	if str1[i] == str2[i]:
		counter += 1
print(counter)