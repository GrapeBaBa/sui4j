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


import com.google.common.primitives.Bytes;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.HDPath;
import org.bitcoinj.crypto.HDUtils;

/**
 * Derive ed25519 key from path.
 *
 * @author fearlessfe
 * @since 2023.02
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class ED25519KeyDerive {

  private static final String DEFAULT_DERIVE_PATH = "m/44H/784H/0H/0H/0H";
  private final byte[] key;
  private final byte[] chaincode;

  /**
   * Create key by default path ed 25519 key derive.
   *
   * @param seed the seed
   * @return the ed 25519 key derive
   */
  public static ED25519KeyDerive createKeyByDefaultPath(byte[] seed) {
    return createMasterKey(seed).deriveFromPath("");
  }

  /**
   * Create master key ed 25519 key derive.
   *
   * @param seed the seed
   * @return the ed 25519 key derive
   */
  public static ED25519KeyDerive createMasterKey(byte[] seed) {
    byte[] i = HDUtils.hmacSha512("ed25519 seed".getBytes(Charset.defaultCharset()), seed);
    byte[] il = Arrays.copyOfRange(i, 0, 32);
    byte[] ir = Arrays.copyOfRange(i, 32, 64);
    return new ED25519KeyDerive(il, ir);
  }

  /**
   * Instantiates a new Ed 25519 key derive.
   *
   * @param key the key
   * @param chaincode the chaincode
   */
  public ED25519KeyDerive(byte[] key, byte[] chaincode) {
    this.key = key;
    this.chaincode = chaincode;
  }

  /**
   * Derive ed 25519 key derive.
   *
   * @param index the index
   * @return the ed 25519 key derive
   */
  public ED25519KeyDerive derive(int index) {
    if (!hasHardenedBit(index)) {
      // todo: create an exception
      throw new RuntimeException();
    }

    byte[] indexBytes = new byte[4];
    ByteBuffer.wrap(indexBytes).putInt(index);

    byte[] data = Bytes.concat(new byte[] {0x00}, this.key, indexBytes);

    byte[] i = HDUtils.hmacSha512(this.chaincode, data);
    byte[] il = Arrays.copyOfRange(i, 0, 32);
    byte[] ir = Arrays.copyOfRange(i, 32, 64);

    return new ED25519KeyDerive(il, ir);
  }

  /**
   * Derive from path ed 25519 key derive.
   *
   * @param path the path
   * @return the ed 25519 key derive
   */
  public ED25519KeyDerive deriveFromPath(String path) {
    if (StringUtils.isAnyBlank(path)) {
      path = DEFAULT_DERIVE_PATH;
    }
    HDPath hdPath = HDPath.parsePath(path);
    Iterator<ChildNumber> it = hdPath.iterator();
    ED25519KeyDerive current = this;
    while (it.hasNext()) {
      current = current.derive(it.next().getI());
    }
    return current;
  }

  private boolean hasHardenedBit(int a) {
    return (a & ChildNumber.HARDENED_BIT) != 0;
  }

  /**
   * Get key byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getKey() {
    return key;
  }

  /**
   * Get chaincode byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getChaincode() {
    return chaincode;
  }
}
