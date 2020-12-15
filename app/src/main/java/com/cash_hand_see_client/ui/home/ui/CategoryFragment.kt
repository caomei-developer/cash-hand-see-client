package com.cash_hand_see_client.ui.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cash_hand_see_client.R
import com.cash_hand_see_client.base.mvp.ui.BaseMvpFragment
import com.cash_hand_see_client.ui.home.adapter.CategoryAdapter
import com.cash_hand_see_client.ui.home.bean.BooksComics
import com.cash_hand_see_client.ui.home.contract.CategoryContract
import com.cash_hand_see_client.ui.home.presenter.CategoryPresenter

class CategoryFragment : BaseMvpFragment<CategoryContract.View, CategoryPresenter>(),
    CategoryContract.View {

    private var categoryAdapter: CategoryAdapter? = null
    var testData: MutableList<BooksComics> = ArrayList()

    var type: String? = null

    var recyclerView: RecyclerView? = null

    fun newInstance(type: String): CategoryFragment {
        val args = Bundle()
        args.putSerializable("type", type)
        val fragment = CategoryFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPresenter = CategoryPresenter()
        mPresenter!!.CategoryPresenter()
        mPresenter!!.attachView(this)

        recyclerView = view.findViewById(R.id.pull_recycler_view)

        recyclerView!!.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        arguments?.apply {
            type = getString("type", "")
        }

        categoryAdapter = CategoryAdapter()
        recyclerView!!.adapter = categoryAdapter
        mPresenter!!.categoryList(type!!)
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError(code: Int, msg: String) {

    }

    override fun onSuccess(category: MutableList<BooksComics>) {
        run {
            if (category != null && category.size > 0) {
                category.removeAt(0)
                categoryAdapter!!.setData(category)
            }
        }

    }
}