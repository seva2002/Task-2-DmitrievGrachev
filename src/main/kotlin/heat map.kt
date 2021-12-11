// файл main.kt

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.*
import jetbrains.letsPlot.ggsize
import jetbrains.letsPlot.letsPlot
import kotlin.math.*

fun makeHeatMap(xs: List<Any>, ys: List<Any>, zs: List<Any>, name: String)
{
    val data_3 = mapOf<String, Any>("Re" to xs, "Im" to ys, "zs" to zs)
    val fig_3 = letsPlot(data_3) + geomTile() {x = "Re"; y = "Im"; fill = "zs"}
    ggsave(fig_3, name+".png")
}

// Мы сделали через тайлсы, но это выглядит очень птопорно, и не красиво.
//

fun main() {

    val maxNumOfSteps = 200
    val inf = Double.MAX_VALUE
    val step = 0.005
    val weight = 100
    val height = 100

    val xs = ArrayList<Double>()
    val ys = ArrayList<Double>()
    val zs = ArrayList<Double>()
    var zTmp = ComplexNumber(0.0,0.0)
    val z0 = ComplexNumber(-0.4,0.6)

    for (i in -weight..weight) {
        for (j in -height..height) {
            zTmp = ComplexNumber(i*step, j*step)
            xs.add(zTmp.re)
            ys.add(zTmp.im)
            for(s in 1..maxNumOfSteps) {
                if(zTmp.abs()>inf) {
                    zs.add(s*1.0)
                    break
                }
                zTmp = zTmp * zTmp + z0
                if(s==maxNumOfSteps) {
                    zs.add(1.0)
                    break
                }
            }
        }
    }

    makeHeatMap(xs,ys,zs,"julia")

    println("bye!")
}