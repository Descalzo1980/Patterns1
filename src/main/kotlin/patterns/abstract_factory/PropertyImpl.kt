package patterns.abstract_factory

data class PropertyImpl(
    override val name: String,
    override val value: Any
): Property


data class IntProperty(
    override val name: String,
    override val value: Int
) : Property
data class StringProperty(
    override val name: String,
    override val value: String
) : Property
