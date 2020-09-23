# BookStore

Android Project to fetch books from Google API, that let the user set favorite books and if available, sends the user to the buy page if wanted.

The project is developed in Java, using MVVM architeture, with Retrofit2 + RxJava to fetch the data from API, jDeffered for Async Tasks and Glide to fetch all the images.

As the project is a way to have contact with many diferent ideias, the favorite books aren't saved into the user Google Account with the already existing API endpoints, 
instead are saved on SharedPrefences to be able to have the favorites always reachable in the specific phone.

# TODO

* It's need to create more validations on all data fetched. 
* Instead of using 2 activities with the same layout, it should be all done with one using the same RecyclerView. (It was the first approach but problems with some data being 
duplicated led to 2 activites in the final version).

# Notes about MVVM

* View -> Activity/Fragments, that the only "logic" on them it's regarding the view to show to the user, the information to present it's obtained from the ViewModel.
	The view has a direct reference to the ViewModel, to use the observable on the ViewModel. The view is observating the data on the ViewModel using - LiveData or RxJava
	(p.e. onClick, send what was clicked to view model, the view model does the logic, send back to view(activity) that shows the result to the user)

* ViewModel -> Since the view got a direct reference to the ViewModel, but the ViewModel doesn't know about the View, it's needed to have the data on the ViewModel observable,
	being that the data is observable, when the data changes, all the view that are observing, will be notified to update to the new data.
	 & that makes the view get information about the result of the logic

* Model -> A Repository is used to be the "middle man" between ViewModel & the actual data that is needed. The ViewModel ask's for data to the Repository & the Repository gets
	the data from where it's needed, like from local memory or from the API. 


LiveData vs RxJava:
Using LiveData, it will automatically not send notification to views that are already destroyed. 
Using RxJava, you need to unsubscribe the observable inside the onDestroy of the View.

Using LiveData:
MutableLiveData let's you change the data, LiveData only let's you get & observe.

MUST SEE VIDEO:
https://www.youtube.com/watch?v=_T4zjIEkGOM
