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


import java.util.HashMap;
import java.util.Map;

/**
 * The enum Signature scheme.
 *
 * @author grapebaba
 * @since 2022.11
 */
public enum SignatureScheme {
  /** Ed 25519 signature scheme. */
  ED25519((byte) 0x00),
  /** Secp 256 k 1 signature scheme. */
  Secp256k1((byte) 0x01),
  /** Bls 12381 signature scheme. */
  BLS12381((byte) 0xff);

  private static final Map<Byte, SignatureScheme> BY_SCHEME = new HashMap<>();

  static {
    for (SignatureScheme e : values()) {
      BY_SCHEME.put(e.scheme, e);
    }
  }

  private final byte scheme;

  SignatureScheme(byte scheme) {
    this.scheme = scheme;
  }

  /**
   * Gets scheme.
   *
   * @return the scheme
   */
  public byte getScheme() {
    return scheme;
  }

  /**
   * Value of signature scheme.
   *
   * @param scheme the scheme
   * @return the signature scheme
   */
  public static SignatureScheme valueOf(byte scheme) {
    return BY_SCHEME.get(scheme);
  }
}
