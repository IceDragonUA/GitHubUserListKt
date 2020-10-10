package com.evaluation.details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.evaluation.R
import com.evaluation.adapter.AdapterItemClickListener
import com.evaluation.adapter.viewholder.item.BaseItemView
import com.evaluation.databinding.DetailLayoutBinding
import com.evaluation.utils.autoCleared
import com.evaluation.details.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * @author Vladyslav Havrylenko
 * @since 09.10.2020
 */
@AndroidEntryPoint
class DetailFragment : Fragment(), AdapterItemClickListener<BaseItemView> {

    private var binding: DetailLayoutBinding by autoCleared()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_layout, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromBundle = DetailFragmentArgs.fromBundle(requireArguments())
        initRootView()
        initLoader(fromBundle)
    }

    private fun initRootView() {
        binding.listView.listener = this
    }

    override fun onClicked(item: BaseItemView) {
        Timber.d("Details view item clicked: $item")
    }

    private fun initLoader(fromBundle: DetailFragmentArgs) {
        viewModel.items.observe(viewLifecycleOwner, binding.listView.adapter::submitList)
        viewModel.load(fromBundle.userLogin)
    }
}