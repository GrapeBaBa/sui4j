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


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The type File based key store.
 *
 * @author grapebaba
 * @since 2022.11
 */
public class FileBasedKeyStore extends AbstractKeyStore {

  private final String path;

  /**
   * Instantiates a new File based key store.
   *
   * @param path the path
   */
  public FileBasedKeyStore(String path) {
    this.path = path;
    if (Files.exists(Paths.get(this.path))) {
      try {
        JsonArray json =
            new Gson().fromJson(Files.newBufferedReader(Paths.get(this.path)), JsonArray.class);
        json.asList()
            .forEach(
                jsonElement -> {
                  try {
                    final SuiKeyPair<?> keyPair =
                        SuiKeyPair.decodeBase64(jsonElement.getAsString());
                    FileBasedKeyStore.super.keys.putIfAbsent(keyPair.address(), keyPair);
                  } catch (SignatureSchemeNotSupportedException e) {
                    throw new FileBasedKeyStoreInitException(e);
                  }
                });
      } catch (IOException e) {
        throw new FileBasedKeyStoreInitException(e);
      }
    }
  }

  /**
   * Gets path.
   *
   * @return the path
   */
  public String getPath() {
    return path;
  }

  @Override
  public void addKey(String address, SuiKeyPair<?> keyPair) {
    FileBasedKeyStore.super.keys.put(address, keyPair);
    save();
  }

  private void save() {
    List<String> address = FileBasedKeyStore.super.keys.values().stream().map(item -> item.address()).collect(Collectors.toList());
    Type listType = new TypeToken<List<String>>() {}.getType();

    Gson gson = new Gson();
    String content = gson.toJson(address, listType);
    try {
      Files.write(Path.of(path), content.getBytes());
    } catch (IOException e) {
      throw new FileBasedKeyStoreSaveException(e);
    }
  }
}
