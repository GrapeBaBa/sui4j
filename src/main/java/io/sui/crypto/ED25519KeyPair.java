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


import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.jcajce.provider.digest.SHA3.Digest256;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

/**
 * The type Secp256k1 key pair.
 *
 * @author grapebaba
 * @since 2022.11
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class ED25519KeyPair extends SuiKeyPair<AsymmetricCipherKeyPair> {

  /**
   * Instantiates a new Ed 25519 key pair.
   *
   * @param privateKeyParameters the private key parameters
   * @param publicKeyParameters the public key parameters
   */
  public ED25519KeyPair(
      Ed25519PrivateKeyParameters privateKeyParameters,
      Ed25519PublicKeyParameters publicKeyParameters) {
    this.keyPair = new AsymmetricCipherKeyPair(publicKeyParameters, privateKeyParameters);
  }

  @Override
  public String address() {
    final Digest256 digest256 = new Digest256();
    final byte[] hash =
        digest256.digest(
            Arrays.prepend(
                ((Ed25519PublicKeyParameters) keyPair.getPublic()).getEncoded(),
                SignatureScheme.ED25519.getScheme()));
    return "0x" + StringUtils.substring(Hex.toHexString(hash), 0, 40);
  }

  @Override
  public String publicKey() {
    return Base64.toBase64String(((Ed25519PublicKeyParameters) keyPair.getPublic()).getEncoded());
  }

  @Override
  public SignatureScheme signatureScheme() {
    return SignatureScheme.ED25519;
  }

  @Override
  public String sign(String msg) throws SigningException {
    Signer signer = new Ed25519Signer();
    signer.init(true, keyPair.getPrivate());
    byte[] msgBytes = Base64.decode(msg);
    signer.update(msgBytes, 0, msgBytes.length);
    try {
      byte[] signature = signer.generateSignature();
      return Base64.toBase64String(signature);
    } catch (CryptoException e) {
      throw new SigningException(e);
    }
  }

  /**
   * Decode base 64 sui key pair.
   *
   * @param encoded the encoded
   * @return the sui key pair
   */
  public static ED25519KeyPair decodeBase64(byte[] encoded) {
    Ed25519PrivateKeyParameters privateKeyParameters = new Ed25519PrivateKeyParameters(encoded, 1);
    Ed25519PublicKeyParameters publicKeyParameters = privateKeyParameters.generatePublicKey();
    return new ED25519KeyPair(privateKeyParameters, publicKeyParameters);
  }
}
