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


import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

/**
 * The type Sui key pair.
 *
 * @param <T> the type parameter
 * @author grapebaba
 * @since 2022.11
 */
public abstract class SuiKeyPair<T> {

  static {
    if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
      Security.addProvider(new BouncyCastleProvider());
    }
  }

  /** The Key pair. */
  protected T keyPair;

  /**
   * Gets key pair.
   *
   * @return the key pair
   */
  public T getKeyPair() {
    return keyPair;
  }

  @Override
  public String toString() {
    return "SuiKeyPair{" + "keyPair=" + keyPair + '}';
  }

  /**
   * Address string.
   *
   * @return the string
   */
  public abstract String address();

  /**
   * Public key string.
   *
   * @return the string
   */
  public String publicKey() {
    return Base64.toBase64String(this.publicKeyBytes());
  }

  /**
   * Public key byte [ ].
   *
   * @return the byte [ ]
   */
  public abstract byte[] publicKeyBytes();

  /**
   * Signature scheme signature scheme.
   *
   * @return the signature scheme
   */
  public abstract SignatureScheme signatureScheme();

  /**
   * Sign string.
   *
   * @param msg the msg
   * @return the string
   * @throws SigningException the signing exception
   */
  public String sign(String msg) throws SigningException {
    byte[] msgBytes = Base64.decode(msg);
    return Base64.toBase64String(this.sign(msgBytes));
  }

  /**
   * Sign string.
   *
   * @param msg the msg
   * @return the string
   * @throws SigningException the signing exception
   */
  public abstract byte[] sign(byte[] msg) throws SigningException;

  /**
   * Decode base64 sui key pair.
   *
   * @param encoded the encoded
   * @return the sui key pair
   * @throws SignatureSchemeNotSupportedException the signature scheme not supported exception
   */
  public static SuiKeyPair<?> decodeBase64(String encoded)
      throws SignatureSchemeNotSupportedException {
    final byte[] keyPairBytes = Base64.decode(encoded);

    final SignatureScheme scheme = SignatureScheme.valueOf(keyPairBytes[0]);
    if (scheme == null) {
      throw new SignatureSchemeNotSupportedException();
    }
    switch (scheme) {
      case ED25519:
        return ED25519KeyPair.decodeBase64(keyPairBytes);
      case Secp256k1:
        return SECP256K1KeyPair.decodeBase64(keyPairBytes);
      default:
        throw new SignatureSchemeNotSupportedException();
    }
  }

  /**
   * encode base64 sui key.
   *
   * @return the sui key
   */
  public abstract String encodePrivateKey();
}
