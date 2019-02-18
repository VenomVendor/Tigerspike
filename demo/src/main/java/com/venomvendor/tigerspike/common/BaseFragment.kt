/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 18-Feb-2019.
 */

package com.venomvendor.tigerspike.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.CheckResult
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import java.util.Objects
import kotlin.reflect.KClass

/**
 * Root fragment/view to handles common functionality across app.
 */
abstract class BaseFragment<T : ViewModel> : Fragment() {

    protected lateinit var viewModel: T

    /**
     * Get the Layout for the user.
     *
     * @return layout to append
     */
    @get:CheckResult
    @get:LayoutRes
    protected abstract val layout: Int

    /**
     * Get [ViewModel] Implementor
     *
     * @return Class type of current view model.
     */
    @get:CheckResult
    protected abstract val viewModelClz: KClass<T>?

    /**
     * Views are ready to be initialized.
     *
     * @param view current view
     */
    protected abstract fun initViews(view: View)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        // Retain state over orientation.
        retainInstance = true
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelClz?.java?.let {
            // Update View model from default ViewModel Factory.
            this.viewModel = ViewModelProviders.of(this).get(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create View.
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init Views
        initViews(Objects.requireNonNull<View>(getView(), "View is not loaded yet"))
    }
}
