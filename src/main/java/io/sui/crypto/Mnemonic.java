package io.sui.crypto;

import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mnemonic {

    public static List<String> generateMnemonic() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] entropy = new byte[16];
            secureRandom.nextBytes(entropy);
            return MnemonicCode.INSTANCE.toMnemonic(entropy);
        } catch (Exception e) {
            // never happened
        }
        return null;
    }

    public static byte[] toSeed(String mnemonic, String password) {
        return MnemonicCode.toSeed(Arrays.asList(mnemonic.split(" ")), password);
    }

    public static byte[] toSeed(List<String> mnemonic, String password) {
        return MnemonicCode.toSeed(mnemonic, password);
    }
}
