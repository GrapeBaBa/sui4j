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

package io.sui.models.transactions;


import java.util.Objects;

/**
 * The type Parsed transaction response.
 *
 * @author grapebaba
 * @since 2022.11
 */
public abstract class ParsedTransactionResponseKind {

  /** The type Parsed merge coin response kind. */
  public static class ParsedMergeCoinResponseKind extends ParsedTransactionResponseKind {

    @SuppressWarnings("checkstyle:MemberName")
    private ParsedMergeCoinResponse MergeCoin;

    /**
     * Gets merge coin.
     *
     * @return the merge coin
     */
    public ParsedMergeCoinResponse getMergeCoin() {
      return MergeCoin;
    }

    /**
     * Sets merge coin.
     *
     * @param mergeCoin the merge coin
     */
    public void setMergeCoin(ParsedMergeCoinResponse mergeCoin) {
      MergeCoin = mergeCoin;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ParsedMergeCoinResponseKind)) {
        return false;
      }
      ParsedMergeCoinResponseKind that = (ParsedMergeCoinResponseKind) o;
      return MergeCoin.equals(that.MergeCoin);
    }

    @Override
    public int hashCode() {
      return Objects.hash(MergeCoin);
    }

    @Override
    public String toString() {
      return "ParsedMergeCoinResponseKind{" + "MergeCoin=" + MergeCoin + '}';
    }
  }

  /** The type Parsed publish response kind. */
  public static class ParsedPublishResponseKind extends ParsedTransactionResponseKind {

    @SuppressWarnings("checkstyle:MemberName")
    private ParsedPublishResponse Publish;

    /**
     * Gets publish.
     *
     * @return the publish
     */
    public ParsedPublishResponse getPublish() {
      return Publish;
    }

    /**
     * Sets publish.
     *
     * @param publish the publish
     */
    public void setPublish(ParsedPublishResponse publish) {
      Publish = publish;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ParsedPublishResponseKind)) {
        return false;
      }
      ParsedPublishResponseKind that = (ParsedPublishResponseKind) o;
      return Publish.equals(that.Publish);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Publish);
    }

    @Override
    public String toString() {
      return "ParsedPublishResponseKind{" + "Publish=" + Publish + '}';
    }
  }

  /** The type Parsed split coin response kind. */
  public static class ParsedSplitCoinResponseKind extends ParsedTransactionResponseKind {

    @SuppressWarnings("checkstyle:MemberName")
    private ParsedSplitCoinResponse SplitCoin;

    /**
     * Gets split coin.
     *
     * @return the split coin
     */
    public ParsedSplitCoinResponse getSplitCoin() {
      return SplitCoin;
    }

    /**
     * Sets split coin.
     *
     * @param splitCoin the split coin
     */
    public void setSplitCoin(ParsedSplitCoinResponse splitCoin) {
      SplitCoin = splitCoin;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ParsedSplitCoinResponseKind)) {
        return false;
      }
      ParsedSplitCoinResponseKind that = (ParsedSplitCoinResponseKind) o;
      return SplitCoin.equals(that.SplitCoin);
    }

    @Override
    public int hashCode() {
      return Objects.hash(SplitCoin);
    }

    @Override
    public String toString() {
      return "ParsedSplitCoinResponseKind{" + "SplitCoin=" + SplitCoin + '}';
    }
  }
}
