package abstractFactoryPattern;

interface IThemeFactory {
    
    public IBgClass getBackground();

    public ITextClass getText(String text, String fontFamily, String fontSize, String fontWeight);

    public IButtonClass getButton(String text, String textColor, String buttonBg, String fontFamily, String fontSize, String fontWeight);
    
        
}