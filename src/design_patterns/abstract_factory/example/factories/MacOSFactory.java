package design_patterns.abstract_factory.example.factories;

import design_patterns.abstract_factory.example.buttons.Button;
import design_patterns.abstract_factory.example.buttons.MacOSButton;
import design_patterns.abstract_factory.example.checkboxes.Checkbox;
import design_patterns.abstract_factory.example.checkboxes.MacOSCheckbox;

/**
 * Каждая конкретная фабрика знает и создаёт только продукты своей вариации.
 */
public class MacOSFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
