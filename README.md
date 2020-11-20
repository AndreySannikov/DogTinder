# DogTinder
Это андроид приложение, в котором пользователю предоставляет пользователю случайные изображения собак с помощью открытого API(https://dog.ceo/dog-api/). Изображения можно просматривать в RecyclerView и сохранять в базу данных(Room). Работа с сетью реализована через Retrofit и OkHttp. Многопоточная работа для сети реализована через RxJava, а для работы с базой данных через coroutines. Для реализации паттерна dependency injection используется Dagger2.  
Приложение в Google Play https://play.google.com/store/apps/details?id=ru.degus.doginder&hl=ru
