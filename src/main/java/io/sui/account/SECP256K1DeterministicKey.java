package io.sui.account;

import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.crypto.*;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;


public class SECP256K1DeterministicKey {

    private final String defaultDerivePath = "m/54H/784H/0H/0/0";
    private byte[] key;
    private byte[] chaincode;

    private DeterministicKey parent;

    private HDPath childPath;

    public static SECP256K1DeterministicKey createKeyByDefaultPath(byte[] seed) {
        return createMasterKey(seed).deriveFromPath("");
    }

    public static SECP256K1DeterministicKey createMasterKey(byte[] seed) {
        DeterministicKey master = HDKeyDerivation.createMasterPrivateKey(seed);
        return new SECP256K1DeterministicKey(master.getPrivKey().toByteArray(), master.getChainCode(), master.getPath());
    }

    public SECP256K1DeterministicKey(byte[] key, byte[] chaincode) {
        this.key = key;
        this.chaincode = chaincode;
    }

    public SECP256K1DeterministicKey(byte[] key, byte[] chaincode, HDPath path) {
        this.key = key;
        this.chaincode = chaincode;
        this.childPath = path;
    }

    public SECP256K1DeterministicKey(byte[] key, byte[] chaincode, HDPath path, DeterministicKey parent) {
        this.key = key;
        this.chaincode = chaincode;
        this.childPath = path;
        this.parent = parent;
    }

    public SECP256K1DeterministicKey derive(int index) throws Exception {
        boolean isHardened = hasHardenedBit(index);
        if (isHardened) {
            index = index & (~ChildNumber.HARDENED_BIT);
        }
        DeterministicKey parent = new DeterministicKey(this.childPath, this.chaincode,
                new BigInteger(1, this.key), this.parent);

        DeterministicKey childKey =  HDKeyDerivation
                .deriveChildKey(parent, new ChildNumber(index, isHardened));

        return new SECP256K1DeterministicKey(childKey.getPrivKey().toByteArray(), childKey.getChainCode(),
                childKey.getPath(), childKey.getParent());
    }

    public SECP256K1DeterministicKey deriveFromPath(String path) throws Exception {
        if (StringUtils.isAnyBlank(path)) {
            path = defaultDerivePath;
        }
        HDPath hdPath = HDPath.parsePath(path);
        Iterator<ChildNumber> it = hdPath.iterator();
        SECP256K1DeterministicKey current = this;
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
