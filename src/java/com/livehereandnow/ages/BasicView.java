package com.livehereandnow.ages;

import com.livehereandnow.ages.card.AgesCard;
import com.livehereandnow.ages.cardrow.Cardrow;
import com.livehereandnow.ages.engine.AgesEngine;
import com.livehereandnow.ages.exception.AgesException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class BasicView {

    AgesEngine engine;
    private String text;
    private String debug;
    private int counter;

    public int getRoundNumber() {
        return engine.getField().getRound().getVal();
    }

    public String getCurrentPlayer() {
        return engine.getField().getCurrentPlayer().getName();
    }

    public void doStart() throws IOException, AgesException {
        engine.doCmd("start");
    }

    public void doNewGame() throws IOException, AgesException {
        engine.doCmd("new-game");
    }

    public void doChangeTurn() throws IOException, AgesException {

        engine.doCmd("c");
    }

    public void doTakeCard(int index) throws IOException, AgesException{
        System.out.println(" DOING... USE SEQ TO TAKE CARD, SEQ="+index);
//        engine.parser("take-card "+index);
//        setDebug(this.getText()+"...");
        
    }
   
    public void doSubmitCommand() throws IOException, AgesException{
        engine.parser(getText());
//        setDebug(this.getText()+"...");
        
    }
    List<Cardrow> listCardRowInTable;

    private String getCardrowUI(AgesCard ac) {
        StringBuilder sb = new StringBuilder();
        String ageStr[] = {"A", "I", "II", "III", ""};
        sb.append("【 ").append(ageStr[ac.getAge()]).append("-");
        sb.append(ac.getId()).append(" ");
//        sb.append(ac.getTag()).append("-");
        sb.append(ac.getName()).append(" 】 ");
        return sb.toString();
    }

    public String getImgBaseDir(){
//        return "2nd2go.org/ages/img/abcd/d";
        return "http://2nd2go.org/ages/img/abcd/d";
//        return "/resources/img/d";
    }
    public String getImgExt(){
        return ".jpg";
    }
    
    public List<AgesCard> getCardRowIdList() {
        return engine.getField().getCardRow();
    }
    public List<AgesCard> getP1Hand() {
        return engine.getField().getP1().get手牌內政牌區();
    }
    public List<AgesCard> getP2Hand() {
        return engine.getField().getP2().get手牌內政牌區();
    }  
    public List<Cardrow> getCardRowInTable() {
        List<AgesCard> cardrow = engine.getField().getCardRow();

        listCardRowInTable = new ArrayList<>();
        Cardrow r1 = new Cardrow();
        Cardrow r2 = new Cardrow();
        Cardrow r3 = new Cardrow();

        if (cardrow.size() > 0) {
            r1.setId(1);
            r1.setF1("0" + getCardrowUI(cardrow.get(0)));
            r1.setF2("1" + getCardrowUI(cardrow.get(1)));
            r1.setF3("2" + getCardrowUI(cardrow.get(2)));
            r1.setF4("3" + getCardrowUI(cardrow.get(3)));
            r1.setF5("4" + getCardrowUI(cardrow.get(4)));

            r2.setId(2);
            r2.setF1("5" + getCardrowUI(cardrow.get(5)));
            r2.setF2("6" + getCardrowUI(cardrow.get(6)));
            r2.setF3("7" + getCardrowUI(cardrow.get(7)));
            r2.setF4("8" + getCardrowUI(cardrow.get(8)));
            r2.setF5(engine.getNOCARD().getName());

            r3.setId(3);
            r3.setF1("9" + getCardrowUI(cardrow.get(9)));
            r3.setF2("10" + getCardrowUI(cardrow.get(10)));
            r3.setF3("11" + getCardrowUI(cardrow.get(11)));
            r3.setF4("12" + getCardrowUI(cardrow.get(12)));
            r3.setF5(engine.getNOCARD().getName());
        } else {
            r1.setId(1);
            r1.setF1(engine.getNOCARD().getName());
            r1.setF2(engine.getNOCARD().getName());
            r1.setF3(engine.getNOCARD().getName());
            r1.setF4(engine.getNOCARD().getName());
            r1.setF5(engine.getNOCARD().getName());

            r2.setId(2);
            r2.setF1(engine.getNOCARD().getName());
            r2.setF2(engine.getNOCARD().getName());
            r2.setF3(engine.getNOCARD().getName());
            r2.setF4(engine.getNOCARD().getName());
            r2.setF5(engine.getNOCARD().getName());

            r3.setId(3);
            r3.setF1(engine.getNOCARD().getName());
            r3.setF2(engine.getNOCARD().getName());
            r3.setF3(engine.getNOCARD().getName());
            r3.setF4(engine.getNOCARD().getName());
            r3.setF5(engine.getNOCARD().getName());

        }

        listCardRowInTable.add(r1);
        listCardRowInTable.add(r2);
        listCardRowInTable.add(r3);

        return listCardRowInTable;
    }

    public AgesEngine getEngine() {
        return engine;
    }

    public BasicView() throws AgesException {
        counter = 0;
        engine = new AgesEngine();
    }

    public String getDebug() {
        return engine.getDebug();
    }

//    public void setDebug(String debug) {
//        this.debug = debug;
//    }

    public String getText() {
        return text;
    }

    public void setText(String text) throws IOException, AgesException {
        this.text = text;
        counter++;
//        engine.doCmd("start");
//        setDebug("counter=" + counter + " " + text);
    }
}
