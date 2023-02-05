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

package io.sui.models.objects;


import com.google.common.base.Objects;

/**
 * the checkpoint.
 *
 * @author thinkAfCod
 * @since 2023.2
 */
public class Checkpoint {

  private CheckpointSummary summary;

  private CheckpointContents content;

  public CheckpointSummary getSummary() {
    return summary;
  }

  public void setSummary(CheckpointSummary summary) {
    this.summary = summary;
  }

  public CheckpointContents getContent() {
    return content;
  }

  public void setContent(CheckpointContents content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Checkpoint that = (Checkpoint) o;
    return Objects.equal(summary, that.summary) && Objects.equal(content, that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(summary, content);
  }

  @Override
  public String toString() {
    return "Checkpoint{" + "summary=" + summary + ", content=" + content + '}';
  }
}
