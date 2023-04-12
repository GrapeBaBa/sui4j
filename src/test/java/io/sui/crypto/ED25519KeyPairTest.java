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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.provider.digest.SHA3.Digest256;
import org.bouncycastle.util.encoders.Base64;
import org.junit.jupiter.api.Test;

/**
 * The type Ed25519 key pair test.
 *
 * @author grapebaba
 * @since 2022.11
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
class ED25519KeyPairTest {

  /** Address. */
  @Test
  void address() {
    final String base64 = "AGppxlDnjjspxa/5JSzPXx9s4QfqhDN7prZ9ZSpk/1qk";
    final ED25519KeyPair keyPair = ED25519KeyPair.decodeBase64(Base64.decode(base64));

    assertEquals(
        "0x0e842f5339d01f22f9b6412884cee3defdccb0d8c092755e2a043c842941b6ab", keyPair.address());
  }

  /** Decode base 64. @throws CryptoException the crypto exception */
  @Test
  void decodeBase64() throws CryptoException {
    final String base64 = "AGppxlDnjjspxa/5JSzPXx9s4QfqhDN7prZ9ZSpk/1qk";
    final ED25519KeyPair ed25519KeyPair = ED25519KeyPair.decodeBase64(Base64.decode(base64));

    Signer signer = new Ed25519Signer();
    signer.init(true, ed25519KeyPair.getKeyPair().getPrivate());
    final String msg = "test";
    final SHA3.Digest256 digest = new Digest256();
    final byte[] encodedHash = digest.digest(msg.getBytes(StandardCharsets.UTF_8));
    signer.update(encodedHash, 0, encodedHash.length);
    byte[] signature = signer.generateSignature();

    Signer verifier = new Ed25519Signer();
    verifier.init(false, ed25519KeyPair.getKeyPair().getPublic());
    verifier.update(encodedHash, 0, encodedHash.length);
    assertTrue(verifier.verifySignature(signature));
  }
}
