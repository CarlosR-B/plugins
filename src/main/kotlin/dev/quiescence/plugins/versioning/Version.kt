package dev.quiescence.plugins.versioning

data class Version(val major: Int, val minor: Int, val patch: Int? = null, val label: String? = null) {
    fun value() = toString()
    override fun toString() =
        "$major.$minor${if (patch != null) ".$patch" else ""}${if (label != null) "-$label" else ""}"

    fun incrementPatch(): Version = Version(this.major, this.minor, this.patch?.plus(1) ?: 0, this.label)
    fun incrementMinor(): Version = Version(this.major, minor + 1, if (this.patch == null) null else 0, this.label)
    fun incrementMajor(): Version = Version(this.major + 1, 0, if (this.patch == null) null else 0, this.label)
}

fun String.toVersion(): Version {
    val numbers = Regex("[0-9]+")
        .findAll(this)
        .map(MatchResult::value)
        .toList()
        .plus("")

    val label = this.substringAfterLast(delimiter = "-", missingDelimiterValue = "")

    return Version(
        numbers[0].toInt(),
        numbers[1].toInt(),
        numbers[2].toIntOrNull(),
        if (label.isBlank()) null else label
    )
}
