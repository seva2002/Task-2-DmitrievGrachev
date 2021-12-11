import kotlin.math.*

class ComplexNumber(var re: Double, var im: Double) {

    constructor(): this(0.0,0.0)
    constructor(re: Int,  im: Int): this(re.toDouble(), im.toDouble())

    fun abs():Double {
        return sqrt(re*re+im*im)
    }

    operator fun ComplexNumber.unaryMinus() = ComplexNumber(-re, -im)
    operator fun plus(a: ComplexNumber): ComplexNumber {
        return ComplexNumber(re+a.re, im+a.im)
    }
    operator fun plus(a: Double): ComplexNumber {
        return ComplexNumber(re+a, im)
    }
    operator fun plus(a: Int): ComplexNumber {
        return ComplexNumber(re+a, im)
    }
    operator fun minus(a: ComplexNumber): ComplexNumber {
        return ComplexNumber(re-a.re, im-a.im)
    }
    operator fun minus(a: Double): ComplexNumber {
        return ComplexNumber(re-a, im)
    }
    operator fun minus(a: Int): ComplexNumber {
        return ComplexNumber(re-a, im)
    }
    operator fun times(a: ComplexNumber): ComplexNumber {
        return ComplexNumber(re*a.re - im*a.im, re*a.im + a.re*im)
    }
    operator fun times(a: Double): ComplexNumber {
        return ComplexNumber(re*a, im*a)
    }
    operator fun times(a: Int): ComplexNumber {
        return ComplexNumber(re*a, im*a)
    }
    operator fun div(a: Double): ComplexNumber {
        return ComplexNumber(re/a, im/a)
    }
    operator fun div(a: Int): ComplexNumber {
        return ComplexNumber(re/a, im/a)
    }
    operator fun div(a: ComplexNumber): ComplexNumber {
        return ComplexNumber(re*a.re+im*a.im, a.re*im-a.im*re)/ComplexNumber(re,im).abs()
    }
}

fun print(z: ComplexNumber){
    print(z.re)
    if(z.im >=0)
        print("+ ")
    else
        print("- ")
    print(abs(z.im))
    print(" i")
}

fun println(z: ComplexNumber){
    print(z.re)
    if(z.im >=0)
        print(" + ")
    else
        print(" - ")
    print(abs(z.im))
    println(" i")
}

fun getRandom():Double
{
    return 1.0*(-Int.MAX_VALUE..Int.MAX_VALUE).random()/Int.MAX_VALUE
}