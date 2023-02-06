## Init sui client

```java
  Sui sui = new Sui("<your_node_url>", "<your_keystore_path>")
```
or
```java
  Sui sui = new Sui("<your_node_url>", "<your_keystore_path>", false)
```

To init sui client, you must provide the sui full node endpoint and keystore path.

The third parameter represents if enable local transaction builder. 
By default, it is enabled since performance, however you still can use remote transaction builder by pass `false`.

> **Note:** You only need **one** sui client instance in your application.  