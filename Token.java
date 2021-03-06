public class Token {

    protected Sym symbol;

    public Token(Sym s){
	this.symbol = s;
    }
    public Sym symbol(){
	return symbol;
    }
    public String toString(){
	return "Symbol : "+this.symbol;
    }

}
class ValuedToken extends Token {
    private String value;

    public ValuedToken(Sym s, String v){
	super(s);
	value=v;
    }
    public String getValue(){
	return value;
    }
    public String toString(){
	return "Symbol : "+symbol+" | Value : "+value;
    }
}
