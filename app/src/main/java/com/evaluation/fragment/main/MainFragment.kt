package com.evaluation.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.evaluation.R
import com.evaluation.adapter.AdapterItemClickListener
import com.evaluation.databinding.MainLayoutBinding
import com.evaluation.utils.autoCleared
import com.evaluation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

/**
 * @author Vladyslav Havrylenko
 * @since 09.03.2020
 */
@AndroidEntryPoint
class MainFragment : Fragment(), AdapterItemClickListener<String> {

    private var binding: MainLayoutBinding by autoCleared()

    private val viewModel: UserViewModel by viewModels()

    private var queryTextChangedJob: Job? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_layout, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.toolBar.setupWithNavController(findNavController())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initLoader()
    }

    private fun initView() {
        binding.toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> {
                    val searchView = it.actionView as SearchView
                    searchView.queryHint = getString(R.string.search)
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(query: String?): Boolean {
                            queryTextChangedJob?.cancel()
                            queryTextChangedJob = GlobalScope.launch(Dispatchers.Main) {
                                delay(500)
                                if (!query.isNullOrEmpty()) viewModel.search(query)
                            }
                            return false
                        }
                    })
                    true
                }
                else -> false
            }
        }
        binding.listView.listener = this
    }

    private fun initLoader() {
        viewModel.items.observe(viewLifecycleOwner, binding.listView.adapter::submitList)
    }

    override fun onClicked(item: String) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(item))
    }
}