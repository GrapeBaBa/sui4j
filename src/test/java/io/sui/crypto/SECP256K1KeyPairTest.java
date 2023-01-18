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

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.ECKey.ECDSASignature;
import org.bitcoinj.core.Sha256Hash;
import org.bouncycastle.util.encoders.Base64;
import org.junit.jupiter.api.Test;

/**
 * The type Secp256k1 key pair test.
 *
 * @author grapebaba
 * @since 2022.11
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
class SECP256K1KeyPairTest {

  /** Address. */
  @Test
  void address() {
    final String base64 = "ABw1fhWdG+Ni9eFfyLdfdmsiMWyirHCV/UVC9jcloBm8";
    final SuiKeyPair<ECKey> secp256K1KeyPair = SECP256K1KeyPair.decodeBase64(Base64.decode(base64));

    assertEquals("0xea650e4199befc011d2373decabc4c3deb0275d8", secp256K1KeyPair.address());
  }

  /** Decode base 64. */
  @Test
  void decodeBase64() {
    final String base64 = "ABw1fhWdG+Ni9eFfyLdfdmsiMWyirHCV/UVC9jcloBm8";
    final SuiKeyPair<ECKey> secp256K1KeyPair = SECP256K1KeyPair.decodeBase64(Base64.decode(base64));
    final String msg = "test";
    final ECDSASignature signature = secp256K1KeyPair.keyPair.sign(Sha256Hash.of(msg.getBytes()));
    assertTrue(secp256K1KeyPair.keyPair.verify(Sha256Hash.of(msg.getBytes()), signature));
  }
}
