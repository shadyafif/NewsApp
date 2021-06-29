package com.example.newsapp.Ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Ui.Adapter.MainAdapter
import com.example.newsapp.Data.Model.Article
import com.example.newsapp.R
import com.example.newsapp.Ui.Viewmodels.HeadLineViewModel
import com.example.newsapp.Utlies.Helper.Replace
import com.example.newsapp.databinding.FragmentHeadlinesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HeadlinesFragment : Fragment(), MainAdapter.OnNewsItemClick {
    private var _binding: FragmentHeadlinesBinding? = null
    private val binding get() = _binding!!
    var headLineAdapter: MainAdapter? = null
    private var viewModel: HeadLineViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHeadlinesBinding.inflate(inflater, container, false)
        initViews()
        viewModel = ViewModelProvider(this).get(HeadLineViewModel::class.java)
        viewModel = HeadLineViewModel()
        GlobalScope.launch(Dispatchers.IO) {
            viewModel!!.articles.collectLatest { pagedData: PagingData<Article> ->
                headLineAdapter!!.submitData(pagedData)

            }
        }
        return binding.root
    }

    fun initViews() {
        headLineAdapter = MainAdapter(this,activity!!)
        binding.recHeadLine.layoutManager = LinearLayoutManager(activity)
        binding.recHeadLine.adapter = headLineAdapter

        LoadError()

    }

    private fun LoadError() {
        headLineAdapter!!.addLoadStateListener {
//            when (it.refresh) {
//                LoadState.Loading -> {
//                    binding.loadingIndicator.show()
//                }
//                is LoadState.Error -> {
//                    binding.loadingIndicator.hide()
//                    requireActivity().toast(R.string.failed)
//                    binding.btnRetry.visibility = View.VISIBLE
//                }
//                else -> {
//                    binding.loadingIndicator.hide()
//                }
//            }
        }
    }

    override fun onItemClick(NewsItems: Article, position: Int) {
        var newsDetailsfragment = NewsDetailsFragment()
        val bundle = Bundle()
        bundle.putParcelable("NewsItems", NewsItems)
        newsDetailsfragment.arguments = bundle
        Replace(
            newsDetailsfragment,
            R.id.frame_layout,
            activity!!.supportFragmentManager.beginTransaction()
        )
    }


}