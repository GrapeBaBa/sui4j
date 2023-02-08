package io.sui.crypto;

/**
 * @author fearlessfe
 * @since 2023 02
 */
public class KeyResponse {
    private String mnemonic;
    private String address;


    public KeyResponse(String mnemonic, String address) {
        this.mnemonic = mnemonic;
        this.address = address;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public String getAddress() {
        return address;
    }
}
