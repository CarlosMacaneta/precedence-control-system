package com.cm.precedencecontrolsystem.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.cm.precedencecontrolsystem.R
import com.cm.precedencecontrolsystem.data.DBConfig
import com.cm.precedencecontrolsystem.databinding.FragmentMainBinding
import com.cm.precedencecontrolsystem.domain.models.Subscription
import com.cm.precedencecontrolsystem.ui.home.adapter.PaymentRecyclerViewAdapter
import com.cm.precedencecontrolsystem.ui.home.viewModel.HomeViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class PaymentsFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: HomeViewModel by activityViewModels()

    @Suppress("DEPRECATION")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Room.databaseBuilder(
            requireContext(),
            DBConfig::class.java,
            DBConfig.DB_NAME
        ).build()

        initRecyclerView(db)
    }

    private fun initRecyclerView(db: DBConfig) {
        binding.subscriptionsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.subscriptionsRv.setHasFixedSize(true)
        val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        val args = arguments?.getInt("pos")
        val list = mutableListOf(
            Subscription(
                1,
                1,
                7004,
                12794468723,
                2100.00,
                df.format(Date(System.currentTimeMillis()))
            ),
            Subscription(
                2,
                2,
                7004,
                12794468723,
                2100.00,
                df.format(Date(System.currentTimeMillis()))
            ),
            Subscription(
                3,
                3,
                7004,
                12794468723,
                2100.00,
                df.format(Date(System.currentTimeMillis()))
            )
        )

        CoroutineScope(Dispatchers.IO).launch {
            val students = db.subscriptionDao().getAllSubscriptions()
            withContext(Dispatchers.Main) {
                binding.subscriptionsRv.adapter = PaymentRecyclerViewAdapter(
                    if (args == 0) list else students
                ).apply {
                    notifyDataSetChanged()
                }
            }
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith(
            "inflater.inflate(R.menu.top_app_bar, menu)",
            "com.cm.scpuem.R"
        )
    )
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                true
            }
            R.id.calendar -> {
                val dateRangePicker =
                    MaterialDatePicker.Builder.dateRangePicker()
                        .setTitleText("Select dates")
                        .build()

                dateRangePicker.show(childFragmentManager, "DATE_RANGE")
                true
            }
            else -> true
        }
    }
}