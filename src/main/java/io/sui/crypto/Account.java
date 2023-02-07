/*
 * Copyright 2023 281165273grape@gmail.com
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


import java.util.List;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;

/**
 * @author fearlessfe
 * @since 2023.02
 */
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
