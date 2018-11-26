import math
import random
import string 

# NOTE: currentPosition is a potential key currentPosition[0] = 'K' currentPosition[1] = 'E' currentPosition[2] = 'Y'.
# TODO: Decrypt the encrypted text using the potential key.
# TODO: Then perform fitness test on the decrypted text.


def decrypt(encrypted, key):
	# print(alphabet[int(positionArr[i])], end="")
	# print(string)
	#print(key, "----")
	## Decryption...

	cipher = []
	i = 0
	for c in encrypted:
		if c in string.ascii_uppercase:
			pos_c = ord(c) - ord('A')
			pos_k = ord(key[i]) - ord('A')
			cipher.append(chr((pos_c - pos_k) % 26 + ord('A')))
			i = (i+1) % len(key)
		else:
			cipher.append(c)
	
	decrypted = ''.join(cipher)
	#print(decrypted, "\n")
	
	return decrypted

def fitnessFunc(currentPosition):

	# print("currentPosition---------- ", currentPosition)
	stdFreq = {'A': 8.55, 'B': 1.60, 'C': 3.16, 'D': 3.87, 'E':12.10, 'F': 2.18, 'G': 2.09, 'H': 4.96, 'I': 7.33, 'J': 0.22,
	'K': 0.81, 'L': 4.21, 'M': 2.53, 'N': 7.17, 'O': 7.47, 'P': 2.07, 'Q': 0.10, 'U': 2.68, 'R': 6.33, 'S': 6.73,'T': 8.94, 
	'V': 1.06, 'W': 1.83, 'X': 0.19, 'Y': 1.72, 'Z': 0.11}

	alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
	    'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
	with open ("cipher.txt") as file:
		encryText = file.read()
	
	key = ""
	# print(int(positionArr[0]), int(positionArr[1]),int(positionArr[2]), "
	# ", end="")
	for i in range(0, len(currentPosition)):
		key = key + alphabet[int(currentPosition[i])]
	decryText = decrypt(encryText, key)
	#print(decryText)

	obFreq = {}
	for w in decryText:
		obFreq[w] = decryText.count(w)
	#print(decryText)
	#print(currentPosition)
	fitness = []
	diff = 0
	for w in stdFreq:
		if w in obFreq:
			diff = diff + (abs(stdFreq[w] - (obFreq[w]/len(decryText) * 100)))
		else:
			# if the character not in the key.
			diff = diff + (abs(stdFreq[w] - 0 / len(decryText) * 100))
			#print(w, " ", (obFreq[w]))	
	print(diff)
	return diff

class Particle:
	
	def __init__ (self):
		self.velocity = []
		self.position = []
		self.pBestSelf = []
		self.bestError = -1
		self.currError = -1
		self.keyLen = 5                                  # Change this to w/e you like.....

		# initialize a random position and random velocity
		for i in range(0, self.keyLen):                  # iterate through the length of key.
			self.position.append(random.random()*26)     # randomly initializing particle's position
			self.velocity.append(random.uniform(-1, 1))   # ranomly initializing particle's velocity 
		
		
	def updateVelocity (self, gBestPos):
		c1 = 2.05    # self confidence
		c2 = 2.05    # swarm confidence
		w = 0.9      # inertia
		for i in range(0, self.keyLen):
			r1 = random.uniform(0, 1)
			r2 = random.uniform(0, 1)
			
			# cognitive 
			vPersonal = c1 * r1 * (self.pBestSelf[i] - self.position[i])
			# social
			vGroup = c2 * r2 * (gBestPos[i] - self.position[i])			
			# Vector sum and store to velocity
			self.velocity[i] = w * self.velocity[i] + vPersonal + vGroup
			
			
	def updatePosition (self, bounds):
		for i in range (0, self.keyLen):
			self.position[i] = self.position[i] + self.velocity[i]
			
			if self.position[i] > bounds[i][1]:
				self.position[i] = bounds[i][1]
				
			if self.position[i] < bounds[i][0]:
				self.position[i] = bounds[i][0]
			
			
		# just add the velocity to the position in each dimension
		
	def calcError (self, func):
		self.currError = func(self.position)
		if self.currError < self.bestError or self.bestError == -1:
			self.pBestSelf = self.position
			self.bestError = self.currError
		# implement this by yourself
		# remember that this must update the currError thingy, and the bestError (for self)
	
class PSO():
	
	def __init__ (self, maxIter, numParticles, bounds, fitnessFunc):
		# create a particle using the constructor n times based on numParticles
		    
		bestErrorG = -1
		bestPositionG = []
		swarm = []
		alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P','Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
		
		
		for i in range(0, numParticles):
			p = Particle()
			swarm.append(p)
		i = 0
		while i < maxIter:
			for j in range (0, numParticles):
				#print(i, " ", bestErrorG)
				swarm[j].calcError(fitnessFunc)

				#print("BestError: ", bestErrorG, " current Error: ", swarm[j].currError)
				
				if swarm[j].currError < bestErrorG or bestErrorG == -1:
					bestPositionG = list(swarm[j].position)
					bestErrorG = float(swarm[j].currError)
			
			for j in range(0, numParticles):
				swarm[j].updateVelocity(bestPositionG)
				swarm[j].updatePosition(bounds)
			
			i = i + 1
			
			#print("The key: ", end="")	

		for key in range (0, len(bestPositionG)):
			print(alphabet[int(bestPositionG[key])] , end="")
		print()



iterations = 100
particle = 100
bounds = [(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25),(0,25)]
PSO(iterations, particle, bounds, fitnessFunc) 


	
