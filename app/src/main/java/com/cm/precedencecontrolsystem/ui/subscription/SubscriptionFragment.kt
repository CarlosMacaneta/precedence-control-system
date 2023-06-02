package com.cm.precedencecontrolsystem.ui.subscription

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.room.Room
import com.cm.precedencecontrolsystem.R
import com.cm.precedencecontrolsystem.data.DBConfig
import com.cm.precedencecontrolsystem.databinding.FragmentSubscriptionBinding
import com.cm.precedencecontrolsystem.domain.models.Subject
import com.cm.precedencecontrolsystem.domain.models.Subscription
import com.cm.precedencecontrolsystem.ui.utils.UIProps.buildDialog
import com.cm.precedencecontrolsystem.ui.utils.UIProps.isViewEnabled
import com.cm.precedencecontrolsystem.ui.utils.UIProps.setMargin
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class SubscriptionFragment: DialogFragment() {

    private lateinit var binding: FragmentSubscriptionBinding

    private var hasTail = false
    private var totalCredits = 0

    private var userId = -1
    private var canSubmit = false

    private val subjects = mutableListOf(
        Subject( "Matematica basica", 6),
        Subject("Fundamentos de Geometria", 6),
        Subject( "Metodos de estudos", 6),
        Subject( "Tics", 6),
        Subject( "Estatistica basica", 6),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubscriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateOut()

        binding.toolbar.setNavigationOnClickListener { dismiss() }

        val db = Room.databaseBuilder(
            requireContext(),
            DBConfig::class.java,
            DBConfig.DB_NAME
        ).build()

        binding.fab.setOnClickListener {
            if (!hasTail || canSubmit) {
                buildDialog(requireContext(), R.layout.dialog_subscription_info).apply {
                    val close = findViewById<MaterialButton>(R.id.close)

                    val date = findViewById<TextView>(R.id.date)
                    val entity = findViewById<TextView>(R.id.entity)
                    val reference = findViewById<TextView>(R.id.reference)
                    val value = findViewById<TextView>(R.id.amount)
                    val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                    date.text = df.format(Date(System.currentTimeMillis()))
                    entity.text = (7700..7704).random().toString()
                    reference.text = (10000000000..99999999999).random().toString()
                    value.text = "3000"

                    val subscription = Subscription(
                        userId,
                        1,
                        entity.text.toString().toInt(),
                        reference.text.toString().toLong(),
                        3000.00,
                        date.text.toString()
                    )
                    CoroutineScope(Dispatchers.IO).launch {
                        db.subscriptionDao().create(subscription)
                    }

                    close.setOnClickListener {
                        this.dismiss()
                        this@SubscriptionFragment.dismiss()
                    }
                    show()
                }
                canSubmit = false
            } else {
                canSubmit = true
                buildDialog(requireContext(), R.layout.dialog_subscription_info_den).apply {
                    val close = findViewById<MaterialButton>(R.id.close)
                    close.setOnClickListener { this.dismiss() }
                    show()
                }
            }
        }

        setStudentData(db)
        academicYearChildren()
        initAcademicYears()
    }

    private fun setStudentData(db: DBConfig) {

        val isBeast = requireActivity().intent.getBooleanExtra("TAIL", false)

        CoroutineScope(Dispatchers.IO).launch {
            val student = db.studentDao().getStudentByTail(isBeast)
            withContext(Dispatchers.Main) {
                student?.id?.let { userId = it }
                binding.studentId.text = student?.id.toString()
                binding.fullName.text = student?.name
                binding.phoneNumber.text = student?.phoneNumber
                binding.email.text = student?.email

                binding.laboral.isChecked = true
                student?.tail?.let { hasTail = it }

                if (student?.gender.equals("female", true))
                    binding.rbFemale.isChecked = true
                else
                    binding.rbMale.isChecked = true

                if (student?.nationality.equals("moçambicana", true))
                    binding.rbMozambique.isChecked = true
                else binding.foreing.isChecked = true

                if (student?.studentType.equals("tempo inteiro", true))
                    binding.rbFullTime.isChecked = true
                else binding.rbPartTime.isChecked = true
            }
        }
    }

    private fun academicYearChildren() {
        binding.levelI.setOnClickListener {
            if (binding.levelI.isChecked)
                binding.yearI.visibility = View.VISIBLE
            else binding.yearI.visibility = View.GONE

            hideNonConsecutiveAcademicYear()
        }

        binding.levelIi.setOnClickListener {
            if (binding.levelIi.isChecked)
                binding.yearIi.visibility = View.VISIBLE
            else binding.yearIi.visibility = View.GONE

            hideNonConsecutiveAcademicYear()
        }

        binding.levelIii.setOnClickListener {
            if (binding.levelIii.isChecked)
                binding.yearIii.visibility = View.VISIBLE
            else binding.yearIii.visibility = View.GONE

            hideNonConsecutiveAcademicYear()
        }

        binding.levelIv.setOnClickListener {
            if (binding.levelIv.isChecked)
                binding.yearIv.visibility = View.VISIBLE
            else binding.yearIv.visibility = View.GONE

            hideNonConsecutiveAcademicYear()
        }
    }

    private fun hideNonConsecutiveAcademicYear() {
        if (binding.levelI.isChecked ||
            (binding.levelI.isChecked && binding.levelIi.isChecked)) {
            binding.levelIii.isEnabled = false
            binding.levelIv.isEnabled = false
        } else if (binding.levelIi.isChecked ||
            (binding.levelIi.isChecked && binding.levelIii.isChecked)) {
            binding.levelI.isEnabled = false
            binding.levelIv.isEnabled = false
        } else if (binding.levelIii.isChecked || binding.levelIv.isChecked ||
            (binding.levelIii.isChecked && binding.levelIv.isChecked)) {
            binding.levelI.isEnabled = false
            binding.levelIi.isEnabled = false
        } else {
            binding.levelI.isEnabled = true
            binding.levelIi.isEnabled = true
            binding.levelIii.isEnabled = true
            binding.levelIv.isEnabled = true
        }
    }

    private fun initAcademicYears() {
        subjects.forEach {
            val checkBox = CheckBox(requireContext()).apply {
                layoutParams = setMargin(10, 10, 10, 10)
                setOnClickListener {
                    if (isChecked) totalCredits += 6
                    else totalCredits -= 6

                    if (totalCredits >= 30) {
                        binding.subjects.children.forEach { view ->
                            val cb = view as CheckBox
                            if (!cb.isChecked) cb.isEnabled = false
                        }

                        isViewEnabled(
                            false,
                            binding.subjectsIi.children,
                            binding.subjectsIii.children,
                            binding.subjectsIv.children
                        )
                    } else {
                        binding.subjects.children.forEach { view ->
                            val cb = view as CheckBox
                            if (!cb.isChecked) cb.isEnabled = true
                        }
                        isViewEnabled(
                            true,
                            binding.subjectsIi.children,
                            binding.subjectsIii.children,
                            binding.subjectsIv.children
                        )
                    }
                }
            }
            checkBox.text = it.name
            binding.subjects.addView(checkBox)
        }

        subjects.forEach {
            val checkBox = CheckBox(requireContext()).apply {
                layoutParams = setMargin(10, 10, 10, 10)
                setOnClickListener {
                    if (isChecked) totalCredits += 6
                    else totalCredits -= 6

                    if (totalCredits >= 30) {
                        binding.subjectsIi.children.forEach { view ->
                            val cb = view as CheckBox
                            if (!cb.isChecked) cb.isEnabled = false
                        }
                        isViewEnabled(
                            false,
                            binding.subjects.children,
                            binding.subjectsIii.children,
                            binding.subjectsIv.children
                        )
                    } else {
                        binding.subjectsIi.children.forEach { view ->
                            val cb = view as CheckBox
                            if (!cb.isChecked) cb.isEnabled = true
                        }
                        isViewEnabled(
                            true,
                            binding.subjects.children,
                            binding.subjectsIii.children,
                            binding.subjectsIv.children
                        )
                    }
                }
            }
            checkBox.text = it.name
            binding.subjectsIi.addView(checkBox)
        }

        subjects.forEach { subject ->
            val checkBox = CheckBox(requireContext()).apply {
                layoutParams = setMargin(10, 10, 10, 10)

                setOnClickListener {
                    if (isChecked) totalCredits += subject.credits
                    else totalCredits -= subject.credits

                    if (totalCredits >= 30) {
                        binding.subjectsIii.children.forEach { view ->
                            val cb = view as CheckBox
                            if (!cb.isChecked) cb.isEnabled = false
                        }
                        isViewEnabled(
                            false,
                            binding.subjectsIi.children,
                            binding.subjects.children,
                            binding.subjectsIv.children
                        )
                    } else {
                        binding.subjectsIii.children.forEach { view ->
                            val cb = view as CheckBox
                            if (!cb.isChecked) cb.isEnabled = true
                        }
                        isViewEnabled(
                            true,
                            binding.subjectsIi.children,
                            binding.subjects.children,
                            binding.subjectsIv.children
                        )
                    }
                }
            }
            checkBox.text = subject.name
            binding.subjectsIii.addView(checkBox)
        }

        subjects.forEach {
            val checkBox = CheckBox(requireContext()).apply {
                layoutParams = setMargin(10, 10, 10, 10)

                setOnClickListener {
                    if (isChecked) totalCredits += 6
                    else totalCredits -= 6

                    if (totalCredits >= 30) {
                        binding.subjectsIv.children.forEach { view ->
                            val cb = view as CheckBox
                            if (!cb.isChecked) cb.isEnabled = false
                        }
                        isViewEnabled(
                            false,
                            binding.subjectsIi.children,
                            binding.subjectsIii.children,
                            binding.subjects.children
                        )
                    } else {
                        binding.subjectsIv.children.forEach { view ->
                            val cb = view as CheckBox
                            if (!cb.isChecked) cb.isEnabled = true
                        }
                        isViewEnabled(
                            true,
                            binding.subjectsIi.children,
                            binding.subjectsIii.children,
                            binding.subjects.children
                        )
                    }
                }
            }
            checkBox.text = it.name
            binding.subjectsIv.addView(checkBox)
        }
    }

    private fun navigateOut() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    this@SubscriptionFragment.dismiss()
                }
            })
    }
}