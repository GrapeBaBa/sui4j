package io.sui.account;

import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.HDPath;
import org.bitcoinj.crypto.HDUtils;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;

public class ED25519DeterministicKey {

    private final String defaultDerivePath = "m/44H/784H/0H/0H/0H";
    private byte[] key;
    private byte[] chaincode;

    public static ED25519DeterministicKey createKeyByDefaultPath(byte[] seed) {
        return createMasterKey(seed).deriveFromPath("");
    }

    public static ED25519DeterministicKey createMasterKey(byte[] seed) {
        byte[] i = HDUtils.hmacSha512("ed25519 seed".getBytes(), seed);
        byte[] il = Arrays.copyOfRange(i, 0, 32);
        byte[] ir = Arrays.copyOfRange(i, 32, 64);
        return new ED25519DeterministicKey(il, ir);
    }

    public ED25519DeterministicKey(byte[] key, byte[] chaincode) {
        this.key = key;
        this.chaincode = chaincode;
    }

    public ED25519DeterministicKey derive(int index) throws Exception {
        if (!hasHardenedBit(index)) {

        }

        byte[] indexBytes = new byte[4];
        ByteBuffer.wrap(indexBytes).putInt(index);

        byte[] a = new byte[]{0x00};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(a);
        outputStream.write(this.key);
        outputStream.write(indexBytes);

        byte[] data = outputStream.toByteArray();

        outputStream.write(a);
        byte[] i = HDUtils.hmacSha512(this.chaincode, data);
        byte[] il = Arrays.copyOfRange(i, 0, 32);
        byte[] ir = Arrays.copyOfRange(i, 32, 64);

        return new ED25519DeterministicKey(il, ir);
    }

    public ED25519DeterministicKey deriveFromPath(String path) {
        if (StringUtils.isAnyBlank(path)) {
            path = defaultDerivePath;
        }
        HDPath hdPath = HDPath.parsePath(path);
        Iterator<ChildNumber> it = hdPath.iterator();
        ED25519DeterministicKey current = this;
        while (it.hasNext()) {
            current = current.derive(it.next().getI());
        }
        return current;
    }

    private boolean hasHardenedBit(int a) {
        return (a & ChildNumber.HARDENED_BIT) != 0;
    }

    public byte[] getKey() {
        return key;
    }

    public byte[] getChaincode() {
        return chaincode;
    }

}
