package meibasic

trait Element {
  def validate(): Boolean = true
  def children: scala.List[Element]
  def withChildren(newChildren: scala.List[Element]): Element
  def transform(rule: Element => Element): Element = {
    val newChildren = this.children.map(_.transform(rule))
    val newNode = this.withChildren(newChildren)
    rule(newNode)
  }
}

case class Text(value: java.lang.String) extends Element {
  override def children: scala.List[Element] = scala.Nil
  override def withChildren(newChildren: scala.List[Element]): Element = {
    if (newChildren.nonEmpty) throw new IllegalArgumentException("Text node cannot have children")
    this
  }
}
