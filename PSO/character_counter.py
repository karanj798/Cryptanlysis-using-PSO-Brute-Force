str1 = input("Enter actual KEY")
str2 = input("Enter PSO KEY")
counter = 0
for i in range (0, len(str1)):
	if str1[i] == str2[i]:
		counter += 1
print(counter)