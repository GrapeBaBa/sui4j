package io.sui.account;

public class Node {
    private byte[] key;
    private byte[] chaincode;

    public Node(byte[] key, byte[] chaincode) {
        this.key = key;
        this.chaincode = chaincode;
    }

    public byte[] getChaincode() {
        return chaincode;
    }

    public byte[] getKey() {
        return key;
    }
}
