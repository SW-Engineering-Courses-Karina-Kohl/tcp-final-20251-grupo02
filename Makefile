JC=javac
JAYLIB=lib/jaylib-5.5.0-2.jar
JAYUNIT=lib/junit-platform-console-standalone-1.11.0.jar

build:
	javac -g -Xlint:all -deprecation -cp "$(JAYLIB):$(JAYUNIT)" -d bin `find . -name "*.java" -not -name ".*"`


run: src/*/*.java
	javac -g -Xlint:all -deprecation -cp "$(JAYLIB):$(JAYUNIT)" -d bin `find . -name "*.java" -not -name ".*"`
	java -cp $(JAYLIB):bin app.Main

test: build
	java -jar  lib/junit-platform-console-standalone-1.11.0.jar --class-path "bin" --scan-class-path

clean:
	rm -r bin
