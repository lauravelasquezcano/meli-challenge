package com.lauravelasquezcano.melichallenge.app.ui.main.results

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.lauravelasquezcano.melichallenge.R
import com.lauravelasquezcano.melichallenge.app.ui.utils.ProgressDialog
import com.lauravelasquezcano.melichallenge.data.model.SearchItemState
import com.lauravelasquezcano.melichallenge.data.model.SaveItemState
import com.lauravelasquezcano.melichallenge.databinding.FragmentResultsBinding
import com.lauravelasquezcano.melichallenge.domain.Item
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ResultsFragment : Fragment(), ResultsAdapter.ResultsInterface {

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    private lateinit var progressDialog: ProgressDialog

    @Inject
    lateinit var resultsViewModel: ResultsViewModel

    private val args: ResultsFragmentArgs by navArgs()

    private var adapter: ResultsAdapter? = null

    private var selectedItemId : String? = null

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
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = ResultsAdapter(this)
        binding.rvResults.layoutManager = LinearLayoutManager(requireContext())
        binding.rvResults.setHasFixedSize(true)
        binding.rvResults.adapter = adapter
    }

    private fun initObserver() {
        resultsViewModel.searchItemState.observe(viewLifecycleOwner) { searchItemState ->
            when (searchItemState) {
                SearchItemState.Loading -> {
                    progressDialog.showProgress()
                }
                is SearchItemState.Success -> {
                    progressDialog.hideProgress()
                    handleItemsList(searchItemState.items)
                }
                is SearchItemState.Failure -> {
                    showMessageDialog(searchItemState.message)
                }
            }
        }
        resultsViewModel.saveItemState.observe(viewLifecycleOwner) { saveItemState ->
            when (saveItemState) {
                SaveItemState.Success -> {
                    goToDetails()
                }
                SaveItemState.Failure -> {
                    showMessageDialog(getString(R.string.go_to_details_error))
                }
            }

        }
    }

    private fun handleItemsList(itemsList: List<Item>) {
        if (itemsList.isNotEmpty()) {
            showRecyclerView()
            adapter?.let {
                it.setData(itemsList)
            }
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

    override fun saveItem(selectedItem: Item) {
        selectedItemId = selectedItem.id
        resultsViewModel.saveItem(selectedItem)
    }

    private fun goToDetails(){
        selectedItemId?.let {
            Navigation.findNavController(requireView()).navigate(ResultsFragmentDirections.actionGoDetailsFragment(it))
        }
    }
}