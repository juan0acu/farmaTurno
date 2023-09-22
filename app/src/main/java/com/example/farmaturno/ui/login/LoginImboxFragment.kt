package com.example.farmaturno.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.example.farmaturno.databinding.FragmentLoginImboxBinding

class LoginImboxFragment : Fragment() {
    private lateinit var binding: FragmentLoginImboxBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginImboxBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        realFormartMailValidator()
        realFormartPassValidator()
        binding.btnIniciarSesion.setOnClickListener {
            mailValidator(binding.txtCorreo.text.toString())
        }
        binding.btnOlvidoContrasena.setOnClickListener {
            //TODO
        }
        binding.btnAtras.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    private fun mailValidator(mail: String): Boolean {
        val context = requireContext().applicationContext

        return if (mail.isEmpty()) {
            binding.txtInputLayout1.helperText = "Campo correo vacío"
            false
        } else if (mail == "juan@gmail.com") {
            passwordValidator(binding.txtPassword.text.toString())
        } else {
            Toast.makeText(context, "Correo invalido", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun passwordValidator(pass: String): Boolean {
        val context = requireContext().applicationContext

        return if (pass.isEmpty()) {
            binding.txtInputLayout1.helperText = "Campo contraseña vacío"
            false
        } else if (pass == "123456") {
            /*val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)*/
            true
        } else {
            Toast.makeText(context, "Contraseña invalida", Toast.LENGTH_SHORT).show()
            false
        }

    }

    private fun realFormartMailValidator() {

        binding.txtCorreo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se necesita implementar
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val correo = s.toString()
                val isFormatValite: Boolean = formatMail(correo)

                if (!isFormatValite) {
                    binding.txtInputLayout1.error = null
                    binding.txtInputLayout1.helperText = null
                } else {
                    binding.txtInputLayout1.error = "Formato de correo inválido"
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // No se necesita implementar
            }
        })
    }

    private fun realFormartPassValidator() {

        binding.txtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se necesita implementar
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val correo = s.toString()
                val isFormatValite: Boolean = formatPass(correo)

                if (isFormatValite) {
                    binding.txtInputLayout2.error = null
                    binding.txtInputLayout2.helperText = null
                    binding.btnIniciarSesion.isEnabled = true
                } else {
                    binding.txtInputLayout2.error = "Campo password vacío"
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // No se necesita implementar
            }
        })
    }

    private fun formatMail(mail: String): Boolean {
        return !PatternsCompat.EMAIL_ADDRESS.matcher(mail).matches()
    }

    private fun formatPass(pass: String): Boolean {
        return !pass.isEmpty()
    }

}