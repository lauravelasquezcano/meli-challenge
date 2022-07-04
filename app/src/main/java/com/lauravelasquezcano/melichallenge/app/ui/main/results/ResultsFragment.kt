package com.lauravelasquezcano.melichallenge.app.ui.main.results

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.lauravelasquezcano.melichallenge.R
import com.lauravelasquezcano.melichallenge.app.ui.utils.ProgressDialog
import com.lauravelasquezcano.melichallenge.data.model.GetItemsState
import com.lauravelasquezcano.melichallenge.databinding.FragmentResultsBinding
import com.lauravelasquezcano.melichallenge.domain.Item
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    private lateinit var progressDialog: ProgressDialog

    @Inject
    lateinit var resultsViewModel: ResultsViewModel

    private val args: ResultsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initConfiguration()
        initObserver()
        getItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initConfiguration() {
        progressDialog = ProgressDialog(requireContext())
    }

    private fun initObserver() {
        resultsViewModel.getItemsState.observe(viewLifecycleOwner) { getItemState ->
            when (getItemState) {
                GetItemsState.Loading -> {
                    progressDialog.showProgress()
                }
                is GetItemsState.Success -> {
                    progressDialog.hideProgress()
                    handleItemsList(getItemState.items)
                }
                is GetItemsState.Failure -> {
                    showMessageDialog(getItemState.message)
                }
            }
        }
    }

    private fun handleItemsList(itemsList: List<Item>) {
        if (itemsList.isNotEmpty()) {
            showRecyclerView()
        } else {
            showEmptyState()
        }
    }

    private fun showMessageDialog(message: String){
        val messageDialog = AlertDialog.Builder(requireContext())
        messageDialog.setMessage(message)
        messageDialog.setNeutralButton(
            getString(R.string.btn_ok)
        ) { dialog, _ -> dialog.dismiss() }
        messageDialog.show()
    }

    private fun showEmptyState() {
        hideRecyclerView()
        binding.gEmptySearch.visibility = View.VISIBLE
    }

    private fun showRecyclerView() {
        hideEmptyState()
        binding.rvResults.visibility = View.VISIBLE
    }

    private fun hideEmptyState(){
        binding.gEmptySearch.visibility = View.GONE
    }

    private fun hideRecyclerView(){
        binding.rvResults.visibility = View.GONE
    }

    private fun getItems() {
        resultsViewModel.searchItems(args.query)
    }
}