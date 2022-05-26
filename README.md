# Read Me First
The following was discovered as part of building this project:


# Build
```shell
./gradlew  build
```

```shell
docker build -t thiagosoares/branas-ecommerce-kt:1.0 .
```


## Testing

To launch your application's tests, run:


```
./gradlew clean check
```

ou

```
./gradlew clean test integrationTest jacocoTestReport
```


## ToDos

- [ ] Modularizar o projeto
- [ ] Decidir: se tiver 3 implementações de Repository, como testar as 3 sem duplicar meio mundo de código de teste ?



# Test Reports

[test](build/reports/tests/test/index.html)
[Integration](build/reports/tests/integrationTest/index.html)
[jacoco](/build/jacocoHtml/index.html)
