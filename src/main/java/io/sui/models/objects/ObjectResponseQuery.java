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

package io.sui.models.objects;


import java.util.Objects;

/**
 * The type Object response query.
 *
 * @author grapebaba
 * @since 2023.03
 */
public class ObjectResponseQuery {

  private ObjectDataOptions options;

  private ObjectDataFilter filter;

  /**
   * Gets options.
   *
   * @return the options
   */
  public ObjectDataOptions getOptions() {
    return options;
  }

  /**
   * Sets options.
   *
   * @param options the options
   */
  public void setOptions(ObjectDataOptions options) {
    this.options = options;
  }

  /**
   * Gets filter.
   *
   * @return the filter
   */
  public ObjectDataFilter getFilter() {
    return filter;
  }

  /**
   * Sets filter.
   *
   * @param filter the filter
   */
  public void setFilter(ObjectDataFilter filter) {
    this.filter = filter;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ObjectResponseQuery)) {
      return false;
    }
    ObjectResponseQuery that = (ObjectResponseQuery) o;
    return options.equals(that.options) && filter.equals(that.filter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(options, filter);
  }

  @Override
  public String toString() {
    return "ObjectResponseQuery{" + "options=" + options + ", filter=" + filter + '}';
  }
}
