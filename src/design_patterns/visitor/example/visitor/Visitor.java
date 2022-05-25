package design_patterns.visitor.example.visitor;

import design_patterns.visitor.example.shapes.Circle;
import design_patterns.visitor.example.shapes.CompoundShape;
import design_patterns.visitor.example.shapes.Dot;
import design_patterns.visitor.example.shapes.Rectangle;

public interface Visitor {
    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);
}
