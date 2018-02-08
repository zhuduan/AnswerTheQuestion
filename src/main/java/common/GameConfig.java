package common;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 *         created at 1/29/18
 */
public abstract class GameConfig {
    
    // image related 
    private int img_left_x=0;
    private int img_left_y=0;
    private int img_width=100;
    private int img_height=100;
    private String img_suffix = "png"; 
    
    // Q&A related
    private int qa_txt_max_per_line = 15;
    private int qa_answer_max_num = 3;
    private String qa_char_code = "UTF-8";
    
    // performance related
    private int chosen_answer_timeout = 3000;   //unit: ms

    // display related
    private boolean show_browser = false;

    public int getImg_left_x() {
        return img_left_x;
    }

    public void setImg_left_x(int img_left_x) {
        this.img_left_x = img_left_x;
    }

    public int getImg_left_y() {
        return img_left_y;
    }

    public void setImg_left_y(int img_left_y) {
        this.img_left_y = img_left_y;
    }

    public int getImg_width() {
        return img_width;
    }

    public void setImg_width(int img_width) {
        this.img_width = img_width;
    }

    public int getImg_height() {
        return img_height;
    }

    public void setImg_height(int img_height) {
        this.img_height = img_height;
    }

    public int getQa_txt_max_per_line() {
        return qa_txt_max_per_line;
    }

    public void setQa_txt_max_per_line(int qa_txt_max_per_line) {
        this.qa_txt_max_per_line = qa_txt_max_per_line;
    }

    public int getQa_answer_max_num() {
        return qa_answer_max_num;
    }

    public void setQa_answer_max_num(int qa_answer_max_num) {
        this.qa_answer_max_num = qa_answer_max_num;
    }

    public String getImg_suffix() {
        return img_suffix;
    }

    public void setImg_suffix(String img_suffix) {
        this.img_suffix = img_suffix;
    }

    public boolean isShow_browser() {
        return show_browser;
    }

    public void setShow_browser(boolean show_browser) {
        this.show_browser = show_browser;
    }

    public String getQa_char_code() {
        return qa_char_code;
    }

    public void setQa_char_code(String qa_char_code) {
        this.qa_char_code = qa_char_code;
    }

    public int getChosen_answer_timeout() {
        return chosen_answer_timeout;
    }

    public void setChosen_answer_timeout(int chosen_answer_timeout) {
        this.chosen_answer_timeout = chosen_answer_timeout;
    }
}
