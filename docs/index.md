Sui4j
=====

Sui4j is a robust, reactive, type safe Java library for working with Smart Contracts on the
[@MystenLabs/sui](https://docs.sui.io/) network.

This allows you to work with the [@MystenLabs/sui](https://docs.sui.io/) blockchain, without the
additional overhead of having to write your own integration code for the platform in JVM ecosystem.

The vision of sui4j is inspired by [Web3j](https://docs.web3j.io/), expect to accelerate JVM and Android ecosystem to integrate [@MystenLabs/sui](https://docs.sui.io/) network easily.

Features
========

- Complete implementation of @MystenLabs/sui's [JSON-RPC](https://docs.sui.io/sui-jsonrpc) client
  API over HTTP
- [BCS](https://github.com/diem/bcs) serialisation/deserialization support
- Native reactive and asynchronous style API
- Easy to use with utility class
- Comprehensive integration tests and unit tests to guarantee high quality
- Elegant code style and perfect document

Core Dependencies
============

- [RxJava](https://github.com/ReactiveX/RxJava) for its reactive-functional API
- [OKHttp](https://square.github.io/okhttp/) for HTTP connections
- [Gson](https://github.com/google/gson) for fast JSON
  serialisation/deserialization
- [Bouncy Castle](https://www.bouncycastle.org/) for crypto
- [Guava](https://github.com/google/guava) for utility 

