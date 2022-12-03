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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * The type File based key store test.
 *
 * @author grapebaba
 * @since 2022.11
 */
class FileBasedKeyStoreTest {

  /** Gets path. */
  @Test
  void getPath() {
    FileBasedKeyStore fileBasedKeyStore =
        new FileBasedKeyStore(
            Paths.get("src/test/resources/config/sui.keystore").toAbsolutePath().toString());
    System.out.println(fileBasedKeyStore.getPath());
    assertTrue(StringUtils.endsWith(fileBasedKeyStore.getPath(), "config/sui.keystore"));
  }

  /** Init key pairs. */
  @Test
  void initKeyPairs() {
    FileBasedKeyStore fileBasedKeyStore =
        new FileBasedKeyStore(
            Paths.get("src/test/resources/config/sui.keystore").toAbsolutePath().toString());

    assertEquals(11, fileBasedKeyStore.keys.size());
    String expected =
        "0x1e7752f22228753e5745f5ac8ad4ef1bbc502845\n"
            + "0x207f2c9f08472b1ff68644fdfc7a70df10cb3d4e\n"
            + "0x49ef9b602b76a37e0f9177783755c1a190866e72\n"
            + "0x51972acc644b8c6dd81d6088780b40e842a0a10c\n"
            + "0x51de405091c9f971fc6085d384f9ba764f268fca\n"
            + "0x63485e00efc944d62349b79f88a11b7cacc2a764\n"
            + "0x78cec6011e9d0690d5fbbfa4d25987a087a88ee7\n"
            + "0x887ddfbf2bc37d757eabb08d62bf725a04922bde\n"
            + "0xca21af1b5b347d315d7355ff9e6e73cc79d0a4d0\n"
            + "0xe8da3f038048e2cd6339e916a926874d0d0604b7\n"
            + "0xea79464d86786b7a7a63e3f13f798f29f5e65947\n";
    StringBuilder actual = new StringBuilder();
    for (String key : fileBasedKeyStore.keys.navigableKeySet()) {
      actual.append(key).append("\n");
    }
    assertEquals(expected, actual.toString());
  }
}
