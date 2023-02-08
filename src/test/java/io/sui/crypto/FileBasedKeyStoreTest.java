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

  private String filePath = "src/test/resources/config/sui.keystore";

  /** Gets path. */
  @Test
  void getPath() {
    FileBasedKeyStore fileBasedKeyStore =
        new FileBasedKeyStore(
            Paths.get(filePath).toAbsolutePath().toString());
    System.out.println(fileBasedKeyStore.getPath());
    assertTrue(StringUtils.endsWith(fileBasedKeyStore.getPath(), "config/sui.keystore"));
  }

  /** Init key pairs. */
  @Test
  void initKeyPairs() {
    FileBasedKeyStore fileBasedKeyStore =
        new FileBasedKeyStore(
            Paths.get(filePath).toAbsolutePath().toString());

    assertEquals(7, fileBasedKeyStore.keys.size());
    String expected =
        "0x0a7421363a1f6a82800f7c9340ac02b5905798cb\n"
            + "0x64e1714eb06c22dff8ac7dab7eb302d4abededf9\n"
            + "0xa0fd2a6814cff90d4463fb7e5b5b81d01d763472\n"
            + "0xd456963facd4c40adfc3858d1a33c93d4d759664\n"
            + "0xee3752e38e25a52c5d35cff23695d1115d3a0149\n"
            + "0xf76f8c0e679c55a9d17229b9cbda702645836aa7\n"
            + "0xfa423b6448e5e83d03e0d98ce00b5be32da5ee86\n";
    StringBuilder actual = new StringBuilder();
    for (String key : fileBasedKeyStore.keys.navigableKeySet()) {
      actual.append(key).append("\n");
    }
    assertEquals(expected, actual.toString());
  }

  @Test
  void addKeyTest() {

  }

  // ED25519  0x4e0cc5c559ee61c36d61d0624c924cc43348b764
  // "feel acid liar execute insane midnight oval oyster slot uncle bitter person"
  //  m/44'/784'/0'/0'/0'

  // Secp256k1  0x6604964784bd9792e53dca3750d29ab39fb053e5
  // heart position turkey bus virtual host panther pioneer ready lesson fence what
  // m/54'/784'/0'/0/0
  @Test
  void importFromMnemonicTest() throws Exception{

    FileBasedKeyStore fileBasedKeyStore =
            new FileBasedKeyStore(
                    Paths.get(filePath).toAbsolutePath().toString());
    // test data from sui client
    String[] mnemonics = new String[]{
            "feel acid liar execute insane midnight oval oyster slot uncle bitter person",
            "heart position turkey bus virtual host panther pioneer ready lesson fence what",
    };

    String[] addresses = new String[] {
            "0x4e0cc5c559ee61c36d61d0624c924cc43348b764",
            "0x6604964784bd9792e53dca3750d29ab39fb053e5",
    };

    String addr = fileBasedKeyStore.importFromMnemonic(mnemonics[0], SignatureScheme.ED25519);
    assertEquals(addresses[0], addr);

    addr = fileBasedKeyStore.importFromMnemonic(mnemonics[1], SignatureScheme.Secp256k1);
    assertEquals(addresses[1], addr);

  }
}
