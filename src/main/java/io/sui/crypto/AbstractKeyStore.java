/*
 * Copyright 2022-2023 281165273grape@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.sui.crypto;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentSkipListMap;
import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.crypto.MnemonicCode;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;

/**
 * The type Abstract key store.
 *
 * @author grapebaba
 * @since 2022.11
 */
public abstract class AbstractKeyStore implements KeyStore {

  /** The Keys. */
  protected final ConcurrentSkipListMap<String, SuiKeyPair<?>> keys = new ConcurrentSkipListMap<>();

  @Override
  public SuiKeyPair<?> getByAddress(String address) {
    return this.keys.get(address);
  }

  @Override
  public NavigableSet<String> addresses() {
    return this.keys.navigableKeySet();
  }

  @Override
  public abstract void addKey(String address, SuiKeyPair<?> keyPair);

  public KeyResponse generateNewKey(SignatureScheme schema)
      throws SignatureSchemeNotSupportedException {

    SecureRandom secureRandom = new SecureRandom();
    byte[] entropy = new byte[16];
    secureRandom.nextBytes(entropy);
    List<String> mnemonic = new ArrayList<>();

    try {
      mnemonic = MnemonicCode.INSTANCE.toMnemonic(entropy);
    } catch (Exception e) {
      // MnemonicLengthException won't happen
    }

    byte[] seed = MnemonicCode.toSeed(mnemonic, "");
    SuiKeyPair keyPair = genSuiKeyPair(seed, schema);

    this.addKey(keyPair.address(), keyPair);
    return new KeyResponse(StringUtils.join(mnemonic, " "), keyPair.address());
  }

  public String importFromMnemonic(String mnemonic, SignatureScheme schema)
      throws SignatureSchemeNotSupportedException {
    // todo check mnemonic

    byte[] seed = MnemonicCode.toSeed(Arrays.asList(mnemonic.split(" ")), "");

    SuiKeyPair keyPair = genSuiKeyPair(seed, schema);

    this.addKey(keyPair.address(), keyPair);
    return keyPair.address();
  }

  private SuiKeyPair genSuiKeyPair(byte[] seed, SignatureScheme schema)
      throws SignatureSchemeNotSupportedException {
    switch (schema) {
      case ED25519:
        return genED25519KeyPair(seed);
      case Secp256k1:
        return genSECP256K1KeyPair(seed);
      default:
        throw new SignatureSchemeNotSupportedException();
    }
  }

  private ED25519KeyPair genED25519KeyPair(byte[] seed) {
    ED25519KeyDerive key = ED25519KeyDerive.createKeyByDefaultPath(seed);
    Ed25519PrivateKeyParameters parameters = new Ed25519PrivateKeyParameters(key.getKey());
    Ed25519PublicKeyParameters publicKeyParameters = parameters.generatePublicKey();

    ED25519KeyPair keyPair = new ED25519KeyPair(parameters, publicKeyParameters);
    return keyPair;
  }

  private SECP256K1KeyPair genSECP256K1KeyPair(byte[] seed) {
    SECP256K1KeyDerive key = SECP256K1KeyDerive.createKeyByDefaultPath(seed);

    SECP256K1KeyPair keyPair = new SECP256K1KeyPair(key.getKey());
    return keyPair;
  }
}
