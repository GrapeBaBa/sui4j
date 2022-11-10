[![sui4j CI](https://github.com/GrapeBaBa/sui4j/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/GrapeBaBa/sui4j/actions/workflows/gradle.yml)
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

## Working with DevNet

Todo

## Working with local network

Refer to the [JSON RPC](https://github.com/MystenLabs/sui/blob/main/doc/src/build/json-rpc.md) topic
for instructions about how to start a local network and local RPC server.

## Javadoc

For the latest javadocs for the `main` branch, run `./gradlew docs` and open
the [build/docs/javadoc/index.html](build/docs/javadoc/index.html) in your browser.

## Testing

To run unit tests

```
./gradlew test
```

To run E2E tests against local network

```
./gradlew integrationTest
```

To run E2E tests against DevNet

TODO

## Connecting to Sui Network

TODO

## Examples

TODO
