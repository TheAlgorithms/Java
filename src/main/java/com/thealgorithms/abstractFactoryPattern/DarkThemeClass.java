package abstractFactoryPattern;

public class DarkThemeClass implements IThemeFactory{

    @Override
    public IBgClass getBackground() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBackground'");
    }

    @Override
    public ITextClass getText(String text, String fontFamily, String fontSize, String fontWeight) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getText'");
    }

    @Override
    public IButtonClass getButton(String text, String textColor, String buttonBg, String fontFamily, String fontSize,
            String fontWeight) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getButton'");
    }
    
}
