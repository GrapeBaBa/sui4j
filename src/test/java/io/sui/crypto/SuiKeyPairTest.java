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

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.junit.jupiter.api.Test;

/**
 * The type Sui key pair test.
 *
 * @author grapebaba
 * @since 2022.11
 */
class SuiKeyPairTest {

  /** Decode base 64. */
  @Test
  void decodeBase64() {
    final String base64 = "AUsCQlGEBz1TLmEfRsxUzxcyI3yywp+1/k4UZ5wV05CE";
    try {
      SuiKeyPair.decodeBase64(base64);
    } catch (SignatureSchemeNotSupportedException e) {
      e.printStackTrace();
    }

    final String wrongBase64 = Base64.toBase64String("test".getBytes(StandardCharsets.UTF_8));
    assertThrows(
        SignatureSchemeNotSupportedException.class, () -> SuiKeyPair.decodeBase64(wrongBase64));

    final String blsBase64 =
        Base64.toBase64String(Arrays.prepend("test".getBytes(StandardCharsets.UTF_8), (byte) 0xff));
    assertThrows(
        SignatureSchemeNotSupportedException.class, () -> SuiKeyPair.decodeBase64(blsBase64));
  }
}
