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


import com.novi.serde.SerializationError;
import com.novi.serde.Serializer;
import io.sui.bcsgen.CallArg;
import io.sui.bcsgen.ObjectArg;
import java.util.List;
import java.util.Objects;

/**
 * The type Call arg obj vec.
 *
 * @author grapebaba
 * @since 2023.04
 */
public class CallArgObjVec extends CallArg {

  private List<ObjectArg> objectArgs;

  @Override
  public void serialize(Serializer serializer) throws SerializationError {}

  /**
   * Gets object args.
   *
   * @return the object args
   */
  public List<ObjectArg> getObjectArgs() {
    return objectArgs;
  }

  /**
   * Sets object args.
   *
   * @param objectArgs the object args
   */
  public void setObjectArgs(List<ObjectArg> objectArgs) {
    this.objectArgs = objectArgs;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CallArgObjVec)) {
      return false;
    }
    CallArgObjVec that = (CallArgObjVec) o;
    return objectArgs.equals(that.objectArgs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectArgs);
  }
}
