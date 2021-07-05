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
import com.example.newsapp.Data.Repository.BusinessRepo
import com.example.newsapp.R
import com.example.newsapp.Ui.Viewmodels.BusinessViewmodel
import com.example.newsapp.Utlies.Helper.Replace
import com.example.newsapp.databinding.FragmentBusinessBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BusinessFragment : Fragment() , MainAdapter.OnNewsItemClick{
    var BusinessAdapter: MainAdapter? = null
    private var _binding: FragmentBusinessBinding? = null
    private val binding get() = _binding!!
    lateinit var layoutmanger: LinearLayoutManager
    private lateinit var viewModel: BusinessViewmodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBusinessBinding.inflate(inflater, container, false)
        initViews()
        viewModel = ViewModelProvider(this).get(BusinessViewmodel::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.articles.collectLatest { pagedData: PagingData<Article> ->
                BusinessAdapter!!.submitData(pagedData)

            }
        }



        return binding.root
    }
    fun initViews() {
        BusinessAdapter = MainAdapter(this,requireActivity())
        binding.RecBusiness.layoutManager = LinearLayoutManager(activity)
        binding.RecBusiness.adapter = BusinessAdapter

    }


    override fun onItemClick(NewsItems: Article, position: Int) {
        var newsDetailsfragment = NewsDetailsFragment()
        val bundle = Bundle()
        bundle.putParcelable("NewsItems", NewsItems)
        newsDetailsfragment.setArguments(bundle)
        Replace(
            newsDetailsfragment,
            R.id.frame_layout,
            requireActivity().supportFragmentManager.beginTransaction()
        )
    }


}