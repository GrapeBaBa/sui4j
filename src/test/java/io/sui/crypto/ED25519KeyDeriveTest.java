/*
 * Copyright 2023 281165273grape@gmail.com
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

import com.google.common.io.BaseEncoding;
import org.junit.jupiter.api.Test;

public class ED25519KeyDeriveTest {

  public static final int HARDENED_BIT = 0x80000000;

  private String seedString = "000102030405060708090a0b0c0d0e0f";

  private String m_priv = "2b4be7f19ee27bbf30c667b642d5f4aa69fd169872f8fc3059c08ebae2eb19e7";
  private String m_chain = "90046a93de5380a72b5e45010748567d5ea02bbf6522f979e05c0d8d8ca9fffb";

  private int[] path =
      new int[] {
        0 | HARDENED_BIT,
        1 | HARDENED_BIT,
        2 | HARDENED_BIT,
        2 | HARDENED_BIT,
        1000000000 | HARDENED_BIT
      };
  private String[] chains =
      new String[] {
        "8b59aa11380b624e81507a27fedda59fea6d0b779a778918a2fd3590e16e9c69",
        "a320425f77d1b5c2505a6b1b27382b37368ee640e3557c315416801243552f14",
        "2e69929e00b5ab250f49c3fb1c12f252de4fed2c1db88387094a0f8c4c9ccd6c",
        "8f6d87f93d750e0efccda017d662a1b31a266e4a6f5993b15f5c1f07f74dd5cc",
        "68789923a0cac2cd5a29172a475fe9e0fb14cd6adb5ad98a3fa70333e7afa230"
      };

  private String[] privs =
      new String[] {
        "68e0fe46dfb67e368c75379acec591dad19df3cde26e63b93a8e704f1dade7a3",
        "b1d0bad404bf35da785a64ca1ac54b2617211d2777696fbffaf208f746ae84f2",
        "92a5b23c0b8a99e37d07df3fb9966917f5d06e02ddbd909c7e184371463e9fc9",
        "30d1dc7e5fc04c31219ab25a27ae00b50f6fd66622f6e9c913253d6511d1e662",
        "8f94d394a8e8fd6b1bc2f3f49f5c47e385281d5c17e65324b0f62483e37e8793"
      };

  @Test
  void TestKeyDerive() throws Exception {
    byte[] seed = BaseEncoding.base16().decode(seedString.toUpperCase());

    ED25519KeyDerive master = ED25519KeyDerive.createMasterKey(seed);
    assertEquals(m_priv.toUpperCase(), BaseEncoding.base16().encode(master.getKey()));
    assertEquals(m_chain.toUpperCase(), BaseEncoding.base16().encode(master.getChaincode()));

    ED25519KeyDerive current = master;

    for (int i = 0; i < path.length; i++) {
      ED25519KeyDerive next = current.derive(path[i]);

      assertEquals(privs[i].toUpperCase(), BaseEncoding.base16().encode(next.getKey()));
      assertEquals(chains[i].toUpperCase(), BaseEncoding.base16().encode(next.getChaincode()));
      current = next;
    }
  }

  @Test
  void TestKeyDerivePath() throws Exception {
    byte[] seed = BaseEncoding.base16().decode(seedString.toUpperCase());

    ED25519KeyDerive master = ED25519KeyDerive.createMasterKey(seed);

    String path = "m/0H/1H/2H/2H/1000000000H";
    ED25519KeyDerive last = master.deriveFromPath(path);

    assertEquals(
        privs[privs.length - 1].toUpperCase(), BaseEncoding.base16().encode(last.getKey()));
    assertEquals(
        chains[chains.length - 1].toUpperCase(), BaseEncoding.base16().encode(last.getChaincode()));
  }
}
