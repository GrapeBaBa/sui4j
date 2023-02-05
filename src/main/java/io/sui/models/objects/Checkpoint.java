package io.sui.models.objects;

import com.google.common.base.Objects;

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
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Checkpoint that = (Checkpoint) o;
    return Objects.equal(summary, that.summary) && Objects.equal(content, that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(summary, content);
  }

  @Override
  public String toString() {
    return "Checkpoint{" +
        "summary=" + summary +
        ", content=" + content +
        '}';
  }
}
