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

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Lists;
import io.sui.models.transactions.TypeTag.SimpleType;
import io.sui.models.transactions.TypeTag.StructType;
import io.sui.models.transactions.TypeTag.VectorType;
import org.junit.jupiter.api.Test;

/**
 * The type Type tag test.
 *
 * @author grapebaba
 * @since 2022.11
 */
class TypeTagTest {

  /** Type tag. */
  @Test
  void typeTag() {
    StructTag structTag = new StructTag();
    structTag.setAddress("0x2");
    structTag.setModule("sui");
    structTag.setName("Sui");
    structTag.setTypeParams(Lists.newArrayList(SimpleType.bool, SimpleType.u16));
    VectorType vectorType = new TypeTag.VectorType();
    StructType structType = new StructType();
    structType.setStructTag(structTag);
    vectorType.setTypeTag(structType);
    System.out.println(vectorType);
    assertEquals("vector<0x2::sui::Sui<bool, u16>>", vectorType.toString());

    StructTag structTag1 = new StructTag();
    structTag1.setAddress("0x2");
    structTag1.setModule("test");
    structTag1.setName("Test");
    structTag1.setTypeParams(Lists.newArrayList(SimpleType.u128, vectorType));

    VectorType vectorType1 = new TypeTag.VectorType();
    StructType structType1 = new StructType();
    structType1.setStructTag(structTag1);
    vectorType1.setTypeTag(structType1);
    System.out.println(vectorType1);
    assertEquals(
        "vector<0x2::test::Test<u128, vector<0x2::sui::Sui<bool, u16>>>>", vectorType1.toString());
  }
}
