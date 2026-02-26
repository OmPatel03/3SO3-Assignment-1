# 3SO3-Assignment-1

## Task 3: Testing parts of large systems

Run section 3 of the assignment by running the following command in the terminal from the root directory of the project:

```bash
javac src/catan/*.java
java -cp src catan.Demo
```

## Task 4: Test-driven development (TDD)

Run Task 4 (TDD calculator) from the root directory:

```bash
javac src/calculator/*.java
java -cp src calculator.CalculatorTddTest
```

## Task 5: Test coverage and AI

Run Task 5 (e-commerce order processing tests) from the root directory:

```bash
cd src/task5
mvn clean test
```

Generate JaCoCo coverage report:

```bash
cd src/task5
mvn clean test jacoco:report
```

Generate PIT mutation testing report:

```bash
cd src/task5
mvn org.pitest:pitest-maven:mutationCoverage
```

Reports are generated at:

- `src/task5/target/site/jacoco/index.html`
- `src/task5/target/pit-reports/index.html`
