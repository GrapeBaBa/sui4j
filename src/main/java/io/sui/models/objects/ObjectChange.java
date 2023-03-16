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


import java.util.List;
import java.util.Objects;

/**
 * The type Object change.
 *
 * @author grapebaba
 * @since 2023.03
 */
public abstract class ObjectChange {

  /** The enum Object change type. */
  public enum ObjectChangeType {
    /** Published object change type. */
    published,
    /** Transferred object change type. */
    transferred,

    /** Mutated object change type. */
    mutated,

    /** Deleted object change type. */
    deleted,

    /** Wrapped object change type. */
    wrapped,

    /** Created object change type. */
    created
  }

  /** The type Object change published. */
  public static class ObjectChangePublished extends ObjectChange {

    private String packageId;

    private Long version;

    private String digest;

    private List<String> modules;

    private ObjectChangeType type = ObjectChangeType.published;

    /**
     * Gets package id.
     *
     * @return the package id
     */
    public String getPackageId() {
      return packageId;
    }

    /**
     * Sets package id.
     *
     * @param packageId the package id
     */
    public void setPackageId(String packageId) {
      this.packageId = packageId;
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public Long getVersion() {
      return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(Long version) {
      this.version = version;
    }

    /**
     * Gets digest.
     *
     * @return the digest
     */
    public String getDigest() {
      return digest;
    }

    /**
     * Sets digest.
     *
     * @param digest the digest
     */
    public void setDigest(String digest) {
      this.digest = digest;
    }

    /**
     * Gets modules.
     *
     * @return the modules
     */
    public List<String> getModules() {
      return modules;
    }

    /**
     * Sets modules.
     *
     * @param modules the modules
     */
    public void setModules(List<String> modules) {
      this.modules = modules;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public ObjectChangeType getType() {
      return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(ObjectChangeType type) {
      this.type = type;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ObjectChangePublished)) {
        return false;
      }
      ObjectChangePublished that = (ObjectChangePublished) o;
      return packageId.equals(that.packageId)
          && version.equals(that.version)
          && digest.equals(that.digest)
          && modules.equals(that.modules)
          && type == that.type;
    }

    @Override
    public int hashCode() {
      return Objects.hash(packageId, version, digest, modules, type);
    }

    @Override
    public String toString() {
      return "ObjectChangePublished{"
          + "packageId='"
          + packageId
          + '\''
          + ", version="
          + version
          + ", digest='"
          + digest
          + '\''
          + ", modules="
          + modules
          + ", type="
          + type
          + '}';
    }
  }

  /** The type Object change transferred. */
  public static class ObjectChangeTransferred extends ObjectChange {

    private String sender;

    private SuiObjectOwner recipient;

    private String objectType;

    private String objectId;

    private Long version;

    private String digest;

    private ObjectChangeType type = ObjectChangeType.transferred;

    /**
     * Gets sender.
     *
     * @return the sender
     */
    public String getSender() {
      return sender;
    }

    /**
     * Sets sender.
     *
     * @param sender the sender
     */
    public void setSender(String sender) {
      this.sender = sender;
    }

    /**
     * Gets recipient.
     *
     * @return the recipient
     */
    public SuiObjectOwner getRecipient() {
      return recipient;
    }

    /**
     * Sets recipient.
     *
     * @param recipient the recipient
     */
    public void setRecipient(SuiObjectOwner recipient) {
      this.recipient = recipient;
    }

    /**
     * Gets object type.
     *
     * @return the object type
     */
    public String getObjectType() {
      return objectType;
    }

    /**
     * Sets object type.
     *
     * @param objectType the object type
     */
    public void setObjectType(String objectType) {
      this.objectType = objectType;
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

    /**
     * Gets version.
     *
     * @return the version
     */
    public Long getVersion() {
      return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(Long version) {
      this.version = version;
    }

    /**
     * Gets digest.
     *
     * @return the digest
     */
    public String getDigest() {
      return digest;
    }

    /**
     * Sets digest.
     *
     * @param digest the digest
     */
    public void setDigest(String digest) {
      this.digest = digest;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public ObjectChangeType getType() {
      return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(ObjectChangeType type) {
      this.type = type;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ObjectChangeTransferred)) {
        return false;
      }
      ObjectChangeTransferred that = (ObjectChangeTransferred) o;
      return sender.equals(that.sender)
          && recipient.equals(that.recipient)
          && objectType.equals(that.objectType)
          && objectId.equals(that.objectId)
          && version.equals(that.version)
          && digest.equals(that.digest)
          && type == that.type;
    }

    @Override
    public int hashCode() {
      return Objects.hash(sender, recipient, objectType, objectId, version, digest, type);
    }

    @Override
    public String toString() {
      return "ObjectChangeTransferred{"
          + "sender='"
          + sender
          + '\''
          + ", recipient="
          + recipient
          + ", objectType='"
          + objectType
          + '\''
          + ", objectId='"
          + objectId
          + '\''
          + ", version="
          + version
          + ", digest='"
          + digest
          + '\''
          + ", type="
          + type
          + '}';
    }
  }

  /** The type Object change mutated. */
  public static class ObjectChangeMutated extends ObjectChange {

    private String sender;

    private SuiObjectOwner owner;

    private String objectType;

    private String objectId;

    private Long version;

    private String digest;

    private ObjectChangeType type = ObjectChangeType.mutated;

    /**
     * Gets sender.
     *
     * @return the sender
     */
    public String getSender() {
      return sender;
    }

    /**
     * Sets sender.
     *
     * @param sender the sender
     */
    public void setSender(String sender) {
      this.sender = sender;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public SuiObjectOwner getOwner() {
      return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(SuiObjectOwner owner) {
      this.owner = owner;
    }

    /**
     * Gets object type.
     *
     * @return the object type
     */
    public String getObjectType() {
      return objectType;
    }

    /**
     * Sets object type.
     *
     * @param objectType the object type
     */
    public void setObjectType(String objectType) {
      this.objectType = objectType;
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

    /**
     * Gets version.
     *
     * @return the version
     */
    public Long getVersion() {
      return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(Long version) {
      this.version = version;
    }

    /**
     * Gets digest.
     *
     * @return the digest
     */
    public String getDigest() {
      return digest;
    }

    /**
     * Sets digest.
     *
     * @param digest the digest
     */
    public void setDigest(String digest) {
      this.digest = digest;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public ObjectChangeType getType() {
      return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(ObjectChangeType type) {
      this.type = type;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ObjectChangeMutated)) {
        return false;
      }
      ObjectChangeMutated that = (ObjectChangeMutated) o;
      return sender.equals(that.sender)
          && owner.equals(that.owner)
          && objectType.equals(that.objectType)
          && objectId.equals(that.objectId)
          && version.equals(that.version)
          && digest.equals(that.digest)
          && type == that.type;
    }

    @Override
    public int hashCode() {
      return Objects.hash(sender, owner, objectType, objectId, version, digest, type);
    }

    @Override
    public String toString() {
      return "ObjectChangeMutated{"
          + "sender='"
          + sender
          + '\''
          + ", owner="
          + owner
          + ", objectType='"
          + objectType
          + '\''
          + ", objectId='"
          + objectId
          + '\''
          + ", version="
          + version
          + ", digest='"
          + digest
          + '\''
          + ", type="
          + type
          + '}';
    }
  }

  /** The type Object change deleted. */
  public static class ObjectChangeDeleted extends ObjectChange {

    private String sender;

    private String objectType;

    private String objectId;

    private Long version;

    private ObjectChangeType type = ObjectChangeType.deleted;

    /**
     * Gets sender.
     *
     * @return the sender
     */
    public String getSender() {
      return sender;
    }

    /**
     * Sets sender.
     *
     * @param sender the sender
     */
    public void setSender(String sender) {
      this.sender = sender;
    }

    /**
     * Gets object type.
     *
     * @return the object type
     */
    public String getObjectType() {
      return objectType;
    }

    /**
     * Sets object type.
     *
     * @param objectType the object type
     */
    public void setObjectType(String objectType) {
      this.objectType = objectType;
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

    /**
     * Gets version.
     *
     * @return the version
     */
    public Long getVersion() {
      return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(Long version) {
      this.version = version;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public ObjectChangeType getType() {
      return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(ObjectChangeType type) {
      this.type = type;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ObjectChangeDeleted)) {
        return false;
      }
      ObjectChangeDeleted that = (ObjectChangeDeleted) o;
      return sender.equals(that.sender)
          && objectType.equals(that.objectType)
          && objectId.equals(that.objectId)
          && version.equals(that.version)
          && type == that.type;
    }

    @Override
    public int hashCode() {
      return Objects.hash(sender, objectType, objectId, version, type);
    }

    @Override
    public String toString() {
      return "ObjectChangeDeleted{"
          + "sender='"
          + sender
          + '\''
          + ", objectType='"
          + objectType
          + '\''
          + ", objectId='"
          + objectId
          + '\''
          + ", version="
          + version
          + ", type="
          + type
          + '}';
    }
  }

  /** The type Object change wrapped. */
  public static class ObjectChangeWrapped extends ObjectChange {

    private String sender;

    private String objectType;

    private String objectId;

    private Long version;

    private ObjectChangeType type = ObjectChangeType.wrapped;

    /**
     * Gets sender.
     *
     * @return the sender
     */
    public String getSender() {
      return sender;
    }

    /**
     * Sets sender.
     *
     * @param sender the sender
     */
    public void setSender(String sender) {
      this.sender = sender;
    }

    /**
     * Gets object type.
     *
     * @return the object type
     */
    public String getObjectType() {
      return objectType;
    }

    /**
     * Sets object type.
     *
     * @param objectType the object type
     */
    public void setObjectType(String objectType) {
      this.objectType = objectType;
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

    /**
     * Gets version.
     *
     * @return the version
     */
    public Long getVersion() {
      return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(Long version) {
      this.version = version;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public ObjectChangeType getType() {
      return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(ObjectChangeType type) {
      this.type = type;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ObjectChangeWrapped)) {
        return false;
      }
      ObjectChangeWrapped that = (ObjectChangeWrapped) o;
      return sender.equals(that.sender)
          && objectType.equals(that.objectType)
          && objectId.equals(that.objectId)
          && version.equals(that.version)
          && type == that.type;
    }

    @Override
    public int hashCode() {
      return Objects.hash(sender, objectType, objectId, version, type);
    }

    @Override
    public String toString() {
      return "ObjectChangeWrapped{"
          + "sender='"
          + sender
          + '\''
          + ", objectType='"
          + objectType
          + '\''
          + ", objectId='"
          + objectId
          + '\''
          + ", version="
          + version
          + ", type="
          + type
          + '}';
    }
  }

  /** The type Object change created. */
  public static class ObjectChangeCreated extends ObjectChange {

    private String sender;

    private SuiObjectOwner owner;

    private String objectType;

    private String objectId;

    private Long version;

    private String digest;

    private ObjectChangeType type = ObjectChangeType.created;

    /**
     * Gets sender.
     *
     * @return the sender
     */
    public String getSender() {
      return sender;
    }

    /**
     * Sets sender.
     *
     * @param sender the sender
     */
    public void setSender(String sender) {
      this.sender = sender;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public SuiObjectOwner getOwner() {
      return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(SuiObjectOwner owner) {
      this.owner = owner;
    }

    /**
     * Gets object type.
     *
     * @return the object type
     */
    public String getObjectType() {
      return objectType;
    }

    /**
     * Sets object type.
     *
     * @param objectType the object type
     */
    public void setObjectType(String objectType) {
      this.objectType = objectType;
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

    /**
     * Gets version.
     *
     * @return the version
     */
    public Long getVersion() {
      return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     */
    public void setVersion(Long version) {
      this.version = version;
    }

    /**
     * Gets digest.
     *
     * @return the digest
     */
    public String getDigest() {
      return digest;
    }

    /**
     * Sets digest.
     *
     * @param digest the digest
     */
    public void setDigest(String digest) {
      this.digest = digest;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public ObjectChangeType getType() {
      return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(ObjectChangeType type) {
      this.type = type;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ObjectChangeCreated)) {
        return false;
      }
      ObjectChangeCreated that = (ObjectChangeCreated) o;
      return sender.equals(that.sender)
          && owner.equals(that.owner)
          && objectType.equals(that.objectType)
          && objectId.equals(that.objectId)
          && version.equals(that.version)
          && digest.equals(that.digest)
          && type == that.type;
    }

    @Override
    public int hashCode() {
      return Objects.hash(sender, owner, objectType, objectId, version, digest, type);
    }

    @Override
    public String toString() {
      return "ObjectChangeCreated{"
          + "sender='"
          + sender
          + '\''
          + ", owner="
          + owner
          + ", objectType='"
          + objectType
          + '\''
          + ", objectId='"
          + objectId
          + '\''
          + ", version="
          + version
          + ", digest='"
          + digest
          + '\''
          + ", type="
          + type
          + '}';
    }
  }
}
