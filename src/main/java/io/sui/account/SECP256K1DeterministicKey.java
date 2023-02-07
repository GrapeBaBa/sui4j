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

package io.sui.account;


import java.math.BigInteger;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.crypto.*;

public class SECP256K1DeterministicKey {

  private final String defaultDerivePath = "m/54H/784H/0H/0/0";
  private byte[] key;
  private byte[] chaincode;

  private DeterministicKey parent;

  private HDPath childPath;

  public static SECP256K1DeterministicKey createKeyByDefaultPath(byte[] seed) {
    return createMasterKey(seed).deriveFromPath("");
  }

  public static SECP256K1DeterministicKey createMasterKey(byte[] seed) {
    DeterministicKey master = HDKeyDerivation.createMasterPrivateKey(seed);
    return new SECP256K1DeterministicKey(
        master.getPrivKey().toByteArray(), master.getChainCode(), master.getPath());
  }

  public SECP256K1DeterministicKey(byte[] key, byte[] chaincode) {
    this.key = key;
    this.chaincode = chaincode;
  }

  public SECP256K1DeterministicKey(byte[] key, byte[] chaincode, HDPath path) {
    this.key = key;
    this.chaincode = chaincode;
    this.childPath = path;
  }

  public SECP256K1DeterministicKey(
      byte[] key, byte[] chaincode, HDPath path, DeterministicKey parent) {
    this.key = key;
    this.chaincode = chaincode;
    this.childPath = path;
    this.parent = parent;
  }

  public SECP256K1DeterministicKey derive(int index) throws Exception {
    boolean isHardened = hasHardenedBit(index);
    if (isHardened) {
      index = index & (~ChildNumber.HARDENED_BIT);
    }
    DeterministicKey parent =
        new DeterministicKey(
            this.childPath, this.chaincode, new BigInteger(1, this.key), this.parent);

    DeterministicKey childKey =
        HDKeyDerivation.deriveChildKey(parent, new ChildNumber(index, isHardened));

    return new SECP256K1DeterministicKey(
        childKey.getPrivKey().toByteArray(),
        childKey.getChainCode(),
        childKey.getPath(),
        childKey.getParent());
  }

  public SECP256K1DeterministicKey deriveFromPath(String path) throws Exception {
    if (StringUtils.isAnyBlank(path)) {
      path = defaultDerivePath;
    }
    HDPath hdPath = HDPath.parsePath(path);
    Iterator<ChildNumber> it = hdPath.iterator();
    SECP256K1DeterministicKey current = this;
    while (it.hasNext()) {
      current = current.derive(it.next().getI());
    }
    return current;
  }

  private boolean hasHardenedBit(int a) {
    return (a & ChildNumber.HARDENED_BIT) != 0;
  }

  public byte[] getKey() {
    return key;
  }

  public byte[] getChaincode() {
    return chaincode;
  }
}
