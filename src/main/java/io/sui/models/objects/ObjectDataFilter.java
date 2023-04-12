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
 * The type Object data filter.
 *
 * @author grapebaba
 * @since 2023.03
 */
public abstract class ObjectDataFilter {

  /** The type Package filter. */
  public static class PackageFilter extends ObjectDataFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private String Package;

    /**
     * Gets package.
     *
     * @return the package
     */
    public String getPackage() {
      return Package;
    }

    /**
     * Sets package.
     *
     * @param aPackage the a package
     */
    @SuppressWarnings("checkstyle:ParameterName")
    public void setPackage(String aPackage) {
      Package = aPackage;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof PackageFilter)) {
        return false;
      }
      PackageFilter that = (PackageFilter) o;
      return Package.equals(that.Package);
    }

    @Override
    public int hashCode() {
      return Objects.hash(Package);
    }

    @Override
    public String toString() {
      return "PackageFilter{" + "Package='" + Package + '\'' + '}';
    }
  }

  /** The type Move module filter. */
  public static class MoveModuleFilter extends ObjectDataFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private MoveModule MoveModule;

    /**
     * Gets move module.
     *
     * @return the move module
     */
    public io.sui.models.objects.MoveModule getMoveModule() {
      return MoveModule;
    }

    /**
     * Sets move module.
     *
     * @param moveModule the move module
     */
    public void setMoveModule(io.sui.models.objects.MoveModule moveModule) {
      MoveModule = moveModule;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MoveModuleFilter)) {
        return false;
      }
      MoveModuleFilter that = (MoveModuleFilter) o;
      return MoveModule.equals(that.MoveModule);
    }

    @Override
    public int hashCode() {
      return Objects.hash(MoveModule);
    }

    @Override
    public String toString() {
      return "MoveModuleFilter{" + "MoveModule=" + MoveModule + '}';
    }
  }

  /** The type Struct type filter. */
  public static class StructTypeFilter extends ObjectDataFilter {

    @SuppressWarnings("checkstyle:MemberName")
    private String StructType;

    /**
     * Gets struct type.
     *
     * @return the struct type
     */
    public String getStructType() {
      return StructType;
    }

    /**
     * Sets struct type.
     *
     * @param structType the struct type
     */
    public void setStructType(String structType) {
      StructType = structType;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof StructTypeFilter)) {
        return false;
      }
      StructTypeFilter that = (StructTypeFilter) o;
      return StructType.equals(that.StructType);
    }

    @Override
    public int hashCode() {
      return Objects.hash(StructType);
    }

    @Override
    public String toString() {
      return "StructTypeFilter{" + "StructType='" + StructType + '\'' + '}';
    }
  }
}
