package io.sui.account;

// 助记词生成 512 位的 seed
// seed 会被 BIP32 用来生成确定性钱包

import io.sui.crypto.SECP256K1KeyPair;
import io.sui.crypto.SuiKeyPair;
import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.crypto.*;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;


// https://github.com/satoshilabs/slips/blob/master/slip-0010.md
// https://github.com/bitcoin/bips/blob/master/bip-0032.mediawiki
// https://github.com/anytypeio/go-slip10
// https://github.com/MystenLabs/sui
public class Account {

    private final String suiChainPath = "m/44'/784'/0'/0'/0'";

    private List<String> wordList;
    private byte[] seed;
    private SECP256K1KeyPair keyPair;


    public Account() {
        try {
            this.wordList = generateMnemonic();
            this.seed = MnemonicCode.toSeed(this.wordList, "");
            DeterministicKey deterministicKey = HDKeyDerivation.createMasterPrivateKey(seed);
            HDPath path = HDPath.parsePath("m/44'/784'/0'/0'/0'");
            DeterministicKey suiChainKey = new DeterministicKey(path, deterministicKey.getChainCode(), deterministicKey.getPrivKey(), deterministicKey);
            SECP256K1KeyPair secKeyPair = new SECP256K1KeyPair(suiChainKey.getPrivKey().toByteArray());
            this.keyPair = secKeyPair;
        } catch (Exception e) {

        }

    }

    public Account(Words word, String pwd) {

    }

    public static List<String> generateMnemonic() throws MnemonicException.MnemonicLengthException {
        SecureRandom secureRandom = new SecureRandom();
        byte[] entropy = new byte[256];
        secureRandom.nextBytes(entropy);
        return MnemonicCode.INSTANCE.toMnemonic(entropy);
    }

    public static byte[] toSeed(String mnemonic, String password) {
        return MnemonicCode.toSeed(Arrays.asList(mnemonic.split(" ")), password);
    }

//    public Account() throws Exception {
//        HDPath path = HDPath.parsePath("m/44'/784'/0'/0'/0'");
//
//        List<String> wordList = generateMnemonic();
//        byte[] seed = toSeed(StringUtils.join(wordList, " "), "password");
//
//        DeterministicKey deterministicKey = HDKeyDerivation.createMasterPrivateKey(seed);
//        deterministicKey.getPrivKey();
//        deterministicKey.getPubKey();
//        deterministicKey.getChainCode();
//
//        DeterministicKey suiChainKey = new DeterministicKey(path, deterministicKey.getChainCode(), deterministicKey.getPrivKey(), deterministicKey);
//
//        suiChainKey.
//    }
}
