class_path = algs4.jar
cmp_file = *.java
run_file = Percolation

cmp_optns = -g 
cmp_optns += -classpath .:$(class_path)
cmp_optns += -Xdiags:verbose

compile:
	javac $(cmp_optns) $(cmp_file) && echo compilation succeeded || echo error

all: compile run

run:
	java -classpath '.:$(class_path)' $(run_file) && echo finished running || echo error

clean:
	rm *.log* *.class