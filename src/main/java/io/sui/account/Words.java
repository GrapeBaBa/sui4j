package io.sui.account;

public enum Words {

    TWELVE(128),
    FIFTEEN(160),
    EIGHTEEN(192),
    TWENTY_ONE(224),
    TWENTY_FOUR(256);

    private final int bitLength;

    Words(int bitLength) {
        this.bitLength = bitLength;
    }

    public int bitLength() {
        return bitLength;
    }

    public int byteLength() {
        return bitLength / 8;
    }
}
