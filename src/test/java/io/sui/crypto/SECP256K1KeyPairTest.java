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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.provider.digest.SHA3.Digest256;
import org.bouncycastle.util.encoders.Base64;
import org.junit.jupiter.api.Test;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;

/**
 * The type Secp256k1 key pair test.
 *
 * @author grapebaba
 * @since 2022.11
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
class SECP256K1KeyPairTest {

  /**
   * Address.
   *
   * @throws NoSuchAlgorithmException the no such algorithm exception
   */
  @Test
  void address() throws NoSuchAlgorithmException {
    final String base64 =
        "AQLE7fDdDt4nrbGgCX8umsFscJRFY4t3Bkrk3MaB"
            + "b1nnA6dD5QHIFrPAdPQtdDyfoJNjiN/ghxuVLxfHxehcwec0";
    final SuiKeyPair<ECKeyPair> secp256K1KeyPair =
        SECP256K1KeyPair.decodeBase64(Base64.decode(base64));

    assertEquals("0xe8da3f038048e2cd6339e916a926874d0d0604b7", secp256K1KeyPair.address());
  }

  /**
   * Decode base 64.
   *
   * @throws SignatureException the signature exception
   */
  @Test
  void decodeBase64() throws SignatureException {
    final String base64 =
        "AQLE7fDdDt4nrbGgCX8umsFscJRFY4t3Bkrk3MaBb1nnA6dD5QH"
            + "IFrPAdPQtdDyfoJNjiN/ghxuVLxfHxehcwec0";
    final SuiKeyPair<ECKeyPair> secp256K1KeyPair =
        SECP256K1KeyPair.decodeBase64(Base64.decode(base64));
    final String msg = "test";
    final SHA3.Digest256 digest = new Digest256();
    final byte[] encodedHash = digest.digest(msg.getBytes(StandardCharsets.UTF_8));
    final SignatureData signatureData =
        Sign.signMessage(encodedHash, secp256K1KeyPair.keyPair, false);
    BigInteger pubKeyRecovered = Sign.signedMessageHashToKey(encodedHash, signatureData);
    assertEquals(secp256K1KeyPair.getKeyPair().getPublicKey(), pubKeyRecovered);
  }
}
