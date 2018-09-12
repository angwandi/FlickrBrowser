package app.a2ms.flickrbrowser

import android.util.Log
import java.io.IOException
import java.io.ObjectStreamException
import java.io.Serializable

class Photo(var title: String, var author: String, var authorId: String, var link: String, var tags: String, var image: String)
    : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return "Photo(title='$title', author='$author', authorId='$authorId', link='$link', tags='$tags', image='$image')"
    }

    @Throws(IOException::class)
    private fun writeObject(out: java.io.ObjectOutputStream) {
        Log.d("photo", "writeObject called")
        out.writeUTF(title)
        out.writeUTF(author)
        out.writeUTF(authorId)
        out.writeUTF(link)
        out.writeUTF(tags)
        out.writeUTF(image)
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(inputStream: java.io.ObjectInputStream) {
        Log.d("photo", "readObject called")
        title = inputStream.readUTF()
        author = inputStream.readUTF()
        authorId = inputStream.readUTF()
        link = inputStream.readUTF()
        tags = inputStream.readUTF()
        image = inputStream.readUTF()
    }

    @Throws(ObjectStreamException::class)
    private fun readObjectNoData() {
        Log.d("Photo", "readObjectNoData called")
    }
}
