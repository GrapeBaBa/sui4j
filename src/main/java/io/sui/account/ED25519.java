package io.sui.account;

import org.bitcoinj.crypto.HDUtils;
import org.bouncycastle.util.encoders.Hex;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ED25519 {
    private final long FirstHardenedIndex = 0x80000000;
    public Node masterKey;

    public ED25519(byte[] seed) {
        byte[] i = HDUtils.hmacSha512("ed25519 seed".getBytes(), seed);
        byte[] il = Arrays.copyOfRange(i, 0, 32);
        byte[] ir = Arrays.copyOfRange(i, 32, 64);
        this.masterKey = new Node(il, ir);
    }

    public Node derive(Node node, int index) throws Exception {
        long hardenedIndex = FirstHardenedIndex + index;
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putLong(hardenedIndex);
        byte[] indexBytes = Arrays.copyOfRange(bytes, 4, 8);

        byte[] a = new byte[]{0x00};
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(a);
        outputStream.write(node.getKey());
        outputStream.write(indexBytes);
        byte[] data = outputStream.toByteArray();

        byte[] i = HDUtils.hmacSha512(node.getChaincode(), data);
        byte[] il = Arrays.copyOfRange(i, 0, 32);
        byte[] ir = Arrays.copyOfRange(i, 32, 64);
        return new Node(il, ir);
    }


}
