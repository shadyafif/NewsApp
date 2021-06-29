package com.example.newsapp.Ui.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Ui.Adapter.FavoriteAdapter
import com.example.newsapp.Data.Model.Article
import com.example.newsapp.R
import com.example.newsapp.Ui.Viewmodels.FavoriteViewModel
import com.example.newsapp.Utlies.Helper
import com.example.newsapp.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment(), FavoriteAdapter.OnNewsItemClick {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavoriteViewModel
    var favoriteAdapter: FavoriteAdapter? = null
    lateinit var layoutmanger: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        InitView()
        favoriteAdapter = FavoriteAdapter(requireContext(), this)

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        viewModel.getNews().observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                binding.ivEmptyList.visibility = View.VISIBLE
                binding.tvEmptyList.visibility = View.VISIBLE
                binding.RecFavorite.visibility=View.INVISIBLE
            } else {
                binding.ivEmptyList.visibility = View.GONE
                binding.tvEmptyList.visibility = View.GONE
                binding.RecFavorite.visibility=View.VISIBLE
                favoriteAdapter!!.setFavNewsList(it)
                binding.RecFavorite.adapter = favoriteAdapter
            }


        })
        return binding.root
    }

    fun InitView() {
        layoutmanger = LinearLayoutManager(activity)
        binding.RecFavorite.layoutManager = layoutmanger
        binding.RecFavorite.setHasFixedSize(true)


    }

    override fun onItemClick(NewsItems: Article, position: Int) {
        var newsDetailsfragment = NewsDetailsFragment()
        val bundle = Bundle()
        bundle.putParcelable("NewsItems", NewsItems)
        newsDetailsfragment.arguments = bundle
        Helper.Replace(
            newsDetailsfragment,
            R.id.frame_layout,
            requireActivity().supportFragmentManager.beginTransaction()
        )
    }


}