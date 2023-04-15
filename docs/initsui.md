## Init sui client

```java
  Sui sui = new Sui("<full_node_url>", "<faucet_node_url>", "<your_keystore_path>")
```

To init sui client, you must provide the sui full node endpoint, faucet endpoint and keystore path.

> **Note:** You only need **one** sui client instance in your application.  