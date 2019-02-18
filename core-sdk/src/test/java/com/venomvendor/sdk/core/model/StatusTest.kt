/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 16-Feb-2019.
 */

package com.venomvendor.sdk.core.model

import com.openpojo.reflection.impl.PojoClassFactory
import com.openpojo.validation.ValidatorBuilder
import com.openpojo.validation.rule.impl.NoNestedClassRule
import com.openpojo.validation.rule.impl.NoStaticExceptFinalRule
import com.openpojo.validation.test.impl.DefaultValuesNullTester
import com.openpojo.validation.test.impl.GetterTester
import com.openpojo.validation.test.impl.SetterTester
import org.junit.Test

class StatusTest {

    @Test
    fun validateClass() {
        val validator = ValidatorBuilder.create()
            .with(
                SetterTester(),
                GetterTester(),
                DefaultValuesNullTester()
            )
            .with(
                NoNestedClassRule(),
                NoStaticExceptFinalRule()
            )
            .build()

        val clazz = PojoClassFactory.getPojoClass(Status::class.java)
        validator.validate(clazz)
    }
}
