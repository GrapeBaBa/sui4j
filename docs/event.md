## Subscribe Events

### SubscribeEvent

```java
    AllEventFilter eventFilter = new AllEventFilter();

    Disposable disposable =
    SUI.subscribeEvent(eventFilter, System.out::println, System.out::println);

    disposable.dispose();
```

For more examples, you can see [SuiIntTests](https://github.com/GrapeBaBa/sui4j/blob/main/src/integrationTest/java/io/sui/SuiIntTests.java)
