CC=javac 

all:
	$(CC) *.java

javadoc:
	javadoc -charset utf8 *.java

clean::
	rm -f *.class *.html *.css package-list
	rm -Rf resources
