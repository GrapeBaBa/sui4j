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

import com.novi.serde.DeserializationError;
import com.novi.serde.SerializationError;
import io.sui.bcsgen.TransactionData;
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

  @Test
  void dsda() throws DeserializationError, SerializationError {
    String a = "AAP6QjtkSOXoPQPg2YzgC1vjLaXuhgHoAwAAAAAAAAp0ITY6H2qCgA98k0CsArWQV5jLAykExC6ho+WShcCYXFiNia8DXrkBAAAAAAAAACB5nE7FsyYq1ZZ1RW5rmNBWY8tRMVaFLgaKyubriPiN2gEAAAAAAAAAZAAAAAAAAAA=";
    String b = "AAP6QjtkSOXoPQPg2YzgC1vjLaXuhgHoAwAAAAAAAAp0ITY6H2qCgA98k0CsArWQV5jLAykExC6ho+WShcCYXFiNia8DXrkBAAAAAAAAACB5nE7FsyYq1ZZ1RW5rmNBWY8tRMVaFLgaKyubriPiN2gEAAAAAAAAAZAAAAAAAAA==";
    TransactionData transactionData = TransactionData.bcsDeserialize(Base64.decode(a));
    System.out.println(Base64.toBase64String(transactionData.bcsSerialize()));
    System.out.println(a.equals(Base64.toBase64String(transactionData.bcsSerialize())));
  }

  /**
   * Decode base 64.
   */
  @Test
  void decodeBase64() {
    final String base64 = "ADfbVnAT2QLG7W+bM+1ENzEKAxnoUx10+WfGg5zx8VRm";
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
