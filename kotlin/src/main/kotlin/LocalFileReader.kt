import java.io.InputStream

class LocalFileReader: FileReader {
    override fun getFileAsInputStream(filePath: String): InputStream {
        val classLoader = object {}.javaClass
        val stream = classLoader.getResourceAsStream(filePath)
        return stream
    }
}
