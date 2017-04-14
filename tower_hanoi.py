def tower(n, src, dest, spare):
	if n != 0:
		# move the n-1 from src to spare
		tower(n - 1, src=src, dest=spare, spare=dest)

		# move n from src to dest
		print('move disk', n, 'from', src, 'to', dest)

		# return n-1 from spare to dest
		tower(n - 1, src=spare, dest=dest, spare=src)

		# now we have moved all the tower to dest

tower(3, 'a', 'b', 'c')

