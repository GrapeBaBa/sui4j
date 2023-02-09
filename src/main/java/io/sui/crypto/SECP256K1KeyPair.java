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

import static org.bouncycastle.util.Arrays.prepend;

import com.google.common.primitives.Bytes;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.ECKey.ECDSASignature;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Utils;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

/**
 * The type Secp256k1 key pair.
 *
 * @author grapebaba
 * @since 2022.11
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class SECP256K1KeyPair extends SuiKeyPair<ECKey> {

  /**
   * Instantiates a new Secp 256 k 1 key pair.
   *
   * @param privateKey the secret key
   */
  public SECP256K1KeyPair(byte[] privateKey) {
    this.keyPair = ECKey.fromPrivate(privateKey);
  }

  /**
   * Decode base 64 sui key pair.
   *
   * @param encoded the encoded
   * @return the sui key pair
   */
  public static SECP256K1KeyPair decodeBase64(byte[] encoded) {
    return new SECP256K1KeyPair(Arrays.copyOfRange(encoded, 1, encoded.length));
  }

  @Override
  public String address() {
    SHA3.Digest256 digest256 = new SHA3.Digest256();
    byte[] hash =
        digest256.digest(prepend(keyPair.getPubKey(), SignatureScheme.Secp256k1.getScheme()));
    return "0x" + StringUtils.substring(Hex.toHexString(hash), 0, 40);
  }

  @Override
  public byte[] publicKeyBytes() {
    return keyPair.getPubKey();
  }

  @Override
  public SignatureScheme signatureScheme() {
    return SignatureScheme.Secp256k1;
  }

  @Override
  public byte[] sign(byte[] msg) throws SigningException {
    Sha256Hash sha256Hash = Sha256Hash.of(msg);
    ECDSASignature signature = keyPair.sign(sha256Hash);
    byte recId = findRecoveryId(sha256Hash, signature);

    byte[] sigData = new byte[65]; // 32 bytes for R + 32 bytes for S + 1 recID
    System.arraycopy(Utils.bigIntegerToBytes(signature.r, 32), 0, sigData, 0, 32);
    System.arraycopy(Utils.bigIntegerToBytes(signature.s, 32), 0, sigData, 32, 32);
    sigData[64] = recId;
    return sigData;
  }

  private byte findRecoveryId(Sha256Hash hash, ECDSASignature sig) throws SigningException {
    byte recId = -1;
    for (byte i = 0; i < 2; i++) {
      ECKey k = ECKey.recoverFromSignature(i, sig, hash, keyPair.isCompressed());
      if (k != null && Arrays.equals(k.getPubKey(), keyPair.getPubKey())) {
        recId = i;
        break;
      }
    }
    if (recId == -1) {
      throw new SigningException(
          "Could not construct a recoverable key. This should never happen.");
    }
    return recId;
  }

  /**
   * Encode base 64 sui key.
   *
   * @return the sui key
   */
  public String encodePrivateKey() {
    byte[] data =
        Bytes.concat(
            new byte[] {SignatureScheme.Secp256k1.getScheme()}, this.keyPair.getPrivKeyBytes());

    return Base64.toBase64String(data);
  };
}
