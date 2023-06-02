package com.cm.precedencecontrolsystem.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.cm.precedencecontrolsystem.data.DBConfig
import com.cm.precedencecontrolsystem.databinding.ActivityLoginBinding
import com.cm.precedencecontrolsystem.domain.models.Student
import com.cm.precedencecontrolsystem.ui.home.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            DBConfig::class.java,
            DBConfig.DB_NAME
        ).build()



        binding.login?.setOnClickListener {
            if (binding.username?.text.toString() == "admin.test@uem.ac.mz"
                && binding.password?.text.toString() == "admin@123") {
                val user = Student(
                    "Admin Test",
                    "Masculino",
                    "Moçambicana",
                    "+258 840000000",
                    "admin.test@uem.ac.mz",
                    "Tempo Inteiro",
                    2,
                    false
                )

                CoroutineScope(Dispatchers.IO).launch {
                    val student = db.studentDao().getStudentByTail(false)
                    if (student == null) {
                        db.studentDao().create(user)
                    }

                    withContext(Dispatchers.Main) {
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        intent.putExtra("TAIL", false)
                        startActivity(intent)
                        finish()
                    }
                }
            } else if (binding.username?.text.toString() == "test.admin@uem.ac.mz"
                && binding.password?.text.toString() == "test@123") {
                val user = Student(
                    "Test Admin",
                    "Femenino",
                    "Estrangeiro",
                    "+258 840000000",
                    "test.admin@uem.ac.mz",
                    "Tempo Inteiro",
                    3,
                    true
                )
                CoroutineScope(Dispatchers.IO).launch {
                    val student = db.studentDao().getStudentByTail(true)
                    if (student == null) {
                        db.studentDao().create(user)
                    }

                    withContext(Dispatchers.Main) {
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        intent.putExtra("TAIL", true)
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

        /*val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }*/
    }



    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}