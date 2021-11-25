package cc.design.design25visitor;

public interface ComputerPart {
    void accept(ComputerPartVisitor computerPartVisitor);
}
