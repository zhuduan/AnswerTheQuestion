package model;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 *         created at 2/7/18
 */
public class ChosenAnswer {
    
    private Integer chooseIndex = -1;
    private String answer = "未知";

    public ChosenAnswer() {
    }

    public ChosenAnswer(Integer chooseIndex, String answer) {
        this.chooseIndex = chooseIndex;
        this.answer = answer;
    }

    public Integer getChooseIndex() {
        return chooseIndex;
    }

    public void setChooseIndex(Integer chooseIndex) {
        this.chooseIndex = chooseIndex;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    @Override
    public String toString(){
        return "answer is " + (chooseIndex+1) + " : " + answer;
    }
}
