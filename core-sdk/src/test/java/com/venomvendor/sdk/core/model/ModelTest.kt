/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 17-Feb-2019.
 */

package com.venomvendor.sdk.core.model

import com.openpojo.reflection.impl.PojoClassFactory
import com.openpojo.validation.ValidatorBuilder
import com.openpojo.validation.rule.impl.NoStaticExceptFinalRule
import com.openpojo.validation.test.impl.DefaultValuesNullTester
import com.openpojo.validation.test.impl.GetterTester
import com.openpojo.validation.test.impl.SetterTester
import com.venomvendor.sdk.core.model.gallery.PublicFeed
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ModelTest {

    @Test
    fun isSuccessWhenOk() {
        val feed = PublicFeed()

        feed.stat = "ok"

        assertTrue(feed.isSuccess)
    }

    @Test
    fun isNoSuccessWhenFail() {
        val feed = PublicFeed()
        feed.stat = "fail"

        assertFalse(feed.isSuccess)
    }

    @Test
    fun isSuccessWhenJunk() {
        val feed = PublicFeed()
        feed.stat = "junk"

        assertTrue(feed.isSuccess)
    }

    @Test
    fun isSuccessWhenNull() {
        val feed = PublicFeed()
        assertTrue(feed.isSuccess)
    }

    @Test
    fun validatePackage() {
        val validator = ValidatorBuilder.create()
            .with(
                SetterTester(),
                GetterTester(),
                DefaultValuesNullTester()
            )
            .with(
                NoStaticExceptFinalRule()
            )
            .build()

        val classes = PojoClassFactory.getPojoClassesRecursively(PACKAGE, null)
        validator.validate(classes)
    }

    companion object {

        private val PACKAGE = PublicFeed::class.java.getPackage()!!.name
    }
}
