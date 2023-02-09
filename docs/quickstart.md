## Add Sui4j

### Maven

```xml
<!-- https://mvnrepository.com/artifact/me.grapebaba/sui4j -->
<dependency>
<groupId>me.grapebaba</groupId>
<artifactId>sui4j</artifactId>
<version>0.4.1</version>
</dependency>
```

### Gradle

```groovy
// https://mvnrepository.com/artifact/me.grapebaba/sui4j
implementation 'me.grapebaba:sui4j:0.4.1'
```

## Use Sui4j

### Mint an example NFT

```java
  Sui sui = new Sui("<your_node_url>", "<your_keystore_path>", true);
  CompletableFuture<ExecuteTransactionResponse> res =
    sui.moveCall(
        "0x0a7421363a1f6a82800f7c9340ac02b5905798cb",
        "0x0000000000000000000000000000000000000002",
        "devnet_nft",
        "mint",
        Lists.newArrayList(),
        Lists.newArrayList(
        "Example NFT",
        "An example NFT.",
        "ipfs://bafkreibngqhl3gaa7daob4i2vccziay2jjlp435cf66vhono7nrvww53ty"),
        null,
        2000L,
        ExecuteTransactionRequestType.WaitForLocalExecution);
```

### Transfer coin

```java
 Sui sui = new Sui("<your_node_url>", "<your_keystore_path>", true);
 CompletableFuture<ExecuteTransactionResponse> res =
    sui.transferSui(
        "0x4a2a5a8e193b608a802cd733158ccb63c2092bdb",
        "0x42f92e4c7ceec704941b48dd8919afa991913db8",
        2000L,
        "0xa0fd2a6814cff90d4463fb7e5b5b81d01d763472",
        20000L,
        ExecuteTransactionRequestType.WaitForLocalExecution);
```
    