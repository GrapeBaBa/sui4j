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

package io.sui.clients;


import io.sui.bcsgen.ObjectArg;
import io.sui.bcsgen.ObjectID;

/**
 * The type Mismatched object argument exception.
 *
 * @author grapebaba
 * @since 2023.04
 */
public class MismatchedObjectArgumentException extends RuntimeException {

  /**
   * Instantiates a new Mismatched object argument exception.
   *
   * @param id the id
   * @param old the old
   * @param objectArg the object arg
   */
  public MismatchedObjectArgumentException(ObjectID id, ObjectArg old, ObjectArg objectArg) {
    super(
        String.format(
            "Mismatched Object argument kind for object %s. %s is not compatible with %s",
            id, old, objectArg));
  }
}
