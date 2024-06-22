package com.ritikprajapati.recordkeeper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ritikprajapati.recordkeeper.databinding.FragmentRunningBinding

class RunningFragment : Fragment() {

    private lateinit var binding: FragmentRunningBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentRunningBinding.inflate(inflater, container, false)
        return binding.root
        /*val view=inflater.inflate(R.layout.fragment_running, container, false)
        return view; */
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        //Here we will display the saved records

    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    override fun onStart() {
        super.onStart()
    }

    private fun setupClickListeners(){
        binding.container5km.setOnClickListener {launchRunningRecordScreen("5Km") }
        binding.container10km.setOnClickListener {launchRunningRecordScreen("10Km") }
        binding.containerHalfMarathon.setOnClickListener { launchRunningRecordScreen("Half Marathon")}
        binding.containerFullMarathon.setOnClickListener {launchRunningRecordScreen("Marathon") }

    }

    private fun displayRecords() {
        val runningPreferences = requireContext().getSharedPreferences("running", Context.MODE_PRIVATE)

        binding.textView5kmValue.text = runningPreferences.getString("5Km record", null)
        binding.textView5kmDate.text = runningPreferences.getString("5Km date", null)
        binding.textView10kmValue.text = runningPreferences.getString("10Km record", null)
        binding.textView10kmDate.text = runningPreferences.getString("10Km date", null)
        binding.textViewHalfMarathonValue.text = runningPreferences.getString("Half Marathon record", null)
        binding.textViewHalfMarathonDate.text = runningPreferences.getString("Half Marathon date", null)
        binding.textViewFullMarathonValue.text = runningPreferences.getString("Marathon record", null)
        binding.textViewFullMarathonDate.text = runningPreferences.getString("Marathon date", null)
    }

    private fun launchRunningRecordScreen(distance: String) {
        val intent= Intent(context, EditRunningRecordActivity::class.java)
        intent.putExtra("Distance", distance )
        startActivity(intent)
    }
}

//if error in intent->watch lt-139 or if not replace 'this' with 'context' inside bracket