package com.cm.precedencecontrolsystem.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.cm.precedencecontrolsystem.R
import com.cm.precedencecontrolsystem.databinding.ActivityMainBinding
import com.cm.precedencecontrolsystem.ui.subscription.SubscriptionFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = binding.tabs
        setupWithViewPager(tabs, binding.viewPager)

        binding.fab.setOnClickListener {
            val subscriptionFragment = SubscriptionFragment()

            val fm = supportFragmentManager
            val transaction: FragmentTransaction = fm.beginTransaction()
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction.add(android.R.id.content, subscriptionFragment)
                .addToBackStack(null).commit()
        }
    }

    private fun setupWithViewPager(tab: TabLayout, viewPager2: ViewPager2) {
        TabLayoutMediator(
            tab, viewPager2
        ) { tab1: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> {
                    tab1.icon = getDrawable(R.drawable.ic_confirmed)
                    tab1.text = getString(TAB_TITLES.first())
                }
                1 -> {
                    tab1.icon = getDrawable(R.drawable.ic_pending)
                    tab1.text = getString(TAB_TITLES[1])
                }
            }
        }.attach()
    }
}