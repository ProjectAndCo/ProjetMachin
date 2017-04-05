import java.util.*;

public class Parser {

    /*
     * DOCUMENT -> CORPS
     * CORPS -> \begindoc SUITE_ELEMENTS \enddoc
     * SUITE_ELEMENTS -> ELEMENT SUITE_ELEMENTS | epsilon
     * ELEMENT -> MOT | \linebreak | \bf{ SUITE_ELEMENTS } | \it{ SUITE_ELEMENTS } | ENUMERATION
     
     * ENUMERATION -> \beginenum SUITE_ITEMS \endenum
     * SUITE_ITEMS -> ITEM SUITE_ITEMS | epsilon
     * ITEM -> \item SUITE_ELEMENTS
     */

    protected LookAhead1 reader;

    public Parser(LookAhead1 r){
	this.reader = r;
    }

    public void nonterm_DOCUMENT() throws Exception {

	this.nonterm_CORPS();
    }

    public void nonterm_CORPS() throws Exception {

	this.reader.eat(Sym.BEGINDOC);
	this.nonterm_SUITE_ELEMENTS();
	this.reader.eat(Sym.ENDDOC);	
    }

    public void nonterm_SUITE_ELEMENTS() throws Exception {

	this.nonterm_ELEMENT();
	this.nonterm_SUITE_ELEMENTS();
    }

    public void nonterm_ELEMENT() throws Exception {

	if(this.reader.check(Sym.MOT)){
	    this.reader.eat(Sym.MOT);
	} else if(this.reader.check(Sym.LINEBREAK)){
	    this.reader.eat(Sym.LINEBREAK);
	} else if(this.reader.check(Sym.BOLD)){
	    this.reader.eat(Sym.BOLD);
	    this.reader.eat(Sym.LBRACKET);
	    this.nonterm_SUITE_ELEMENTS();
	    this.reader.eat(Sym.RBRACKET);
	}  else if(this.reader.check(Sym.ITALIC)){
	    this.reader.eat(Sym.ITALIC);
	    this.reader.eat(Sym.LBRACKET);
	    this.nonterm_SUITE_ELEMENTS();
	    this.reader.eat(Sym.RBRACKET);
	} else
	    this.nonterm_ENUMERATION();
    }

    public void nonterm_ENUMERATION() throws Exception {

	this.reader.eat(Sym.BEGINENUM);
	this.nonterm_SUITE_ITEMS();
	this.reader.eat(Sym.ENDENUM);
    }

    public void nonterm_SUITE_ITEMS() throws Exception {

	this.nonterm_ITEM();
	this.nonterm_SUITE_ITEMS();
    }

    public void nonterm_ITEM() throws Exception {

	this.reader.eat(Sym.ITEM);
	this.nonterm_SUITE_ELEMENTS();
    }
}
