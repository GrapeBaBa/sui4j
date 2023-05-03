[![sui4j CI](https://github.com/GrapeBaBa/sui4j/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/GrapeBaBa/sui4j/actions/workflows/build.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

# Sui4j

Sui4j is a robust, reactive, type safe Java library for working with Smart Contracts on the
[@MystenLabs/sui](https://docs.sui.io/) network.

This allows you to work with the [@MystenLabs/sui](https://docs.sui.io/) blockchain, without the
additional overhead of having to write your own integration code for the platform in JVM ecosystem.


>**WARNING**: Note that we are still iterating on the RPC and SDK API before TestNet, therefore please
expect frequent breaking changes in the short-term. We expect the API to stabilize after the
upcoming TestNet launch.

## Using

The latest 1.0.0-alpha version tested with sui v1.0.0 version.(dynamic field api will be added soon)

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

## Building Locally

To get started you need to install JDK8+, then run the following command:

```bash
$ ./gradlew build
```

## Javadoc

For the latest javadocs for the `main` branch, run `./gradlew javadoc` and open
the document under the `build/docs/javadoc/index.html` in your browser.

## Testing

### To run unit tests

```
./gradlew test
```

### To run E2E tests against local network

You can start sui local network refer
to [sui-local-network](https://github.com/MystenLabs/sui/blob/main/doc/src/build/sui-local-network.md)
doc.

#### Start local network

```
git clone git@github.com:MystenLabs/sui.git
cd sui
RUST_LOG="consensus=off" cargo run --bin sui-test-validator
```

#### To run Integration tests

```
./gradlew integrationTest
```

## How to use it

- local-fullnode:http://127.0.0.1:9000
- local-faucet:http://localhost:9123
- devnet-fullnode:https://fullnode.devnet.sui.io
- devnet-faucet:https://faucet.devnet.sui.io

### Connecting to Sui Network
```java
Sui sui = new Sui("<full_node_url>","<faucet_url>","<your_keystore_path>");
```

### New Address
```java
KeyResponse keyRes = sui.newAddress(SignatureScheme.ED25519);
```

### Request Faucet
```java
CompletableFuture<FaucetResponse> faucetRes = sui.requestSuiFromFaucet(s);
```

### Writing APIs

#### Move Call
```java
CompletableFuture<TransactionBlockResponse> callRes =
	sui.moveCall(
		"0x0a7421363a1f6a82800f7c9340ac02b5905798cb",
		"0x02",
		"pay",
		"split",
		Lists.newArrayList(structType),
		Lists.newArrayList("0x4b89576d18d500194f14c935bc8b297a8e1556f3217e5f125ae3d1c0f13408f9", 10000L),
		null,
		3000000L,
		null,
		null,
		transactionBlockResponseOptions,
		ExecuteTransactionRequestType.WaitForLocalExecution);
```

#### Transfer Object
```java
CompletableFuture<TransactionBlockResponse> res =
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

#### Publish
```java
CompletableFuture<TransactionBlockResponse> res =
	sui.publish(
		sender.get(),
		Lists.newArrayList(
			"oRzrCwYAAAAKAQAUAhQsA0BJBIkBEgWbAWcHggLNAgjPBGAGrwXCAwrxCC0MngnUAQAMAR4B"
				+ "JAIRAh0CHwIlAiYCJwIoAAACAAABDAAAAwQAAQQHAQAAAgYHAAMCDAEIAQQIBAAFBQwABwcCAAkJ"
				+ "BwAAFgABAAEcARUBAAEjFBUBAAIpCwwAAwoNAQEIAxoJCgEIBBoSEwAFDgYHAQIGIREBAQwGJREB"
				+ "AQgHIg4PAAgXBAUBAgkbCxYACwMHAwUIBAgIEAgHAgwBDAkIAggABwgIAAILBQEIAQgHAQgAAQYJ"
				+ "AAEBAgkABwgIAQgHAQgBAgYIBwcICAELBQEJAAEKAgEIBAMHCwUBCQAKCAQKCAQBBggIAQUBCwUB"
				+ "CAECCQAFAQcICAEIBgEJAAELAwEJAAEICQVCT0FSUwRCb2FyB0Rpc3BsYXkITWV0YWRhdGEGT3B0"
				+ "aW9uCVB1Ymxpc2hlcgZTdHJpbmcJVHhDb250ZXh0A1VJRANVcmwMYWRkX211bHRpcGxlA2FnZQVi"
				+ "b2FycwVidXllcgVjbGFpbQdjcmVhdG9yC2Rlc2NyaXB0aW9uB2Rpc3BsYXkLZHVtbXlfZmllbGQI"
				+ "ZnVsbF91cmwCaWQHaW1nX3VybARpbml0E2lzX29uZV90aW1lX3dpdG5lc3MIbWV0YWRhdGEEbmFt"
				+ "ZQNuZXcVbmV3X3Vuc2FmZV9mcm9tX2J5dGVzBG5vbmUGb2JqZWN0Bm9wdGlvbgdwYWNrYWdlBXBy"
				+ "aWNlD3B1YmxpY190cmFuc2ZlcgZzZW5kZXIEc29tZQZzdHJpbmcIdHJhbnNmZXIKdHhfY29udGV4"
				+ "dAV0eXBlcwN1cmwEdXRmOAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
				+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAgMI"
				+ "AAAAAAAAAAAKAgUEbmFtZQoCDAtkZXNjcmlwdGlvbgoCCAdpbWdfdXJsCgIIB2NyZWF0b3IKAgYF"
				+ "cHJpY2UKAgwLcHJvamVjdF91cmwKAgQDYWdlCgIGBWJ1eWVyCgIJCGZ1bGxfdXJsCgIODWVzY2Fw"
				+ "ZV9zeW50YXgKAgcGe25hbWV9CgI7OlVuaXF1ZSBCb2FyIGZyb20gdGhlIEJvYXJzIGNvbGxlY3Rp"
				+ "b24gd2l0aCB7bmFtZX0gYW5kIHtpZH0KAiEgaHR0cHM6Ly9nZXQtYS1ib2FyLmNvbS97aW1nX3Vy"
				+ "bH0KAgoJe2NyZWF0b3J9CgIIB3twcmljZX0KAhgXaHR0cHM6Ly9nZXQtYS1ib2FyLmNvbS8KAg8O"
				+ "e21ldGFkYXRhLmFnZX0KAggHe2J1eWVyfQoCCwp7ZnVsbF91cmx9CgIJCFx7bmFtZVx9CgIKCWZp"
				+ "cnN0LnBuZwoCCwpGaXJzdCBCb2FyCgImJUZpcnN0IEJvYXIgZnJvbSB0aGUgQm9hcnMgY29sbGVj"
				+ "dGlvbiEKAgYFQ2hyaXMKAiAfaHR0cHM6Ly9nZXQtYS1ib2FyLmZ1bGx1cmwuY29tLwACARIBAQIJ"
				+ "FAgGFQgEGQgEEAgEDwsDAQgEIAsDAQgEGAgCDQUTCAkCAgELAwAAAAACXw4AOAAEBAUICwEBBwAn"
				+ "CwAKATgBDAMOAwoBOAIMAg0CBwERAwcCEQMHAxEDBwQRAwcFEQMHBhEDBwcRAwcIEQMHCREDBwoR"
				+ "A0AMCgAAAAAAAAAHCxEDBwwRAwcNEQMHDhEDBw8RAwcQEQMHEREDBxIRAwcTEQMHFBEDQAwKAAAA"
				+ "AAAAADgDCwIKAS4RCjgECwMKAS4RCjgFCgERBgcVEQMHFhEDBxcRAwcYEQM4BjgHBgoAAAAAAAAA"
				+ "EgIKAS4RCgcZEQwSAQsBLhEKOAgCAA=="),
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

### Reading APIs

#### Get Owned Objects
```java
CompletableFuture<PaginatedObjectsResponse> res =
	sui.getObjectsOwnedByAddress(sender.get(), null, null, null);
```

#### Get Coins
```java
CompletableFuture<PaginatedCoins> res = sui.getAllCoins(sender.get(), null, null);
```

#### Get Transaction Block
```java
CompletableFuture<PaginatedTransactionResponse> res =
	sui.queryTransactionBlocks(query, null, 10, false);
```

### Event APIs

#### Subscribe
```java
Disposable disposable =
	sui.subscribeEvent(eventFilter, System.out::println, System.out::println);

disposable.dispose();
```

For more examples, you can see [SuiIntTests](src/integrationTest/java/io/sui/SuiIntTests.java)

## Contribution
To help sui4j grow, follow [Contributing to sui4j](CONTRIBUTING.md).
