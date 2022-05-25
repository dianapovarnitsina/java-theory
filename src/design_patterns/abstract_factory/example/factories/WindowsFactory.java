package design_patterns.abstract_factory.example.factories;

import design_patterns.abstract_factory.example.buttons.Button;
import design_patterns.abstract_factory.example.buttons.WindowsButton;
import design_patterns.abstract_factory.example.checkboxes.Checkbox;
import design_patterns.abstract_factory.example.checkboxes.WindowsCheckbox;

/**
 * Каждая конкретная фабрика знает и создаёт только продукты своей вариации.
 */
public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
