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

The latest 0.4.1 version tested with sui devnet-0.24.0 version.

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
cargo build --release
cd sui/target/release
./sui genesis
./sui start
```

#### To run Integration tests

```
./gradlew integrationTest
```

## Supported APIs (sui SDK 0.25.0)

- [x] sui_batchTransaction
- [ ] sui_devInspectTransaction
- [x] sui_dryRunTransaction
- [x] sui_executeTransaction
- [x] sui_executeTransactionSerializedSig(same with sui_executeTransaction)
- [x] sui_getAllBalances
- [x] sui_getAllCoins
- [x] sui_getBalance
- [x] sui_getCheckpointContents
- [x] sui_getCheckpointContentsByDigest
- [x] sui_getCheckpointSummary
- [x] sui_getCheckpointSummaryByDigest
- [x] sui_getCoinMetadata
- [x] sui_getCoins
- [x] sui_getCommitteeInfo
- [ ] sui_getDelegatedStakes
- [ ] sui_getDynamicFieldObject
- [ ] sui_getDynamicFields
- [x] sui_getEvents
- [ ] sui_getLatestCheckpointSequenceNumber
- [x] sui_getMoveFunctionArgTypes
- [x] sui_getNormalizedMoveFunction
- [x] sui_getNormalizedMoveModule
- [x] sui_getNormalizedMoveModulesByPackage
- [x] sui_getNormalizedMoveStruct
- [x] sui_getObject
- [x] sui_getObjectsOwnedByAddress
- [x] sui_getObjectsOwnedByObject
- [x] sui_getRawObject
- [x] sui_getReferenceGasPrice
- [x] sui_getSuiSystemState
- [ ] sui_getTotalSupply
- [x] sui_getTotalTransactionNumber
- [x] sui_getTransaction
- [ ] sui_getTransactionAuthSigners
- [x] sui_getTransactions
- [x] sui_getTransactionsInRange
- [ ] sui_getValidators
- [x] sui_mergeCoins
- [x] sui_moveCall
- [x] sui_pay
- [x] sui_payAllSui
- [x] sui_paySui
- [x] sui_publish
- [ ] sui_requestAddDelegation
- [ ] sui_requestSwitchDelegation
- [ ] sui_requestWithdrawDelegation
- [x] sui_splitCoin
- [x] sui_splitCoinEqual
- [x] sui_subscribeEvent
- [ ] sui_tblsSignRandomnessObject
- [x] sui_transferObject
- [x] sui_transferSui
- [x] sui_tryGetPastObject


## Examples

```java
	String BASE_URL="http://localhost:9000";
	// It must be a absolute path
	String TEST_KEY_STORE_PATH=
	System.getProperty("user.home")+"/.sui/sui_config/sui.keystore";
	Sui sui=new Sui(BASE_URL,TEST_KEY_STORE_PATH);

	// query objects
	CompletableFuture<List<SuiObjectInfo>>res=
	sui.getObjectsOwnedByAddress("0xea79464d86786b7a7a63e3f13f798f29f5e65947");
	List<SuiObjectInfo> objects=res.get();
	String coinObjectId=objects.get(0).getObjectId();
	List<String> addresses=new ArrayList<>(sui.addresses());

	// Transfer sui
	CompletableFuture<ExecuteTransactionResponse> res1=
	sui.transferSui(
	"0xea79464d86786b7a7a63e3f13f798f29f5e65947",
	coinObjectId,
	100L,
	addresses.get(0),
	2000L,
	ExecuteTransactionRequestType.WaitForLocalExecution);

	CompletableFuture<ExecuteTransactionResponse> res2=
	sui.moveCall(
	"0xea79464d86786b7a7a63e3f13f798f29f5e65947",
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

For more examples, you can see [SuiIntTests](src/integrationTest/java/io/sui/SuiIntTests.java)

## Contribution
To help sui4j grow, follow [Contributing to sui4j](CONTRIBUTING.md).
