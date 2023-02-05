package io.sui.crypto;

import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;

import java.util.List;


// https://github.com/satoshilabs/slips/blob/master/slip-0010.md
// https://github.com/bitcoin/bips/blob/master/bip-0032.mediawiki
// https://github.com/anytypeio/go-slip10
// https://github.com/MystenLabs/sui
// https://github.com/satoshilabs/slips/blob/master/slip-0044.md


public class Account {

    private List<String> wordList;
    private String mnemonic;
    private byte[] seed;

    private SuiKeyPair<?> keyPair;

    private SignatureScheme accountType;

    public Account(SignatureScheme accountType) throws SignatureSchemeNotSupportedException {
        this.accountType = accountType;
        this.wordList = Mnemonic.generateMnemonic();
        this.mnemonic = String.join(" ", wordList);
        this.seed = Mnemonic.toSeed(wordList, "");
        this.keyPair = this.genKeyPair();
    }

    private SuiKeyPair<?> genKeyPair() throws SignatureSchemeNotSupportedException {
        switch (this.accountType) {
            case ED25519:
                return genED25519KeyPair();
            case Secp256k1:
                return genSECP256K1KeyPair();
            default:
                throw new SignatureSchemeNotSupportedException();
        }
    }

    private ED25519KeyPair genED25519KeyPair() {
       ED25519DeterministicKey key = ED25519DeterministicKey.createKeyByDefaultPath(this.seed);
       Ed25519PrivateKeyParameters parameters = new Ed25519PrivateKeyParameters(key.getKey());
       Ed25519PublicKeyParameters publicKeyParameters = parameters.generatePublicKey();

       ED25519KeyPair keyPair = new ED25519KeyPair(parameters, publicKeyParameters);
       this.keyPair = keyPair;
       return keyPair;
    }

    private SECP256K1KeyPair genSECP256K1KeyPair() {
        SECP256K1DeterministicKey key = SECP256K1DeterministicKey.createKeyByDefaultPath(this.seed);

        SECP256K1KeyPair keyPair = new SECP256K1KeyPair(key.getKey());
        this.keyPair = keyPair;
        return keyPair;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public String address() {
        return this.keyPair.address();
    }

    public SuiKeyPair<?> getKeyPair() {
        return keyPair;
    }
}