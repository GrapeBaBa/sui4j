## New Address

### Generate Address

```java
KeyResponse res1 = sui.newAddress(SignatureScheme.ED25519);

KeyResponse res2 = sui.newAddress(SignatureScheme.Secp256k1);
```

### Request Faucet

```java
CompletableFuture<FaucetResponse> res = sui.requestSuiFromFaucet(s);
```

For more examples, you can see [SuiIntTests](https://github.com/GrapeBaBa/sui4j/blob/main/src/integrationTest/java/io/sui/SuiIntTests.java)
