/*
 * Copyright 2022 281165273grape@gmail.com
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

import static org.bouncycastle.util.Arrays.prepend;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;

/**
 * The type Secp256k1 key pair.
 *
 * @author grapebaba
 * @since 2022.11
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class SECP256K1KeyPair extends SuiKeyPair<ECKeyPair> {

  /**
   * Instantiates a new Secp 256 k 1 key pair.
   *
   * @param privateKey the secret key
   */
  public SECP256K1KeyPair(byte[] privateKey) {
    this.keyPair = ECKeyPair.create(privateKey);
  }

  /**
   * Decode base 64 sui key pair.
   *
   * @param encoded the encoded
   * @return the sui key pair
   */
  public static SECP256K1KeyPair decodeBase64(byte[] encoded) {
    final int compressedPublicKeySize = 33;
    return new SECP256K1KeyPair(
        Arrays.copyOfRange(encoded, 1 + compressedPublicKeySize, encoded.length));
  }

  @Override
  public String address() {
    SHA3.Digest256 digest256 = new SHA3.Digest256();
    byte[] hash =
        digest256.digest(
            prepend(
                Sign.publicPointFromPrivate(keyPair.getPrivateKey()).getEncoded(true),
                SignatureScheme.Secp256k1.getScheme()));
    return "0x" + StringUtils.substring(Hex.toHexString(hash), 0, 40);
  }
}
