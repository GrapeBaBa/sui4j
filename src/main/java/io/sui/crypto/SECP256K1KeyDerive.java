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


import java.math.BigInteger;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.crypto.HDPath;

/**
 * The type Secp 256 k 1 key derive.
 *
 * @author fearlessfe
 * @since 2023.02
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class SECP256K1KeyDerive {

  private static final String DEFAULT_DERIVE_PATH = "m/54H/784H/0H/0/0";
  private final byte[] key;
  private final byte[] chaincode;

  private DeterministicKey parent;

  private HDPath childPath;

  /**
   * Create key by default path secp 256 k 1 key derive.
   *
   * @param seed the seed
   * @return the secp 256 k 1 key derive
   */
  public static SECP256K1KeyDerive createKeyByDefaultPath(byte[] seed) {
    return createMasterKey(seed).deriveFromPath("");
  }

  /**
   * Create master key secp 256 k 1 key derive.
   *
   * @param seed the seed
   * @return the secp 256 k 1 key derive
   */
  public static SECP256K1KeyDerive createMasterKey(byte[] seed) {
    DeterministicKey master = HDKeyDerivation.createMasterPrivateKey(seed);
    return new SECP256K1KeyDerive(
        master.getPrivKey().toByteArray(), master.getChainCode(), master.getPath());
  }

  /**
   * Instantiates a new Secp 256 k 1 key derive.
   *
   * @param key the key
   * @param chaincode the chaincode
   */
  public SECP256K1KeyDerive(byte[] key, byte[] chaincode) {
    this.key = key;
    this.chaincode = chaincode;
  }

  /**
   * Instantiates a new Secp 256 k 1 key derive.
   *
   * @param key the key
   * @param chaincode the chaincode
   * @param path the path
   */
  public SECP256K1KeyDerive(byte[] key, byte[] chaincode, HDPath path) {
    this.key = key;
    this.chaincode = chaincode;
    this.childPath = path;
  }

  /**
   * Instantiates a new Secp 256 k 1 key derive.
   *
   * @param key the key
   * @param chaincode the chaincode
   * @param path the path
   * @param parent the parent
   */
  public SECP256K1KeyDerive(byte[] key, byte[] chaincode, HDPath path, DeterministicKey parent) {
    this.key = key;
    this.chaincode = chaincode;
    this.childPath = path;
    this.parent = parent;
  }

  /**
   * Derive secp 256 k 1 key derive.
   *
   * @param index the index
   * @return the secp 256 k 1 key derive
   */
  public SECP256K1KeyDerive derive(int index) {
    boolean isHardened = hasHardenedBit(index);
    if (isHardened) {
      index = index & (~ChildNumber.HARDENED_BIT);
    }
    DeterministicKey parent =
        new DeterministicKey(
            this.childPath, this.chaincode, new BigInteger(1, this.key), this.parent);

    DeterministicKey childKey =
        HDKeyDerivation.deriveChildKey(parent, new ChildNumber(index, isHardened));

    return new SECP256K1KeyDerive(
        childKey.getPrivKey().toByteArray(),
        childKey.getChainCode(),
        childKey.getPath(),
        childKey.getParent());
  }

  /**
   * Derive from path secp 256 k 1 key derive.
   *
   * @param path the path
   * @return the secp 256 k 1 key derive
   */
  public SECP256K1KeyDerive deriveFromPath(String path) {
    if (StringUtils.isAnyBlank(path)) {
      path = DEFAULT_DERIVE_PATH;
    }
    HDPath hdPath = HDPath.parsePath(path);
    Iterator<ChildNumber> it = hdPath.iterator();
    SECP256K1KeyDerive current = this;
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
