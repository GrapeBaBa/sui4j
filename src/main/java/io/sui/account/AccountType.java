package io.sui.account;

public enum AccountType {


    ED25519((byte) 0x00),

    Secp256k1((byte) 0x01);

    private byte accountType;

    AccountType(byte accountType) {
        this.accountType = accountType;
    }

    public byte getScheme() {
        return accountType;
    }
}
