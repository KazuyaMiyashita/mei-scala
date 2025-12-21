package meibasic

import org.scalatest.funsuite.AnyFunSuite

import meibasic.Syntax
import meibasic.Syntax.*

class MeiSyntaxSpec extends AnyFunSuite {

  test("Constructing MEI structure") {
    println("Constructing MEI structure...")

    val meiStructure = mei()(
      meiHead()(
        fileDesc()(
          titleStmt()(
            title()("My Works"),
          ),
        ),
      ),
      music()(
        body()(
          mdiv()(
            score()(
              scoreDef(`meter.count` = "4", `meter.unit` = "4", keysig = "1f")(),
              section()(
                measure(n = "1")(
                  staff(n = "1")(
                    layer(n = "1")(
                      mRest(`xml:id` = "d1e220")(),
                    ),
                  ),
                ),
                measure(n = "1")(
                  staff(n = "1")(
                    layer(n = "1")(
                      rest(`xml:id` = "d1e282", dur = "4")(),
                      Syntax.note(`xml:id` = "d1e290", pname = "g", oct = "3", dur = "4", `stem.dir` = "down")(),
                      Syntax.note(`xml:id` = "d1e304", pname = "g", oct = "3", dur = "4", `stem.dir` = "down")(),
                      Syntax.note(`xml:id` = "d1e318", pname = "b", oct = "3", dur = "4", `stem.dir` = "down")(),
                    ),
                  ),
                ),
              ),
            ),
          ),
        ),
      ),
    )

    println("MEI structure constructed successfully!")
    println(meiStructure)
    println(s"Valid: ${meiStructure.validate()}")
    assert(meiStructure.validate())
  }
}
