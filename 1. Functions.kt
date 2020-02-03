package syntax


fun main(args: Array<String>) {


    /*****************************************
    Default Parameter Values and Named Arguments
     ******************************************/
    printMessageWithPrefix("Hello")//Console: [Info] Hello
    printMessageWithPrefix("Hello", "Log")//Console: [Log] Hello
    printMessageWithPrefix(prefix = "NewLog", msg = "Wow")//Console: [NewLog] Wow


    /*****************************************************************************************
    Member functions and extensions with a single parameter can be turned into infix functions.
    Функции-члены и расширения с одним параметром можно превратить в инфиксные функции.
     ******************************************************************************************/

    val person = Person("Oleg")
    person rename "Kolian"
    println(person.name)//Console: Kolian

    infix fun Person.addNickName(nickName: String): String {
        return "${this.name} and nick name is $nickName"
    }
    println(person.addNickName("MEGOLOG"))//Console: Kolian and nick name is MEGOLOG


    //Определяет инфиксную функцию расширения на Int
    infix fun Int.times(str: String): String {
        return str.repeat(this)
    } // or just: infix fun Int.times(str: String) = str.repeat(this)

    //Calls the infix function.
    println(3 times "Bye-")//Console: Bye-Bye-Bye-

    //Creates a Pair by calling the infix function to from the standard library.
    val pair = "Lada" to "Margarita"
    println(pair)//Console: (Lada, Margarita)

    //Here's your own implementation of to creatively called myTo.
    infix fun String.myTo(other: String): Pair<String, String> {
        return Pair(this, other)
    } // or just: infix fun String.myTo(other:String) = Pair(this, other)

    val first = "first"
    val myPair = first.myTo("second")
    println(myPair)//Console: (first, second)


    /**
     * Certain functions can be "upgraded" to operators, allowing their calls with the corresponding operator symbol.
     *(times *; plus +; minus -; contains in / !in; etc
     * )
     */

    operator fun Int.times(str: String) = str.repeat(this)
    println(3 * "Bye-")//Console: Bye-Bye-Bye-

    operator fun String.get(range: IntRange): String{
        return substring(range)
    }

    val str = "Always forgive your enemies; nothing annoys them so much."
    println(str[0..14])//Console: Always forgive
}

//  Default Parameter Values and Named Arguments
fun printMessageWithPrefix(msg: String, prefix: String = "Info") {
    println("[$prefix] $msg")
}

// Infix Functions
class Person(var name: String) {

    //Infix notation also works on members functions (methods).
    infix fun rename(newName: String) {
        this.name = newName;
    }


}

