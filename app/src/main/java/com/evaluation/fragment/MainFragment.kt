package com.evaluation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.evaluation.R
import com.evaluation.adapter.AdapterItemClickListener
import com.evaluation.adapter.viewholders.CardItemHolder
import com.evaluation.adapter.viewholders.NoItemHolder
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
        binding = MainLayoutBinding.inflate(inflater, container, false)
        binding.toolBar.setupWithNavController(this.findNavController())
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

                        override fun onQueryTextChange(word: String?): Boolean {
                            queryTextChangedJob?.cancel()
                            queryTextChangedJob = GlobalScope.launch(Dispatchers.Main) {
                                delay(500)
                                if (!word.isNullOrEmpty()) viewModel.search(word)
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
        viewModel.iterator.observe(viewLifecycleOwner, { source ->
            source?.third?.let {
                when (it) {
                    false -> {
                        source.first?.let { state ->
                            binding.progressSpinner.visibility = if (state) View.VISIBLE else View.GONE
                        }
                    }
                    true -> {
                        source.second?.let { users ->
                            binding.listView.adapter.submitList(users)
                        }
                    }
                }
            }
        })
    }

    override fun onClicked(item: String) {
        findNavController()/*.navigate(
            R.id.action_charactersFragment_to_characterDetailFragment,
            bundleOf("id" to item)
        )*/
    }
}