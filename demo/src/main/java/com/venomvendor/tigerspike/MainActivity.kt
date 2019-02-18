/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 16-Feb-2019.
 */

package com.venomvendor.tigerspike

import androidx.annotation.LayoutRes
import androidx.navigation.fragment.NavHostFragment
import com.venomvendor.tigerspike.common.BaseActivity

/**
 * Only screen acts as interaction layer between user and device
 */
class MainActivity : BaseActivity() {

    @LayoutRes
    // Return current layout
    override val layout = R.layout.activity_main

    override fun initViews() {
        // Get Navigation Graph
        val host = NavHostFragment.create(R.navigation.nav_graph)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, host)
            .setPrimaryNavigationFragment(host)
            .commit()
    }
}
