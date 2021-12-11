import java.awt.Frame

fun renderInterval(speed: Int, min: Double, max: Double, firstFrame: Int): Int {

    val width = 1000
    val height = 1000
    val frames = ((max - min) * speed).toInt()
    val lastFrameNumber = firstFrame + frames

    for (i in firstFrame..lastFrameNumber) {
        val f: (z: ComplexNumber) -> ComplexNumber = { z -> z * z +
                    ComplexNumber(-0.4,
                (max - min) *  (i-firstFrame) / frames + min) }
        val img = makeJuliaImage(width, height, f, 100, 2.0)
        saveImgToPng(img, "gif-output-2\\f" + i)
    }

    return lastFrameNumber
}

fun main() {

    var lastFrame = 0

    lastFrame = renderInterval(100, 0.3, 0.57, lastFrame)
    lastFrame = renderInterval(2000, 0.57, 0.72, lastFrame)
    lastFrame = renderInterval(100, 0.72, 1.00, lastFrame)

    println(lastFrame)
}