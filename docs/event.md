## Subscribe Events

### SubscribeEvent

```java
        EventTypeEventFilter eventFilter = new EventTypeEventFilter();
        eventFilter.setEventType(EventType.CoinBalanceChange);
        Disposable disposable =
          sui.subscribeEvent(
                eventFilter,
                <event_callback>,
                <error_callback>);  
        moveCall(); // transaction invocation
    
        disposable.dispose(); // undo subscribe
```

For more examples, you can see [SuiIntTests](https://github.com/GrapeBaBa/sui4j/blob/main/src/integrationTest/java/io/sui/SuiIntTests.java)
