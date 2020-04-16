# ChuckNorris

4e commit (End_Part.1) : Adapter

Pour cette partie, je transforme le fichier Kotlin "Joke.kt" de l'ancien commit en classe DataSource, qui va contenir un objet,
qui lui contient la fonction createDataSet() contenant la liste de "Joke".

A côté de ça, je crée une data-class "Joke" qui va apporter une structure à mon "objet" de type Joke. 
La classe Joke prend un unique paramètre qui est une String.

Passons à la classe JokeAdapter.
C'est une classe étendue de la classe RecyclerView.Adapter, il faut donc lui implémenter les 3 fonctions qui vont avec.
Je crée d'abord une variable de type liste de Joke, qui est une ArrayList.

La classe interne JokeViewHolder => ce à quoi ma "view" va ressembler dans mon RecyclerView
ELle est étendue de la classe Recycler.ViewHolder, qui est le type de la classe JokeAdapter, et prend
en paramètre une TextView.
Cette classe me sert à créer une fonction bind() qui va attribuer l'unique paramètre String de mes Joke
à mon id "my_joke" du fichier joke_layout.xml

Pour les 3 fonctions de la classe Joke Adapter :

- onCreateViewHolder() => crée une "view" pour chaque élément de ma liste
Cette fonction retourne un JokeViewHolder utilisant un LayoutInflater qui va se référer au layout en question,
ici joke_layout.

- onBindViewHolder()
En référant le ViewHolder, on peut lui relier les données correspondantes (blagues).
- getItemCount() => permet de compter combien de Joke y a-t-il dans ma liste

J'ai décidé de rajouter également une fonction submitList() qui va me servir pour le MainActivity.

Et enfin, dans mon fichier MainActivity, je fais appel à mes 2 nouvelles fonctions créées :
- addDataSet(), qui soumet la liste de données présentes dans ma classe DataSource (la liste de blagues)
à ma variable jokeAdapter de type JokeAdapter.
- initRecyclerView() qui va lier mon jokeAdapter à l'id "my_recycler_view" du fichier activity_main.xml
