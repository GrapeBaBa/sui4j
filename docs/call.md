## Objects and Smart Contract transaction

### TransferObject

```java
        CompletableFuture<ExecuteTransactionResponse> res =
          sui.transferObject(
                "<sender_address>",
                "<object_id>",
                "<receiver_address>",
                "<gas_object_id>",
                <gas_budget>,
                ExecuteTransactionRequestType.WaitForLocalExecution);
```

### Publish

```java
        CompletableFuture<ExecuteTransactionResponse> res =
          sui.publish(
                "<sender_address>",
                ["<base64_of_compiled_module>"],
                "<gas_object_id>",
                <gas_budget>,
                ExecuteTransactionRequestType.WaitForLocalExecution);
```

### MoveCall

```java
        CompletableFuture<ExecuteTransactionResponse> res =
          sui.moveCall(
                "<sender_address>",
                "<package_object_id>",
                "<module>",
                "<function>",
                [<type_argument>],
                [<argument>],
                "<gas_object_id>",
                <gas_budget>,
                ExecuteTransactionRequestType.WaitForLocalExecution);
```

### BatchTransaction

```java
        CompletableFuture<ExecuteTransactionResponse> res =
          sui.batchTransaction(
                "<sender_address>",
                [<call_request_parameter>],
                "<gas_object_id>",
                <gas_budget>,
                ExecuteTransactionRequestType.WaitForLocalExecution);
```

For more examples, you can see [SuiIntTests](https://github.com/GrapeBaBa/sui4j/blob/main/src/integrationTest/java/io/sui/SuiIntTests.java)
