package hu.renes.kotlin.domain.model

data class Candidate(val name: String, val age: Int, val workplaces: List<Workplace>)

data class Workplace(val company: String,
                     val logo: String,
                     val start: Int,
                     val end: Int,
                     val title: String,
                     val website: String)