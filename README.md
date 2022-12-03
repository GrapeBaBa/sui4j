[![sui4j CI](https://github.com/GrapeBaBa/sui4j/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/GrapeBaBa/sui4j/actions/workflows/build.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

# Sui Java SDK

This is the Sui Java SDK built on the
Sui [JSON RPC API](https://github.com/MystenLabs/sui/blob/main/doc/src/build/json-rpc.md). It
provides utility classes and functions for applications to sign transactions and interact with the
Sui network.

WARNING: Note that we are still iterating on the RPC and SDK API before TestNet, therefore please
expect frequent breaking changes in the short-term. We expect the API to stabilize after the
upcoming TestNet launch.

## Building Locally

To get started you need to install JDK8+, then run the following command:

```bash
$ ./gradlew build
```

## Javadoc

For the latest javadocs for the `main` branch, run `./gradlew javadoc` and open
the [javadoc](build/docs/javadoc/index.html) in your browser.

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
cargo build --release
cd sui/target/release
./sui genesis
./sui start
```

#### To run Integration tests

```
./gradlew integrationTest
```

### To run E2E tests against DevNet

TODO

## Supported APIs
- [ ] sui_batchTransaction
- [x] sui_dryRunTransaction
- [ ] sui_executeTransaction
- [ ] sui_getCoinMetadata
- [x] sui_getCommitteeInfo
- [x] sui_getEvents
- [x] sui_getMoveFunctionArgTypes
- [x] sui_getNormalizedMoveFunction
- [x] sui_getNormalizedMoveModule
- [x] sui_getNormalizedMoveModulesByPackage
- [x] sui_getNormalizedMoveStruct
- [x] sui_getObject
- [x] sui_getObjectsOwnedByAddress
- [x] sui_getObjectsOwnedByObject
- [x] sui_getRawObject
- [x] sui_getTotalTransactionNumber
- [x] sui_getTransaction
- [ ] sui_getTransactions
- [x] sui_getTransactionsInRange
- [x] sui_mergeCoins
- [x] sui_moveCall
- [x] sui_pay
- [x] sui_payAllSui
- [x] sui_paySui
- [x] sui_publish
- [x] sui_splitCoin
- [x] sui_splitCoinEqual
- [ ] sui_subscribeEvent
- [x] sui_transferObject
- [x] sui_transferSui
- [ ] sui_tryGetPastObject

## Examples

### create client

```java
final String BASE_URL="http://localhost:9000";
final JsonHandler jsonHandler=new GsonJsonHandler();

final JsonRpcClientProvider jsonRpcClientProvider=
	new OkHttpJsonRpcClientProvider(BASE_URL,jsonHandler);
final SuiClient client=new SuiClientImpl(jsonRpcClientProvider);
```

### sui_getCommitteeInfo

```java
	CompletableFuture<CommitteeInfoResponse> res=client.getCommitteeInfo(1L);
```

### sui_getEvents

```java
	TransactionEventQuery query=new TransactionEventQuery();
	query.setTransaction("ov1tDrhdOqRW2uFweTbSSTaQbBbnjHWmrsh675lwb0Q=");
	CompletableFuture<PaginatedEvents> res=client.getEvents(query,null,1,false);
```

### sui_getMoveFunctionArgTypes

```java
	CompletableFuture<List<MoveFunctionArgType>>res=
	client.getMoveFunctionArgTypes("0x0000000000000000000000000000000000000002","bag","add");
```

### sui_getNormalizedMoveFunction

```java
	CompletableFuture<MoveNormalizedFunction> res=
	client.getNormalizedMoveFunction(
	"0x0000000000000000000000000000000000000002","bag","add");
```

### sui_getNormalizedMoveModule

```java
	CompletableFuture<MoveNormalizedModule> res=
	client.getNormalizedMoveModule("0x0000000000000000000000000000000000000002","bag");
```

### sui_getNormalizedMoveModulesByPackage

```java
	CompletableFuture<Map<String, MoveNormalizedModule>>res=
	client.getNormalizedMoveModulesByPackage("0x0000000000000000000000000000000000000002");
```

### sui_getNormalizedMoveStruct

```java
	CompletableFuture<MoveNormalizedStruct> res=
	client.getNormalizedMoveStruct("0x0000000000000000000000000000000000000002","bag","Bag");
```

### sui_getObject

```java
	CompletableFuture<GetObjectResponse> res=
	client.getObject("0x342950ba2451c2f27ed128e591c2b4551e5177c2");
```

### sui_getObjectsOwnedByAddress

```java
	CompletableFuture<List<SuiObjectInfo>>res=
	client.getObjectsOwnedByAddress("0xea79464d86786b7a7a63e3f13f798f29f5e65947");
```

### sui_getObjectsOwnedByObject

```java
	CompletableFuture<List<SuiObjectInfo>>res=
	client.getObjectsOwnedByObject("0xde2952390ab3d0cfbb0a0602532480ed5ec99cf3");
```

### sui_getRawObject

```java
	CompletableFuture<GetObjectResponse> res=
	client.getRawObject("0x342950ba2451c2f27ed128e591c2b4551e5177c2");
```

### sui_getTotalTransactionNumber

```java
	CompletableFuture<Long> res=client.getTotalTransactionNumber();
```

### sui_getTransaction

```java
	CompletableFuture<TransactionResponse> res=
	client.getTransaction("3Dda4/74LXf6GmOxMxp3qdbW/WdQ6/8EHobZ1LvSyYk=");
```

### sui_getTransactionsInRange

```java
	CompletableFuture<List<String>>res=client.getTransactionsInRange(0L,100L);
```
