import numpy as np
import math
import random
'''
Frequencies:
A :  8.55        K :  0.81        U :  2.68
B :  1.60        L :  4.21        V :  1.06
C :  3.16        M :  2.53        W :  1.83
D :  3.87        N :  7.17        X :  0.19
E : 12.10        O :  7.47        Y :  1.72
F :  2.18        P :  2.07        Z :  0.11
G :  2.09        Q :  0.10                 
H :  4.96        R :  6.33                 
I :  7.33        S :  6.73                 
J :  0.22        T :  8.94     
'''
class Particle:
	
	
	def __init__ (self):
		self.velocity = []
		self.position = []
		self.pBestSelf = []
		self.bestError = -1
		self.currError = -1
		self.keyLen = 3
		# initialize a random position and random velocity
		for i in range(0, self.keyLen):                  # iterate through the length of key.
			self.position.append(random.random()*26)     # randomly initializing particle's position
			self.velocity.append(random.uniform(0, 1))   # ranomly initializing particle's	velocity 
		
		
	def updateVelocity (self, gBestPos, gBestErr):
		c1 = 2.05    # self confidence
		c2 = 2.05    # swarm confidence
		w = 0.9      # inertia
		for i in range(0, keyLen):
			r1 = random.random()
			r2 = random.random()
			
			# cognitive 
			vPersonal = c1 * r1 * (self.pBestSelf[i] - self.position[i])
			# social
			vGroup = c2 * r2 * (gBestSelf[i] - self.position[i])			
			# Vector sum and store to velocity
			self.velocity[i] = self.velocity [i] + vPersonal + vGroup
			
		#inertia * current Velocity  = vel1
		# calculate cognitive velocity		
		# calculate social velocity		
		#resulting velocity = vector sum of all 3 of the above
		
	def updatePosition (self):
		for i in range (0, keyLen):
			self.position[i] = self.position[i] + self.velocity[i]
			if 
			
		#just add the velocity to the position in each dimension
		
	def calcError (self, fitnessFunc):
		for i in range (0, keyLen):
			
		#implement this by yourself
		# remember that this must update the currError thingy, and the bestError (for self)

		
	
	
class PSO:
	swarm = []
	errorBestGlobal = -1
	
	def __init__ (self, maxIter, numParticles):
		#create a particle using the constructor n times based on numParticles
		
		
	def runPSO():
		for i in range(0, len(maxIter)):
			#calcError() for each particle
			
			#find the best error globally
			
			#update velocity based on global and personal errors
			
			#update position based on velocity
			
			#repeat
			
			

	
