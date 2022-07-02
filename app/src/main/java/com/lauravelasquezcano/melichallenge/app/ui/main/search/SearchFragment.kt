package com.lauravelasquezcano.melichallenge.app.ui.main.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.navigation.Navigation
import com.lauravelasquezcano.melichallenge.R
import com.lauravelasquezcano.melichallenge.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                if(binding.etSearch.text.toString().isEmpty()) {
                    manageError()
                }else {
                    gotToDetails(binding.etSearch.text.toString())
                }
            }
            true
        }
    }

    private fun manageError() {
        hideKeyBoard()
        binding.etSearch.error = getString(R.string.error_mandatory_field)
    }

    private fun gotToDetails(inputText: String) {
        Navigation.findNavController(requireView()).navigate(SearchFragmentDirections.actionGoResultsFragment(inputText))
    }

    private fun hideKeyBoard() {
        val window = requireActivity().window.decorView
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.windowToken, 0)
    }
}