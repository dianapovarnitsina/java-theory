package design_patterns.factory_method.example.buttons;

/**
 * Общий интерфейс для всех продуктов.
 */
public interface Button {
    void render();
    void onClick();
}
