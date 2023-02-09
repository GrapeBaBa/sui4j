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

import com.google.common.io.BaseEncoding;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * The type Secp256k1 key derive test.
 *
 * @author f
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class SECP256K1KeyDeriveTest {

  /** The constant HARDENED_BIT. */
  public static final int HARDENED_BIT = 0x80000000;
  // test case from https://github.com/satoshilabs/slips/blob/master/slip-0010.md
  private final String seedString = "000102030405060708090a0b0c0d0e0f";

  private final int[] path = new int[] {0 | HARDENED_BIT, 1, 2 | HARDENED_BIT, 2, 1000000000};
  private final String[] chains =
      new String[] {
        "47fdacbd0f1097043b78c63c20c34ef4ed9a111d980047ad16282c7ae6236141",
        "2a7857631386ba23dacac34180dd1983734e444fdbf774041578e9b6adb37c19",
        "04466b9cc8e161e966409ca52986c584f07e9dc81f735db683c3ff6ec7b1503f",
        "cfb71883f01676f587d023cc53a35bc7f88f724b1f8c2892ac1275ac822a3edd",
        "c783e67b921d2beb8f6b389cc646d7263b4145701dadd2161548a8b078e65e9e"
      };

  private final String[] privs =
      new String[] {
        "edb2e14f9ee77d26dd93b4ecede8d16ed408ce149b6cd80b0715a2d911a0afea",
        "3c6cb8d0f6a264c91ea8b5030fadaa8e538b020f0a387421a12de9319dc93368",
        "cbce0d719ecf7431d88e6a89fa1483e02e35092af60c042b1df2ff59fa424dca",
        "0f479245fb19a38a1954c5c7c0ebab2f9bdfd96a17563ef28a6a4b1a2a764ef4",
        "471b76e389e528d6de6d816857e012c5455051cad6660850e58372a6c3e6e7c8"
      };

  // private is 32 bytes, left padding with 00 to 33 bytes
  @SuppressWarnings("checkstyle:MemberName")
  private final String m_priv = "e8f32e723decf4051aefac8e2c93c9c5b214313817cdb01a1494b917c8436b35";

  @SuppressWarnings("checkstyle:MemberName")
  private final String m_chain = "873dff81c02f525623fd1fe5167eac3a55a049de3d314bb42ee227ffed37d508";

  /**
   * Test key derive.
   *
   * @throws Exception the exception
   */
  @Test
  void testKeyDerive() throws Exception {
    byte[] seed = BaseEncoding.base16().decode(seedString.toUpperCase());

    SECP256K1KeyDerive master = SECP256K1KeyDerive.createMasterKey(seed);
    assertEquals(m_priv.toUpperCase(), BaseEncoding.base16().encode(getPriv(master)));
    assertEquals(m_chain.toUpperCase(), BaseEncoding.base16().encode(master.getChaincode()));

    SECP256K1KeyDerive current = master;

    for (int i = 0; i < path.length; i++) {
      SECP256K1KeyDerive next = current.derive(path[i]);
      assertEquals(privs[i].toUpperCase(), BaseEncoding.base16().encode(getPriv(next)));
      assertEquals(chains[i].toUpperCase(), BaseEncoding.base16().encode(next.getChaincode()));
      current = next;
    }
  }

  /**
   * Test key derive path.
   *
   * @throws Exception the exception
   */
  @Test
  void testKeyDerivePath() throws Exception {
    byte[] seed = BaseEncoding.base16().decode(seedString.toUpperCase());

    SECP256K1KeyDerive master = SECP256K1KeyDerive.createMasterKey(seed);

    String path = "m/0H/1/2H/2/1000000000";
    SECP256K1KeyDerive last = master.deriveFromPath(path);

    assertEquals(
        privs[privs.length - 1].toUpperCase(), BaseEncoding.base16().encode(getPriv(last)));
    assertEquals(
        chains[chains.length - 1].toUpperCase(), BaseEncoding.base16().encode(last.getChaincode()));
  }

  private byte[] getPriv(SECP256K1KeyDerive key) {
    byte[] privKey = key.getKey();
    if (privKey.length == 33) {
      return Arrays.copyOfRange(privKey, 1, privKey.length);
    }
    return privKey;
  }
}
