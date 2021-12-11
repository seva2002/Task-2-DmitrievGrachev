
import java.awt.*
import java.awt.image.BufferedImage
import javax.swing.*
import kotlin.math.*
import java.io.*
import javax.imageio.ImageIO

fun isMSetPoint(c: ComplexNumber, f: (z: ComplexNumber, c:ComplexNumber) -> ComplexNumber, steps: Int): Int {
    var z = ComplexNumber()
    val sin2: (Int) -> Double = { i -> sin(Math.PI/100*i) * sin(Math.PI/100*i) }

    for (i in 0..steps) {
        if (z.abs() > 2) {
            // ЧБ
            //return makeColor(0.0, 0.0, 1.0*i/steps)
            //return makeColor(0.0, 0.0, ln(10.0*i/steps+1.0))
            //return makeColor(0.0,0.0,0.1/(1.0*i/steps))
            //return makeColor(0.0,0.0, sin(10.0*i/steps)*sin(10.0*i/steps))
            //return makeColor(0.0, 0.0, (i.toDouble())/steps.toDouble() + (ln(2.0)/z.abs())/5.0) // Нашли красивую

            // Цветные
            //return 10*i // по сути черно-белое, но цвет благородный, выглядит красиво, поэтом решили оставить
            //return makeColor(5.0*i/steps,1.0,2.0*i/steps)
            //return 10*i+makeColor(5.0*i/steps,1.0,2.0*(1.0*i/steps))
            //return makeColor(2.0*i/steps, 1.0, 1.0)
            return makeColor(sin2(2*i), sin2(i), sin2(5*i)) // Самая хорошая.
        }
        z = f(z, c)
    }
    return makeColor(0.0, 0.0, 0.0)
}

fun makeModelbrotSetImage(width: Int, heigth: Int, f: (z: ComplexNumber, c:ComplexNumber) -> ComplexNumber,
                          steps: Int, range:Double): BufferedImage
{
    val data = Array(width*heigth) {
        val x = 2.0*range * (1.0*(it%width)/width-0.5)
        val y = 2.0*range/width*heigth * (1.0*(it/width)/heigth-0.5) //range/width*heigth
        ComplexNumber(x, y)
    }

    val pixels = data.map{isMSetPoint(it, f, steps)}
    val img = BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB)
    img.setRGB(0, 0, width, heigth, pixels.toIntArray(), 0, width)
    return img
}

fun saveImgToPng(img: BufferedImage, name: String)
{
    ImageIO.write(img, "PNG", File(name + ".PNG"))
}






fun main() {

    // Ширина и высота подобраны так чтобы картинка занимала +- весь экран
    val width = 1500
    val height = 780

    // Функция для множества Мондельброта
    val f: (z: ComplexNumber, c:ComplexNumber) -> ComplexNumber = { z,c -> z * z + c }

    // Мы нашли пример работы с графикой и переделали под свои нужды, глубоко не вникали
    val frame = JFrame("Julia Image")
    val panel = JPanel()
    val imgAsLabel = JLabel()

    // Последние 2 параметра отвечают за максимальное количество итераций и размер обозреваемой области
    // Большинство раскрасок смотрятся хорошо только при 150. При числах больше перестают различаться внешние контуры
    val img = makeModelbrotSetImage(width, height, f, 150, 4.0)
    val scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH)

    imgAsLabel.icon = ImageIcon(scaledImg)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    panel.add(imgAsLabel)
    frame.contentPane.add(panel)
    frame.pack()
    frame.isVisible = true

    saveImgToPng(img, "my-output\\MondelbrotSet")

}