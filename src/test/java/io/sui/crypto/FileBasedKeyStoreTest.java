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

import java.nio.file.Files;
import java.nio.file.Path;
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

    assertEquals(7, fileBasedKeyStore.keys.size());
    String expected =
        "0x078ad0970cacfec86cd404e6382a86de441ec14c310e3f8c0e310733802f4992\n"
            + "0x0e842f5339d01f22f9b6412884cee3defdccb0d8c092755e2a043c842941b6ab\n"
            + "0x16aa1d0462fe9b4a97d2f9ad1a1f299b21c5002f7f1b7320d83ff3c8ee97d951\n"
            + "0x1b8321a438ab8eea0ab6eb94e1dd0b2da347b1697a066cbe5e5122f5d7debe6c\n"
            + "0x49e14eea497baef59ed20be791790ae152d63209bfc4b5235c608f60caba82bd\n"
            + "0x508bbbb2d623e7718a298e13cf78e8d667b2299e77d87407f6e32c84a4c3dc37\n"
            + "0xb43d0468fbc80c81931b73a4b9ef4663e671b65a07ae5b336a0e7d8a70ac0646\n";
    StringBuilder actual = new StringBuilder();
    for (String key : fileBasedKeyStore.keys.navigableKeySet()) {
      actual.append(key).append("\n");
    }
    assertEquals(expected, actual.toString());
  }

  // ED25519  0xf6959f5969790295e2fe37615fe78f1b2e9311ff93fe560ac08b0d2963efe7c5
  // "version final youth crush olive light leg perfect fashion able rally cotton pulse giraffe hurt
  // blush type devote provide leopard echo print mention leg"

  // Secp256k1  0x16aa1d0462fe9b4a97d2f9ad1a1f299b21c5002f7f1b7320d83ff3c8ee97d951
  // chest shuffle jar trophy nurse fish board member dove rival adjust awful wool camp rebuild fire
  // bunker verify message velvet save oblige net twice
  @Test
  void importFromMnemonicTest() throws Exception {

    Path filePath = Paths.get("src/test/resources/config/sui1.keystore");
    FileBasedKeyStore fileBasedKeyStore =
        new FileBasedKeyStore(filePath.toAbsolutePath().toString());
    // test data from sui client
    String[] mnemonics =
        new String[] {
          "version final youth crush olive light leg perfect fashion able rally cotton pulse "
              + "giraffe hurt blush type devote provide leopard echo print mention leg",
          "chest shuffle jar trophy nurse fish board member dove rival adjust awful wool camp "
              + "rebuild fire bunker verify message velvet save oblige net twice",
        };

    String[] addresses =
        new String[] {
          "0xf6959f5969790295e2fe37615fe78f1b2e9311ff93fe560ac08b0d2963efe7c5",
          "0x16aa1d0462fe9b4a97d2f9ad1a1f299b21c5002f7f1b7320d83ff3c8ee97d951",
        };

    String addr = fileBasedKeyStore.importFromMnemonic(mnemonics[0], SignatureScheme.ED25519);
    assertEquals(addresses[0], addr);

    addr = fileBasedKeyStore.importFromMnemonic(mnemonics[1], SignatureScheme.Secp256k1);
    assertEquals(addresses[1], addr);

    FileBasedKeyStore instance2 = new FileBasedKeyStore(filePath.toAbsolutePath().toString());

    assertTrue(instance2.addresses().toString().contains(addresses[0]));
    assertTrue(instance2.addresses().toString().contains(addresses[1]));

    Files.delete(filePath);
  }

  @Test
  void geneNewKey() throws Exception {
    Path filePath = Paths.get("src/test/resources/config/sui1.keystore");
    FileBasedKeyStore fileBasedKeyStore =
        new FileBasedKeyStore(filePath.toAbsolutePath().toString());
    KeyResponse res = fileBasedKeyStore.generateNewKey(SignatureScheme.ED25519);

    assertTrue(res.getMnemonic().length() > 0);
    assertTrue(res.getAddress().length() > 0);

    FileBasedKeyStore instance2 = new FileBasedKeyStore(filePath.toAbsolutePath().toString());

    assertTrue(instance2.addresses().contains(res.getAddress()));
    Files.delete(filePath);
  }
}
