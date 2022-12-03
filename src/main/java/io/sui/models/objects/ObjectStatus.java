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

package io.sui.models.objects;

/**
 * The enum Object status.
 *
 * @author grapebaba
 * @since 2022.11
 */
public enum ObjectStatus {
  /** Exists object status. */
  Exists,
  /** Not exists object status. */
  NotExists,
  /** Deleted object status. */
  Deleted,
  /** Object not exists object status. */
  ObjectNotExists,
  /** Object deleted object status. */
  ObjectDeleted,
  /** Version found object status. */
  VersionFound,
  /** Version not found object status. */
  VersionNotFound,
  /** Version too high object status. */
  VersionTooHigh
}
