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


import java.util.List;
import java.util.Objects;

/**
 * The interface Rpc transaction request params.
 *
 * @author grapebaba
 * @since 2022.11
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public interface RPCTransactionRequestParams {

  /** The type Transfer object request params. */
  class TransferObjectRequestParams implements RPCTransactionRequestParams {

    private TransferObjectParams transferObjectRequestParams;

    /**
     * Gets transfer object request params.
     *
     * @return the transfer object request params
     */
    public TransferObjectParams getTransferObjectRequestParams() {
      return transferObjectRequestParams;
    }

    /**
     * Sets transfer object request params.
     *
     * @param transferObjectRequestParams the transfer object request params
     */
    public void setTransferObjectRequestParams(TransferObjectParams transferObjectRequestParams) {
      this.transferObjectRequestParams = transferObjectRequestParams;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TransferObjectRequestParams)) {
        return false;
      }
      TransferObjectRequestParams that = (TransferObjectRequestParams) o;
      return transferObjectRequestParams.equals(that.transferObjectRequestParams);
    }

    @Override
    public int hashCode() {
      return Objects.hash(transferObjectRequestParams);
    }

    @Override
    public String toString() {
      return "TransferObjectRequestParams{"
          + "transferObjectRequestParams="
          + transferObjectRequestParams
          + '}';
    }
  }

  /** The type Move call request params. */
  class MoveCallRequestParams implements RPCTransactionRequestParams {

    private MoveCallParams moveCallRequestParams;

    /**
     * Gets move call request params.
     *
     * @return the move call request params
     */
    public MoveCallParams getMoveCallRequestParams() {
      return moveCallRequestParams;
    }

    /**
     * Sets move call request params.
     *
     * @param moveCallRequestParams the move call request params
     */
    public void setMoveCallRequestParams(MoveCallParams moveCallRequestParams) {
      this.moveCallRequestParams = moveCallRequestParams;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveCallRequestParams)) {
        return false;
      }
      MoveCallRequestParams that = (MoveCallRequestParams) o;
      return moveCallRequestParams.equals(that.moveCallRequestParams);
    }

    @Override
    public int hashCode() {
      return Objects.hash(moveCallRequestParams);
    }

    @Override
    public String toString() {
      return "MoveCallRequestParams{" + "moveCallRequestParams=" + moveCallRequestParams + '}';
    }
  }

  /** The type Transfer object params. */
  class TransferObjectParams {

    private String recipient;

    private String objectId;

    /**
     * Gets recipient.
     *
     * @return the recipient
     */
    public String getRecipient() {
      return recipient;
    }

    /**
     * Sets recipient.
     *
     * @param recipient the recipient
     */
    public void setRecipient(String recipient) {
      this.recipient = recipient;
    }

    /**
     * Gets object id.
     *
     * @return the object id
     */
    public String getObjectId() {
      return objectId;
    }

    /**
     * Sets object id.
     *
     * @param objectId the object id
     */
    public void setObjectId(String objectId) {
      this.objectId = objectId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof TransferObjectParams)) {
        return false;
      }
      TransferObjectParams that = (TransferObjectParams) o;
      return recipient.equals(that.recipient) && objectId.equals(that.objectId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(recipient, objectId);
    }

    @Override
    public String toString() {
      return "TransferObjectParams{"
          + "recipient='"
          + recipient
          + '\''
          + ", object_id='"
          + objectId
          + '\''
          + '}';
    }
  }

  /** The type Move call params. */
  class MoveCallParams {

    private String packageObjectId;

    private String module;

    private String function;

    private List<String> typeArguments;

    private List<?> arguments;

    /**
     * Gets package object id.
     *
     * @return the package object id
     */
    public String getPackageObjectId() {
      return packageObjectId;
    }

    /**
     * Sets package object id.
     *
     * @param packageObjectId the package object id
     */
    public void setPackageObjectId(String packageObjectId) {
      this.packageObjectId = packageObjectId;
    }

    /**
     * Gets module.
     *
     * @return the module
     */
    public String getModule() {
      return module;
    }

    /**
     * Sets module.
     *
     * @param module the module
     */
    public void setModule(String module) {
      this.module = module;
    }

    /**
     * Gets function.
     *
     * @return the function
     */
    public String getFunction() {
      return function;
    }

    /**
     * Sets function.
     *
     * @param function the function
     */
    public void setFunction(String function) {
      this.function = function;
    }

    /**
     * Gets type arguments.
     *
     * @return the type arguments
     */
    public List<String> getTypeArguments() {
      return typeArguments;
    }

    /**
     * Sets type arguments.
     *
     * @param typeArguments the type arguments
     */
    public void setTypeArguments(List<String> typeArguments) {
      this.typeArguments = typeArguments;
    }

    /**
     * Gets arguments.
     *
     * @return the arguments
     */
    public List<?> getArguments() {
      return arguments;
    }

    /**
     * Sets arguments.
     *
     * @param arguments the arguments
     */
    public void setArguments(List<?> arguments) {
      this.arguments = arguments;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveCallParams)) {
        return false;
      }
      MoveCallParams that = (MoveCallParams) o;
      return packageObjectId.equals(that.packageObjectId)
          && module.equals(that.module)
          && function.equals(that.function)
          && typeArguments.equals(that.typeArguments)
          && arguments.equals(that.arguments);
    }

    @Override
    public int hashCode() {
      return Objects.hash(packageObjectId, module, function, typeArguments, arguments);
    }

    @Override
    public String toString() {
      return "MoveCallParams{"
          + "packageObjectId='"
          + packageObjectId
          + '\''
          + ", module='"
          + module
          + '\''
          + ", function='"
          + function
          + '\''
          + ", typeArguments="
          + typeArguments
          + ", arguments="
          + arguments
          + '}';
    }
  }
}
