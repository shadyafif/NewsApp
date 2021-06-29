package com.example.newsapp.Ui.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapp.Data.Model.Article
import com.example.newsapp.Data.Room.ArticleDatabase
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsDetailsBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class NewsDetailsFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var artical: Article
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        InitViews()
        return binding.root
    }

    fun InitViews() {
        val bundle = this.arguments
        if (bundle != null) {
            artical = bundle.getParcelable("NewsItems")!!
            binding.tvTitle.text = artical.title
            if (artical.urlToImage == null) {
             binding.imageView.setImageResource(R.drawable.nophoto)
            } else {
                Picasso.get()
                    .load(artical.urlToImage)
                    .placeholder(R.drawable.square)
                    .into(binding.imageView)
            }

            if (artical.source!!.name == resources.getText(R.string.business)) {
                binding.tvCategory.text = resources.getText(R.string.business)
            } else {
                binding.tvCategory.text = resources.getText(R.string.headlines)
            }
            binding.tvDescription.text = artical.description
            binding.tvContent.text = artical.content
            binding.tvSource.text = artical.source!!.name
            binding.ivReadMore.setOnClickListener(this)
            binding.ivShare.setOnClickListener(this)
            binding.chkFavorite.setOnClickListener(this)
        }

        GlobalScope.launch(Dispatchers.IO)
        {

            val articel = ArticleDatabase(requireActivity()).getArticleDao().fetchCart(artical.title)

            if (articel != null) {
                binding.chkFavorite.isChecked = true
            }
        }
        binding.chkFavorite.setOnClickListener(View.OnClickListener { view ->
            if (binding.chkFavorite.isChecked()) {
                GlobalScope.launch(Dispatchers.IO)
                {
                    ArticleDatabase(requireContext()).getArticleDao().addArticle(artical)
                    val Message: String = context?.getString(R.string.checkChecked)!!
                    Snackbar.make(view, Message, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                    binding.chkFavorite.isChecked = true
                }

            } else {

                GlobalScope.launch(Dispatchers.IO)
                {
                    ArticleDatabase(requireContext()).getArticleDao().deleteArticle(artical)
                    val message: String = context?.getString(R.string.checkNotChecked)!!
                    Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                    binding.chkFavorite.isChecked = false
                }

            }
        })
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_read_more) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(artical.url)
            startActivity(intent)
        }
        else if(v?.id == R.id.iv_share)
        {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post")
            share.putExtra(Intent.EXTRA_TEXT, artical.url)
            startActivity(Intent.createChooser(share, "Share link!"))
        }
    }
}