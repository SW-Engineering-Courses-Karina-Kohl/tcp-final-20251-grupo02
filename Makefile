JC=javac
JAYLIB=lib/jaylib-5.5.0-2.jar
JAYUNIT=lib/junit-platform-console-standalone-1.11.0.jar

run: src/*/*.java
	javac -g -Xlint:all -deprecation -cp "$(JAYLIB):$(JAYUNIT)" -d bin `find . -name "*.java" -not -name ".*"`
	java -cp $(JAYLIB):bin app.Main

clean:
	rm -r bin
