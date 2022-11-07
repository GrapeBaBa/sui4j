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

package io.sui.models;

/**
 * The enum Transaction kind.
 *
 * @author grapebaba
 * @since 2022.11
 */
public enum TransactionKind {
  /** Transfer object transaction kind. */
  TransferObject,
  /** Publish transaction kind. */
  Publish,
  /** Call transaction kind. */
  Call,
  /** Transfer sui transaction kind. */
  TransferSui,
  /** Pay transaction kind. */
  Pay,
  /** Pay sui transaction kind. */
  PaySui,
  /** Pay all sui transaction kind. */
  PayAllSui,
  /** Change epoch transaction kind. */
  ChangeEpoch
}
