## Coin transaction 

### TransferSui

```java
TransactionBlockResponseOptions transactionBlockResponseOptions =
new TransactionBlockResponseOptions();
transactionBlockResponseOptions.setShowEffects(true);
transactionBlockResponseOptions.setShowEvents(true);
transactionBlockResponseOptions.setShowInput(true);
transactionBlockResponseOptions.setShowObjectChanges(true);

CompletableFuture<TransactionBlockResponse> res =
    sui.transferSui(
    sender.get(),
    null,
    recipient.get(),
    20000L,
    null,
    3000000L,
    null,
    null,
    transactionBlockResponseOptions,
    ExecuteTransactionRequestType.WaitForLocalExecution);

```

### MergeCoins

```java
TransactionBlockResponseOptions transactionBlockResponseOptions =
    new TransactionBlockResponseOptions();
    transactionBlockResponseOptions.setShowEffects(true);
    transactionBlockResponseOptions.setShowEvents(true);
    transactionBlockResponseOptions.setShowInput(true);
    transactionBlockResponseOptions.setShowObjectChanges(true);

CompletableFuture<TransactionBlockResponse> res =
    sui.mergeCoin(
    sender.get(),
    dest,
    source,
    null,
    3000000L,
    null,
    null,
    transactionBlockResponseOptions,
    ExecuteTransactionRequestType.WaitForLocalExecution);
```

For more examples, you can see [SuiIntTests](https://github.com/GrapeBaBa/sui4j/blob/main/src/integrationTest/java/io/sui/SuiIntTests.java)
