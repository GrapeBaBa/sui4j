## Objects and Smart Contract transaction

### TransferObjects

```java
TransactionBlockResponseOptions transactionBlockResponseOptions=
    new TransactionBlockResponseOptions();
    transactionBlockResponseOptions.setShowEffects(true);
    transactionBlockResponseOptions.setShowEvents(true);
    transactionBlockResponseOptions.setShowInput(true);
    transactionBlockResponseOptions.setShowObjectChanges(true);
    CompletableFuture<TransactionBlockResponse> res=
    sui.transferObjects(
    sender.get(),
    Lists.newArrayList(objects.get(0).getData().getObjectId()),
    recipient.get(),
    null,
    3000000L,
    null,
    null,
    transactionBlockResponseOptions,
    ExecuteTransactionRequestType.WaitForLocalExecution);
```

### Publish

```java
TransactionBlockResponseOptions transactionBlockResponseOptions=
    new TransactionBlockResponseOptions();
    transactionBlockResponseOptions.setShowEffects(true);
    transactionBlockResponseOptions.setShowEvents(true);
    transactionBlockResponseOptions.setShowInput(true);
    transactionBlockResponseOptions.setShowObjectChanges(true);
    CompletableFuture<TransactionBlockResponse> res=
    sui.publish(
    sender.get(),
    Lists.newArrayList(
    "oRzrCwYAAAAKAQAUAhQsA0BJBIkBEgWbAWcHggLNAgjPBGAGrwXCAwrxCC0MngnUAQAMAR4B"
    +"JAIRAh0CHwIlAiYCJwIoAAACAAABDAAAAwQAAQQHAQAAAgYHAAMCDAEIAQQIBAAFBQwABwcCAAkJ"
    +"BwAAFgABAAEcARUBAAEjFBUBAAIpCwwAAwoNAQEIAxoJCgEIBBoSEwAFDgYHAQIGIREBAQwGJREB"
    +"AQgHIg4PAAgXBAUBAgkbCxYACwMHAwUIBAgIEAgHAgwBDAkIAggABwgIAAILBQEIAQgHAQgAAQYJ"
    +"AAEBAgkABwgIAQgHAQgBAgYIBwcICAELBQEJAAEKAgEIBAMHCwUBCQAKCAQKCAQBBggIAQUBCwUB"
    +"CAECCQAFAQcICAEIBgEJAAELAwEJAAEICQVCT0FSUwRCb2FyB0Rpc3BsYXkITWV0YWRhdGEGT3B0"
    +"aW9uCVB1Ymxpc2hlcgZTdHJpbmcJVHhDb250ZXh0A1VJRANVcmwMYWRkX211bHRpcGxlA2FnZQVi"
    +"b2FycwVidXllcgVjbGFpbQdjcmVhdG9yC2Rlc2NyaXB0aW9uB2Rpc3BsYXkLZHVtbXlfZmllbGQI"
    +"ZnVsbF91cmwCaWQHaW1nX3VybARpbml0E2lzX29uZV90aW1lX3dpdG5lc3MIbWV0YWRhdGEEbmFt"
    +"ZQNuZXcVbmV3X3Vuc2FmZV9mcm9tX2J5dGVzBG5vbmUGb2JqZWN0Bm9wdGlvbgdwYWNrYWdlBXBy"
    +"aWNlD3B1YmxpY190cmFuc2ZlcgZzZW5kZXIEc29tZQZzdHJpbmcIdHJhbnNmZXIKdHhfY29udGV4"
    +"dAV0eXBlcwN1cmwEdXRmOAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
    +"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAgMI"
    +"AAAAAAAAAAAKAgUEbmFtZQoCDAtkZXNjcmlwdGlvbgoCCAdpbWdfdXJsCgIIB2NyZWF0b3IKAgYF"
    +"cHJpY2UKAgwLcHJvamVjdF91cmwKAgQDYWdlCgIGBWJ1eWVyCgIJCGZ1bGxfdXJsCgIODWVzY2Fw"
    +"ZV9zeW50YXgKAgcGe25hbWV9CgI7OlVuaXF1ZSBCb2FyIGZyb20gdGhlIEJvYXJzIGNvbGxlY3Rp"
    +"b24gd2l0aCB7bmFtZX0gYW5kIHtpZH0KAiEgaHR0cHM6Ly9nZXQtYS1ib2FyLmNvbS97aW1nX3Vy"
    +"bH0KAgoJe2NyZWF0b3J9CgIIB3twcmljZX0KAhgXaHR0cHM6Ly9nZXQtYS1ib2FyLmNvbS8KAg8O"
    +"e21ldGFkYXRhLmFnZX0KAggHe2J1eWVyfQoCCwp7ZnVsbF91cmx9CgIJCFx7bmFtZVx9CgIKCWZp"
    +"cnN0LnBuZwoCCwpGaXJzdCBCb2FyCgImJUZpcnN0IEJvYXIgZnJvbSB0aGUgQm9hcnMgY29sbGVj"
    +"dGlvbiEKAgYFQ2hyaXMKAiAfaHR0cHM6Ly9nZXQtYS1ib2FyLmZ1bGx1cmwuY29tLwACARIBAQIJ"
    +"FAgGFQgEGQgEEAgEDwsDAQgEIAsDAQgEGAgCDQUTCAkCAgELAwAAAAACXw4AOAAEBAUICwEBBwAn"
    +"CwAKATgBDAMOAwoBOAIMAg0CBwERAwcCEQMHAxEDBwQRAwcFEQMHBhEDBwcRAwcIEQMHCREDBwoR"
    +"A0AMCgAAAAAAAAAHCxEDBwwRAwcNEQMHDhEDBw8RAwcQEQMHEREDBxIRAwcTEQMHFBEDQAwKAAAA"
    +"AAAAADgDCwIKAS4RCjgECwMKAS4RCjgFCgERBgcVEQMHFhEDBxcRAwcYEQM4BjgHBgoAAAAAAAAA"
    +"EgIKAS4RCgcZEQwSAQsBLhEKOAgCAA=="),
    Lists.newArrayList(
    "0x0000000000000000000000000000000000000000000000000000000000000001",
    "0x0000000000000000000000000000000000000000000000000000000000000002"),
    null,
    30000000L,
    null,
    null,
    transactionBlockResponseOptions,
    ExecuteTransactionRequestType.WaitForLocalExecution);
```

