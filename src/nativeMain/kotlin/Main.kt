import kotlinx.cinterop.*
import libserver.*

val arena = Arena()
fun main(args: Array<String>) {
    val getURI = "/param/:param".cstr;
    memScoped {
        val cFunctionPointerGet = staticCFunction<CPointer<ByteVar>?, CPointer<ByteVar>?> {
            println("Getting json in kotlin")
            "{\"response\":\"hello\"}".cstr.getPointer(arena)
        }
        get(getURI, cFunctionPointerGet)
        println("Starting point ...")
        val addr = "127.0.0.1:8087".cstr
        start_rust_server(addr)
        println("After server started ...")
    }
}

