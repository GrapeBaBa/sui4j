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


import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import org.bitcoinj.crypto.MnemonicCode;

/**
 * @author fearlessfe
 * @since 2023.02
 */
public class Mnemonic {

  public static List<String> generateMnemonic() {
    try {
      SecureRandom secureRandom = new SecureRandom();
      byte[] entropy = new byte[16];
      secureRandom.nextBytes(entropy);
      return MnemonicCode.INSTANCE.toMnemonic(entropy);
    } catch (Exception e) {
      // never happened
    }
    return null;
  }

  public static byte[] toSeed(String mnemonic, String password) {
    return MnemonicCode.toSeed(Arrays.asList(mnemonic.split(" ")), password);
  }

  public static byte[] toSeed(List<String> mnemonic, String password) {
    return MnemonicCode.toSeed(mnemonic, password);
  }
}
