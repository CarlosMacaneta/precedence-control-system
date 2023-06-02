package com.cm.precedencecontrolsystem.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cm.precedencecontrolsystem.R

val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

class SectionsPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = TAB_TITLES.size

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> {
                val paymentsFragment = PaymentsFragment()
                val args = Bundle()
                args.putInt("pos", 0)

                paymentsFragment.arguments = args

                paymentsFragment
            }
            1 -> {
                val paymentsFragment = PaymentsFragment()
                val args = Bundle()
                args.putInt("pos", 1)

                paymentsFragment.arguments = args

                paymentsFragment
            }
            else -> { throw IllegalArgumentException("Invalid position") }
        }
}