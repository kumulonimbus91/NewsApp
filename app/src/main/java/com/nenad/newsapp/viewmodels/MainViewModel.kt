package com.nenad.newsapp.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Button
import androidx.lifecycle.*
import com.nenad.newsapp.database.model.apiresponse.Article
import com.nenad.newsapp.database.model.apiresponse.Result
import com.nenad.newsapp.database.network.Response
import com.nenad.newsapp.database.repository.Repository
import com.nenad.newsapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    //remote

     val page:Int = 1

     var category: String = "sports"

     val result = MutableLiveData<Response<Result>>()

     val resultCategory = MutableLiveData<Response<Result>>()

     var tempCategory = MutableLiveData<String>()

    val searchNews: MutableLiveData<Response<Result>> = MutableLiveData()
    var searchNewsPage = 1


    init {
        getHeadlinesInUI(Constants.country)
        getSportNews()
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Response.Loading())
        val response = repository.remote.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }


    private fun handleSearchNewsResponse(response: retrofit2.Response<Result>) : Response<Result> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Response.Success(resultResponse)
            }
        }
        return Response.Error(response.message())
    }






     //viewmodel scope makes sure this coroutines stays alive as long as the viewmodel itself
    fun getHeadlinesInUI(countryCode: String) = viewModelScope.launch {




         result.postValue(Response.Loading())

         val response = repository.getHeadlinesRepo()

         result.postValue(handleHeadlinesResponse(response))


    }




    fun getSportNews() = viewModelScope.launch {
        resultCategory.postValue(Response.Loading())
        val responseSports = repository.getSportsNews()
        resultCategory.postValue(handleHeadlinesResponse(responseSports))
    }
    fun getBusinessNews() = viewModelScope.launch {
        resultCategory.postValue(Response.Loading())
        val responseBusiness = repository.getBusinessNews()
        resultCategory.postValue(handleHeadlinesResponse(responseBusiness))
    }

    fun getEntertainmentNews() = viewModelScope.launch {
        resultCategory.postValue(Response.Loading())
        val responseEntertainment = repository.getEntertainmentNews()
        resultCategory.postValue(handleHeadlinesResponse(responseEntertainment))
    }

    fun getHealthNews() = viewModelScope.launch {
        resultCategory.postValue(Response.Loading())
        val responseHeath = repository.getHealthNews()
        resultCategory.postValue(handleHeadlinesResponse(responseHeath))
    }
    fun getScienceNews() = viewModelScope.launch {
        resultCategory.postValue(Response.Loading())
        val responseScience = repository.getScienceNews()
        resultCategory.postValue(handleHeadlinesResponse(responseScience))
    }

    fun getTechNews() = viewModelScope.launch {
        resultCategory.postValue(Response.Loading())
        val responseTech = repository.getTechNews()
        resultCategory.postValue(handleHeadlinesResponse(responseTech))
    }

    fun buttonClicked(button:Button) {

    }

    private fun handleHeadlinesResponse(resultResponse: retrofit2.Response <Result>) : Response<Result> {
        if (resultResponse.isSuccessful) {
                resultResponse.body().let {
                    return Response.Success(it!!)
                }
            }
            return Response.Error(resultResponse.message())


    }





    // checking internet connection. returns true or false.
    private fun hasInternetConnection(): Boolean {
        val connectivityManager =
            getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    //local

    fun insertArticle(article: Article) {

        viewModelScope.launch {
            repository.local.insertArticle(article)
        }



   }
    fun deleteAllSavedArticles() {
        viewModelScope.launch {
            repository.local.deleteAllArticles()
        }
    }

    val readArt: LiveData<List<Article>> = repository.local.readArticles().asLiveData()


    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            repository.local.deleteArticle(article)
        }
    }




}


