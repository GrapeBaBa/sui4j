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

package io.sui.models.transactions;


import java.util.Objects;

/**
 * The type Transaction block response query.
 *
 * @author grapebaba
 * @since 2023.04
 */
public class TransactionBlockResponseQuery {

  private TransactionFilter filter;

  private TransactionBlockResponseOptions options;

  /**
   * Gets filter.
   *
   * @return the filter
   */
  public TransactionFilter getFilter() {
    return filter;
  }

  /**
   * Sets filter.
   *
   * @param filter the filter
   */
  public void setFilter(TransactionFilter filter) {
    this.filter = filter;
  }

  /**
   * Gets options.
   *
   * @return the options
   */
  public TransactionBlockResponseOptions getOptions() {
    return options;
  }

  /**
   * Sets options.
   *
   * @param options the options
   */
  public void setOptions(TransactionBlockResponseOptions options) {
    this.options = options;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TransactionBlockResponseQuery)) {
      return false;
    }
    TransactionBlockResponseQuery that = (TransactionBlockResponseQuery) o;
    return filter.equals(that.filter) && options.equals(that.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filter, options);
  }

  @Override
  public String toString() {
    return "TransactionBlockResponseQuery{" + "filter=" + filter + ", options=" + options + '}';
  }
}
