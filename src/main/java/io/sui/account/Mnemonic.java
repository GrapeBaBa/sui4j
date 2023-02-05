package io.sui.account;

import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class Mnemonic {
    private final String suiCoinType = "784'";


    public static List<String> generateMnemonic() throws MnemonicException.MnemonicLengthException {
        SecureRandom secureRandom = new SecureRandom();
        byte[] entropy = new byte[16];
        secureRandom.nextBytes(entropy);
        return MnemonicCode.INSTANCE.toMnemonic(entropy);
    }

    public static byte[] toSeed(String mnemonic, String password) {
        return MnemonicCode.toSeed(Arrays.asList(mnemonic.split(" ")), password);
    }
}
