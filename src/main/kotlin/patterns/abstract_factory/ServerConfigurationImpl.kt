package patterns.abstract_factory

data class ServerConfigurationImpl(
    override val properties: List<Property>
): ServerConfiguration
