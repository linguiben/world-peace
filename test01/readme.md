## EvoSuite - Unit Test Generator
https://www.evosuite.org/documentation/tutorial-part-1/

1. download  
  wget https://github.com/EvoSuite/evosuite/releases/download/v1.0.6/evosuite-1.0.6.jar  
  wget https://github.com/EvoSuite/evosuite/releases/download/v1.0.6/evosuite-standalone-runtime-1.0.6.jar  
2. test evosuite  
  export PROJECT_HOME="/Users/jupiter/14.idea-workspace/world-peace/test01"; cd $PROJECT_HOME;  
  export CLASSPATH=$CLASSPATH:$PROJECT_HOME/src/lib/*:$PROJECT_HOME/target/classes  
  java -jar $PROJECT_HOME/src/lib/evosuite-1.2.0.jar  
  export EVOSUITE="java -jar $PROJECT_HOME/src/lib/evosuite-1.2.0.jar"  
  $EVOSUITE  
3. compile your source code  
  mvn compile  
  mvn clean install -Dmaven.test.skip=true  
4. generate unit test code
  $EVOSUITE -setup  #create profile
  $EVOSUITE -class com.jupiter.calc.Say -base_dir $PROJECT_HOME
  $EVOSUITE -class <classFullName> -projectCP <.class file path>  #evosuite-tests  
  $EVOSUITE -class com.jupiter.calc.Calculator -projectCP target/classes -base_dir $PROJECT_HOME #evosuite-tests  
5. prepare to run the test case just generated  
  mvn dependency:copy-dependencies  
  export CLASSPATH=target/classes:evosuite-standalone-runtime-1.0.6.jar:evosuite-tests:target/dependency/junit-4.13.2.jar  
6. compile the test case just generated  
  javac evosuite-tests/com/jupiter/calc/*.java  
7. run test case  
  java org.junit.runner.JUnitCore com.jupiter.calc.Say_ESTest  

### describe:
target/classes: This is the root directory which we need for the tutorial.Stack class  
evosuite-standalone-runtime-1.0.6.jar: This is the EvoSuite runtime library.  
evosuite-tests: This is the root directory where we put the test class files  
junit-4.12.jar and hamcrest-core-1.3.jar: We need JUnit to execute JUnit tests.  


cd /Users/jupiter/Downloads/EvoSuite/Tutorial_Stack;
export CLASSPATH=target/classes:/Users/jupiter/Downloads/EvoSuite/Tutorial_Stack/target/dependency/*:/Users/jupiter/Downloads/EvoSuite/Tutorial_Stack/evosuite-tests/lib

javac -cp target/classes:/Users/jupiter/Downloads/EvoSuite/Tutorial_Stack/target/dependency/*:/Users/jupiter/Downloads/EvoSuite/Tutorial_Stack/evosuite-tests/lib evosuite-tests/tutorial/*.java
java org.junit.runner.JUnitCore tutorial.Stack_ESTest



