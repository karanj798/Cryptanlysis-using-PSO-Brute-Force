include numpy as np
include math
include random

class Particle:
	velocity = []
	position = []
	pBestSelf = []
	bestError = -1
	currError = -1
	
	def __init__ ():
		# initialize a random position and random velocity
		for i in range(0, num_dimensions):#num dimensions = length of key
			position.append(random.random()*26)
			velocity.append(random.uniform(0, 1))		
		
		
	def updateVelocity (gBestPos, gBestErr):
		c1 = 2.05 #cognitive constant, social constant, inertia
		c2 = 2.05
		w = 0.9
		v1 = w * randomVel
		
		#inertia * current Velocity  = vel1
		# calculate cognitive velocity
		
		# calculate social velocity
		
		#resulting velocity = vector sum of all 3 of the above
		
	def updatePosition ():
		#just add the velocity to the position in each dimension
		
	def calcError (fitnessFunc):
		#implement this by yourself
		# remember that this must update the currError thingy, and the bestError (for self)

		
	
	
class PSO:
	swarm = []
	errorBestGlobal = -1
	
	def __init__ (self, maxIter, numParticles):
		#create a particle using the constructor n times based on numParticles
		
		
	def runPSO():
		for i in range(0, len(maxIter)):
			calcError() for each particle
			
			find the best error globally
			
			update velocity based on global and personal errors
			
			update position based on velocity
			
			repeat
			
			

	
