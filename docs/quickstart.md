## Add Sui4j

### Maven

```xml
<!-- https://mvnrepository.com/artifact/me.grapebaba/sui4j -->
<dependency>
<groupId>me.grapebaba</groupId>
<artifactId>sui4j</artifactId>
<version>1.0.0-alpha</version>
</dependency>
```

### Gradle

```groovy
// https://mvnrepository.com/artifact/me.grapebaba/sui4j
implementation 'me.grapebaba:sui4j:1.0.0-alpha'
```

## Use Sui4j

### Transfer coin

```java
Sui sui = new Sui("<full_node_url>","<faucet_url>","<your_keystore_path>");

TransactionBlockResponseOptions transactionBlockResponseOptions =
new TransactionBlockResponseOptions();
transactionBlockResponseOptions.setShowEffects(true);
transactionBlockResponseOptions.setShowEvents(true);
transactionBlockResponseOptions.setShowInput(true);
transactionBlockResponseOptions.setShowObjectChanges(true);

CompletableFuture<TransactionBlockResponse> res =
    sui.transferSui(
    "0x490174dbcac203f199d9dc50780f95c3a772a3c7357c5d98924885cd818f6980",
    null,
    "0x4543a8d956875317817c0c794c41c4b2e9a223faf2c1be0aa2c35e48acd8be42",
    20000L,
    null,
    3000000L,
    null,
    null,
    transactionBlockResponseOptions,
    ExecuteTransactionRequestType.WaitForLocalExecution); 
```
    