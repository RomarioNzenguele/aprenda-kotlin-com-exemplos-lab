import kotlin.collections.emptyList
import kotlin.collections.listOf

enum class Nivel {
    PRINCIPIANTE,
    INTERMEDIARIO,
    AVANCADO,
    TODOS_NIVEIS
}

class Usuario

data class ConteudoEducacional(val codigo: Int, val nome: String)

data class Formacao(
        val codigo: Int,
        val nome: String,
        val nivel: Nivel,
        val duracao: Int,
        var conteudos: List<ConteudoEducacional> = emptyList()
) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(vararg usuarios: Usuario) {
        usuarios.forEach { inscritos.add(it) }
    }

    fun gerarFicha() {
        print(
                """
        ============== $codigo - $nome ==============
        Inscritos: ${inscritos.size}
        Duração: $duracao horas com ${conteudos.size} modulos
        Nivel: $nivel
    
        Programa:
        """
        )
        println()
        conteudos.forEach { (_codigo, _nome) -> println("\t\t $_codigo - $_nome.") }
        println()
    }
}

fun main() {

    // ------------------------- Formação
    val curso1 =
            Formacao(1, "Mergulho em Sql", Nivel.TODOS_NIVEIS, 60).apply {
                conteudos =
                        listOf(
                                ConteudoEducacional(1, "Noções básicas e histórial"),
                                ConteudoEducacional(2, "DDL - Data Definition Language"),
                                ConteudoEducacional(3, "DML - Data Manipulation Language"),
                                ConteudoEducacional(4, "DQL - Data Query Language"),
                                ConteudoEducacional(5, "DCL - Data Definition Language"),
                                ConteudoEducacional(6, "Projecto Final")
                        )
            }

    val curso2 =
            Formacao(2, "Desenvolvendo uma Rest API com Spring", Nivel.INTERMEDIARIO, 20).apply {
                conteudos =
                        listOf(
                                ConteudoEducacional(1, "Fundamentos sobre Rest APIs"),
                                ConteudoEducacional(2, "Introdução ao Spring"),
                                ConteudoEducacional(3, "Criando o primeiro projecto"),
                                ConteudoEducacional(4, "Introdução ao Spring Data"),
                                ConteudoEducacional(5, "Realizando o CRUD"),
                                ConteudoEducacional(6, "Implementando boas práticas"),
                                ConteudoEducacional(7, "Testes unitarios"),
                                ConteudoEducacional(8, "Deploy na AWS e Heroku"),
                        )
            }

    // -------------------------- Matriculas
    curso1.matricular(Usuario(), Usuario(), Usuario(), Usuario())
    curso2.matricular(Usuario(), Usuario(), Usuario(), Usuario(), Usuario(), Usuario(), Usuario())

    // -------------------------- Visualizando o resultado

    println("\n\t********************** Fichas Formativas **********************")
    curso1.gerarFicha()
    curso2.gerarFicha()
}
