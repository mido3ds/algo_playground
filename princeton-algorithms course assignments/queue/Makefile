class_path = algs4.jar
cmp_file = Deque.java
run_file = Deque

cmp_optns = -g 
cmp_optns += -classpath .:$(class_path)
cmp_optns += -Xdiags:verbose

compile:
	javac $(cmp_optns) $(cmp_file) && echo compilation succeeded || echo error

all: compile run

run:
	java -ea -classpath '.:$(class_path)' $(run_file) && echo finished running || echo error

clean:
	rm *.log* *.class