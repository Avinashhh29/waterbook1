package com.example.myapplication.myapplication.waterbook1.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myapplication.myapplication.waterbook1.R
import com.example.myapplication.myapplication.waterbook1.commons.Constant.PINCODE_PATTERN
import com.example.myapplication.myapplication.waterbook1.commons.Resource
import com.example.myapplication.myapplication.waterbook1.data.domain.repo.Repository
import com.example.myapplication.myapplication.waterbook1.databinding.FragmentPincodeBinding
import com.google.android.material.textfield.TextInputLayout


class PincodeFragment : Fragment(R.layout.fragment_pincode) {

    private var _binding : FragmentPincodeBinding ?= null
    private val binding get() = _binding!!
    lateinit var pincode : TextInputLayout
    lateinit var pincodeString: String
    var repository : Repository = Repository()
    var viewModel : PincodesViewModel = PincodesViewModel(repository)




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPincodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pincode = binding.pincode
        binding.button2.setOnClickListener {
            if (validatePincode()) {
              viewModel.getAllDetails(pincodeString)
              viewModel.pincodeDetails.observe(viewLifecycleOwner, Observer {
                  when(it) {
                      is Resource.Success -> {
                          it.data?.let { result->
                              result.listIterator().forEach {
                                  binding.details.text = it
                                  binding.details.visibility = View.VISIBLE
                              }
                          }
                      }
                      is Resource.Error -> {
                          it.message?.let { error->
                              Log.e("Pincode","Error occured $error")
                          }
                      }
                      is Resource.Loading -> {
                          binding.details.text = "Loading.."
                          binding.details.visibility = View.VISIBLE
                      }
                  }
              })
            }
        }

    }

    private fun validatePincode(): Boolean {

        pincodeString= pincode.editText?.text.toString()

        if(pincodeString.isEmpty()){
            pincode.error = "Do not Leave the Field empty!"
            return false
        }
        else if (!PINCODE_PATTERN.matcher(pincodeString).matches()){
            pincode.error = "Not a pincode"
            return false
        } else {
            pincode.error = null
            return true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
