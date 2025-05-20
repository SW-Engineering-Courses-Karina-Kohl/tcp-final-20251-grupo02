JC=javac
JAYLIB=lib/jaylib-5.5.0-2.jar

run: src/*/*.java
	javac -cp $(JAYLIB) -d bin `find . -name "*.java" -not -name ".*"`
	java -cp $(JAYLIB):bin app.Main

clean:
	rm -r bin
