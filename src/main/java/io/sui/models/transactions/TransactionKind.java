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

package io.sui.models.transactions;


import java.util.List;
import java.util.Objects;

/**
 * The type Transaction kind.
 *
 * @author grapebaba
 * @since 2022.11
 */
public abstract class TransactionKind {

  /** The type Change epoch transaction kind. */
  public static class ChangeEpochTransactionKind extends TransactionKind {

    @SuppressWarnings("checkstyle:MemberName")
    private ChangeEpoch ChangeEpoch;

    /**
     * Gets change epoch.
     *
     * @return the change epoch
     */
    public ChangeEpoch getChangeEpoch() {
      return ChangeEpoch;
    }

    /**
     * Sets change epoch.
     *
     * @param changeEpoch the change epoch
     */
    public void setChangeEpoch(ChangeEpoch changeEpoch) {
      ChangeEpoch = changeEpoch;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ChangeEpochTransactionKind)) {
        return false;
      }
      ChangeEpochTransactionKind that = (ChangeEpochTransactionKind) o;
      return ChangeEpoch.equals(that.ChangeEpoch);
    }

    @Override
    public int hashCode() {
      return Objects.hash(ChangeEpoch);
    }

    @Override
    public String toString() {
      return "ChangeEpochTransactionKind{" + "ChangeEpoch=" + ChangeEpoch + '}';
    }
  }

  /** The type Genesis transaction kind. */
  public static class GenesisTransactionKind extends TransactionKind {

    @SuppressWarnings("checkstyle:MemberName")
    private Genesis Genesis;

    /**
     * Gets genesis.
     *
     * @return the genesis
     */
    public Genesis getGenesis() {
      return Genesis;
    }

    /**
     * Sets genesis.
     *
     * @param genesis the genesis
     */
    public void setGenesis(Genesis genesis) {
      Genesis = genesis;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof GenesisTransactionKind)) {
        return false;
      }
      GenesisTransactionKind that = (GenesisTransactionKind) o;
      return Genesis.equals(that.Genesis);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Genesis);
    }

    @Override
    public String toString() {
      return "GenesisTransactionKind{" + "Genesis=" + Genesis + '}';
    }
  }

  /** The type Consensus commit prologue transaction kind. */
  public static class ConsensusCommitPrologueTransactionKind extends TransactionKind {

    @SuppressWarnings("checkstyle:MemberName")
    private ConsensusCommitPrologue ConsensusCommitPrologue;

    /**
     * Gets consensus commit prologue.
     *
     * @return the consensus commit prologue
     */
    public TransactionKind.ConsensusCommitPrologue getConsensusCommitPrologue() {
      return ConsensusCommitPrologue;
    }

    /**
     * Sets consensus commit prologue.
     *
     * @param consensusCommitPrologue the consensus commit prologue
     */
    public void setConsensusCommitPrologue(
        TransactionKind.ConsensusCommitPrologue consensusCommitPrologue) {
      ConsensusCommitPrologue = consensusCommitPrologue;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ConsensusCommitPrologueTransactionKind)) {
        return false;
      }
      ConsensusCommitPrologueTransactionKind that = (ConsensusCommitPrologueTransactionKind) o;
      return ConsensusCommitPrologue.equals(that.ConsensusCommitPrologue);
    }

    @Override
    public int hashCode() {
      return Objects.hash(ConsensusCommitPrologue);
    }

    @Override
    public String toString() {
      return "ConsensusCommitPrologueTransactionKind{"
          + "ConsensusCommitPrologue="
          + ConsensusCommitPrologue
          + '}';
    }
  }

  /** The type Programmable transaction transaction kind. */
  public static class ProgrammableTransactionTransactionKind extends TransactionKind {

    @SuppressWarnings("checkstyle:MemberName")
    private ProgrammableTransaction ProgrammableTransaction;

    /**
     * Gets programmable transaction.
     *
     * @return the programmable transaction
     */
    public TransactionKind.ProgrammableTransaction getProgrammableTransaction() {
      return ProgrammableTransaction;
    }

    /**
     * Sets programmable transaction.
     *
     * @param programmableTransaction the programmable transaction
     */
    public void setProgrammableTransaction(
        TransactionKind.ProgrammableTransaction programmableTransaction) {
      ProgrammableTransaction = programmableTransaction;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ProgrammableTransactionTransactionKind)) {
        return false;
      }
      ProgrammableTransactionTransactionKind that = (ProgrammableTransactionTransactionKind) o;
      return ProgrammableTransaction.equals(that.ProgrammableTransaction);
    }

    @Override
    public int hashCode() {
      return Objects.hash(ProgrammableTransaction);
    }

    @Override
    public String toString() {
      return "ProgrammableTransactionTransactionKind{"
          + "ProgrammableTransaction="
          + ProgrammableTransaction
          + '}';
    }
  }

  /**
   * The type Genesis.
   *
   * @author grapebaba
   * @since 2023.03
   */
  public static class Genesis {

    private List<String> objects;

    /**
     * Gets objects.
     *
     * @return the objects
     */
    public List<String> getObjects() {
      return objects;
    }

    /**
     * Sets objects.
     *
     * @param objects the objects
     */
    public void setObjects(List<String> objects) {
      this.objects = objects;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Genesis)) {
        return false;
      }
      Genesis genesis = (Genesis) o;
      return objects.equals(genesis.objects);
    }

    @Override
    public int hashCode() {
      return Objects.hash(objects);
    }

    @Override
    public String toString() {
      return "Genesis{" + "objects=" + objects + '}';
    }
  }

  /**
   * The type Change epoch.
   *
   * @author grapebaba
   * @since 2023.03
   */
  public static class ChangeEpoch {

    private Long epoch;

    @SuppressWarnings("checkstyle:MemberName")
    private Long storage_charge;

    @SuppressWarnings("checkstyle:MemberName")
    private Long computation_charge;

    @SuppressWarnings("checkstyle:MemberName")
    private Long storage_rebate;

    @SuppressWarnings("checkstyle:MemberName")
    private Long epoch_start_timestamp_ms;

    /**
     * Gets storage rebate.
     *
     * @return the storage rebate
     */
    public long getStorage_rebate() {
      return storage_rebate;
    }

    /**
     * Sets storage rebate.
     *
     * @param storage_rebate the storage rebate
     */
    @SuppressWarnings("checkstyle:ParameterName")
    public void setStorage_rebate(long storage_rebate) {
      this.storage_rebate = storage_rebate;
    }

    /**
     * Gets epoch start timestamp ms.
     *
     * @return the epoch start timestamp ms
     */
    public long getEpoch_start_timestamp_ms() {
      return epoch_start_timestamp_ms;
    }

    /**
     * Sets epoch start timestamp ms.
     *
     * @param epoch_start_timestamp_ms the epoch start timestamp ms
     */
    @SuppressWarnings("checkstyle:ParameterName")
    public void setEpoch_start_timestamp_ms(long epoch_start_timestamp_ms) {
      this.epoch_start_timestamp_ms = epoch_start_timestamp_ms;
    }

    /**
     * Gets epoch.
     *
     * @return the epoch
     */
    public Long getEpoch() {
      return epoch;
    }

    /**
     * Sets epoch.
     *
     * @param epoch the epoch
     */
    public void setEpoch(Long epoch) {
      this.epoch = epoch;
    }

    /**
     * Gets storage charge.
     *
     * @return the storage charge
     */
    public Long getStorage_charge() {
      return storage_charge;
    }

    /**
     * Sets storage charge.
     *
     * @param storage_charge the storage charge
     */
    @SuppressWarnings("checkstyle:ParameterName")
    public void setStorage_charge(Long storage_charge) {
      this.storage_charge = storage_charge;
    }

    /**
     * Gets computation charge.
     *
     * @return the computation charge
     */
    public Long getComputation_charge() {
      return computation_charge;
    }

    /**
     * Sets computation charge.
     *
     * @param computation_charge the computation charge
     */
    @SuppressWarnings("checkstyle:ParameterName")
    public void setComputation_charge(Long computation_charge) {
      this.computation_charge = computation_charge;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ChangeEpoch)) {
        return false;
      }
      ChangeEpoch that = (ChangeEpoch) o;
      return epoch.equals(that.epoch)
          && storage_charge.equals(that.storage_charge)
          && computation_charge.equals(that.computation_charge)
          && storage_rebate.equals(that.storage_rebate)
          && epoch_start_timestamp_ms.equals(that.epoch_start_timestamp_ms);
    }

    @Override
    public int hashCode() {
      return Objects.hash(
          epoch, storage_charge, computation_charge, storage_rebate, epoch_start_timestamp_ms);
    }

    @Override
    public String toString() {
      return "ChangeEpoch{"
          + "epoch="
          + epoch
          + ", storage_charge="
          + storage_charge
          + ", computation_charge="
          + computation_charge
          + ", storage_rebate="
          + storage_rebate
          + ", epoch_start_timestamp_ms="
          + epoch_start_timestamp_ms
          + '}';
    }
  }

  /**
   * The type Consensus commit prologue.
   *
   * @author grapebaba
   * @since 2023.03
   */
  public static class ConsensusCommitPrologue {

    private Long epoch;

    private Long round;

    @SuppressWarnings("checkstyle:MemberName")
    private Long commit_timestamp_ms;

    /**
     * Gets epoch.
     *
     * @return the epoch
     */
    public long getEpoch() {
      return epoch;
    }

    /**
     * Sets epoch.
     *
     * @param epoch the epoch
     */
    public void setEpoch(long epoch) {
      this.epoch = epoch;
    }

    /**
     * Gets round.
     *
     * @return the round
     */
    public long getRound() {
      return round;
    }

    /**
     * Sets round.
     *
     * @param round the round
     */
    public void setRound(long round) {
      this.round = round;
    }

    /**
     * Gets commit timestamp ms.
     *
     * @return the commit timestamp ms
     */
    public long getCommit_timestamp_ms() {
      return commit_timestamp_ms;
    }

    /**
     * Sets commit timestamp ms.
     *
     * @param commit_timestamp_ms the commit timestamp ms
     */
    @SuppressWarnings("checkstyle:ParameterName")
    public void setCommit_timestamp_ms(long commit_timestamp_ms) {
      this.commit_timestamp_ms = commit_timestamp_ms;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ConsensusCommitPrologue)) {
        return false;
      }
      ConsensusCommitPrologue that = (ConsensusCommitPrologue) o;
      return epoch.equals(that.epoch)
          && round.equals(that.round)
          && commit_timestamp_ms.equals(that.commit_timestamp_ms);
    }

    @Override
    public int hashCode() {
      return Objects.hash(epoch, round, commit_timestamp_ms);
    }

    @Override
    public String toString() {
      return "ConsensusCommitPrologue{"
          + "epoch="
          + epoch
          + ", round="
          + round
          + ", commit_timestamp_ms="
          + commit_timestamp_ms
          + '}';
    }
  }

  /** The type Programmable transaction. */
  public static class ProgrammableTransaction {

    private List<Command> commands;

    private List<?> inputs;

    /**
     * Gets commands.
     *
     * @return the commands
     */
    public List<Command> getCommands() {
      return commands;
    }

    /**
     * Sets commands.
     *
     * @param commands the commands
     */
    public void setCommands(List<Command> commands) {
      this.commands = commands;
    }

    /**
     * Gets inputs.
     *
     * @return the inputs
     */
    public List<?> getInputs() {
      return inputs;
    }

    /**
     * Sets inputs.
     *
     * @param inputs the inputs
     */
    public void setInputs(List<?> inputs) {
      this.inputs = inputs;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ProgrammableTransaction)) {
        return false;
      }
      ProgrammableTransaction that = (ProgrammableTransaction) o;
      return commands.equals(that.commands) && inputs.equals(that.inputs);
    }

    @Override
    public int hashCode() {
      return Objects.hash(commands, inputs);
    }

    @Override
    public String toString() {
      return "ProgrammableTransaction{" + "commands=" + commands + ", inputs=" + inputs + '}';
    }
  }
}
