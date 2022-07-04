package com.lauravelasquezcano.melichallenge.app.ui.main.details

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.lauravelasquezcano.melichallenge.R
import com.lauravelasquezcano.melichallenge.app.database.DbItem
import com.lauravelasquezcano.melichallenge.app.ui.utils.ProgressDialog
import com.lauravelasquezcano.melichallenge.data.model.GetItemState
import com.lauravelasquezcano.melichallenge.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var progressDialog: ProgressDialog

    @Inject
    lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initConfiguration()
        initObserver()
        detailViewModel.getItemById(args.itemId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initConfiguration() {
        progressDialog = ProgressDialog(requireContext())
    }

    private fun initObserver() {
        detailViewModel.getItemState.observe(viewLifecycleOwner) { getItemState ->
            when (getItemState) {
                GetItemState.Loading -> {
                    progressDialog.showProgress()
                }
                is GetItemState.Success -> {
                    progressDialog.hideProgress()
                    setData(getItemState.item)
                }
                GetItemState.Failure -> {
                    progressDialog.hideProgress()
                    showMessageDialog(getString(R.string.go_to_details_error))
                }
            }
        }
    }

    private fun setData(item: DbItem) {
        with(binding) {
            if (item.title.isEmpty()) {
                tvDetailsItemTitle.visibility =
                    View.GONE
            } else {
                tvDetailsItemTitle.text = item.title
            }
            if (item.price.toString().isEmpty()) {
                tvDetailsItemPrice.visibility =
                    View.GONE
            } else {
                tvDetailsItemPrice.text =
                    item.price.toString() + item.currency_id
            }
            if (item.condition.isEmpty()) {
                tvDetailsItemCondition.visibility =
                    View.GONE
            } else {
                tvDetailsItemCondition.text = item.condition
            }
            if (item.city_name.isEmpty()) {
                tvDetailsItemLocation.visibility =
                    View.GONE
            } else {
                tvDetailsItemLocation.text = item.city_name
            }
            if (item.sold_quantity.toString().isEmpty()) {
                tvDetailsSoldQuantity.visibility = View.GONE
            } else {
                String.format(
                    getString(R.string.item_sold_quantity),
                    item.sold_quantity.toString()
                ).also { tvDetailsSoldQuantity.text = it }
            }
            if (item.available_quantity.toString().isEmpty()) {
                tvDetailsAvailableQuantity.visibility = View.GONE
            } else {
                tvDetailsAvailableQuantity.text = String.format(
                    getString(R.string.item_available_quantity),
                    item.available_quantity
                )
            }

            setFreeShipping(item.free_shipping)

            setStorePickUp(item.store_pick_up)

            Glide.with(requireContext())
                .load(item.thumbnail)
                .override(200, 200)
                .placeholder(R.drawable.ic_image_holder)
                .error(R.drawable.ic_broken_image)
                .into(ivDetailsItemImage)
        }
    }

    private fun setFreeShipping(freeShipping: Boolean) {
        with(binding) {
            if (freeShipping)
                ivDetailsFreeShipping.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_check
                    )
                )
            else
                ivDetailsFreeShipping.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_cancel
                    )
                )
        }
    }

    private fun setStorePickUp(storePickUp: Boolean) {
        with(binding) {
            if (storePickUp)
                ivDetailsStorePickUp.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_check
                    )
                )
            else
                ivDetailsStorePickUp.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_cancel
                    )
                )
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
}