### MoveCall

```java
TransactionBlockResponseOptions transactionBlockResponseOptions=
    new TransactionBlockResponseOptions();
    transactionBlockResponseOptions.setShowEffects(true);
    transactionBlockResponseOptions.setShowEvents(true);
    transactionBlockResponseOptions.setShowInput(true);
    transactionBlockResponseOptions.setShowObjectChanges(true);

    io.sui.models.transactions.TypeTag.StructType structType=
    new io.sui.models.transactions.TypeTag.StructType();
    io.sui.models.transactions.StructTag structTag=new StructTag();
    structTag.setAddress("0x02");
    structTag.setModule("sui");
    structTag.setName("SUI");
    structType.setStructTag(structTag);
    CompletableFuture<TransactionBlockResponse> res=
    sui.moveCall(
    sender.get(),
    "0x02",
    "pay",
    "split",
    Lists.newArrayList(structType),
    Lists.newArrayList(suiObjectResponse.getData().getObjectId(),10000L),
    null,
    3000000L,
    null,
    null,
    transactionBlockResponseOptions,
    ExecuteTransactionRequestType.WaitForLocalExecution);
```

### SponsoredTransaction

```java
CompletableFuture<TransactionBlockResponse> res=
    sui.newTransactionBlock()
    .thenCompose(
    (Function<TransactionBlock, CompletableFuture<TransactionBlockResponse>>)
    transactionBlock->{
      transactionBlock.setExpiration(null);
      transactionBlock.setSender(sender.get());
      return transactionBlock
        .transferObjects(
          Lists.newArrayList(objects.get(0).getData().getObjectId()),
          recipient.get())
          .thenCompose(
            (Function<Argument, CompletableFuture<TransactionBlockResponse>>)
              argument->{
                    final CompletableFuture<TransactionData>
                                transactionDataCompletableFuture=
                                    transactionBlock
                                        .setGasData(
                                            Lists.newArrayList(),
                                            sponsor.get(),
                                            3000000L,
                                            null)
                                            .thenCompose(
                                            (Function<
                                                Void,
                                                CompletableFuture<TransactionData>>)
                                                  unused->transactionBlock.build());

                                                    TransactionBlockResponseOptions
                                                    transactionBlockResponseOptions=
                                                    new TransactionBlockResponseOptions();
                                                    transactionBlockResponseOptions.setShowEffects(true);
                                                    transactionBlockResponseOptions.setShowEvents(true);
                                                    transactionBlockResponseOptions.setShowInput(true);
                                                    transactionBlockResponseOptions.setShowObjectChanges(true);
                                                    return transactionDataCompletableFuture.thenCompose(
                                                          (Function<
                                                              TransactionData,
                                                              CompletableFuture<TransactionBlockResponse>>)
                                                                transactionData->{
                                                                    Intent intent=sui.transactionDataIntent();
                                                                    String sponsorSig=
                                                                    sui.signTransactionBlock(
                                                                    sponsor.get(),transactionData,intent);
                                                                    String senderSig=
                                                                    sui.signTransactionBlock(
                                                                    sender.get(),transactionData,intent);
                                                                    return sui.executeTransaction(
                                                                      transactionData,
                                                                      Lists.newArrayList(senderSig,sponsorSig),
                                                                      transactionBlockResponseOptions,
                                                                      ExecuteTransactionRequestType
                                                                      .WaitForLocalExecution);
                                                                });
                                                  });
                              });
```

For more examples, you can
see [SuiIntTests](https://github.com/GrapeBaBa/sui4j/blob/main/src/integrationTest/java/io/sui/SuiIntTests.java)
