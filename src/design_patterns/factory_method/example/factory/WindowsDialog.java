package design_patterns.factory_method.example.factory;

import design_patterns.factory_method.example.buttons.Button;
import design_patterns.factory_method.example.buttons.WindowsButton;

/**
 * Диалог на элементах операционной системы.
 */
public class WindowsDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
