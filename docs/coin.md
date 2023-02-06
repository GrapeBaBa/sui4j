## Coin transaction 

### TransferSui

```java
        CompletableFuture<ExecuteTransactionResponse> res =
          sui.transferSui(
                "<sender_address>",   
                "<coin_object_id>",
                <gas_budget>,
                "<receiver_address>",
                <amount>,
                ExecuteTransactionRequestType.WaitForLocalExecution);
```

### PaySui

```java
        CompletableFuture<ExecuteTransactionResponse> res =
          sui.paySui(
                "<sender_address>",
                ["<coin_object_id>"],
                ["<receiver_address>"],
                [<amount>],
                <gas_budget>,
                ExecuteTransactionRequestType.WaitForLocalExecution);
```

### SplitCoin

```java
        CompletableFuture<ExecuteTransactionResponse> res =
          sui.splitCoin(
                "<sender_address>",
                "<coin_object_id>",
                [<amount>],
                "<gas_object_id>",
                <gas_budget>,
                ExecuteTransactionRequestType.WaitForLocalExecution);
```

### MergeCoins

```java
        CompletableFuture<ExecuteTransactionResponse> res =
          sui.mergeCoins(
                "<sender_address>",
                "<primary_coin_object_id>",
                "<to_merge_coin_object_id>",
                "<gas_object_id>",
                <gas_budget>,
                ExecuteTransactionRequestType.WaitForLocalExecution);
```

For more examples, you can see [SuiIntTests](https://github.com/GrapeBaBa/sui4j/blob/main/src/integrationTest/java/io/sui/SuiIntTests.java)
