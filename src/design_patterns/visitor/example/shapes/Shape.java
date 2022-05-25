package design_patterns.visitor.example.shapes;

import design_patterns.visitor.example.visitor.Visitor;

public interface Shape {
    void move(int x, int y);
    void draw();
    String accept(Visitor visitor);
}
