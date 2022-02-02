import kotlinx.cinterop.*
import libserver.*

val arena = Arena()
fun main(args: Array<String>) {
    memScoped {
        val cFunctionPointerPost = staticCFunction<CPointer<ByteVar>?, CPointer<ByteVar>?> {

            val input = it?.toKString()
            println("Getting json in kotlin: \n $input")
            memScoped {
                "{\"response\":\"hello\"}".cstr.ptr
            }
        }

        val cFunctionPointerGet= staticCFunction<CPointer<ByteVar>?, CPointer<ByteVar>?> {
            val input = it?.toKString()
            println("Getting param in kotlin:  $input")
            memScoped {
                "Hello from $input".cstr.ptr
            }
        }

        val postRouter = cValue<KRouter> {
            handler = cFunctionPointerPost
            method = "post".cstr.getPointer(arena)
            uri = "/body".cstr.getPointer(arena)
        }

        val getRouter = cValue<KRouter> {
            handler = cFunctionPointerGet
            method = "get".cstr.getPointer(arena)
            uri = "/param/:param".cstr.getPointer(arena)
        }

        println("Starting point ...")
        val addr = "127.0.0.1:8087".cstr
        val mem = nativeHeap.allocArray<KRouter>(2)
        postRouter.place(mem[0].ptr)
        getRouter.place(mem[1].ptr)
        start_rust_server(addr, mem, 2)
        println("After server started ...")
    }
}

