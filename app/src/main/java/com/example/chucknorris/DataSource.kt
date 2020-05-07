package com.example.chucknorris

//import com.example.chucknorris.Joke

class DataSource {
    companion object{

        fun createDataSet(): List<Joke> {
            val list = listOf(
                Joke(emptyList(),"09/02","aaaaa","1","10/02","jiung" ,"The end of the world is scared to come because Chuck Norris will round house kick is ass."),
                Joke(emptyList(),"09/03","bbbbb","2","10/03","hglklk","All e-mail from Chuck Norris is preceded by this warning: Open at your own risk. Enough said.")
//            )
            )
//            list.add(
//                Joke("Chuck Norris can kill you just by hitting.......... at GTA 2")
//            )
//            list.add(
//                Joke("Chuck norris can kick u with his hands, and punch u with his feet.")
//            )
//            list.add(
//                  Joke("Chuck Norris won the National Pie Eating Contest by devoring 168 hotdogs in less than 10 minutes.")
//            )
//            list.add(
//                  Joke("If you are female, and you are reading this, Chuck Norris just fucked you and erased your memory when you read the word 'fucked'. And again, just then. Fuck, sorry.")
//            )
//              list.add(
//                Joke("Chuck Norris predicted the Mayan Calendar.")
//            )
//            //list.add(
//                Joke("Chuck Norris is a bulldozer with a beard.")
//            )

                return list
        }
    }
}

/*    object JokeList {
        val jokes = listOf<String>(
        "The end of the world is scared to come because Chuck Norris will round house kick is ass.",
        "All e-mail from Chuck Norris is preceded by this warning: Open at your own risk. Enough said.",
        "Chuck Norris can kill you just by hitting.......... at GTA 2",
        "Chuck norris can kick u with his hands, and punch u with his feet.",
        "Chuck Norris won the National Pie Eating Contest by devoring 168 hotdogs in less than 10 minutes.",
        "If you are female, and you are reading this, Chuck Norris just fucked you and erased your memory when you read the word 'fucked'. And again, just then. Fuck, sorry.",
        "Chuck Norris predicted the Mayan Calendar.",
        "Chuck Norris is a bulldozer with a beard."
    )

}*/
//convertir liste de strings en liste de jokes......extensions
//fun List<String>.toJokes():List<Joke>{
//    return this.map{it:String
//        Joke{
//            categories = emptyList(),
//            createAt="",
//            isFavorite = false,
//            updateAt = "",
//            url ="",
//            id="fakeid",
//            value = jokeString
//
//        }}

//}


