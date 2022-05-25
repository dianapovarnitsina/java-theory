package design_patterns.abstract_factory.example.factories;

import design_patterns.abstract_factory.example.buttons.Button;
import design_patterns.abstract_factory.example.checkboxes.Checkbox;

/**
 * Абстрактная фабрика знает обо всех (абстрактных) типах продуктов.
 */
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
